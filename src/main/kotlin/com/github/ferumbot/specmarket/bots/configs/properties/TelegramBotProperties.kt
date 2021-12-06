package com.github.ferumbot.specmarket.bots.configs.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "bots.telegram.api")
data class TelegramBotProperties(
    val token: String,
    val botUserName: String,
    val webhookPath: String,
)
