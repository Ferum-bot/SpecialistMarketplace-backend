package com.github.ferumbot.specmarket.bots.ui.inline_buttons

import com.github.ferumbot.specmarket.bots.state_machine.event.*
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

class DefaultMessageButtonsProvider: InlineMessageButtonsProvider {

    companion object {

        private val ALL_SPECIALIST_NAME = OpenAllSpecialistsScreenEvent.friendlyName
        private val I_AM_CUSTOMER_NAME = OpenIAmCustomerScreenEvent.friendlyName
        private val I_AM_SPECIALIST_NAME = OpenIAmSpecialistScreenEvent.friendlyName
        private val MY_PROFILE_NAME = OpenMyProfileScreenEvent.friendlyName
        private val CONTACT_WITH_US_NAME = OpenContactWithUsScreenEvent.friendlyName
    }

    override fun provideStartScreenButtons(): ReplyKeyboardMarkup {
        val keyboardMarkup = ReplyKeyboardMarkup().apply {
            selective = true
            resizeKeyboard = true
            oneTimeKeyboard = true
        }
        val keyboard = mutableListOf<KeyboardRow>()

        val firstRow = KeyboardRow()
        val allSpecialistsButton = KeyboardButton(ALL_SPECIALIST_NAME)
        firstRow.add(allSpecialistsButton)
        val secondRow = KeyboardRow()
        val iAmCustomerButton = KeyboardButton(I_AM_CUSTOMER_NAME)
        val iAmSpecialistButton = KeyboardButton(I_AM_SPECIALIST_NAME)
        secondRow.addAll(listOf(iAmCustomerButton, iAmSpecialistButton))
        val thirdRow = KeyboardRow()
        val myProfileButton = KeyboardButton(MY_PROFILE_NAME)
        thirdRow.add(myProfileButton)
        val forthRow = KeyboardRow()
        val contactWithUsButton = KeyboardButton(CONTACT_WITH_US_NAME)
        forthRow.add(contactWithUsButton)

        keyboard.apply {
            add(firstRow)
            add(secondRow)
            add(thirdRow)
            add(forthRow)
        }
        keyboardMarkup.keyboard = keyboard
        return keyboardMarkup
    }
}