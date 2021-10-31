package com.github.ferumbot.specmarket.bots.adapters.result

import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateResultBunch
import org.telegram.telegrambots.meta.api.methods.BotApiMethod

interface BotUpdateResultAdapter {

    fun adaptResult(result: MessageUpdateResultBunch<*>): BotApiMethod<*>
}