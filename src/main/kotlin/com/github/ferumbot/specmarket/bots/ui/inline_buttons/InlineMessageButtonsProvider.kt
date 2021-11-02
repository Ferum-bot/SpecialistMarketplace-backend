package com.github.ferumbot.specmarket.bots.ui.inline_buttons

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

interface InlineMessageButtonsProvider {

    fun provideStartScreenButtons(): ReplyKeyboardMarkup
}