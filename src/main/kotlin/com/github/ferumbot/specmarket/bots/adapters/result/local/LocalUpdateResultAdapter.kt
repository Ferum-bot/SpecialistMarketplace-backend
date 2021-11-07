package com.github.ferumbot.specmarket.bots.adapters.result.local

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

interface LocalUpdateResultAdapter {

    companion object {

        fun unSupportedState(info: BaseUpdateInfo): BotApiMethod<*> {
            val chatId = info.chatId
            return SendMessage(chatId.toString(), "Un supported action!")
        }
    }

    fun isFor(bunch: MessageUpdateResultBunch<*>): Boolean

    fun adapt(bunch: MessageUpdateResultBunch<*>): BotApiMethod<*>?
}