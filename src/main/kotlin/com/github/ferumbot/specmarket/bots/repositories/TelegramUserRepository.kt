package com.github.ferumbot.specmarket.bots.repositories

import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TelegramUserRepository: JpaRepository<TelegramUser, Long> {

    fun findByTelegramUserId(telegramUserId: Long): TelegramUser?

    fun existsTelegramUserByTelegramUserId(telegramUserId: Long): Boolean

    @Query(
        value = "SELECT COUNT(specialist_id) FROM telegram_user_to_specialist_requests WHERE telegram_user_id = :telegram_user_id",
        nativeQuery = true,
    )
    fun countUserSpecialistRequests(
        @Param(value = "telegram_user_id")
        telegramUserId: Long
    ): Int
}