package com.github.ferumbot.specmarket.bots.repositories

import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TelegramUserRepository: JpaRepository<TelegramUser, Long> {
}