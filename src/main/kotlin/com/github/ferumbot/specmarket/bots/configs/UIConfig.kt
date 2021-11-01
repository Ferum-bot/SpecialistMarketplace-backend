package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.ui.DefaultMessageTextProvider
import com.github.ferumbot.specmarket.bots.ui.MessageTextProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UIConfig {

    @Bean
    fun provideMessageTextProvider(): MessageTextProvider {
        return DefaultMessageTextProvider()
    }
}