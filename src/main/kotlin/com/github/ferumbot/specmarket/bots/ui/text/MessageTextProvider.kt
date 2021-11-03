package com.github.ferumbot.specmarket.bots.ui.text

interface MessageTextProvider {

    fun provideStartScreenMessage(): String

    fun provideContactWithUsMessage(): String

    fun provideNotImplementedMessage(): String

    fun provideUnSupportedMessage(): String

    fun provideIAmCustomerInfoMessage(): String

    fun provideIDoNotKnowWhatIWantMessage(): String

    fun provideIDoNotKnowWhoISearchMessage(): String

    fun provideAboutEachSpecialistMessage(): String

    fun provideIAmSpecialistInfoMessage(): String

    fun provideAllSpecialistInfoMessage(): String
}