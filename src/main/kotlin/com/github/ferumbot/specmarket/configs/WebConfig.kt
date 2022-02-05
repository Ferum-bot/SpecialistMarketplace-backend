package com.github.ferumbot.specmarket.configs

import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatusConverter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {

    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(ProfileStatusConverter())
    }
}