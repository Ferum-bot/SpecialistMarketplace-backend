package com.github.ferumbot.specmarket.bots.ui.inline_buttons

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup

interface InlineMessageButtonsProvider {

    fun provideStartScreenButtons(): InlineKeyboardMarkup
}