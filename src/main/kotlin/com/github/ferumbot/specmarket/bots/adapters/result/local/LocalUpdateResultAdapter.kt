package com.github.ferumbot.specmarket.bots.adapters.result.local

import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateResultBunch
import org.telegram.telegrambots.meta.api.methods.BotApiMethod

interface LocalUpdateResultAdapter {

    fun isFor(bunch: MessageUpdateResultBunch<*>): Boolean

    fun adapt(bunch: MessageUpdateResultBunch<*>): BotApiMethod<*>
}