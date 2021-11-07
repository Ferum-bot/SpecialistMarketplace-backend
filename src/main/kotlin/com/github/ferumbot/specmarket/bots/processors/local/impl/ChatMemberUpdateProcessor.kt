package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.state_machine.event.ChatMemberEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.EmptyScreenState

class ChatMemberUpdateProcessor: LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is ChatMemberEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val info = bunch.extraInformation
        val state = EmptyScreenState

        return MessageUpdateResultBunch(state, info)
    }
}