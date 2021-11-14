package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.CreatingProfileUserInput
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.services.TelegramUserSpecialistService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.*
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
            is OnUserInputFullNameEvent -> processOnUserInputFullName(info as CreatingProfileUserInput)
            is OnUserInputDepartmentEvent -> processOnUserInputDepartment(info as CreatingProfileUserInput)
            is OnUserInputProfessionEvent -> processOnUserInputProfession(info as CreatingProfileUserInput)
            is OnUserInputKeySkillsEvent -> processOnUserInputKeySkills(info as CreatingProfileUserInput)
            is OnUserInputPortfolioLinkEvent -> processOnUserInputPortfolioLink(info as CreatingProfileUserInput)
            is OnUserInputAboutMeEvent -> processOnUserInputAboutMe(info as CreatingProfileUserInput)
            is OnUserInputWorkingConditionsEvent -> processOnUserInputWorkingConditions(info as CreatingProfileUserInput)
            is OnUserInputEducationGradeEvent -> processOnUserInputEducationGrade(info as CreatingProfileUserInput)
            is OnUserInputContactLinksEvent -> processOnUserInputContactLinks(info as CreatingProfileUserInput)
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

    private fun processOnUserInputFullName(info: CreatingProfileUserInput): MessageUpdateResultBunch<*> {
        val state = UserInputDepartmentScreenState
        val fullName = info.userInput.firstOrNull().orEmpty()
        specialistService.updateFullName(info, fullName)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputDepartment(info: CreatingProfileUserInput): MessageUpdateResultBunch<*> {
        val state = UserInputProfessionScreenState
        val department = info.userInput.firstOrNull().orEmpty()
        specialistService.updateDepartment(info, department)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputProfession(info: CreatingProfileUserInput): MessageUpdateResultBunch<*> {
        val state = UserInputKeySkillsScreenState
        val profession = info.userInput.firstOrNull().orEmpty()
        specialistService.addProfession(info, profession)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputKeySkills(info: CreatingProfileUserInput): MessageUpdateResultBunch<*> {
        val state = UserInputPortfolioLinkScreenState
        val keySkills = info.userInput
        specialistService.addKeySkills(info, keySkills)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputPortfolioLink(info: CreatingProfileUserInput): MessageUpdateResultBunch<*> {
        val state = UserInputAboutMeScreenState
        val portfolioLink = info.userInput.firstOrNull().orEmpty()
        specialistService.updatePortfolioLink(info, portfolioLink)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputAboutMe(info: CreatingProfileUserInput): MessageUpdateResultBunch<*> {
        val state = UserInputWorkingConditionsScreenState
        val aboutMe = info.userInput.firstOrNull().orEmpty()
        specialistService.updateAboutMe(info, aboutMe)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputWorkingConditions(info: CreatingProfileUserInput): MessageUpdateResultBunch<*> {
        val state = UserInputEducationGradeScreenState
        val workingConditions = info.userInput.firstOrNull().orEmpty()
        specialistService.updateWorkingConditions(info, workingConditions)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputEducationGrade(info: CreatingProfileUserInput): MessageUpdateResultBunch<*> {
        val state = UserInputContactLinksScreenState
        val educationGrade = info.userInput.firstOrNull().orEmpty()
        specialistService.updateEducationGrade(info, educationGrade)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOnUserInputContactLinks(info: CreatingProfileUserInput): MessageUpdateResultBunch<*> {
        val state = ShowProfilePreviewScreenState
        val contactLinks = info.userInput.firstOrNull().orEmpty()
        specialistService.updateContactLinks(info, contactLinks)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
    }

    private fun processOpenHowProfileLooksNow(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val state = ShowHowProfileLooksNowScreenState
        val specialist = userService.getUserSpecialist(info)
        val resultInfo = UserSpecialistInfo.getFrom(info, specialist!!)
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, resultInfo)
    }

    private fun processFinishRegistrationFlow(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val state = YouAreAuthorizedScreenState
        userService.setNewUserState(state, info)

        return MessageUpdateResultBunch(state, info)
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