package com.github.ferumbot.specmarket.bots.interactors.impl

import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.processors.BotUpdateProcessor
import org.springframework.beans.factory.annotation.Autowired

class BotAdapterToProcessorInteractor(
    private val updateProcessor: BotUpdateProcessor
): BotInteractor<MessageUpdateBunch<*>, MessageUpdateResultBunch<*>> {

    override fun handleTransfer(input: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        return updateProcessor.process(input)
    }
}