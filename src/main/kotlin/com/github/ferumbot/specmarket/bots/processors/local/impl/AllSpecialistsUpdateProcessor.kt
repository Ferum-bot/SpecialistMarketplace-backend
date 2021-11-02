package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.AllSpecialistsEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenFilterScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenIDoNotKnowWhoISearchScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.IDoNotKnowWhoISearchScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.NotImplementedScreenState

class AllSpecialistsUpdateProcessor(
    private val userService: TelegramUserService
): LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is AllSpecialistsEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent as AllSpecialistsEvent
        val info = bunch.extraInformation

        return when(event) {
            is OpenFilterScreenEvent -> processOpenFilterScreenEvent(info)
            is OpenIDoNotKnowWhoISearchScreenEvent -> processOpenIDoNotKnowWhoISearchScreenEvent(info)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processOpenFilterScreenEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = NotImplementedScreenState
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processOpenIDoNotKnowWhoISearchScreenEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = IDoNotKnowWhoISearchScreenState
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }
}