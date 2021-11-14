package com.github.ferumbot.specmarket.bots.models.dto.holders

import org.telegram.telegrambots.meta.api.methods.BotApiMethod

data class ApiMethodHolder(
    var apiMethod: BotApiMethod<*>? = null
)
