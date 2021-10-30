package com.github.ferumbot.specmarket.bots.controllers

import com.github.ferumbot.specmarket.bots.TelegramBot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

@RestController
class TelegramController @Autowired constructor(
    private val telegramBot: TelegramBot
) {

    @PostMapping("/")
    fun onUpdateReceived(
        @RequestBody
        update: Update
    ): BotApiMethod<*> {
        return telegramBot.onWebhookUpdateReceived(update)
    }

}