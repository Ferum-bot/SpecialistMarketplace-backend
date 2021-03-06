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
            Endpoints для взаимодействия с профессиями.
        """

        const val SPECIALIST_CONTROLLER_DESCRIPTION = """
            Endpoints для взаимодействия со специалистами. 
        """

        const val TELEGRAM_CONTROLLER_DESCRIPTION = """
            Endpoints для взаимодействия с пользователями бота.
        """

        const val NICHE_CONTROLLER_DESCRIPTION = """
            Endpoints для взаимодействия с нишами.
        """

        const val SPECIALIST_STATUS_CONTROLLER_DESCRIPTION = """
            Endpoints для взаимодействия со статусами специалистов.
        """
    }

    @Bean
    fun configureApi(): OpenAPI {
        return OpenAPI()
            .info(Info().apply {
                title = "Swagger для SMM Маркетплейс API"
                description = "Позволяет управлять профилями, профессиями, нишами и телеграмм ботом"
                contact = Contact().apply {
                    name = "Матвей Попов"
                    url = "https://t.me/ma_popovv"
                    email = "ma.popovv@gmail.com"
                }
                version = "1.1.0"
            })
    }
}