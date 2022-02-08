package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.*
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramBotFlowService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.CurrentSpecialistsContactsScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.CurrentSpecialistsScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.NicheFilterScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.ProfessionFilterScreenState
import com.github.ferumbot.specmarket.core.extensions.removeFirstCharIf
import com.github.ferumbot.specmarket.models.dto.NicheDto
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses.APPROVED
import com.github.ferumbot.specmarket.services.NicheService
import com.github.ferumbot.specmarket.services.ProfessionService
import com.github.ferumbot.specmarket.services.SpecialistService

class FilterUpdateProcessor(
    private val professionService: ProfessionService,
    private val specialistService: SpecialistService,
    private val nicheService: NicheService,
    private val userService: TelegramBotFlowService,
): LocalUpdateProcessor {

    companion object {

        private const val SPECIALIST_PER_PAGE = 1
    }

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is FilterEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent as FilterEvent
        val info = bunch.extraInformation

        return when(event) {
            is OpenProfessionFilterScreenEvent ->
                processOpenProfessionFilterEvent(info)
            is ApplyProfessionFilterEvent ->
                processApplyProfessionFilterEvent(info as BaseDataInfo)
            is ApplyNicheFilterEvent ->
                processApplyNicheFilterEvent(info as BaseDataInfo)
            is OpenCurrentSpecialistsScreenEvent ->
                processOpenCurrentSpecialistsEvent(info)
            is OpenAnotherSpecialistsPageScreenEvent ->
                processOpenAnotherSpecialistsPageEvent(info as OpenAnotherPageInfo)
            is GetSpecialistsContactsEvent ->
                processGetSpecialistsContactsEvent(info as GetSpecialistContactsInfo)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processOpenProfessionFilterEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val availableProfessions = professionService.getAllAvailableProfessions()
            .map { ProfessionDto.from(it) }
        val newState = ProfessionFilterScreenState
        val newInfo = ProfessionsInfo.from(info, availableProfessions)
        userService.setNewUserState(newState, info)

        return MessageUpdateResultBunch(newState, newInfo)
    }

    private fun processApplyProfessionFilterEvent(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val professionAlias = info.simpleInput
            .removeFirstCharIf { it.first() == '/' }
        userService.setProfessionToUserFilter(professionAlias, info)

        val availableNiches = nicheService.getAllAvailableNiches()
            .map { NicheDto.from(it) }
        val newState = NicheFilterScreenState
        val newInfo = NichesInfo.from(info, availableNiches)
        userService.setNewUserState(newState, info)

        return MessageUpdateResultBunch(newState, newInfo)
    }

    private fun processApplyNicheFilterEvent(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val nicheAlias = info.simpleInput
            .removeFirstCharIf { it.first() == '/' }
        userService.setNicheToUserFilter(nicheAlias, info)

        return processOpenCurrentSpecialistsEvent(info)
    }

    private fun processOpenCurrentSpecialistsEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val firstPage = 1
        val (professionAlias, nicheAlias) = getProfessionAndNicheFromUserFilter(info)

        val (specialists, specialistsCount) = getApprovedSpecialistsAndCountWith(
            professionAlias, nicheAlias, firstPage
        )

        val newState = CurrentSpecialistsScreenState
        val newInfo = SpecialistsPageInfo.from(
            info, specialists, firstPage, specialistsCount
        )
        userService.setNewUserState(newState, info)

        return MessageUpdateResultBunch(newState, newInfo)
    }

    private fun processOpenAnotherSpecialistsPageEvent(info: OpenAnotherPageInfo): MessageUpdateResultBunch<*> {
        val newPage = info.pageNumber
        val (professionAlias, nicheAlias) = getProfessionAndNicheFromUserFilter(info)

        val (specialists, specialistsCount) = getApprovedSpecialistsAndCountWith(
            professionAlias, nicheAlias, newPage
        )

        val newState = CurrentSpecialistsScreenState
        val newInfo = SpecialistsPageInfo.from(
            info, specialists, newPage, specialistsCount
        )

        return MessageUpdateResultBunch(newState, newInfo)
    }

    private fun processGetSpecialistsContactsEvent(info: GetSpecialistContactsInfo): MessageUpdateResultBunch<*> {
        val specialistId = info.specialistId
        val contacts = specialistService.getSpecialistById(specialistId)?.contactLinks.orEmpty()
        val state = CurrentSpecialistsContactsScreenState
        userService.addSpecialistToUserRequests(info, specialistId)
        userService.setNewUserState(state, info)

        val newInfo = BaseDataInfo.from(info, contacts)

        return MessageUpdateResultBunch(state, newInfo)
    }

    private fun getApprovedSpecialistsAndCountWith(
        professionAlias: String?, nicheAlias: String?, page: Int
    ): Pair<Collection<SpecialistDto>, Int> {
        var specialists: Collection<SpecialistDto> = emptyList()
        var specialistsCount = 0

        professionAlias?.let { profession ->
            if (nicheAlias == null) {
                specialists = specialistService.getSpecialistsByProfessionWithStatus(
                    alias = profession, status = APPROVED, page, SPECIALIST_PER_PAGE
                )
                specialistsCount = specialistService.countSpecialistsByProfessionWithStatus(
                    professionAlias = profession, status = APPROVED
                )
            } else {
                specialists = specialistService.getSpecialistsWithProfessionAndNiche(
                    professionAlias = profession, nicheAlias = nicheAlias, status = APPROVED, page, SPECIALIST_PER_PAGE
                )
                specialistsCount = specialistService.countSpecialistsWithNicheAndProfession(
                    professionAlias = profession, nicheAlias = nicheAlias, status = APPROVED
                )
            }
        } ?: run {
            if (nicheAlias == null) {
                specialists = specialistService.getSpecialistsWithStatus(
                    status = APPROVED, pageNumber = page, SPECIALIST_PER_PAGE
                )
                specialistsCount = specialistService.countSpecialistsWithStatus(
                    status = APPROVED
                )
            } else {
                specialists = specialistService.getSpecialistsByNicheWithStatus(
                    alias = nicheAlias, status = APPROVED, pageNumber = page, SPECIALIST_PER_PAGE
                )
                specialistsCount = specialistService.countSpecialistsByNicheWithStatus(
                    status = APPROVED, nicheAlias = nicheAlias,
                )
            }
        }

        return specialists to specialistsCount
    }

    private fun getProfessionAndNicheFromUserFilter(
        info: BaseUpdateInfo
    ): Pair<String?, String?> {
        val profession = userService.getProfessionFromUserFilter(info)
            ?.alias
        val niche = userService.getNicheFromUserFilter(info)
            ?.alias
        return profession to niche
    }
}
