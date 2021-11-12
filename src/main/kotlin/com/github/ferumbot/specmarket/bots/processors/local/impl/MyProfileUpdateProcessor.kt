package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.MyProfileEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenEditInfoScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenMyRequestsScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.MyRequestsScreenState

class MyProfileUpdateProcessor(
    private val userService: TelegramUserService,
): LocalUpdateProcessor {

    companion object {

        private const val SPECIALIST_PER_SCREEN = 1
    }

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is MyProfileEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent as MyProfileEvent
        val info = bunch.extraInformation

        return when(event) {
            is OpenMyRequestsScreenEvent -> openMyRequestsScreen(info)
            is OpenEditInfoScreenEvent -> openEditMyProfile(info)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun openMyRequestsScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = MyRequestsScreenState
        val
    }

    private fun openEditMyProfile(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {

    }
}