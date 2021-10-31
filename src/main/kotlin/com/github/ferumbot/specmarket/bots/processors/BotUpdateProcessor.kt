package com.github.ferumbot.specmarket.bots.processors

import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateResultBunch

interface BotUpdateProcessor {

    fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*>
}