package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.ContactWithUsScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.IAmCustomerInfoScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.IAmSpecialistInfoScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.NotImplementedScreenState

class StartUpdateProcessor(
    private val userService: TelegramUserService
): LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is StartEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent as StartEvent
        val info = bunch.extraInformation

        return when(event) {
            is OpenContactWithUsScreenEvent -> processOpenContactWithUsScreen(info)
            is OpenIAmCustomerScreenEvent -> processOpenIAmCustomerScreen(info)
            is OpenIAmSpecialistScreenEvent -> processOpenIAmSpecialistScreen(info)
            is OpenMyProfileScreenEvent -> processOpenMyProfileScreen(info)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processOpenContactWithUsScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = ContactWithUsScreenState
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processOpenIAmCustomerScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = IAmCustomerInfoScreenState
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processOpenIAmSpecialistScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = IAmSpecialistInfoScreenState
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processOpenMyProfileScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = NotImplementedScreenState
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }
}