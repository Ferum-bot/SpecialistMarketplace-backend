package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.controllers.TelegramController
import com.github.ferumbot.specmarket.bots.models.TelegramBotUserInfo
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextStartedEvent
import org.springframework.context.event.EventListener

@Configuration
@ComponentScan(basePackageClasses = [
    TelegramController::class
])
@EntityScan(basePackageClasses = [
    TelegramBotUserInfo::class,
])
class OnTelegramInitConfig {

    @EventListener
    fun onContextStarted(event: ContextStartedEvent) {

    }

}