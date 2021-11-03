package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.adapters.result.BotUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.adapters.result.FacadeResultUpdateAdapter
import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.adapters.result.local.impl.*
import com.github.ferumbot.specmarket.bots.adapters.update.BotUpdateAdapter
import com.github.ferumbot.specmarket.bots.adapters.update.FacadeBotUpdateAdapter
import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.adapters.update.local.impl.*
import com.github.ferumbot.specmarket.bots.interactors.impl.BotAdapterToProcessorInteractor
import com.github.ferumbot.specmarket.bots.processors.BotUpdateProcessor
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.keyboard_buttons.KeyboardMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AdaptersConfig @Autowired constructor(
    private val messageTextProvider: MessageTextProvider,
    private val messageKeyboardButtonsProvider: KeyboardMessageButtonsProvider,
    private val messageInlineButtonsProvider: InlineMessageButtonsProvider,
    private val processor: BotUpdateProcessor,
) {

    @Bean
    fun provideFacadeUpdateAdapter(): BotUpdateAdapter {
        val adapters = mutableListOf(
            provideCommonEventAdapter(),
            provideStartEventAdapter(),
            provideAllSpecialistsEventAdapter(),
            provideIAmCustomerEventAdapter(),
            provideIAmSpecialistEventAdapter()
        )

        /**
         * Always must be in the end
         */
        adapters.add(
            provideUnSupportedEventAdapter()
        )

        return FacadeBotUpdateAdapter(provideAdapterToProcessorInteractor(), adapters)
    }

    @Bean
    fun provideFacadeResultUpdateAdapter(): BotUpdateResultAdapter {
        val adapters = listOf(
            provideGeneralStateAdapter(),
            provideAllSpecialistStateAdapter(),
            provideIAmCustomerStateAdapter(),
            provideIAmSpecialistStateAdapter(),
            provideNotAvailableStateAdapter()
        )

        return FacadeResultUpdateAdapter(adapters)
    }

    @Bean
    fun provideCommonEventAdapter(): LocalUpdateAdapter {
        return CommonEventAdapter()
    }

    @Bean
    fun provideAllSpecialistsEventAdapter(): LocalUpdateAdapter {
        return AllSpecialistEventAdapter()
    }

    @Bean
    fun provideIAmCustomerEventAdapter(): LocalUpdateAdapter {
        return IAmCustomerEventAdapter()
    }

    @Bean
    fun provideIAmSpecialistEventAdapter(): LocalUpdateAdapter {
        return IAmSpecialistEventAdapter()
    }

    @Bean
    fun provideStartEventAdapter(): LocalUpdateAdapter {
        return StartEventAdapter()
    }

    @Bean
    fun provideUnSupportedEventAdapter(): LocalUpdateAdapter {
        return UnSupportedEventAdapter()
    }

    @Bean
    fun provideGeneralStateAdapter(): LocalUpdateResultAdapter {
        return GeneralStateAdapter(messageTextProvider, messageKeyboardButtonsProvider)
    }

    @Bean
    fun provideAllSpecialistStateAdapter(): LocalUpdateResultAdapter {
        return AllSpecialistStateAdapter(messageTextProvider, messageKeyboardButtonsProvider, messageInlineButtonsProvider)
    }

    @Bean
    fun provideIAmCustomerStateAdapter(): LocalUpdateResultAdapter {
        return IAmCustomerStateAdapter(messageTextProvider, messageKeyboardButtonsProvider, messageInlineButtonsProvider)
    }

    @Bean
    fun provideIAmSpecialistStateAdapter(): LocalUpdateResultAdapter {
        return IAmSpecialistStateAdapter(messageTextProvider, messageInlineButtonsProvider)
    }

    @Bean
    fun provideNotAvailableStateAdapter(): LocalUpdateResultAdapter {
        return NotAvailableStateAdapter(messageTextProvider, messageInlineButtonsProvider)
    }

    /**
     * Clearly not the right place for this bean.
     * TODO() Move lately
     */
    @Bean
    fun provideAdapterToProcessorInteractor(): BotAdapterToProcessorInteractor {
        return BotAdapterToProcessorInteractor(processor)
    }
}