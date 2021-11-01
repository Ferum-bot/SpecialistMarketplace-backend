package com.github.ferumbot.specmarket.bots.adapters.update.local

import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getUserId
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.state_machine.event.UnSupportedEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.UnSupportedScreenState
import org.telegram.telegrambots.meta.api.objects.Update

interface LocalUpdateAdapter {

    companion object {

        fun unSupportedUpdate(update: Update): MessageUpdateBunch<*> {
            val event = UnSupportedEvent
            val info = BaseUpdateInfo.get(update.getChatId(), update.getUserId())
            return MessageUpdateBunch(event, info)
        }
    }

    fun isFor(update: Update): Boolean

    fun adapt(update: Update): MessageUpdateBunch<*>
}