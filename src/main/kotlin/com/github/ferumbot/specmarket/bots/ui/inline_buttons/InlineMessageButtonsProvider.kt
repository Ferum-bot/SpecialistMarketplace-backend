package com.github.ferumbot.specmarket.bots.ui.inline_buttons

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup

interface InlineMessageButtonsProvider {

    fun provideUnSupportedScreenButtons(): InlineKeyboardMarkup

    fun provideNotImplementedScreenButtons(): InlineKeyboardMarkup

    fun provideBackButton(): InlineKeyboardMarkup

    fun provideIDoNotKnowWhatIWantScreenButtons(): InlineKeyboardMarkup

    fun provideSpecialistRequestsButtons(currentPage: Int, totalPageCount: Int): InlineKeyboardMarkup

    fun provideCreatingProfileButtons(): InlineKeyboardMarkup

    fun provideHowProfileLooksLikeKnowButtons(): InlineKeyboardMarkup
}