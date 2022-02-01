package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseDataInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.ProfessionsInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramBotUserService
import com.github.ferumbot.specmarket.bots.services.TelegramUserSpecialistService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.*
import com.github.ferumbot.specmarket.core.extensions.removeFirstCharIf
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
import com.github.ferumbot.specmarket.services.ProfessionService

class EditProfileUpdateProcessor(
    private val userService: TelegramBotUserService,
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
            is ChangeNicheEvent -> processChangeDepartment(info)
            is ChangeProfessionEvent -> processChangeProfession(info)
            is ChangeKeySkillsEvent -> processChangeKeySkills(info)
            is ChangePortfolioLinkEvent -> processChangePortfolioLink(info)
            is ChangeAboutMeEvent -> processChangeAboutMe(info)
            is ChangeWorkingConditionsEvent -> processChangeWorkingConditions(info)
            is ChangeEducationGradeEvent -> processChangeEducationGrade(info)
            is ChangeContactLinksEvent -> processChangeContactLinks(info)
            is FinishProfileEditingEvent -> processFinishProfileEditing(info)

            is OnUserChangedFullNameEvent -> processUserChangedFullName(info as BaseDataInfo)
            is OnUserChangedNicheEvent -> processUserChangedDepartment(info as BaseDataInfo)
            is OnUserChangedProfessionEvent -> processUserChangedProfession(info as BaseDataInfo)
            is OnUserChangedKeySkillsEvent -> processUserChangedKeySkills(info as BaseDataInfo)
            is OnUserChangedPortfolioLinkEvent -> processUserChangedPortfolioLink(info as BaseDataInfo)
            is OnUserChangedAboutMeEvent -> processUserChangedAboutMe(info as BaseDataInfo)
            is OnUserChangedWorkingConditionsEvent -> processUserChangedWorkingConditions(info as BaseDataInfo)
            is OnUserChangedEducationGradeEvent -> processUserChangedEducationGrade(info as BaseDataInfo)
            is OnUserChangedContactLinksEvent -> processUserChangedContactLinks(info as BaseDataInfo)

            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processChangeFullName(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangeFullNameScreenState
        return setNewStateAndReturn(info, newState)
    }

    private fun processChangeDepartment(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserNicheScreenState
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

    private fun processUserChangedFullName(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val newFullName = info.simpleInput

        val specialist = specialistService.updateFullName(info, newFullName)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedDepartment(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val newDepartment = info.simpleInput

        val specialist = specialistService.addNiche(info, newDepartment)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedProfession(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val newProfession = info.simpleInput
            .removeFirstCharIf { it.first() == '/' }

        specialistService.clearProfessions(info)
        val specialist = specialistService.addProfession(info, newProfession)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedKeySkills(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val newKeySkills = info.userInput

        specialistService.clearKeySkills(info)
        val specialist = specialistService.addKeySkills(info, newKeySkills)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedPortfolioLink(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val newPortfolioLink = info.simpleInput

        val specialist = specialistService.updatePortfolioLink(info, newPortfolioLink)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedAboutMe(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val newAbout = info.simpleInput

        val specialist = specialistService.updateAboutMe(info, newAbout)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedWorkingConditions(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val newWorkingConditions = info.simpleInput

        val specialist = specialistService.updateWorkingConditions(info, newWorkingConditions)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedEducationGrade(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val newEducationGrade = info.simpleInput

        val specialist = specialistService.updateEducationGrade(info, newEducationGrade)
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedContactLinks(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val newContactLinks = info.simpleInput

        val specialist = specialistService.updateContactLinks(info, newContactLinks)
        return setEditStateAndReturn(info, specialist)
    }

    private fun setNewStateAndReturn(info: BaseUpdateInfo, newState: BotState): MessageUpdateResultBunch<*> {
        userService.setNewUserState(newState, info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun setEditStateAndReturn(info: BaseUpdateInfo, specialist: SpecialistProfile): MessageUpdateResultBunch<UserSpecialistInfo> {
        val newState = EditProfileScreenState
        userService.setNewUserState(newState, info)

        val newInfo = UserSpecialistInfo.getFrom(info, specialist)
        return MessageUpdateResultBunch(newState, newInfo)
    }
}