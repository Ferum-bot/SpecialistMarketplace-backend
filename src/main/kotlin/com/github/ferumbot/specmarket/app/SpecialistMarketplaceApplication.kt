package com.github.ferumbot.specmarket.app

import com.github.ferumbot.specmarket.bots.TelegramBot
import com.github.ferumbot.specmarket.bots.configs.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@SpringBootApplication
@ComponentScan(basePackageClasses = [
	OnTelegramConfig::class,
])
class SpecialistMarketplaceApplication

/**
 * If you change file class path change the class path value in
 * Config.kt file.
 * @see buildSrc/src/main/kotlin/Config.kt
 */
fun main(args: Array<String>) {
	val context = runApplication<SpecialistMarketplaceApplication>(*args)

	try {
		initTelegramApi(context)
	} catch (ex: Exception) {
		ex.printStackTrace()
	}
}

private fun initTelegramApi(context: ApplicationContext) {
	val bot = context.getBean(TelegramBot::class.java)
	val webhook = SetWebhook.builder().url("https://ce4c-91-219-188-77.ngrok.io").build()

	val telegramBotApi = TelegramBotsApi(DefaultBotSession::class.java)
	telegramBotApi.registerBot(bot, webhook)
}
