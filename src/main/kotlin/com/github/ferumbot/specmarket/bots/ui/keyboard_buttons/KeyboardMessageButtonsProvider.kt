package com.github.ferumbot.specmarket.bots.ui.keyboard_buttons

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

interface KeyboardMessageButtonsProvider {

    fun provideStartScreenButtons(): ReplyKeyboardMarkup

    fun provideAllSpecialistScreenButtons(): ReplyKeyboardMarkup

    fun provideIAmCustomerInfoScreenButtons(): ReplyKeyboardMarkup

    fun provideIAmSpecialistInfoScreenButtons(): ReplyKeyboardMarkup

    fun provideMyCVInfoScreenButtons(): ReplyKeyboardMarkup

    fun provideIDoNotKnowWhoISearchButtons(): ReplyKeyboardMarkup

    fun provideNotAuthorizedInfoScreenButtons(): ReplyKeyboardMarkup

    fun providePartiallyAuthorizedInfoScreenButtons(): ReplyKeyboardMarkup

    fun provideAuthorizedInfoScreenButtons(): ReplyKeyboardMarkup

    fun provideEditProfileInfoScreenButtons(): ReplyKeyboardMarkup

    fun provideShowProfileButtons(): ReplyKeyboardMarkup
}