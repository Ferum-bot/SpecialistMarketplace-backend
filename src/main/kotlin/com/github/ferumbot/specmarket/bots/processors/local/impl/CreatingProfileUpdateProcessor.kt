package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.*
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramBotUserService
import com.github.ferumbot.specmarket.bots.services.TelegramUserSpecialistService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.*
import com.github.ferumbot.specmarket.core.extensions.removeFirstCharIf
import com.github.ferumbot.specmarket.models.dto.NicheDto
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
import com.github.ferumbot.specmarket.services.NicheService
import com.github.ferumbot.specmarket.services.ProfessionService

class CreatingProfileUpdateProcessor(
    private val userService: TelegramBotUserService,
    private val specialistService: TelegramUserSpecialistService,
    private val professionsService: ProfessionService,
    private val nicheService: NicheService,
): LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is CreatingProfileEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent as CreatingProfileEvent
        val info = bunch.extraInformation

        return when(event) {
            is StartRegistrationFlowEvent -> processStartRegistrationFlow(info)
            is ContinueCreatingProfileFlowEvent -> processContinueRegistrationFlow(info)
            is OnUserInputFullNameEvent -> processOnUserInputFullName(info as BaseDataInfo)
            is OnUserInputProfessionEvent -> processOnUserInputProfession(info as BaseDataInfo)
            is OnUserFinishInputProfessionEvent -> processOnUserFinishInputProfession(info)
            is OnUserInputNicheEvent -> processOnUserInputNiche(info as BaseDataInfo)
            is OnUserFinishInputNicheEvent -> processOnUserFinishInputNiche(info)
            is OnUserInputKeySkillsEvent -> processOnUserInputKeySkills(info as BaseDataInfo)
            is OnUserInputPortfolioLinkEvent -> processOnUserInputPortfolioLink(info as BaseDataInfo)
            is OnUserInputAboutMeEvent -> processOnUserInputAboutMe(info as BaseDataInfo)
            is OnUserInputWorkingConditionsEvent -> processOnUserInputWorkingConditions(info as BaseDataInfo)
            is OnUserInputEducationGradeEvent -> processOnUserInputEducationGrade(info as BaseDataInfo)
            is OnUserInputContactLinksEvent -> processOnUserInputContactLinks(info as BaseDataInfo)
            is OpenHowItLooksLikeNowScreenEvent -> processOpenHowProfileLooksNow(info)
            is OnUserRegistrationFinishedEvent -> processFinishRegistrationFlow(info)
            is RestartRegistrationFlowEvent -> processRestartRegistrationFlow(info)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processStartRegistrationFlow(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val state = UserInputFullNameScreenState
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processContinueRegistrationFlow(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val specialist = userService.getUserSpecialist(info)
        val state = specialist.getFirstNotImplementedFieldState()
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputFullName(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val state = UserInputProfessionScreenState
        val fullName = info.userInput.firstOrNull().orEmpty()
        specialistService.updateFullName(info, fullName)
        userService.setNewUserState(state, info)

        val professions = professionsService.getAllAvailableProfessions()
            .map { ProfessionDto.from(it) }
        val newInfo = ProfessionsInfo.from(info, professions)

        return MessageUpdateResultBunch(state, newInfo)
    }

    private fun processOnUserInputProfession(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val state = UserInputProfessionScreenState
        val chosenProfession = info.userInput.firstOrNull().orEmpty()
            .removeFirstCharIf { it.first() == '/' }

        val specialist = specialistService.addProfession(info, chosenProfession)
        userService.setNewUserState(state, info)

        val allProfessions = professionsService.getAllAvailableProfessions()
        val chosenProfessions = specialist.professions
        val availableProfessions = allProfessions.filter { profession ->
            chosenProfessions.count { it.id == profession.id } == 0
        }.map { ProfessionDto.from(it) }

        val newInfo = ProfessionsInfo.from(info, availableProfessions, false)

        return MessageUpdateResultBunch(state, newInfo)
    }

    private fun processOnUserFinishInputProfession(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val state = UserInputNicheScreenState
        val niches = nicheService.getAllAvailableNiches()
            .map { NicheDto.from(it) }

        userService.setNewUserState(state, info)

        val newInfo = NichesInfo.from(info, niches)
        return MessageUpdateResultBunch(state, newInfo)
    }

    private fun processOnUserInputNiche(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val state = UserInputNicheScreenState
        val chosenNiche = info.userInput.firstOrNull().orEmpty()
            .removeFirstCharIf { it.first() == '/' }

        val specialist = specialistService.addNiche(info, chosenNiche)
        userService.setNewUserState(state, info)

        val allNiches = nicheService.getAllAvailableNiches()
        val chosenNiches = specialist.niches
        val availableNiches = allNiches.filter { niche ->
            chosenNiches.count { it.id == niche.id } == 0
        }.map { NicheDto.from(it) }

        val newInfo = NichesInfo.from(info, availableNiches, false)

        return MessageUpdateResultBunch(state, newInfo)
    }

    private fun processOnUserFinishInputNiche(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val state = UserInputKeySkillsScreenState
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputKeySkills(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val state = UserInputPortfolioLinkScreenState
        val keySkills = info.userInput
        specialistService.addKeySkills(info, keySkills)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputPortfolioLink(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val state = UserInputAboutMeScreenState
        val portfolioLink = info.userInput.firstOrNull().orEmpty()
        specialistService.updatePortfolioLink(info, portfolioLink)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputAboutMe(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val state = UserInputWorkingConditionsScreenState
        val aboutMe = info.userInput.firstOrNull().orEmpty()
        specialistService.updateAboutMe(info, aboutMe)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputWorkingConditions(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val state = UserInputEducationGradeScreenState
        val workingConditions = info.userInput.firstOrNull().orEmpty()
        specialistService.updateWorkingConditions(info, workingConditions)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputEducationGrade(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val state = UserInputContactLinksScreenState
        val educationGrade = info.userInput.firstOrNull().orEmpty()
        specialistService.updateEducationGrade(info, educationGrade)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputContactLinks(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val state = ShowProfilePreviewScreenState
        val contactLinks = info.userInput.firstOrNull().orEmpty()
        specialistService.updateContactLinks(info, contactLinks)
        userService.setNewUserState(state, info)

        val specialist = userService.getUserSpecialist(info)
        val newInfo = UserSpecialistInfo.getFrom(info, specialist!!)

        return MessageUpdateResultBunch(state, newInfo)
    }

    private fun processOpenHowProfileLooksNow(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val state = ShowHowProfileLooksNowScreenState
        val specialist = userService.getUserSpecialist(info)
        userService.setNewUserState(state, info)

        val resultInfo = UserSpecialistInfo.getFrom(info, specialist!!)

        return MessageUpdateResultBunch(state, resultInfo)
    }

    private fun processFinishRegistrationFlow(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val state = YouAreAuthorizedScreenState
        userService.setNewUserState(state, info)

        val specialist = userService.getUserSpecialist(info)
        val resultInfo = UserSpecialistInfo.getFrom(info, specialist!!)

        return MessageUpdateResultBunch(state, resultInfo)
    }

    private fun processRestartRegistrationFlow(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val state = UserInputFullNameScreenState
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun SpecialistProfile?.getFirstNotImplementedFieldState(): CreatingProfileState {
        return this?.run {
            if (fullName.isNullOrBlank()) {
                return UserInputFullNameScreenState
            }
            if (niches.isEmpty()) {
                return UserInputNicheScreenState
            }
            if (professions.isEmpty()) {
                return UserInputProfessionScreenState
            }
            if (keySkills.isEmpty()) {
                return UserInputKeySkillsScreenState
            }
            if (portfolioLink.isNullOrBlank()) {
                return UserInputPortfolioLinkScreenState
            }
            if (aboutMe.isNullOrBlank()) {
                return UserInputAboutMeScreenState
            }
            if (workingConditions.isNullOrBlank()) {
                return UserInputWorkingConditionsScreenState
            }
            if (educationGrade.isNullOrBlank()) {
                return UserInputEducationGradeScreenState
            }
            if (contactLinks.isNullOrBlank()) {
                return UserInputContactLinksScreenState
            }

            UserInputFullNameScreenState
        } ?: UserInputFullNameScreenState
    }
}