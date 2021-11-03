package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.state_machine.event.UnSupportedEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.UnSupportedScreenState

class UnSupportedUpdateProcessor: LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is UnSupportedEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val info = bunch.extraInformation
        val newState = UnSupportedScreenState
        return MessageUpdateResultBunch(newState, info)
    }
}