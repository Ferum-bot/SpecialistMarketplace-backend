package com.github.ferumbot.specmarket.bots.configs.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "bots.telegram.api")
data class TelegramBotProperties(
    var token: String = "",
    var botUserName: String = "",
    var webhookPath: String = "",
)
