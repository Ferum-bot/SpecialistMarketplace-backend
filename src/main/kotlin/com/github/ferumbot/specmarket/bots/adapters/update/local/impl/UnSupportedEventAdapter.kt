package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getUserId
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.UnSupportedEvent
import org.telegram.telegrambots.meta.api.objects.Update

class UnSupportedEventAdapter: LocalUpdateAdapter {

    override fun isFor(update: Update) = true

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = UnSupportedEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }
}