package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateResultBunch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AdaptersConfig {

    @Autowired
    private lateinit var interactor: BotInteractor<MessageUpdateBunch<*>, MessageUpdateResultBunch<*>>

    @Bean
    fun provideFacadeUpdateAdapter() {

    }

    @Bean
    fun provideFacadeResultUpdateAdapter() {

    }

    @Bean
    fun provideCommonEventAdapter() {

    }

    @Bean
    fun provideAllSpecialistsEventAdapter() {

    }

    @Bean
    fun provideIAmCustomerEventAdapter() {

    }

    @Bean
    fun provideIAmSpecialistEventAdapter() {

    }

    @Bean
    fun provideStartEventAdapter() {

    }
}