package com.github.ferumbot.specmarket.bots.ui.inline_buttons

import com.github.ferumbot.specmarket.bots.state_machine.event.GoBackEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenAboutEachSpecialistScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenStartScreenEvent
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class DefaultInlineButtonsProvider: InlineMessageButtonsProvider {

    companion object {

        private val START_SCREEN_COMMAND = OpenStartScreenEvent.commandAlias
        private val START_SCREEN_NAME = OpenStartScreenEvent.friendlyName

        private val BACK_SCREEN_COMMAND = GoBackEvent.commandAlias
        private val BACK_SCREEN_NAME = GoBackEvent.friendlyName

        private val ABOUT_EACH_SPECIALIST_COMMAND = OpenAboutEachSpecialistScreenEvent.commandAlias
        private val ABOUT_EACH_SPECIALIST_NAME = OpenAboutEachSpecialistScreenEvent.friendlyName
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

    override fun provideIDoNotKnowWhatIWantScreenButtons(): InlineKeyboardMarkup {
        val firstRow = getInlineRow(InlineButton(ABOUT_EACH_SPECIALIST_NAME, ABOUT_EACH_SPECIALIST_COMMAND))
        val secondRow = getInlineRow(InlineButton(BACK_SCREEN_NAME, BACK_SCREEN_COMMAND))

        return getInlineKeyboard(firstRow, secondRow)
    }

    private fun getSingleInlineButton(alias: String, command: String): InlineKeyboardMarkup {
        val row = getInlineRow(InlineButton(alias, command))
        return getInlineKeyboard(row)
    }

    private fun getInlineKeyboard(vararg rows: InlineRow): InlineKeyboardMarkup {
        val inlineKeyboard = InlineKeyboardMarkup()
        val apiRows = mutableListOf<List<InlineKeyboardButton>>()

        rows.forEach { row ->
            val apiButtons = row.toApiRow()
            apiRows.add(apiButtons)
        }

        inlineKeyboard.keyboard = apiRows
        return inlineKeyboard
    }

    private fun getInlineRow(vararg buttons: InlineButton): InlineRow {
        return InlineRow(buttons.toList())
    }

    private data class InlineButton(val alias: String, val command: String)

    private data class InlineRow(val buttons: List<InlineButton>)

    private fun InlineRow.toApiRow(): List<InlineKeyboardButton> {
        return buttons.map { button ->
            val apiButton = InlineKeyboardButton(button.alias)
            apiButton.callbackData = button.command
            apiButton
        }
    }
}