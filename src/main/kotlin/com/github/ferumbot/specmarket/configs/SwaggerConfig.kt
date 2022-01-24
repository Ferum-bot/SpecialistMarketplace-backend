package com.github.ferumbot.specmarket.configs

import com.github.ferumbot.specmarket.core.annotations.SwaggerVisible
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    companion object {

        const val PROFESSION_CONTROLLER_DESCRIPTION = """
            API for interaction with professions. Creating and editing professions.
        """

        const val SPECIALIST_CONTROLLER_DESCRIPTION = """
            API for interaction with specialists. Viewing and analysis of specialists
        """

        const val TELEGRAM_CONTROLLER_DESCRIPTION = """
            API for interaction with telegram api. Viewing service users interacting via a telegram bot.
        """

        const val NICHE_CONTROLLER_DESCRIPTION = """
            API for interaction with niches. Viewing, creating and editing niches.
        """
    }

    @Bean
    fun configureApi(): OpenAPI {
        return OpenAPI()
            .info(Info().apply {
                title = "Swagger for SMM marketplace API"
                description = "Allows to manage, create or delete entities from SMM marketplace application"
                contact = Contact().apply {
                    name = "Matvey Popov"
                    url = "https://t.me/ma_popovv"
                    email = "ma.popovv@gmail.com"
                }
                version = "1.0.0"

            })
    }
}