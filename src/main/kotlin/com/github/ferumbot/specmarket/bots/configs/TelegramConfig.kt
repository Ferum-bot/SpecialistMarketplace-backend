package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.TelegramBot
import com.github.ferumbot.specmarket.bots.configs.properties.TelegramBotProperties
import com.github.ferumbot.specmarket.bots.controllers.TelegramController
import com.github.ferumbot.specmarket.bots.interactors.impl.BotUpdateToAdapterInteractor
import com.github.ferumbot.specmarket.bots.interceptors.ExceptionInterceptor
import com.github.ferumbot.specmarket.bots.interceptors.ExceptionInterceptorFacade
import com.github.ferumbot.specmarket.bots.interceptors.impl.DebugExceptionInterceptor
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.*
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@Configuration
@ComponentScan(
    basePackageClasses = [
        TelegramController::class,
        AdaptersConfig::class,
        InteractorsConfig::class,
        ProcessorsConfig::class,
        RepositoriesConfig::class,
        ServicesConfig::class,
        UIConfig::class,
        ExceptionInterceptorFacade::class,
    ],
)
class TelegramConfig {

    private val logger = LoggerFactory.getLogger(TelegramConfig::class.java)

    @Autowired
    private lateinit var interactor: BotUpdateToAdapterInteractor

    @Autowired
    private lateinit var exceptionInterceptor: ExceptionInterceptorFacade

    @Autowired
    private lateinit var telegramProperties: TelegramBotProperties

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
        return TelegramBot(options, interactor, exceptionInterceptor, telegramProperties)
    }

    @Bean
    fun provideWebhook(
        telegramProperties: TelegramBotProperties,
    ): SetWebhook {
        return SetWebhook.builder().url(telegramProperties.webhookPath).build()
    }

    private fun initTelegramApi() {
        val bot = provideTelegramBot()
        val webhook = provideWebhook(telegramProperties)

        val telegramBotApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotApi.registerBot(bot, webhook)
    }
}