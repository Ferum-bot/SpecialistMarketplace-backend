package com.github.ferumbot.specmarket.app

import com.github.ferumbot.specmarket.bots.TelegramBot
import com.github.ferumbot.specmarket.bots.configs.*
import com.github.ferumbot.specmarket.bots.repositories.TelegramChatRepository
import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import com.github.ferumbot.specmarket.configs.ControllerConfig
import com.github.ferumbot.specmarket.configs.ServiceConfig
import com.github.ferumbot.specmarket.security.SecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@SpringBootApplication
@ComponentScan(basePackageClasses = [
	OnTelegramConfig::class,
	ControllerConfig::class,
	RepositoriesConfig::class,
	ServiceConfig::class,
	SecurityConfig::class,
])
class SpecialistMarketplaceApplication

/**
 * If you change file class path change the class path value in
 * Config.kt file.
 * @see buildSrc/src/main/kotlin/Config.kt
 */
fun main(args: Array<String>) {
	runApplication<SpecialistMarketplaceApplication>(*args)
}
