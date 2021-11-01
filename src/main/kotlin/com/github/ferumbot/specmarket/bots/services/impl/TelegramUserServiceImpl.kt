package com.github.ferumbot.specmarket.bots.services.impl

import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import com.github.ferumbot.specmarket.bots.services.TelegramUserService

class TelegramUserServiceImpl(
    private val repository: TelegramUserRepository
): TelegramUserService {
}