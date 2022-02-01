package com.github.ferumbot.specmarket.bots.services

interface TelegramChatService {

    fun sendMessage(chatId: Int, message: Int)
}