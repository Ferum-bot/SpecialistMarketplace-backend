package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUserInputInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.services.TelegramUserSpecialistService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.*
import com.github.ferumbot.specmarket.core.extensions.removeFirstCharIf
import com.github.ferumbot.specmarket.models.entities.Specialist

class CreatingProfileUpdateProcessor(
    private val userService: TelegramUserService,
    private val specialistService: TelegramUserSpecialistService
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
            is OnUserInputFullNameEvent -> processOnUserInputFullName(info as BaseUserInputInfo)
            is OnUserInputDepartmentEvent -> processOnUserInputDepartment(info as BaseUserInputInfo)
            is OnUserInputProfessionEvent -> processOnUserInputProfession(info as BaseUserInputInfo)
            is OnUserInputKeySkillsEvent -> processOnUserInputKeySkills(info as BaseUserInputInfo)
            is OnUserInputPortfolioLinkEvent -> processOnUserInputPortfolioLink(info as BaseUserInputInfo)
            is OnUserInputAboutMeEvent -> processOnUserInputAboutMe(info as BaseUserInputInfo)
            is OnUserInputWorkingConditionsEvent -> processOnUserInputWorkingConditions(info as BaseUserInputInfo)
            is OnUserInputEducationGradeEvent -> processOnUserInputEducationGrade(info as BaseUserInputInfo)
            is OnUserInputContactLinksEvent -> processOnUserInputContactLinks(info as BaseUserInputInfo)
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

    private fun processOnUserInputFullName(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val state = UserInputDepartmentScreenState
        val fullName = info.userInput.firstOrNull().orEmpty()
        specialistService.updateFullName(info, fullName)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputDepartment(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val state = UserInputProfessionScreenState
        val department = info.userInput.firstOrNull().orEmpty()
        specialistService.updateDepartment(info, department)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputProfession(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val state = UserInputKeySkillsScreenState
        val profession = info.userInput.firstOrNull().orEmpty()
            .removeFirstCharIf { it.first() == '/' }

        specialistService.addProfession(info, profession)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputKeySkills(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val state = UserInputPortfolioLinkScreenState
        val keySkills = info.userInput
        specialistService.addKeySkills(info, keySkills)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputPortfolioLink(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val state = UserInputAboutMeScreenState
        val portfolioLink = info.userInput.firstOrNull().orEmpty()
        specialistService.updatePortfolioLink(info, portfolioLink)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputAboutMe(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val state = UserInputWorkingConditionsScreenState
        val aboutMe = info.userInput.firstOrNull().orEmpty()
        specialistService.updateAboutMe(info, aboutMe)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputWorkingConditions(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val state = UserInputEducationGradeScreenState
        val workingConditions = info.userInput.firstOrNull().orEmpty()
        specialistService.updateWorkingConditions(info, workingConditions)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputEducationGrade(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val state = UserInputContactLinksScreenState
        val educationGrade = info.userInput.firstOrNull().orEmpty()
        specialistService.updateEducationGrade(info, educationGrade)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputContactLinks(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
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
        specialistService.updateCompletelyFilled(info, true)
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

    private fun Specialist?.getFirstNotImplementedFieldState(): CreatingProfileState {
        return this?.run {
            if (fullName == null) {
                return UserInputFullNameScreenState
            }
            if (department == null) {
                return UserInputDepartmentScreenState
            }
            if (professions.isEmpty()) {
                return UserInputProfessionScreenState
            }
            if (keySkills.isEmpty()) {
                return UserInputKeySkillsScreenState
            }
            if (portfolioLink == null) {
                return UserInputPortfolioLinkScreenState
            }
            if (aboutMe == null) {
                return UserInputAboutMeScreenState
            }
            if (workingConditions == null) {
                return UserInputWorkingConditionsScreenState
            }
            if (educationGrade == null) {
                return UserInputEducationGradeScreenState
            }
            if (contactLinks == null) {
                return UserInputContactLinksScreenState
            }

            UserInputFullNameScreenState
        } ?: UserInputFullNameScreenState
    }
}