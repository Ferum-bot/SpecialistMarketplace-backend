package com.github.ferumbot.specmarket.bots.ui.inline_buttons

import com.github.ferumbot.specmarket.bots.state_machine.event.*
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

        private val OPEN_REQUESTS_PAGE_COMMAND = OpenAnotherMyRequestsPageScreenEvent.commandAlias
        private val OPEN_SPECIALISTS_PAGE_COMMAND = OpenAnotherSpecialistsPageScreenEvent.commandAlias

        private val SHOW_HOW_IT_LOOKS_LIKE_NOW_NAME = OpenHowItLooksLikeNowScreenEvent.friendlyName
        private val SHOW_HOW_IT_LOOKS_LIKE_NOW_COMMAND = OpenHowItLooksLikeNowScreenEvent.commandAlias

        private val CONTINUE_REGISTRATION_FLOW_NAME = ContinueCreatingProfileFlowEvent.friendlyName
        private val CONTINUE_REGISTRATION_FLOW_COMMAND = ContinueCreatingProfileFlowEvent.commandAlias

        private val RESTART_REGISTRATION_FLOW_NAME = RestartRegistrationFlowEvent.friendlyName
        private val RESTART_REGISTRATION_FLOW_COMMAND = RestartRegistrationFlowEvent.commandAlias

        private val GET_SPECIALISTS_CONTACTS_NAME = GetSpecialistsContactsEvent.friendlyName
        private val GET_SPECIALISTS_CONTACTS_COMMAND = GetSpecialistsContactsEvent.commandAlias
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
        val buttonProvider = object: ButtonProvider {

            override fun invoke(page: Int, selectedPage: Int)
            = InlineButton(
                aliasForPageButton(page, selectedPage, totalPageCount),
                commandForPageRequestsButton(page)
            )
        }

        return if (totalPageCount <= 5) {
            getPageSimpleButtons(currentPage, totalPageCount, buttonProvider)
        } else {
            getPageComplicatedButtons(currentPage, totalPageCount, buttonProvider)
        }
    }

    override fun provideCurrentSpecialistsButton(
        currentPage: Int, totalPageCount: Int, professionAlias: String, specialistId: Long,
    ): InlineKeyboardMarkup {
        val getContactRow = getGetSpecialistsContactsRow(specialistId)
        val buttonProvider = object: ButtonProvider {

            override fun invoke(page: Int, selectedPage: Int)
            = InlineButton(
                aliasForPageButton(page, selectedPage, totalPageCount),
                commandForPageSpecialistsButton(page, professionAlias),
            )
        }

        return if (totalPageCount <= 5) {
            getPageSimpleButtons(currentPage, totalPageCount, buttonProvider, getContactRow)
        } else {
            getPageComplicatedButtons(currentPage, totalPageCount, buttonProvider, getContactRow)
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

    private fun getPageSimpleButtons(
        selectedPage: Int, pageCount: Int, buttonProvider: ButtonProvider,
        vararg extraRows: InlineRow
    ): InlineKeyboardMarkup {
        val buttons = (1).rangeTo(pageCount).map { page ->
            buttonProvider(page, selectedPage)
        }
        val row = getInlineRow(*buttons.toTypedArray())

        return getInlineKeyboard(row, *extraRows)
    }

    private fun getPageComplicatedButtons(
        selectedPage: Int, pageCount: Int, buttonProvider: ButtonProvider,
        vararg extraRows: InlineRow
    ): InlineKeyboardMarkup {
        val first = 1

        val firstButton = buttonProvider(first, selectedPage)
        val fivesButton = buttonProvider(pageCount, selectedPage)

        if (selectedPage == pageCount - 1) {
            val forthButton = buttonProvider(pageCount - 1, selectedPage)
            val thirdButton = buttonProvider(pageCount - 2, selectedPage)
            val secondButton = buttonProvider(pageCount - 3, selectedPage)

            val row = getInlineRow(firstButton, secondButton, thirdButton, forthButton, fivesButton)

            return getInlineKeyboard(row)
        }

        if (selectedPage == first + 1) {
            val secondButton = buttonProvider(first + 1, selectedPage)
            val thirdButton = buttonProvider(first + 2, selectedPage)
            val forthButton = buttonProvider(first + 3, selectedPage)

            val row = getInlineRow(firstButton, secondButton, thirdButton, forthButton, fivesButton)

            return getInlineKeyboard(row)
        }

        val secondButton = buttonProvider(selectedPage - 1, selectedPage)
        val thirdButton = buttonProvider(selectedPage, selectedPage)
        val forthButton = buttonProvider(selectedPage + 1, selectedPage)

        val row = getInlineRow(firstButton, secondButton, thirdButton, forthButton)

        return getInlineKeyboard(row, *extraRows)
    }

    private fun getGetSpecialistsContactsRow(specialistId: Long): InlineRow {
        val button = InlineButton(
            GET_SPECIALISTS_CONTACTS_NAME,
            commandForGetSpecialistsContacts(specialistId)
        )

        return getInlineRow(button)
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

    private fun InlineRow.toApiRow(): List<InlineKeyboardButton> {
        return buttons.map { button ->
            val apiButton = InlineKeyboardButton(button.alias)
            apiButton.callbackData = button.command
            apiButton
        }
    }

    private fun aliasForPageButton(
        page: Int, selectedPage: Int, pageCount: Int,
    ): String {
        val isLeftBorder = page == 1
        val isRightBorder = page == pageCount

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

    private fun commandForPageRequestsButton(page: Int) =
        "$OPEN_REQUESTS_PAGE_COMMAND$page"

    private fun commandForPageSpecialistsButton(page: Int, alias: String) =
        "$OPEN_SPECIALISTS_PAGE_COMMAND:$page:$alias"

    private fun commandForGetSpecialistsContacts(specialistId: Long) =
        "$GET_SPECIALISTS_CONTACTS_COMMAND:$specialistId"

    /**
     * @param First current page
     * @param Second selected page
     */
    private interface ButtonProvider: ((Int, Int) -> InlineButton)

    private data class InlineButton(val alias: String, val command: String)

    private data class InlineRow(val buttons: List<InlineButton>)
}