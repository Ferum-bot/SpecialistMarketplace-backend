package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.adapters.result.BotUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.adapters.update.BotUpdateAdapter
import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import com.github.ferumbot.specmarket.bots.interactors.impl.BotAdapterToProcessorInteractor
import com.github.ferumbot.specmarket.bots.interactors.impl.BotUpdateToAdapterInteractor
import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.processors.BotUpdateProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

@Configuration
class InteractorsConfig {

    @Autowired
    private lateinit var updateAdapter: BotUpdateAdapter

    @Autowired
    private lateinit var resultAdapter: BotUpdateResultAdapter

    @Autowired
    private lateinit var processor: BotUpdateProcessor

    @Bean
    fun provideUpdateToAdapterInteractor(): BotInteractor<Update, BotApiMethod<*>> {
        return BotUpdateToAdapterInteractor(updateAdapter, resultAdapter)
    }

    @Bean
    fun provideAdapterToProcessorInteractor(): BotInteractor<MessageUpdateBunch<*>, MessageUpdateResultBunch<*>> {
        return BotAdapterToProcessorInteractor(processor)
    }
}