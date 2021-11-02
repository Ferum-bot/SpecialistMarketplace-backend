package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.IAmSpecialistEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.SubmitYourApplicationEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.NotImplementedScreenState

class IAmSpecialistUpdateProcessor(
    private val userService: TelegramUserService
): LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is IAmSpecialistEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent as IAmSpecialistEvent
        val info = bunch.extraInformation

        return when(event) {
            is SubmitYourApplicationEvent -> processSubmitYourApplicationEvent(info)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processSubmitYourApplicationEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = NotImplementedScreenState
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }
}