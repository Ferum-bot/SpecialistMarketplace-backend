package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.ProfessionsInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.IDoNotKnowWhatINeedEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenAboutEachSpecialistScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenLeaveBidScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.AboutEachSpecialistScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.LeaveBidInfoScreenState
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.services.ProfessionService

class IDoNotKnowWhatINeedUpdateProcessor(
    private val professionService: ProfessionService,
    private val userService: TelegramUserService,
): LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is IDoNotKnowWhatINeedEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent as IDoNotKnowWhatINeedEvent
        val info = bunch.extraInformation

        return when(event) {
            is OpenLeaveBidScreenEvent -> processOpenLeaveBidScreen(info)
            is OpenAboutEachSpecialistScreenEvent -> processOpenAboutEachSpecialistScreen(info)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processOpenLeaveBidScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = LeaveBidInfoScreenState
        userService.setNewUserState(newState, info)

        return MessageUpdateResultBunch(newState, info)
    }

    private fun processOpenAboutEachSpecialistScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = AboutEachSpecialistScreenState
        val professions = professionService.getAllAvailableProfessions()
            .map { ProfessionDto.from(it) }
        val newInfo = ProfessionsInfo.from(info, professions)
        userService.setNewUserState(newState, info)

        return MessageUpdateResultBunch(newState, newInfo)
    }
}