package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getUserId
import org.telegram.telegrambots.meta.api.objects.Update

interface BaseUpdateInfo {

    companion object {

        fun from(chatId: Long, userId: Long): BaseUpdateInfo {
            return object: BaseUpdateInfo {
                override val chatId: Long = chatId
                override val userId: Long = userId
            }
        }

        fun from(update: Update): BaseUpdateInfo {
            return object: BaseUpdateInfo {
                override val chatId: Long = update.getChatId()
                override val userId: Long = update.getUserId()
            }
        }
    }

    val chatId: Long

    val userId: Long
}