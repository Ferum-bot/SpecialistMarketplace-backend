package com.github.ferumbot.specmarket.bots.ui.text

import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.core.wrappers.EmojiWrapper
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import kotlin.text.StringBuilder

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

    override fun provideYouArePartiallyAuthorizedInfoMessage(specialist: SpecialistDto): String {
        return StringBuilder()
            .append("This is Your profile\n")
            .append("You have not completed the authorization process to the end\n")
            .append("You can continue in any moment!\n")
            .append("Now you profile looks like:\n")
            .append(getProfileTemplate(specialist))
            .append("You can watch you requests!\n")
            .toString()
    }

    override fun provideYouAreAuthorizedInfoMessage(specialist: SpecialistDto): String {
        val isVisible = StringBuilder()
        if (specialist.isVisible) {
            isVisible.append("You profile is Visible to customers\n")
        } else {
            isVisible.append("You profile now is NOT Visible to customers\n")
        }

        return StringBuilder()
            .append("This is Your profile\n")
            .append(getProfileTemplate(specialist))
            .append("You can watch you requests!\n")
            .append(isVisible)
            .toString()
    }

    override fun provideSpecialistRequestInfoMessage(specialists: Collection<SpecialistDto>): String {
        val specialistsString = specialists.fold(StringBuilder()) { builder, specialist ->
            builder
                .append(getProfileTemplate(specialist))
                .append("\n")
        }
        return StringBuilder()
            .append("Your specialists requests\n")
            .append(specialistsString)
            .toString()
    }

    override fun provideEditProfileInfoMessage(specialist: SpecialistDto): String {
        return StringBuilder()
            .append("Now you profile looks like this\n")
            .append("You can edit any parameter\n")
            .toString()
    }

    override fun provideUserInputInvalidDataMessage(): String {
        return StringBuilder()
            .append("You input incorrect data!\n")
            .append("Please try again!\n")
            .toString()
    }

    override fun provideUserInputFullNameInfoMessage(): String {
        return StringBuilder()
            .append("Now let's begin with your full name!\n")
            .append("Input your full name\n")
            .append("Example: Bob Bush")
            .toString()
    }

    override fun provideUserInputDepartmentInfoMessage(): String {
        return StringBuilder()
            .append("Now input your department!\n")
            .append("Example: Car business, instagram")
            .toString()
    }

    override fun provideUserInputProfessionsInfoMessage(availableProfessions: Collection<ProfessionDto>): String {
        val professionsInfo = availableProfessions.fold(StringBuilder()) { builder, profession ->
            builder.append("${profession.friendlyName}: ${profession.shortDescription}\n")
        }

        return StringBuilder()
            .append("Now choose you profession!\n")
            .append("Now available ${availableProfessions.count()} professions!\n")
            .append(professionsInfo)
            .append("Choose you profession!\n")
            .toString()
    }

    override fun provideUserInputKeySkillsInfoMessage(): String {
        return StringBuilder()
            .append("Now write you key skills!\n")
            .append("For example: diligence, availability\n")
            .toString()
    }

    override fun provideUserInputPortfolioLinkInfoMessage(): String {
        return StringBuilder()
            .append("Now send link to you portfolio!\n")
            .append("Add your cases, done work and etc")
            .toString()
    }

    override fun provideUserInputAboutMeInfoMessage(): String {
        return StringBuilder()
            .append("Write some information about you\n")
            .append("It is very important to your customers!\n")
            .append("For example: I have been working in this industry for more than three years, made a coverage of half a million")
            .toString()
    }

    override fun provideUserInputWorkingConditionsInfoMessage(): String {
        return StringBuilder()
            .append("Now write your working conditions\n")
            .append("For example: from 500$ + percent")
            .toString()
    }

    override fun provideUserInputEducationGradeInfoMessage(): String {
        return StringBuilder()
            .append("Now write your education grade\n")
            .append("For example: Finished guitar courses")
            .toString()
    }

    override fun provideUserInputContactLinksInfoMessage(): String {
        return StringBuilder()
            .append("Now send your contact links!\n")
            .append("Write as most links as you can\n")
            .append("We will show it to customers\n")
            .append("For example: tg: ..., vk: ...")
            .toString()
    }

    override fun provideHowProfileLooksNowMessage(profile: SpecialistDto): String {
        return StringBuilder()
            .append("Now you profile looks like this:\n")
            .append(getProfileTemplate(profile))
            .append("Continue registration?")
            .toString()
    }

    override fun provideProfilePreviewMessage(profile: SpecialistDto): String {
        return StringBuilder()
            .append("Great! You add all needed information!\n")
            .append("This is your profile!\n")
            .append(getProfileTemplate(profile))
            .append("If you finish registration, you profile will automatically become visible to all customers\n")
            .append("You can change it in any time in your profile")
            .append("You can also edit your profile in any time!\n")
            .toString()
    }

    private fun getProfileTemplate(specialist: SpecialistDto): StringBuilder {
        return specialist.run {
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