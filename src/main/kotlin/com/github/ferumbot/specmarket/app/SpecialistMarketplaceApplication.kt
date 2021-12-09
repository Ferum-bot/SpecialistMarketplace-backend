package com.github.ferumbot.specmarket.app

import com.github.ferumbot.specmarket.bots.configs.*
import com.github.ferumbot.specmarket.bots.configs.properties.TelegramBotProperties
import com.github.ferumbot.specmarket.configs.*
import com.github.ferumbot.specmarket.configs.properties.PostgresProperties
import com.github.ferumbot.specmarket.security.SecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(
	basePackageClasses = [
		OnTelegramConfig::class,
		ControllerConfig::class,
		RepositoryConfig::class,
		ServiceConfig::class,
		SecurityConfig::class,
		SwaggerConfig::class,
		DataSourceConfig::class,
	],
	basePackages = [
		"com.github.ferumbot.specmarket"
	],
)
@ConfigurationPropertiesScan(
	basePackageClasses = [
		TelegramBotProperties::class,
		PostgresProperties::class,
	],
	basePackages = [
		"com.github.ferumbot.specmarket.configs.properties",
		"com.github.ferumbot.specmarket.bots.configs.properties",
	]
)
class SpecialistMarketplaceApplication

/**
 * If you change file class path change the class path value in
 * Config.kt file.
 * @see buildSrc/src/main/kotlin/Config.kt
 */
fun main(args: Array<String>) {
	runApplication<SpecialistMarketplaceApplication>(*args)
}
