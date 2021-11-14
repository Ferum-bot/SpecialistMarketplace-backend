package com.github.ferumbot.specmarket.bots.ui.inline_buttons

import com.github.ferumbot.specmarket.bots.state_machine.event.*
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import kotlin.time.times

class DefaultInlineButtonsProvider: InlineMessageButtonsProvider {

    companion object {

        private val START_SCREEN_COMMAND = OpenStartScreenEvent.commandAlias
        private val START_SCREEN_NAME = OpenStartScreenEvent.friendlyName

        private val BACK_SCREEN_COMMAND = GoBackEvent.commandAlias
        private val BACK_SCREEN_NAME = GoBackEvent.friendlyName

        private val ABOUT_EACH_SPECIALIST_COMMAND = OpenAboutEachSpecialistScreenEvent.commandAlias
        private val ABOUT_EACH_SPECIALIST_NAME = OpenAboutEachSpecialistScreenEvent.friendlyName

        private val OPEN_REQUESTS_PAGE_COMMAND = OpenAnotherMyRequestsPageScreenEvent.commandAlias

        private val SHOW_HOW_IT_LOOKS_LIKE_NOW_NAME = OpenHowItLooksLikeNowScreenEvent.friendlyName
        private val SHOW_HOW_IT_LOOKS_LIKE_NOW_COMMAND = OpenHowItLooksLikeNowScreenEvent.commandAlias

        private val CONTINUE_REGISTRATION_FLOW_NAME = ContinueCreatingProfileFlowEvent.friendlyName
        private val CONTINUE_REGISTRATION_FLOW_COMMAND = ContinueCreatingProfileFlowEvent.commandAlias

        private val RESTART_REGISTRATION_FLOW_NAME = RestartRegistrationFlowEvent.friendlyName
        private val RESTART_REGISTRATION_FLOW_COMMAND = RestartRegistrationFlowEvent.commandAlias
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

    override fun provideSpecialistRequestsButtons(currentPage: Int, totalPageCount: Int): InlineKeyboardMarkup {
        return if (totalPageCount <= 5) {
            getSpecialistRequestsSimpleButtons(currentPage, totalPageCount)
        } else {
            getSpecialistRequestComplicatedButtons(currentPage, totalPageCount)
        }
    }

    override fun provideCreatingProfileButtons(): InlineKeyboardMarkup {
        val firstButton = InlineButton(
            SHOW_HOW_IT_LOOKS_LIKE_NOW_NAME, SHOW_HOW_IT_LOOKS_LIKE_NOW_COMMAND
        )
        val firstRow = getInlineRow(firstButton)

        return getInlineKeyboard(firstRow)
    }

    override fun provideHowProfileLooksLikeKnowButtons(): InlineKeyboardMarkup {
        val firstButton = InlineButton(
            CONTINUE_REGISTRATION_FLOW_NAME, CONTINUE_REGISTRATION_FLOW_COMMAND
        )
        val secondButton = InlineButton(
            RESTART_REGISTRATION_FLOW_NAME, RESTART_REGISTRATION_FLOW_COMMAND
        )

        val firstRow = getInlineRow(firstButton)
        val secondRow = getInlineRow(secondButton)

        return getInlineKeyboard(firstRow, secondRow)
    }

    private fun getSpecialistRequestsSimpleButtons(currentPage: Int, pageCount: Int): InlineKeyboardMarkup {
        val buttons = (1).rangeTo(pageCount).map { page ->
            InlineButton(
                aliasForPageButton(page, currentPage),
                commandForPageButton(page)
            )
        }
        val row = getInlineRow(*buttons.toTypedArray())

        return getInlineKeyboard(row)
    }

    private fun getSpecialistRequestComplicatedButtons(currentPage: Int, pageCount: Int): InlineKeyboardMarkup {
        val first = 1

        val firstButton = InlineButton(
            aliasForPageButton(first, currentPage, isLeftBorder = true),
            commandForPageButton(first),
        )
        val fivesButton = InlineButton(
            aliasForPageButton(pageCount, currentPage, isRightBorder = true),
            commandForPageButton(pageCount)
        )

        if (currentPage == pageCount - 1) {
            val forthButton = InlineButton(
                aliasForPageButton(pageCount - 1, currentPage),
                commandForPageButton(pageCount - 1)
            )
            val thirdButton = InlineButton(
                aliasForPageButton(pageCount - 2, currentPage),
                commandForPageButton(pageCount - 2)
            )
            val secondButton = InlineButton(
                aliasForPageButton(pageCount - 3, currentPage),
                commandForPageButton(pageCount - 3)
            )
            val row = getInlineRow(firstButton, secondButton, thirdButton, forthButton, fivesButton)

            return getInlineKeyboard(row)
        }

        if (currentPage == first + 1) {
            val secondButton = InlineButton(
                aliasForPageButton(first + 1, currentPage),
                commandForPageButton(first + 1)
            )
            val thirdButton = InlineButton(
                aliasForPageButton(first + 2, currentPage),
                commandForPageButton(first + 2)
            )
            val forthButton = InlineButton(
                aliasForPageButton(first + 3, currentPage),
                commandForPageButton(first + 3)
            )
            val row = getInlineRow(firstButton, secondButton, thirdButton, forthButton, fivesButton)

            return getInlineKeyboard(row)
        }

        val secondButton = InlineButton(
            aliasForPageButton(currentPage - 1, currentPage),
            commandForPageButton(currentPage - 1)
        )
        val thirdButton = InlineButton(
            aliasForPageButton(currentPage, currentPage),
            commandForPageButton(currentPage)
        )
        val forthButton = InlineButton(
            aliasForPageButton(currentPage + 1, currentPage),
            commandForPageButton(currentPage + 1)
        )
        val row = getInlineRow(firstButton, secondButton, thirdButton, forthButton)

        return getInlineKeyboard(row)
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

    private fun aliasForPageButton(
        page: Int, selectedPage: Int, isLeftBorder: Boolean = false, isRightBorder: Boolean = false,
    ): String {
        if (page == selectedPage) {
            return "* $page *"
        }
        if (isLeftBorder) {
            return "<< $page"
        }
        if (isRightBorder) {
            return "$page >>"
        }
        return page.toString()
    }

    private fun commandForPageButton(page: Int) =
        "$OPEN_REQUESTS_PAGE_COMMAND$page"
}