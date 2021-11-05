package com.github.ferumbot.specmarket.bots.ui.keyboard_buttons

import com.github.ferumbot.specmarket.bots.state_machine.event.*
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

class DefaultKeyboardButtonsProvider: KeyboardMessageButtonsProvider {

    companion object {

        private val ALL_SPECIALIST_NAME = OpenAllSpecialistsScreenEvent.friendlyName
        private val I_AM_CUSTOMER_NAME = OpenIAmCustomerScreenEvent.friendlyName
        private val I_AM_SPECIALIST_NAME = OpenIAmSpecialistScreenEvent.friendlyName
        private val MY_PROFILE_NAME = OpenMyProfileScreenEvent.friendlyName
        private val CONTACT_WITH_US_NAME = OpenContactWithUsScreenEvent.friendlyName

        private const val FIRST_SPECIALIST_NAME = "Specialist 1"
        private const val SECOND_SPECIALIST_NAME = "Specialist 2"
        private const val THIRD_SPECIALIST_NAME = "Specialist 3"
        private const val FORTH_SPECIALIST_NAME = "Specialist 4"
        private const val FIVES_SPECIALIST_NAME = "Specialist 5"
        private const val SIX_SPECIALIST_NAME = "Specialist 6"
        private const val SEVENTH_SPECIALIST_NAME = "Specialist 7"
        private const val EIGHT_SPECIALIST_NAME = "Specialist 8"
        private const val NINES_SPECIALIST_NAME = "Specialist 9"
        private const val TEN_SPECIALIST_NAME = "Specialist 10"

        private val OPEN_FILTER_NAME = OpenFilterScreenEvent.friendlyName
        private val OPEN_I_DO_NOT_WHO_I_SEARCH_NAME = OpenIDoNotKnowWhoISearchScreenEvent.friendlyName

        private val OPEN_I_DO_KNOW_WHAT_I_WANT_NAME = OpenIDoNotKnowWhatIWantScreenEvent.friendlyName
        private val OPEN_ABOUT_EACH_SPECIALIST_NAME = OpenAboutEachSpecialistScreenEvent.friendlyName
    }

    override fun provideStartScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(ALL_SPECIALIST_NAME)
        val secondRow = getRowWithButtons(I_AM_CUSTOMER_NAME, I_AM_SPECIALIST_NAME)
        val thirdRow = getRowWithButtons(MY_PROFILE_NAME)
        val forthRow = getRowWithButtons(CONTACT_WITH_US_NAME)

        return getKeyBoardWithRows(firstRow, secondRow, thirdRow, forthRow)
    }

    override fun provideAllSpecialistScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(OPEN_FILTER_NAME)
        val secondRow = getRowWithButtons(OPEN_I_DO_NOT_WHO_I_SEARCH_NAME)

        return getKeyBoardWithRows(firstRow, secondRow)
    }

    override fun provideIAmCustomerInfoScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(OPEN_I_DO_KNOW_WHAT_I_WANT_NAME)
        val secondRow = getRowWithButtons(OPEN_ABOUT_EACH_SPECIALIST_NAME)

        return getKeyBoardWithRows(firstRow, secondRow)
    }

    override fun provideAboutEachSpecialistInfoScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(FIRST_SPECIALIST_NAME, SECOND_SPECIALIST_NAME)
        val secondRow = getRowWithButtons(THIRD_SPECIALIST_NAME, FORTH_SPECIALIST_NAME)
        val thirdRow = getRowWithButtons(FIVES_SPECIALIST_NAME, SIX_SPECIALIST_NAME)
        val forthRow = getRowWithButtons(SEVENTH_SPECIALIST_NAME, EIGHT_SPECIALIST_NAME)
        val fivesRow = getRowWithButtons(NINES_SPECIALIST_NAME, TEN_SPECIALIST_NAME)

        return getKeyBoardWithRows(firstRow, secondRow, thirdRow, forthRow, fivesRow)
    }

    private fun getDefaultKeyboardMarkup() = ReplyKeyboardMarkup()
    .apply {
        selective = true
        resizeKeyboard = true
        oneTimeKeyboard = true
    }

    private fun getRowWithButtons(vararg buttonTexts: String): KeyboardRow {
        val row = KeyboardRow()
        buttonTexts.forEach { text ->
            val button = KeyboardButton(text)
            row.add(button)
        }
        return row
    }

    private fun getKeyBoardWithRows(vararg rows: KeyboardRow): ReplyKeyboardMarkup {
        val keyboardMarkup = getDefaultKeyboardMarkup()
        val keyboard = mutableListOf<KeyboardRow>()
        rows.forEach { row ->
            keyboard.add(row)
        }
        keyboardMarkup.keyboard = keyboard
        return keyboardMarkup
    }
}