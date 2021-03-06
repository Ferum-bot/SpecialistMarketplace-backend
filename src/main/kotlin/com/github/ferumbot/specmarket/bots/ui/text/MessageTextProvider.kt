package com.github.ferumbot.specmarket.bots.ui.text

import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.models.dto.NicheDto
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

    fun provideLeaveBidInfoMessage(): String

    fun provideAboutEachSpecialistMessage(professions: Collection<ProfessionDto>): String

    fun provideIAmSpecialistInfoMessage(): String

    fun provideMyCVInfoMessage(): String

    fun provideAllSpecialistInfoMessage(): String

    fun provideProfessionFilterScreenInfoMessage(professions: Collection<ProfessionDto>): String

    fun provideNicheFilterScreenInfoMessage(niches: Collection<NicheDto>): String

    fun provideCurrentSpecialistsInfoMessage(specialists: Collection<SpecialistDto>): String

    fun provideCurrentSpecialistsEmptyInfoMessage(): String

    fun provideCurrentSpecialistContactsInfoMessage(contacts: String): String

    fun provideYouAreNotAuthorizedInfoMessage(): String

    fun provideYouArePartiallyAuthorizedInfoMessage(specialist: SpecialistDto): String

    fun provideYouAreAuthorizedInfoMessage(specialist: SpecialistDto): String

    fun provideSpecialistRequestInfoMessage(specialists: Collection<SpecialistDto>): String

    fun provideEditProfileInfoMessage(specialist: SpecialistDto): String

    fun provideUserInputInvalidDataMessage(): String

    fun provideUserInputFullNameInfoMessage(): String

    fun provideUserInputProfessionsInfoMessage(availableProfessions: Collection<ProfessionDto>): String

    fun provideUserInputAnotherProfessionsInfoMessage(availableProfessions: Collection<ProfessionDto>): String

    fun provideUserInputNichesInfoMessage(availableNiches: Collection<NicheDto>): String

    fun provideUserInputAnotherNicheInfoMessage(availableNiches: Collection<NicheDto>): String

    fun provideUserInputKeySkillsInfoMessage(): String

    fun provideUserInputPortfolioLinkInfoMessage(): String

    fun provideUserInputAboutMeInfoMessage(): String

    fun provideUserInputWorkingConditionsInfoMessage(): String

    fun provideUserInputEducationGradeInfoMessage(): String

    fun provideUserInputContactLinksInfoMessage(): String

    fun provideHowProfileLooksNowMessage(profile: SpecialistDto): String

    fun provideProfilePreviewMessage(profile: SpecialistDto): String

    fun provideUserChangeFullNameInfoMessage(): String

    fun provideUserChangeProfessionsInfoMessage(professions: Collection<ProfessionDto>): String

    fun provideUserChangeAnotherProfessionsInfoMessage(professions: Collection<ProfessionDto>): String

    fun provideUserChangeNicheInfoMessage(niches: Collection<NicheDto>): String

    fun provideUserChangeAnotherNicheInfoMessage(niches: Collection<NicheDto>): String

    fun provideUserChangeKeySkillsInfoMessage(): String

    fun provideUserChangePortfolioLinkInfoMessage(): String

    fun provideUserChangeAboutMeInfoMessage(): String

    fun provideUserChangeWorkingConditionsInfoMessage(): String

    fun provideUserChangeEducationGradeInfoMessage(): String

    fun provideUserChangeContactLinksInfoMessage(): String
}