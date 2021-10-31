package com.github.ferumbot.specmarket.bots

import org.springframework.beans.factory.annotation.Value
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class TelegramBot(options: DefaultBotOptions): TelegramWebhookBot(options) {

    @Value("\${bots.telegram.api.token}")
    private lateinit var token: String

    @Value("\${bots.telegram.api.username}")
    private lateinit var userName: String

    @Value("\${bots.telegram.api.path}")
    private lateinit var path: String

    init {

    }

    override fun getBotToken() = token

    override fun getBotUsername() = userName

    override fun getBotPath() = path

    override fun onWebhookUpdateReceived(update: Update): BotApiMethod<*> {
        val message = SendMessage(getChatId(update).toString(), "Test test")
        val inlineMarkup = InlineKeyboardMarkup()
        val buttonFirst = listOf(InlineKeyboardButton("Чего нахуй").apply { callbackData = "11" })
        val buttonSecond = listOf(InlineKeyboardButton("Того нахуй!").apply { callbackData = "22" })
        inlineMarkup.keyboard = listOf(buttonFirst, buttonSecond)
        message.replyMarkup = inlineMarkup
        return message
    }

    private fun getChatId(update: Update): Long {
        if (update.hasMessage()) {
            return update.message.chatId
        }
        if (update.hasCallbackQuery()) {
            return update.callbackQuery.message.chatId
        }
        if (update.hasChannelPost()) {
            return update.channelPost.chatId
        }
        if (update.hasChatMember()) {
            return update.chatMember.chat.id
        }
        if (update.hasChosenInlineQuery()) {
            return update.chosenInlineQuery.from.id
        }
        if (update.hasInlineQuery()) {
            return update.inlineQuery.from.id
        }
        if (update.hasMyChatMember()) {
            return update.myChatMember.chat.id
        }
        if (update.hasPreCheckoutQuery()) {
            return update.preCheckoutQuery.from.id
        }
        if (update.hasShippingQuery()) {
            return update.shippingQuery.from.id
        }
        return 0
    }
}