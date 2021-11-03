package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.TelegramBot
import com.github.ferumbot.specmarket.bots.controllers.TelegramController
import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import com.github.ferumbot.specmarket.bots.interactors.impl.BotUpdateToAdapterInteractor
import com.github.ferumbot.specmarket.bots.models.entity.TelegramChat
import com.github.ferumbot.specmarket.bots.services.impl.TelegramUserServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.ContextStartedEvent
import org.springframework.context.event.EventListener
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.generics.Webhook
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

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

    private val logger = LoggerFactory.getLogger(OnTelegramConfig::class.java)

    @Autowired
    private lateinit var interactor: BotUpdateToAdapterInteractor

    @Value("\${bots.telegram.api.path}")
    private lateinit var path: String

    @EventListener
    fun onContextStarted(event: ContextRefreshedEvent) {
        logger.info("Context refreshed")
        initTelegramApi()
        logger.info("Telegram bots started!")
    }

    @Bean
    @Scope("singleton")
    fun provideTelegramBot(): TelegramBot {
        val options = DefaultBotOptions()
        return TelegramBot(options, interactor)
    }

    @Bean
    fun provideWebhook(): SetWebhook {
        return SetWebhook.builder().url(path).build()
    }

    private fun initTelegramApi() {
        val bot = provideTelegramBot()
        val webhook = provideWebhook()

        val telegramBotApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotApi.registerBot(bot, webhook)
    }
}