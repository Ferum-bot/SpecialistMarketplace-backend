package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.processors.BotUpdateProcessor
import com.github.ferumbot.specmarket.bots.processors.FacadeBotUpdateProcessor
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.processors.local.impl.*
import com.github.ferumbot.specmarket.bots.services.TelegramBotUserService
import com.github.ferumbot.specmarket.bots.services.TelegramUserSpecialistService
import com.github.ferumbot.specmarket.services.ProfessionService
import com.github.ferumbot.specmarket.services.SpecialistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProcessorsConfig {

    @Autowired
    private lateinit var userService: TelegramBotUserService

    @Autowired
    private lateinit var userSpecialistService: TelegramUserSpecialistService

    @Autowired
    private lateinit var professionService: ProfessionService

    @Autowired
    private lateinit var specialistService: SpecialistService

    @Bean
    fun provideFacadeProcessor(): BotUpdateProcessor {
        val processors = mutableListOf(
            provideChatMemberProcessor(),
            provideCommonProcessor(),
            provideAllSpecialistsProcessor(),
            provideIAmCustomerProcessor(),
            provideIAmSpecialistProcessor(),
            provideStartProcessor(),
            provideIDoNotKnowWhatINeedProcessor(),
            provideMyProfileProcessor(),
            provideFilterProcessor(),
            provideCreatingProfileProcessor(),
            provideEditProfileProcessor(),
        )

        /**
         * Always must be in the end.
         */
        processors.add(
            provideUnsupportedProcessor()
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

    @Bean
    fun provideUnsupportedProcessor(): LocalUpdateProcessor {
        return UnSupportedUpdateProcessor()
    }

    @Bean
    fun provideChatMemberProcessor(): LocalUpdateProcessor {
        return ChatMemberUpdateProcessor()
    }

    @Bean
    fun provideMyProfileProcessor(): LocalUpdateProcessor {
        return MyProfileUpdateProcessor(userService, userSpecialistService)
    }

    @Bean
    fun provideCreatingProfileProcessor(): LocalUpdateProcessor {
        return CreatingProfileUpdateProcessor(userService, userSpecialistService, professionService)
    }

    @Bean
    fun provideEditProfileProcessor(): LocalUpdateProcessor {
        return EditProfileUpdateProcessor(userService, userSpecialistService, professionService)
    }

    @Bean
    fun provideFilterProcessor(): LocalUpdateProcessor {
        return FilterUpdateProcessor(professionService, specialistService, userService)
    }

    @Bean
    fun provideIDoNotKnowWhatINeedProcessor(): LocalUpdateProcessor {
        return IDoNotKnowWhatINeedUpdateProcessor(professionService, userService)
    }
}