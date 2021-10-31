package com.github.ferumbot.specmarket.bots.processors.local

import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateResultBunch

interface LocalUpdateProcessor {

    fun canProcess(bunch: MessageUpdateBunch<*>): Boolean

    fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*>
}