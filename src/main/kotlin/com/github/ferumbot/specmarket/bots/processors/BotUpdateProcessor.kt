package com.github.ferumbot.specmarket.bots.processors

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch

interface BotUpdateProcessor {

    fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*>
}