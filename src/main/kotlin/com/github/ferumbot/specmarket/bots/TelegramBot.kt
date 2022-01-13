package com.github.ferumbot.specmarket.bots

import com.github.ferumbot.specmarket.bots.configs.properties.TelegramBotProperties
import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import com.github.ferumbot.specmarket.bots.interceptors.ExceptionInterceptorFacade
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
    private val interactor: BotInteractor<Update, BotApiMethod<*>?>,
    private val exceptionInterceptor: ExceptionInterceptorFacade,
    private val telegramBotProperties: TelegramBotProperties,
): TelegramWebhookBot(options) {

    override fun getBotToken() = telegramBotProperties.token

    override fun getBotUsername() = telegramBotProperties.botUserName

    override fun getBotPath() = telegramBotProperties.webhookPath

    override fun onWebhookUpdateReceived(update: Update): BotApiMethod<*>? {
        return try {
            interactor.handleTransfer(update)
        } catch (ex: Exception) {
            exceptionInterceptor.onExceptionRaised(ex, update)
        }
    }
}