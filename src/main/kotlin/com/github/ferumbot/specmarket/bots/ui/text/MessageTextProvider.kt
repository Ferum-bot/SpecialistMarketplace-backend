package com.github.ferumbot.specmarket.bots.ui.text

import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.dto.SpecialistDto

interface MessageTextProvider {

    fun provideStartScreenMessage(): String

    fun provideContactWithUsMessage(): String

    fun provideNotImplementedMessage(): String

    fun provideUnSupportedMessage(): String

    fun provideIAmCustomerInfoMessage(): String

    fun provideIDoNotKnowWhatIWantMessage(): String

    fun provideIDoNotKnowWhoISearchMessage(): String

    fun provideAboutEachSpecialistMessage(): String

    fun provideIAmSpecialistInfoMessage(): String

    fun provideAllSpecialistInfoMessage(): String

    fun provideYouAreNotAuthorizedInfoMessage(): String

    fun provideYouArePartiallyAuthorizedInfoMessage(specialist: SpecialistDto): String

    fun provideYouAreAuthorizedInfoMessage(specialist: SpecialistDto): String

    fun provideSpecialistRequestInfoMessage(specialists: Collection<SpecialistDto>): String

    fun provideEditProfileInfoMessage(specialist: SpecialistDto): String

    fun provideUserInputInvalidDataMessage(): String

    fun provideUserInputFullNameInfoMessage(): String

    fun provideUserInputDepartmentInfoMessage(): String

    fun provideUserInputProfessionsInfoMessage(availableProfessions: Collection<ProfessionDto>): String

    fun provideUserInputKeySkillsInfoMessage(): String

    fun provideUserInputPortfolioLinkInfoMessage(): String

    fun provideUserInputAboutMeInfoMessage(): String

    fun provideUserInputWorkingConditionsInfoMessage(): String

    fun provideUserInputEducationGradeInfoMessage(): String

    fun provideUserInputContactLinksInfoMessage(): String

    fun provideHowProfileLooksNowMessage(profile: SpecialistDto): String

    fun provideProfilePreviewMessage(profile: SpecialistDto): String
}