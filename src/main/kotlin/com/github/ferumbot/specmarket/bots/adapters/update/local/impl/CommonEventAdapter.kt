package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getUserId
import com.github.ferumbot.specmarket.bots.core.isCommand
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.GoBackEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenContactWithUsScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenStartScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.RegisterNewUserEvent
import org.telegram.telegrambots.meta.api.objects.Update

class CommonEventAdapter: LocalUpdateAdapter {

    companion object {

        private const val START_COMMAND = "/start"
        private const val BACK_COMMAND = "/back"
        private const val MAIN_PAGE_COMMAND = "/main_page"
        private const val CONTACT_WITH_US_COMMAND = "/contact_with_us"

        private val handlingCommands = listOf(
            "/start", "/back", "/main_page", "/contact_with_us"
        )
    }

    override fun isFor(update: Update): Boolean {
        if (!update.isCommand()) {
            return false
        }
        return handlingCommands.contains(update.message.text)
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val command = update.message.text

        return when(command) {
            START_COMMAND -> registerNewUser(update)
            BACK_COMMAND -> goBack(update)
            MAIN_PAGE_COMMAND -> goToMainPage(update)
            CONTACT_WITH_US_COMMAND -> goToContactWithUs(update)
            else -> LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun registerNewUser(update: Update): MessageUpdateBunch<*> {
        val message = update.message
        val userId = update.getUserId()
        val linkedChatId = update.getChatId()
        val firstName: String = message.from.firstName
        val lastName: String? = message.from.lastName
        val userName: String? = message.from.userName
        val isBot = message.from.isBot
        val languageCode = message.from.languageCode

        val updateInfo = RegisterNewUserUpdateInfo(
            linkedChatId, userId, firstName, lastName, userName, isBot, languageCode
        )

        return MessageUpdateBunch(RegisterNewUserEvent, updateInfo)
    }

    private fun goBack(update: Update): MessageUpdateBunch<*> {
        val userId = update.getUserId()
        val chatId = update.getChatId()

        val updateInfo = BaseUpdateInfo.get(chatId, userId)

        return MessageUpdateBunch(GoBackEvent, updateInfo)
    }

    private fun goToMainPage(update: Update): MessageUpdateBunch<*> {
        val userId = update.getUserId()
        val chatId = update.getChatId()

        val updateInfo = BaseUpdateInfo.get(chatId, userId)

        return MessageUpdateBunch(OpenStartScreenEvent, updateInfo)
    }

    private fun goToContactWithUs(update: Update): MessageUpdateBunch<*> {
        val userId = update.getUserId()
        val chatId = update.getChatId()

        val updateInfo = BaseUpdateInfo.get(chatId, userId)

        return MessageUpdateBunch(OpenContactWithUsScreenEvent, updateInfo)
    }
}