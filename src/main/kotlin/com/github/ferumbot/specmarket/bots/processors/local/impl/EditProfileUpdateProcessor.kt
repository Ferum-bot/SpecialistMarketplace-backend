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

class EditProfileUpdateProcessor(
    private val userService: TelegramBotUserService,
    private val specialistService: TelegramUserSpecialistService,
    private val professionService: ProfessionService,
    private val nichesService: NicheService,
): LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is EditProfileEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent
        val info = bunch.extraInformation

        return when(event) {
            is ChangeFullNameEvent -> processChangeFullName(info)
            is ChangeNicheEvent -> processChangeNiche(info)
            is ChangeProfessionEvent -> processChangeProfession(info)
            is ChangeKeySkillsEvent -> processChangeKeySkills(info)
            is ChangePortfolioLinkEvent -> processChangePortfolioLink(info)
            is ChangeAboutMeEvent -> processChangeAboutMe(info)
            is ChangeWorkingConditionsEvent -> processChangeWorkingConditions(info)
            is ChangeEducationGradeEvent -> processChangeEducationGrade(info)
            is ChangeContactLinksEvent -> processChangeContactLinks(info)
            is FinishProfileEditingEvent -> processFinishProfileEditing(info)

            is OnUserChangedFullNameEvent -> processUserChangedFullName(info as BaseDataInfo)
            is OnUserChangedProfessionEvent -> processUserChangedProfession(info as BaseDataInfo)
            is OnUserFinishedChangingProfessionEvent -> processUserFinishChangingProfessions(info)
            is OnUserChangedNicheEvent -> processUserChangedNiche(info as BaseDataInfo)
            is OnUserFinishedChangingNicheEvent -> processUserFinishChangingNiches(info)
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

    private fun processChangeProfession(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangeProfessionScreenState
        userService.setNewUserState(newState, info)
        specialistService.clearProfessions(info)

        val professions = professionService.getAllAvailableProfessions()
            .map { ProfessionDto.from(it) }
        val newInfo = ProfessionsInfo.from(info, professions)

        return MessageUpdateResultBunch(newState, newInfo)
    }

    private fun processChangeNiche(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = UserChangeNicheScreenState
        userService.setNewUserState(newState, info)
        specialistService.clearNiches(info)

        val niches = nichesService.getAllAvailableNiches()
            .map { NicheDto.from(it) }
        val newInfo = NichesInfo.from(info, niches)

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

    private fun processUserChangedProfession(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val newProfession = info.simpleInput
            .removeFirstCharIf { it.first() == '/' }

        val specialist = specialistService.addProfession(info, newProfession)
        val allProfessions = professionService.getAllAvailableProfessions()
        val availableProfessions = allProfessions.filter { profession ->
            specialist.professions.count { it.id == profession.id } == 0
        }.map { ProfessionDto.from(it) }

        val newState = UserChangeProfessionScreenState
        val newInfo = ProfessionsInfo.from(info, availableProfessions, false)

        return MessageUpdateResultBunch(newState, newInfo)
    }

    private fun processUserFinishChangingProfessions(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val specialist = userService.getUserSpecialist(info)!!
        return setEditStateAndReturn(info, specialist)
    }

    private fun processUserChangedNiche(info: BaseDataInfo): MessageUpdateResultBunch<*> {
        val newNiche = info.simpleInput
            .removeFirstCharIf { it.first() == '/' }

        val specialist = specialistService.addNiche(info, newNiche)
        val allNiches = nichesService.getAllAvailableNiches()
        val availableNiches = allNiches.filter { niche ->
            specialist.niches.count { it.id == niche.id } == 0
        }.map { NicheDto.from(it) }

        val newState = UserChangeNicheScreenState
        val newInfo = NichesInfo.from(info, availableNiches, false)

        return MessageUpdateResultBunch(newState, newInfo)
    }

    private fun processUserFinishChangingNiches(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val specialist = userService.getUserSpecialist(info)!!
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