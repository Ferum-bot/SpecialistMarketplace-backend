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
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.keyboard_buttons.KeyboardMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import com.github.ferumbot.specmarket.services.ProfessionService
import org.apache.tomcat.jni.Local
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

    @Autowired
    private lateinit var userService: TelegramUserService

    @Autowired
    private lateinit var professionService: ProfessionService

    /**
     *  Incoming event adapters
     */
    @Bean
    fun provideFacadeUpdateAdapter(): BotUpdateAdapter {
        val adapters = mutableListOf(
            provideChatMemberEventAdapter(),
            provideCommonEventAdapter(),
            provideStartEventAdapter(),
            provideAllSpecialistsEventAdapter(),
            provideIAmCustomerEventAdapter(),
            provideIAmSpecialistEventAdapter(),
            provideMyProfileEventAdapter(),
            provideCreatingProfileCommonEventAdapter(),
            provideCreatingProfileInputEventAdapter(),
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
    fun provideMyProfileEventAdapter(): LocalUpdateAdapter {
        return MyProfileEventAdapter()
    }

    @Bean
    fun provideChatMemberEventAdapter(): LocalUpdateAdapter {
        return ChatMemberEventAdapter()
    }

    @Bean
    fun provideUnSupportedEventAdapter(): LocalUpdateAdapter {
        return UnSupportedEventAdapter()
    }

    @Bean
    fun provideCreatingProfileInputEventAdapter(): LocalUpdateAdapter {
        return CreatingProfileInputEventAdapter(userService)
    }

    @Bean
    fun provideCreatingProfileCommonEventAdapter(): LocalUpdateAdapter {
        return CreatingProfileCommonEventAdapter()
    }

    /**
     * Out coming state adapters
     */
    @Bean
    fun provideFacadeResultUpdateAdapter(): BotUpdateResultAdapter {
        val adapters = listOf(
            provideGeneralStateAdapter(),
            provideAllSpecialistStateAdapter(),
            provideIAmCustomerStateAdapter(),
            provideIAmSpecialistStateAdapter(),
            provideNotAvailableStateAdapter(),
            provideMyProfileStateAdapter(),
            provideCreatingProfileStateAdapter(),
        )

        return FacadeResultUpdateAdapter(adapters)
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

    @Bean
    fun provideMyProfileStateAdapter(): LocalUpdateResultAdapter {
        return MyProfileStateAdapter(
            messageTextProvider, messageKeyboardButtonsProvider, messageInlineButtonsProvider
        )
    }

    fun provideCreatingProfileStateAdapter(): LocalUpdateResultAdapter {
        return CreatingProfileStateAdapter(
            professionService, messageTextProvider, messageInlineButtonsProvider, messageKeyboardButtonsProvider
        )
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