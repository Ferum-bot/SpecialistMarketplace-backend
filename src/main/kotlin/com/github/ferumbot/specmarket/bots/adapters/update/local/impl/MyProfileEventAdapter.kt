package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getCommandAlias
import com.github.ferumbot.specmarket.bots.core.getUserId
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.OpenAnotherPageRequest
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import org.telegram.telegrambots.meta.api.objects.Update

class MyProfileEventAdapter: LocalUpdateAdapter {

    companion object {

        private val OPEN_MY_REQUESTS_NAME = OpenMyRequestsScreenEvent.friendlyName
        private val OPEN_EDIT_PROFILE_NAME = OpenEditInfoScreenEvent.friendlyName
        private val OPEN_CONTINUE_REGISTRATION_NAME = ContinueCreatingProfileFlowEvent.friendlyName
        private val OPEN_START_REGISTRATION_NAME = StartRegistrationFlowEvent.friendlyName
        private val OPEN_ANOTHER_MY_REQUESTS_PAGE_NAME = OpenAnotherMyRequestsPageScreenEvent.friendlyName
        private val OPEN_ANOTHER_MY_REQUESTS_PAGE_COMMAND = OpenAnotherMyRequestsPageScreenEvent.commandAlias
        private val CHANGE_PROFILE_VISIBILITY = ChangeProfileSpecialistVisibilityScreenEvent.friendlyName

        private val handlingEvents = listOf(
            OPEN_MY_REQUESTS_NAME, OPEN_EDIT_PROFILE_NAME,
            OPEN_START_REGISTRATION_NAME, OPEN_CONTINUE_REGISTRATION_NAME,
            OPEN_ANOTHER_MY_REQUESTS_PAGE_NAME, OPEN_ANOTHER_MY_REQUESTS_PAGE_COMMAND,
            CHANGE_PROFILE_VISIBILITY
        )
    }

    override fun isFor(update: Update): Boolean {
        val alias = update.getCommandAlias()
        if (alias.isOpenAnotherRequestsPageCommand()) {
            return true
        }

        return alias in handlingEvents
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val command = update.getCommandAlias()

        if (command.isOpenAnotherRequestsPageCommand()) {
            return openAnotherRequestsPage(update)
        }

        return when(command) {
            OPEN_MY_REQUESTS_NAME -> openMyRequests(update)
            OPEN_EDIT_PROFILE_NAME -> openEditProfile(update)
            OPEN_CONTINUE_REGISTRATION_NAME -> openContinueRegistration(update)
            OPEN_START_REGISTRATION_NAME -> openStartRegistration(update)
            CHANGE_PROFILE_VISIBILITY -> changeProfileVisibility(update)
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

    private fun openAnotherRequestsPage(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = OpenAnotherMyRequestsPageScreenEvent

        val command = update.getCommandAlias()
        val pageToOpen = command.pageToOpen

        val info = OpenAnotherPageRequest(chatId, userId, pageToOpen)
        return MessageUpdateBunch(event, info)
    }

    private fun changeProfileVisibility(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = ChangeProfileSpecialistVisibilityScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun String.isOpenAnotherRequestsPageCommand(): Boolean {
        return startsWith(OPEN_ANOTHER_MY_REQUESTS_PAGE_COMMAND, ignoreCase = true)
    }

    private val String.pageToOpen: Int
    get() {
        val separator = ':'
        val separatorIndex = indexOf(separator)
        if (separatorIndex == -1) {
            return 0
        }

        return substring(separatorIndex + 1).toInt()
    }
}