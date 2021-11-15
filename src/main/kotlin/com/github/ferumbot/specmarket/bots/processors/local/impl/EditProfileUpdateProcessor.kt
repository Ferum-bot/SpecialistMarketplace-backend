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
import com.github.ferumbot.specmarket.core.extensions.firstOrEmpty
import com.github.ferumbot.specmarket.models.entities.Specialist

class EditProfileUpdateProcessor(
    private val userService: TelegramUserService,
    private val specialistService: TelegramUserSpecialistService,
): LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is EditProfileEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent
        val info = bunch.extraInformation

        return when(event) {
            is ChangeFullNameEvent -> processChangeFullName(info)
            is ChangeDepartmentEvent -> processChangeDepartment(info)
            is ChangeProfessionEvent -> processChangeProfession(info)
            is ChangeKeySkillsEvent -> processChangeKeySkills(info)
            is ChangePortfolioLinkEvent -> processChangePortfolioLink(info)
            is ChangeAboutMeEvent -> processChangeAboutMe(info)
            is ChangeWorkingConditionsEvent -> processChangeWorkingConditions(info)
            is ChangeEducationGradeEvent -> processChangeEducationGrade(info)
            is ChangeContactLinksEvent -> processChangeContactLinks(info)
            is FinishProfileEditingEvent -> processFinishProfileEditing(info)

            is OnUserChangedFullNameEvent -> processUserChangedFullName(info as BaseUserInputInfo)
            is OnUserChangedDepartmentEvent -> processUserChangedDepartment(info as BaseUserInputInfo)
            is OnUserChangedProfessionEvent -> processUserChangedProfession(info as BaseUserInputInfo)
            is OnUserChangedKeySkillsEvent -> processUserChangedKeySkills(info as BaseUserInputInfo)
            is OnUserChangedPortfolioLinkEvent -> processUserChangedPortfolioLink(info as BaseUserInputInfo)
            is OnUserChangedAboutMeEvent -> processUserChangedAboutMe(info as BaseUserInputInfo)
            is OnUserChangedWorkingConditionsEvent -> processUserChangedWorkingConditions(info as BaseUserInputInfo)
            is OnUserChangedEducationGradeEvent -> processUserChangedEducationGrade(info as BaseUserInputInfo)
            is OnUserChangedContactLinksEvent -> processUserChangedContactLinks(info as BaseUserInputInfo)

            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processChangeFullName(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangeFullNameScreenState
        return setNewStateAndReturn(info, newState)
    }

    private fun processChangeDepartment(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangeDepartmentScreenState
        return setNewStateAndReturn(info, newState)
    }

    private fun processChangeProfession(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangeProfessionScreenState
        return setNewStateAndReturn(info, newState)
    }

    private fun processChangeKeySkills(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangeKeySkillsScreenState
        return setNewStateAndReturn(info, newState)
    }

    private fun processChangePortfolioLink(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangePortfolioLinkScreenState
        return setNewStateAndReturn(info, newState)
    }

    private fun processChangeAboutMe(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangeAboutMeScreenState
        return setNewStateAndReturn(info, newState)
    }

    private fun processChangeWorkingConditions(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangeWorkingConditionsScreenState
        return setNewStateAndReturn(info, newState)
    }

    private fun processChangeEducationGrade(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangeEducationGradeScreenState
        return setNewStateAndReturn(info, newState)
    }

    private fun processChangeContactLinks(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangeContactLinksScreenState
        return setNewStateAndReturn(info, newState)
    }

    private fun processFinishProfileEditing(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = YouAreAuthorizedScreenState
        userService.setNewUserState(newState, info)

        val specialist = userService.getUserSpecialist(info)
        val newInfo = UserSpecialistInfo.getFrom(info, specialist!!)

        return MessageUpdateResultBunch(newState, newInfo)
    }

    private fun processUserChangedFullName(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val newFullName = info.simpleInput

        val specialist = specialistService.updateFullName(info, newFullName)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedDepartment(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val newDepartment = info.simpleInput

        val specialist = specialistService.updateDepartment(info, newDepartment)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedProfession(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val newProfession = info.simpleInput

        specialistService.clearProfessions(info)
        val specialist = specialistService.addProfession(info, newProfession)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedKeySkills(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val newKeySkills = info.userInput

        specialistService.clearKeySkills(info)
        val specialist = specialistService.addKeySkills(info, newKeySkills)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedPortfolioLink(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val newPortfolioLink = info.simpleInput

        val specialist = specialistService.updatePortfolioLink(info, newPortfolioLink)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedAboutMe(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val newAbout = info.simpleInput

        val specialist = specialistService.updateAboutMe(info, newAbout)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedWorkingConditions(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val newWorkingConditions = info.simpleInput

        val specialist = specialistService.updateWorkingConditions(info, newWorkingConditions)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedEducationGrade(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val newEducationGrade = info.simpleInput

        val specialist = specialistService.updateEducationGrade(info, newEducationGrade)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedContactLinks(info: BaseUserInputInfo): MessageUpdateResultBunch<*> {
        val newContactLinks = info.simpleInput

        val specialist = specialistService.updateContactLinks(info, newContactLinks)
        return setEditStateAndReturn(info, specialist)
    }

    private fun setNewStateAndReturn(info: BaseUpdateInfo, newState: BotState): MessageUpdateResultBunch<*> {
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun setEditStateAndReturn(info: BaseUpdateInfo, specialist: Specialist): MessageUpdateResultBunch<UserSpecialistInfo> {
        val newState = EditProfileScreenState
        userService.setNewUserState(newState, info)

        val newInfo = UserSpecialistInfo.getFrom(info, specialist)
        return MessageUpdateResultBunch(newState, newInfo)
    }
}