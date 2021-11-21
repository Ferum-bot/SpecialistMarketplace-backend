package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.*
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.CurrentSpecialistsContactsScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.CurrentSpecialistsScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.FilterScreenState
import com.github.ferumbot.specmarket.core.extensions.removeFirstCharIf
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.services.ProfessionService
import com.github.ferumbot.specmarket.services.SpecialistService

class FilterUpdateProcessor(
    private val professionService: ProfessionService,
    private val specialistService: SpecialistService,
    private val userService: TelegramUserService,
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
            is OpenFilterScreenEvent ->
                processOpenFilterEvent(info)
            is OpenCurrentSpecialistsScreenEvent ->
                processOpenCurrentSpecialistsEvent(info as BaseDataInfo)
            is OpenAnotherSpecialistsPageScreenEvent ->
                processOpenAnotherSpecialistsPageEvent(info as OpenAnotherPageInfo)
            is GetSpecialistsContactsEvent ->
                processGetSpecialistsContactsEvent(info as GetSpecialistContactsInfo)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processOpenFilterEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val availableProfessions = professionService.getAllAvailableProfessions()
            .map { ProfessionDto.from(it) }
        val newState = FilterScreenState
        val newInfo = ProfessionsInfo.from(info, availableProfessions)

        return MessageUpdateResultBunch(newState, newInfo)
    }

    private fun processOpenCurrentSpecialistsEvent(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val firstPage = 1
        val professionAlias = info.simpleInput.removeFirstCharIf { it.first() == '/' }
        val specialists = specialistService.getSpecialistsByProfessionAlias(professionAlias, firstPage, SPECIALIST_PER_PAGE)
        val specialistsCount = specialistService.countSpecialistsByProfessionAlias(professionAlias)
        val newState = CurrentSpecialistsScreenState
        userService.setNewUserState(newState, info)

        val newInfo = SpecialistsPageInfo.from(
            info, specialists, firstPage, specialistsCount, professionAlias
        )

        return MessageUpdateResultBunch(newState, newInfo)
    }

    private fun processOpenAnotherSpecialistsPageEvent(info: OpenAnotherPageInfo): MessageUpdateResultBunch<*> {
        val currentPage = info.pageNumber
        val professionAlias = info.additionalData.orEmpty()
        val specialists = specialistService.getSpecialistsByProfessionAlias(professionAlias, currentPage, SPECIALIST_PER_PAGE)
        val specialistsCount = specialistService.countSpecialistsByProfessionAlias(professionAlias)
        val state = CurrentSpecialistsScreenState

        val newInfo = SpecialistsPageInfo.from(
            info, specialists, currentPage, specialistsCount
        )

        return MessageUpdateResultBunch(state, newInfo)
    }

    private fun processGetSpecialistsContactsEvent(info: GetSpecialistContactsInfo): MessageUpdateResultBunch<*> {
        val specialistId = info.specialistId
        val contacts = specialistService.getSpecialistById(specialistId)?.contactLinks.orEmpty()
        val state = CurrentSpecialistsContactsScreenState
        userService.setNewUserState(state, info)

        val newInfo = BaseDataInfo.from(info, contacts)

        return MessageUpdateResultBunch(state, newInfo)
    }

}
