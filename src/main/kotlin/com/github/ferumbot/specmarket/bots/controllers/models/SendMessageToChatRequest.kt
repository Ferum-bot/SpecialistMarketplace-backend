package com.github.ferumbot.specmarket.bots.controllers.models

data class SendMessageToChatRequest(

    val chatId: Long,

    val message: String,
)
