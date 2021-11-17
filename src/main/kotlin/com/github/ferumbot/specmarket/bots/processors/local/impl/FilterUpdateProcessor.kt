package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.*
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.FilterEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenAnotherSpecialistsPageScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenCurrentSpecialistsScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenFilterScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.CurrentSpecialistsScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.FilterScreenState
import com.github.ferumbot.specmarket.core.extensions.removeFirstCharIf
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.services.ProfessionService
import com.github.ferumbot.specmarket.services.SpecialistService
import org.springframework.data.domain.PageRequest

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
                processOpenCurrentSpecialistsEvent(info as BaseInputInfo)
            is OpenAnotherSpecialistsPageScreenEvent ->
                processOpenAnotherSpecialistsPageEvent(info as OpenAnotherPageInfo)
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

    private fun processOpenCurrentSpecialistsEvent(info: BaseInputInfo): MessageUpdateResultBunch<*> {
        val firstPage = 1
        val professionAlias = info.simpleInput.removeFirstCharIf { it.first() == '/' }
        val specialists = specialistService.getSpecialistByProfessionAlias(professionAlias, firstPage, SPECIALIST_PER_PAGE)
        val specialistsCount = specialistService.countSpecialistsByProfessionAlias(professionAlias)
        val newState = CurrentSpecialistsScreenState
        userService.setNewUserState(newState, info)

        val newInfo = SpecialistsPageInfo.from(
            info, specialists, firstPage, specialistsCount
        )

        return MessageUpdateResultBunch(newState, newInfo)
    }

    private fun processOpenAnotherSpecialistsPageEvent(info: OpenAnotherPageInfo): MessageUpdateResultBunch<*> {
        val currentPage = info.pageNumber
        val professionAlias = info.additionalData.orEmpty()
        val specialists = specialistService.getSpecialistByProfessionAlias(professionAlias, currentPage, SPECIALIST_PER_PAGE)
        val specialistsCount = specialistService.countSpecialistsByProfessionAlias(professionAlias)
        val state = CurrentSpecialistsScreenState

        val newInfo = SpecialistsPageInfo.from(
            info, specialists, currentPage, specialistsCount
        )

        return MessageUpdateResultBunch(state, newInfo)
    }

}
