package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseInputInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.ProfessionsInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.services.TelegramUserSpecialistService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.*
import com.github.ferumbot.specmarket.core.extensions.removeFirstCharIf
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.entities.Specialist
import com.github.ferumbot.specmarket.services.ProfessionService

class EditProfileUpdateProcessor(
    private val userService: TelegramUserService,
    private val specialistService: TelegramUserSpecialistService,
    private val professionService: ProfessionService,
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

            is OnUserChangedFullNameEvent -> processUserChangedFullName(info as BaseInputInfo)
            is OnUserChangedDepartmentEvent -> processUserChangedDepartment(info as BaseInputInfo)
            is OnUserChangedProfessionEvent -> processUserChangedProfession(info as BaseInputInfo)
            is OnUserChangedKeySkillsEvent -> processUserChangedKeySkills(info as BaseInputInfo)
            is OnUserChangedPortfolioLinkEvent -> processUserChangedPortfolioLink(info as BaseInputInfo)
            is OnUserChangedAboutMeEvent -> processUserChangedAboutMe(info as BaseInputInfo)
            is OnUserChangedWorkingConditionsEvent -> processUserChangedWorkingConditions(info as BaseInputInfo)
            is OnUserChangedEducationGradeEvent -> processUserChangedEducationGrade(info as BaseInputInfo)
            is OnUserChangedContactLinksEvent -> processUserChangedContactLinks(info as BaseInputInfo)

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
        userService.setNewUserState(newState, info)

        val professions = professionService.getAllAvailableProfessions()
            .map { ProfessionDto.from(it) }
        val newInfo = ProfessionsInfo.from(info, professions)

        return MessageUpdateResultBunch(newState, newInfo)
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

    private fun processUserChangedFullName(info: BaseInputInfo): MessageUpdateResultBunch<*> {
        val newFullName = info.simpleInput

        val specialist = specialistService.updateFullName(info, newFullName)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedDepartment(info: BaseInputInfo): MessageUpdateResultBunch<*> {
        val newDepartment = info.simpleInput

        val specialist = specialistService.updateDepartment(info, newDepartment)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedProfession(info: BaseInputInfo): MessageUpdateResultBunch<*> {
        val newProfession = info.simpleInput
            .removeFirstCharIf { it.first() == '/' }

        specialistService.clearProfessions(info)
        val specialist = specialistService.addProfession(info, newProfession)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedKeySkills(info: BaseInputInfo): MessageUpdateResultBunch<*> {
        val newKeySkills = info.userInput

        specialistService.clearKeySkills(info)
        val specialist = specialistService.addKeySkills(info, newKeySkills)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedPortfolioLink(info: BaseInputInfo): MessageUpdateResultBunch<*> {
        val newPortfolioLink = info.simpleInput

        val specialist = specialistService.updatePortfolioLink(info, newPortfolioLink)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedAboutMe(info: BaseInputInfo): MessageUpdateResultBunch<*> {
        val newAbout = info.simpleInput

        val specialist = specialistService.updateAboutMe(info, newAbout)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedWorkingConditions(info: BaseInputInfo): MessageUpdateResultBunch<*> {
        val newWorkingConditions = info.simpleInput

        val specialist = specialistService.updateWorkingConditions(info, newWorkingConditions)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedEducationGrade(info: BaseInputInfo): MessageUpdateResultBunch<*> {
        val newEducationGrade = info.simpleInput

        val specialist = specialistService.updateEducationGrade(info, newEducationGrade)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedContactLinks(info: BaseInputInfo): MessageUpdateResultBunch<*> {
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