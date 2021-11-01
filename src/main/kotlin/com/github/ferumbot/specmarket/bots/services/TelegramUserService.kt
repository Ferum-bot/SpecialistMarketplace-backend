package com.github.ferumbot.specmarket.bots.services

import com.github.ferumbot.specmarket.bots.models.entity.TelegramChat
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser

interface TelegramUserService {

    fun registerNewUserAndChat(chat: TelegramChat, user: TelegramUser)
}