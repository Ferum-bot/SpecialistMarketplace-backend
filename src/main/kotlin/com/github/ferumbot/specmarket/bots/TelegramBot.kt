package com.github.ferumbot.specmarket.bots

import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import org.springframework.beans.factory.annotation.Value
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class TelegramBot(
    options: DefaultBotOptions,
    private val interactor: BotInteractor<Update, BotApiMethod<*>>
): TelegramWebhookBot(options) {

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
        update.message.chat
        return interactor.handleTransfer(update)
    }
}