package com.github.ferumbot.specmarket.bots.services.impl

import com.github.ferumbot.specmarket.bots.models.dto.TelegramUserDto
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import com.github.ferumbot.specmarket.bots.services.TelegramUsersService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TelegramUsersServiceImpl(
    private val repository: TelegramUserRepository,
): TelegramUsersService {

    @Transactional(readOnly = true)
    override fun getAllUsers(pageNumber: Int, pageSize: Int): Collection<TelegramUserDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val users = repository.findAll(page)
        return users.content.map { TelegramUserDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getUserById(id: Long): TelegramUserDto? {
        val user: TelegramUser? = repository.findById(id).orElseGet { null }
        return user?.run { TelegramUserDto.from(this) }
    }

    @Transactional(readOnly = true)
    override fun getUserByTelegramUserId(id: Long): TelegramUserDto? {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getUserByTelegramChatId(id: Long): TelegramUserDto? {
        TODO("Not yet implemented")
    }
}