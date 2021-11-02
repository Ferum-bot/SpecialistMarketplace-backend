package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserUpdateInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.StartScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.UnSupportedScreenState

class CommonUpdateProcessor(
    private val userService: TelegramUserService
): LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is CommonEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val causedEvent = bunch.causedEvent as CommonEvent
        val info = bunch.extraInformation

        return when(causedEvent) {
            GoBackEvent -> processGoBackEvent(info)
            OpenStartScreenEvent -> processOpenStartScreenEvent(info)
            RegisterNewUserEvent -> processRegisterNewUserEvent(info as RegisterNewUserUpdateInfo)
            UnSupportedEvent -> processUnSupportedEvent(info)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processGoBackEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = userService.getAndSetUserPreviousState(info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processOpenStartScreenEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = StartScreenState
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processRegisterNewUserEvent(info: RegisterNewUserUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = StartScreenState
        userService.registerNewUser(info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processUnSupportedEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UnSupportedScreenState
        return MessageUpdateResultBunch(newState, info)
    }
}