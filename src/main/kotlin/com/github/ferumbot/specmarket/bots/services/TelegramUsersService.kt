package com.github.ferumbot.specmarket.bots.services

import com.github.ferumbot.specmarket.bots.models.dto.TelegramUserDto

interface TelegramUsersService {

    fun getAllUsers(pageNumber: Int, pageSize: Int): Collection<TelegramUserDto>

    fun getUserById(id: Long): TelegramUserDto?

    fun getUserByTelegramUserId(id: Long): TelegramUserDto?

    fun getUserByTelegramChatId(id: Long): TelegramUserDto?
}