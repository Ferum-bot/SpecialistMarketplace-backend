package com.github.ferumbot.specmarket.bots.services.impl

import com.github.ferumbot.specmarket.bots.repositories.TelegramChatRepository
import com.github.ferumbot.specmarket.bots.services.TelegramChatService
import org.springframework.stereotype.Service

@Service
class TelegramChatServiceImpl(
    private val repository: TelegramChatRepository,
): TelegramChatService {

    override fun sendMessage(chatId: Int, message: Int) {
        TODO("Not yet implemented")
    }

}