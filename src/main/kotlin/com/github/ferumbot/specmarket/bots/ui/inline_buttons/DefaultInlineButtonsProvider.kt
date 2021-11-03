package com.github.ferumbot.specmarket.bots.ui.inline_buttons

import com.github.ferumbot.specmarket.bots.state_machine.event.GoBackEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenStartScreenEvent
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class DefaultInlineButtonsProvider: InlineMessageButtonsProvider {

    companion object {

        private val START_SCREEN_COMMAND = OpenStartScreenEvent.commandAlias
        private val START_SCREEN_NAME = OpenStartScreenEvent.friendlyName

        private val BACK_SCREEN_COMMAND = GoBackEvent.commandAlias
        private val BACK_SCREEN_NAME = GoBackEvent.friendlyName
    }

    override fun provideNotImplementedScreenButtons(): InlineKeyboardMarkup {
        return getSingleInlineButton(START_SCREEN_NAME, START_SCREEN_COMMAND)
    }

    override fun provideUnSupportedScreenButtons(): InlineKeyboardMarkup {
        return getSingleInlineButton(START_SCREEN_NAME, START_SCREEN_COMMAND)
    }

    override fun provideBackButton(): InlineKeyboardMarkup {
        return getSingleInlineButton(BACK_SCREEN_NAME, BACK_SCREEN_COMMAND)
    }

    private fun getSingleInlineButton(alias: String, command: String): InlineKeyboardMarkup {
        val inlineKeyboard = InlineKeyboardMarkup()
        val rows = mutableListOf(mutableListOf<InlineKeyboardButton>())
        val button = InlineKeyboardButton(alias).apply {
            callbackData = command
        }
        rows[0].add(button)
        inlineKeyboard.keyboard = rows
        return inlineKeyboard
    }

}