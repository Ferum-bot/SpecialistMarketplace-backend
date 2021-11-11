package com.github.ferumbot.specmarket.bots.ui.text

import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.core.wrappers.EmojiWrapper
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

    override fun provideUnSupportedMessage(): String {
        return StringBuilder()
            .append("Un supported command!\n")
            .append("Please use only commands from buttons!\n")
            .append("\n\n")
            .append(EmojiWrapper.getRandomEmojies(4))
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

    override fun provideIDoNotKnowWhoISearchMessage(): String {
        return StringBuilder()
            .append("I don't know who I search screen title\n")
            .append("I don't know who I search screen subtitle\n")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("\n\n")
            .append(EmojiWrapper.getRandomEmojies(3))
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
            .append("I am specialist information screen title\n")
            .append("I am specialist information screen subtitle\n")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("Multiline long long long long long long long long long long long text ")
            .append("\n\n")
            .append(EmojiWrapper.getRandomEmojies(2))
            .toString()
    }

    override fun provideAllSpecialistInfoMessage(): String {
        return StringBuilder()
            .append("This is all available specialists title!\n")
            .append("This is all available specialists subtitle!\n")
            .append("\n")
            .append("Specialist1 -> some information 1\n")
            .append("Specialist2 -> some information 2\n")
            .append("Specialist3 -> some information 3\n")
            .append("Specialist4 -> some information 4\n")
            .append("Specialist5 -> some information 5\n")
            .append("Specialist6 -> some information 6\n")
            .append("\n\n")
            .append(EmojiWrapper.getRandomEmojies(10))
            .toString()
    }

    override fun provideYouAreNotAuthorizedInfoMessage(): String {
        return StringBuilder()
            .append("This is Your profile\n")
            .append("You are not authorized!\n")
            .append("You can watch you requests!\n")
            .toString()
    }

    override fun provideYouArePartiallyAuthorizedInfoMessage(info: UserSpecialistInfo): String {
        return StringBuilder()
            .append("This is Your profile\n")
            .append("You have not completed the authorization process to the end\n")
            .append("You can continue in any moment!\n")
            .append("Now you profile looks like:\n")
            .append(getProfileTemplate(info))
            .append("You can watch you requests!\n")
            .toString()
    }

    override fun provideYouAreAuthorizedInfoMessage(info: UserSpecialistInfo): String {
        return StringBuilder()
            .append("This is Your profile\n")
            .append(getProfileTemplate(info))
            .append("You can watch you requests!\n")
            .toString()
    }

    private fun getProfileTemplate(info: UserSpecialistInfo): StringBuilder {
        return info.run {
            StringBuilder()
                .append("Full Name: ${fullName ?: "-"} \n")
                .append("Department: ${department ?: "-"} \n")
                .append("Activity: ${professions ?: "-"} \n")
                .append("Key skills: ${keySkills.joinToString().ifEmpty { "-" }} \n")
                .append("Portfolio link: ${portfolioLink ?: "-"} \n")
                .append("About me ${aboutMe ?: "-"} \n")
                .append("Working conditions ${workingConditions ?: "-"} \n")
                .append("Education grade ${educationGrade ?: "-"} \n")
                .append("Contact links ${contactLinks ?: "-"} \n")
                .append("\n")
        }
    }
}