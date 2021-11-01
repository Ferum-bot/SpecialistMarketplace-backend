package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.adapters.annotations.CommonAdapterQualifier
import com.github.ferumbot.specmarket.bots.adapters.result.BotUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.adapters.result.local.impl.GeneralStateAdapter
import com.github.ferumbot.specmarket.bots.adapters.update.BotUpdateAdapter
import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.adapters.update.local.impl.CommonEventAdapter
import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AdaptersConfig {

    @Autowired
    private lateinit var interactor: BotInteractor<MessageUpdateBunch<*>, MessageUpdateResultBunch<*>>

    @Autowired
    private lateinit var messageTextProvider: MessageTextProvider

    @Autowired
    private lateinit var messageInlineButtonsProvider: InlineMessageButtonsProvider

    //@Bean
    fun provideFacadeUpdateAdapter(): BotUpdateAdapter {
        TODO()
    }

    //@Bean
    fun provideFacadeResultUpdateAdapter(): BotUpdateResultAdapter {
        TODO()
    }

    @Bean
    @CommonAdapterQualifier
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
}