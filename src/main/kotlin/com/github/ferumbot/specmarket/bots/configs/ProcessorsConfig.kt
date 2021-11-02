package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.processors.BotUpdateProcessor
import com.github.ferumbot.specmarket.bots.processors.FacadeBotUpdateProcessor
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.processors.local.impl.*
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import org.apache.tomcat.jni.Local
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProcessorsConfig {

    @Autowired
    private lateinit var userService: TelegramUserService

    @Bean
    fun provideFacadeProcessor(): BotUpdateProcessor {
        val processors = listOf(
            provideCommonProcessor(),
            provideAllSpecialistsProcessor(),
            provideIAmCustomerProcessor(),
            provideIAmSpecialistProcessor(),
            provideStartProcessor()
        )
        return FacadeBotUpdateProcessor(processors)
    }

    @Bean
    fun provideCommonProcessor(): LocalUpdateProcessor {
        return CommonUpdateProcessor(userService)
    }

    @Bean
    fun provideAllSpecialistsProcessor(): LocalUpdateProcessor {
        return AllSpecialistsUpdateProcessor(userService)
    }

    @Bean
    fun provideIAmCustomerProcessor(): LocalUpdateProcessor {
        return IAmCustomerUpdateProcessor(userService)
    }

    @Bean
    fun provideIAmSpecialistProcessor(): LocalUpdateProcessor {
        return IAmSpecialistUpdateProcessor(userService)
    }

    @Bean
    fun provideStartProcessor(): LocalUpdateProcessor {
        return StartUpdateProcessor(userService)
    }
}