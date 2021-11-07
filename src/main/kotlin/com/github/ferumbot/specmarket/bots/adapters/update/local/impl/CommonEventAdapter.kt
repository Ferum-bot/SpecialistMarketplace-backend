package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getCommandAlias
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

        private val START_COMMAND = RegisterNewUserEvent.commandAlias
        private val BACK_COMMAND = GoBackEvent.commandAlias
        private val MAIN_PAGE_COMMAND = OpenStartScreenEvent.commandAlias
        private val CONTACT_WITH_US_COMMAND = OpenContactWithUsScreenEvent.commandAlias

        private val handlingCommands = listOf(
            START_COMMAND, BACK_COMMAND, MAIN_PAGE_COMMAND, CONTACT_WITH_US_COMMAND
        )
    }

    override fun isFor(update: Update): Boolean {
        if (!update.isCommand()) {
            return false
        }
        return handlingCommands.contains(update.getCommandAlias())
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val command = update.getCommandAlias()

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