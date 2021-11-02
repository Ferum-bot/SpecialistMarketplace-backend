package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.TelegramBot
import com.github.ferumbot.specmarket.bots.controllers.TelegramController
import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import com.github.ferumbot.specmarket.bots.interactors.impl.BotUpdateToAdapterInteractor
import com.github.ferumbot.specmarket.bots.models.entity.TelegramChat
import com.github.ferumbot.specmarket.bots.services.impl.TelegramUserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.event.ContextStartedEvent
import org.springframework.context.event.EventListener
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

@Configuration
@ComponentScan(basePackageClasses = [
    TelegramController::class,
    AdaptersConfig::class,
    InteractorsConfig::class,
    ProcessorsConfig::class,
    UIConfig::class,
    RepositoriesConfig::class,
    TelegramUserServiceImpl::class,
])
class OnTelegramConfig {

    @Autowired
    private lateinit var interactor: BotUpdateToAdapterInteractor

    @EventListener
    fun onContextStarted(event: ContextStartedEvent) {
        println(event)
    }

    @Bean
    @Scope("singleton")
    fun provideTelegramBot(): TelegramBot {
        val options = DefaultBotOptions()
        return TelegramBot(options, interactor)
    }

}