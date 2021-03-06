package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.ui.inline_buttons.DefaultInlineButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.keyboard_buttons.DefaultKeyboardButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.keyboard_buttons.KeyboardMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.DefaultMessageTextProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UIConfig {

    @Bean
    fun provideMessageTextProvider(): MessageTextProvider {
        return DefaultMessageTextProvider()
    }

    @Bean
    fun provideKeyboardButtonsProvider(): KeyboardMessageButtonsProvider {
        return DefaultKeyboardButtonsProvider()
    }

    @Bean
    fun provideInlineButtonsProvider(): InlineMessageButtonsProvider {
        return DefaultInlineButtonsProvider()
    }
}