package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getUserId
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.BotsChatMemberUpdateEvent
import org.telegram.telegrambots.meta.api.objects.Update

class ChatMemberEventAdapter: LocalUpdateAdapter {

    override fun isFor(update: Update): Boolean {
        return update.hasMyChatMember() || update.hasChatMember()
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        return if (update.hasMyChatMember()) {
            myChatMember(update)
        } else {
            chatMember(update)
        }
    }

    private fun myChatMember(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = BotsChatMemberUpdateEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun chatMember(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = BotsChatMemberUpdateEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }
}