package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.IAmCustomerEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenAboutEachSpecialistScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenIDoNotKnowWhatIWantScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.AboutEachSpecialistScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.IDoNotKnowWhatIWantScreenState

class IAmCustomerUpdateProcessor(
    private val userService: TelegramUserService
): LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is IAmCustomerEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent as IAmCustomerEvent
        val info = bunch.extraInformation

        return when(event) {
            is OpenIDoNotKnowWhatIWantScreenEvent -> processOpenIDoNotKnowEvent(info)
            is OpenAboutEachSpecialistScreenEvent -> processOpenAboutEachSpecialistEvent(info)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processOpenIDoNotKnowEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = IDoNotKnowWhatIWantScreenState
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processOpenAboutEachSpecialistEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = AboutEachSpecialistScreenState
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }
}