package com.github.ferumbot.specmarket.bots.repositories

import com.github.ferumbot.specmarket.bots.models.entity.TelegramChat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TelegramChatRepository: JpaRepository<TelegramChat, Long> {
}