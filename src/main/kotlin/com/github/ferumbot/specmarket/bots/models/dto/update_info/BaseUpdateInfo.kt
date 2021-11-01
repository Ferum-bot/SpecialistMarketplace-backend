package com.github.ferumbot.specmarket.bots.models.dto.update_info

interface BaseUpdateInfo {

    companion object {

        fun get(chatId: Long, userId: Long): BaseUpdateInfo {
            return object: BaseUpdateInfo {
                override val chatId: Long = chatId
                override val userId: Long = userId
            }
        }
    }

    val chatId: Long

    val userId: Long
}