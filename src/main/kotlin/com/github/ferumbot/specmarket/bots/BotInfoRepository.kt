package com.github.ferumbot.specmarket.bots

import com.github.ferumbot.specmarket.bots.models.TelegramBotUserInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BotInfoRepository: JpaRepository<TelegramBotUserInfo, Long> {
}