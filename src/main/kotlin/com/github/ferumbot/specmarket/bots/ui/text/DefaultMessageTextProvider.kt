package com.github.ferumbot.specmarket.bots.ui.text

import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import com.github.ferumbot.specmarket.core.wrappers.EmojiWrapper
import com.vdurmont.emoji.EmojiManager
import java.lang.StringBuilder

class DefaultMessageTextProvider: MessageTextProvider {

    override fun provideStartScreenMessage(): String {
        return StringBuilder()
            .append("Start Screen message Title\n")
            .append("Start Screen message Subtitle\n")
            .append("\n\n")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("\n\n")
            .append(EmojiWrapper.getRandomEmojies(5))
            .toString()
    }

    override fun provideContactWithUsMessage(): String {
        return StringBuilder()
            .append("Contact With us Screen message Title\n")
            .append("Contact With us Screen Subtitle\n")
            .append("\n\n")
            .append("Created and powered by @dr_matjo! ")
            .append("Created and powered by @dr_matjo! ")
            .append("Created and powered by @dr_matjo! ")
            .append("Created and powered by @dr_matjo! ")
            .append("Created and powered by @dr_matjo! ")
            .append("\n\n")
            .append(EmojiWrapper.getRandomEmojies(10))
            .toString()
    }

    override fun provideNotImplementedMessage(): String {
        return StringBuilder()
            .append("This screen is not implemented yet!\n")
            .append("Please try later!\n")
            .append("\n\n")
            .append(EmojiWrapper.getRandomEmojies(2))
            .toString()
    }

    override fun provideIAmCustomerInfoMessage(): String {
        return StringBuilder()
            .append("I am Customer information screen title\n")
            .append("I am Customer information subtitle\n")
            .append("\n\n")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("\n\n")
            .append(EmojiWrapper.getRandomEmojies(3))
            .toString()
    }

    override fun provideIDoNotKnowWhatIWantMessage(): String {
        return StringBuilder()
            .append("I don't know what I want screen title\n")
            .append("I don't know what I want screen subtitle\n")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("\n\n")
            .append(EmojiWrapper.getRandomEmojies(5))
            .toString()

    }

    override fun provideAboutEachSpecialistMessage(): String {
        return StringBuilder()
            .append("About each specialist screen title\n")
            .append("About each specialist screen subtitle\n")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("\n\n")
            .append(EmojiWrapper.getRandomEmojies(3))
            .toString()
    }

    override fun provideIAmSpecialistInfoMessage(): String {
        return StringBuilder()
            .append("I am specialist information screen title")
            .append("I am specialist information screen subtitle")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("\n\n")
            .append(EmojiWrapper.getRandomEmojies(2))
            .toString()
    }
}