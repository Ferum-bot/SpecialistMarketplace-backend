package com.github.ferumbot.specmarket.configs

import com.github.ferumbot.specmarket.core.annotations.SwaggerVisible
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.RequestHandler
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.oas.annotations.EnableOpenApi
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
@EnableOpenApi
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
    fun configureApi(): Docket = Docket(DocumentationType.OAS_30)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(SwaggerVisible::class.java))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())


    @Bean
    fun apiInfo(): ApiInfo = ApiInfoBuilder()
        .title("Specialists marketplace API")
        .description("The api used to communicate with marketplace application")
        .contact(Contact("Matvey", "https://t-do.ru/dr_matjo", "ghfdhuf85429532@gmail.com"))
        .version("1.0.0")
        .build()
}