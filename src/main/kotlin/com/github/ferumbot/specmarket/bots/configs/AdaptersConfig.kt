package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.adapters.result.BotUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.adapters.result.FacadeResultUpdateAdapter
import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.adapters.result.local.impl.GeneralStateAdapter
import com.github.ferumbot.specmarket.bots.adapters.update.BotUpdateAdapter
import com.github.ferumbot.specmarket.bots.adapters.update.FacadeBotUpdateAdapter
import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.adapters.update.local.impl.CommonEventAdapter
import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import com.github.ferumbot.specmarket.bots.interactors.impl.BotAdapterToProcessorInteractor
import com.github.ferumbot.specmarket.bots.interactors.impl.BotUpdateToAdapterInteractor
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.processors.BotUpdateProcessor
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
class AdaptersConfig @Autowired constructor(
    private val messageTextProvider: MessageTextProvider,
    private val messageInlineButtonsProvider: InlineMessageButtonsProvider,
    private val processor: BotUpdateProcessor,
) {

    @Bean
    fun provideFacadeUpdateAdapter(): BotUpdateAdapter {
        val adapters = listOf(
            provideCommonEventAdapter()
        )
        return FacadeBotUpdateAdapter(provideAdapterToProcessorInteractor(), adapters)
    }

    @Bean
    fun provideFacadeResultUpdateAdapter(): BotUpdateResultAdapter {
        val adapters = listOf(
            provideGeneralStateAdapter()
        )
        return FacadeResultUpdateAdapter(adapters)
    }

    @Bean
    fun provideCommonEventAdapter(): LocalUpdateAdapter {
        return CommonEventAdapter()
    }

    //@Bean
    fun provideAllSpecialistsEventAdapter(): LocalUpdateAdapter {
        TODO()
    }

    //@Bean
    fun provideIAmCustomerEventAdapter(): LocalUpdateAdapter {
        TODO()
    }

    //@Bean
    fun provideIAmSpecialistEventAdapter(): LocalUpdateAdapter {
        TODO()
    }

    //@Bean
    fun provideStartEventAdapter(): LocalUpdateAdapter {
        TODO()
    }

    @Bean
    fun provideGeneralStateAdapter(): LocalUpdateResultAdapter {
        return GeneralStateAdapter(messageTextProvider, messageInlineButtonsProvider)
    }

    @Bean
    fun provideAdapterToProcessorInteractor(): BotAdapterToProcessorInteractor {
        return BotAdapterToProcessorInteractor(processor)
    }
}