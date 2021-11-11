package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getCommandAlias
import com.github.ferumbot.specmarket.bots.core.getUserId
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.ContinueCreatingProfileFlowEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenEditInfoScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenMyRequestsScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.StartRegistrationFlowEvent
import org.telegram.telegrambots.meta.api.objects.Update

class MyProfileEventAdapter: LocalUpdateAdapter {

    companion object {

        private val OPEN_MY_REQUESTS_NAME = OpenMyRequestsScreenEvent.friendlyName
        private val OPEN_EDIT_PROFILE_NAME = OpenEditInfoScreenEvent.friendlyName
        private val OPEN_CONTINUE_REGISTRATION_NAME = ContinueCreatingProfileFlowEvent.friendlyName
        private val OPEN_START_REGISTRATION_NAME = StartRegistrationFlowEvent.friendlyName

        private val handlingEvents = listOf(
            OPEN_MY_REQUESTS_NAME, OPEN_EDIT_PROFILE_NAME,
            OPEN_START_REGISTRATION_NAME, OPEN_CONTINUE_REGISTRATION_NAME
        )
    }

    override fun isFor(update: Update): Boolean {
        return update.getCommandAlias() in handlingEvents
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val command = update.getCommandAlias()

        return when(command) {
            OPEN_MY_REQUESTS_NAME -> openMyRequests(update)
            OPEN_EDIT_PROFILE_NAME -> openEditProfile(update)
            OPEN_CONTINUE_REGISTRATION_NAME -> openContinueRegistration(update)
            OPEN_START_REGISTRATION_NAME -> openStartRegistration(update)
            else -> LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun openMyRequests(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = OpenMyRequestsScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun openEditProfile(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = OpenEditInfoScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun openContinueRegistration(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = ContinueCreatingProfileFlowEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun openStartRegistration(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = StartRegistrationFlowEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }
}