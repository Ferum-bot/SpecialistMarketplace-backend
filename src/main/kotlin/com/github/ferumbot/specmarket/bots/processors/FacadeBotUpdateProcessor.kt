package com.github.ferumbot.specmarket.bots.processors

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor

class FacadeBotUpdateProcessor(
    private val localProcessors: Collection<LocalUpdateProcessor>
): BotUpdateProcessor {

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        lateinit var resultBunch: MessageUpdateResultBunch<*>
        for (processor in localProcessors) {
            if (processor.canProcess(bunch)) {
                resultBunch = processor.process(bunch)
            }
        }

        return resultBunch
    }
}