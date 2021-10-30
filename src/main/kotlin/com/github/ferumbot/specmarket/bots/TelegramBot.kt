package com.github.ferumbot.specmarket.bots

import org.springframework.beans.factory.annotation.Value
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

class TelegramBot(options: DefaultBotOptions): TelegramWebhookBot(options) {

    @Value("\${bots.telegram.api.token}")
    private lateinit var token: String

    @Value("\${bots.telegram.api.username}")
    private lateinit var userName: String

    @Value("\${bots.telegram.api.path}")
    private lateinit var path: String

    override fun getBotToken() = token

    override fun getBotUsername() = userName

    override fun getBotPath() = path

    override fun onWebhookUpdateReceived(update: Update): BotApiMethod<*> {
        TODO("Not yet implemented")
    }

}