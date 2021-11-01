package com.github.ferumbot.specmarket.bots.ui.text

interface MessageTextProvider {

    fun provideStartScreenMessage(): String

    fun provideContactWithUsMessage(): String

    fun provideNotImplementedMessage(): String

    fun provideIAmCustomerInfoMessage(): String

    fun provideIDoNotKnowWhatIWantMessage(): String

    fun provideAboutEachSpecialistMessage(): String

    fun provideIAmSpecialistInfoMessage(): String
}