package com.github.ferumbot.specmarket.bots.adapters.update

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import org.telegram.telegrambots.meta.api.objects.Update

interface BotUpdateAdapter {

    fun adaptBotUpdate(update: Update): MessageUpdateResultBunch<*>
}