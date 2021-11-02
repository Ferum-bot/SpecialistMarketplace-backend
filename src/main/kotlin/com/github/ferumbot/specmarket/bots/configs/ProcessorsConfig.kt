package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.processors.BotUpdateProcessor
import com.github.ferumbot.specmarket.bots.processors.FacadeBotUpdateProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProcessorsConfig {

    @Bean
    fun provideFacadeProcessor(): BotUpdateProcessor {
        return FacadeBotUpdateProcessor(emptyList())
    }
}