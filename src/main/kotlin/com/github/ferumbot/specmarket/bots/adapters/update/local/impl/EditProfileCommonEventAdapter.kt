package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getCommandAlias
import com.github.ferumbot.specmarket.bots.core.isCommand
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import org.telegram.telegrambots.meta.api.objects.Update

class EditProfileCommonEventAdapter: LocalUpdateAdapter {

    companion object {

        private val CHANGE_FULL_NAME = ChangeFullNameEvent.friendlyName
        private val CHANGE_DEPARTMENT = ChangeDepartmentEvent.friendlyName
        private val CHANGE_PROFESSION = ChangeProfessionEvent.friendlyName
        private val CHANGE_KEY_SKILLS = ChangeKeySkillsEvent.friendlyName
        private val CHANGE_PORTFOLIO_LINK = ChangePortfolioLinkEvent.friendlyName
        private val CHANGE_ABOUT_ME = ChangeAboutMeEvent.friendlyName
        private val CHANGE_WORKING_CONDITIONS = ChangeWorkingConditionsEvent.friendlyName
        private val CHANGE_EDUCATION_GRADE = ChangeEducationGradeEvent.friendlyName
        private val CHANGE_CONTACT_LINKS = ChangeContactLinksEvent.friendlyName

        private val FINISH_PROFILE_EDITING = FinishProfileEditingEvent.friendlyName

        private val handlingEvents = listOf(
            CHANGE_FULL_NAME, CHANGE_DEPARTMENT, CHANGE_PROFESSION,
            CHANGE_KEY_SKILLS, CHANGE_PORTFOLIO_LINK, CHANGE_ABOUT_ME,
            CHANGE_WORKING_CONDITIONS, CHANGE_EDUCATION_GRADE, CHANGE_CONTACT_LINKS,
            FINISH_PROFILE_EDITING
        )
    }

    override fun isFor(update: Update): Boolean {
        if (!update.isCommand()) {
            return false
        }
        return update.getCommandAlias() in handlingEvents
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val command = update.getCommandAlias()

        return when(command) {
            CHANGE_FULL_NAME -> changeFullName(update)
            CHANGE_DEPARTMENT -> changeDepartment(update)
            CHANGE_PROFESSION -> changeProfession(update)
            CHANGE_KEY_SKILLS -> changeKeySkills(update)
            CHANGE_PORTFOLIO_LINK -> changePortfolioLink(update)
            CHANGE_ABOUT_ME -> changeAboutMe(update)
            CHANGE_WORKING_CONDITIONS -> changeWorkingConditions(update)
            CHANGE_EDUCATION_GRADE -> changeEducationGrade(update)
            CHANGE_CONTACT_LINKS -> changeContactLinks(update)
            FINISH_PROFILE_EDITING -> finishProfileEditing(update)
            else -> LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun changeFullName(update: Update): MessageUpdateBunch<*> {
        val event = ChangeFullNameEvent
        val info = BaseUpdateInfo.get(update)

        return MessageUpdateBunch(event, info)
    }

    private fun changeDepartment(update: Update): MessageUpdateBunch<*> {
        val event = ChangeDepartmentEvent
        val info = BaseUpdateInfo.get(update)

        return MessageUpdateBunch(event, info)
    }

    private fun changeProfession(update: Update): MessageUpdateBunch<*> {
        val event = ChangeProfessionEvent
        val info = BaseUpdateInfo.get(update)

        return MessageUpdateBunch(event, info)
    }

    private fun changeKeySkills(update: Update): MessageUpdateBunch<*> {
        val event = ChangeKeySkillsEvent
        val info = BaseUpdateInfo.get(update)

        return MessageUpdateBunch(event, info)
    }

    private fun changePortfolioLink(update: Update): MessageUpdateBunch<*> {
        val event = ChangePortfolioLinkEvent
        val info = BaseUpdateInfo.get(update)

        return MessageUpdateBunch(event, info)
    }

    private fun changeAboutMe(update: Update): MessageUpdateBunch<*> {
        val event = ChangeAboutMeEvent
        val info = BaseUpdateInfo.get(update)

        return MessageUpdateBunch(event, info)
    }

    private fun changeWorkingConditions(update: Update): MessageUpdateBunch<*> {
        val event = ChangeWorkingConditionsEvent
        val info = BaseUpdateInfo.get(update)

        return MessageUpdateBunch(event, info)
    }

    private fun changeEducationGrade(update: Update): MessageUpdateBunch<*> {
        val event = ChangeEducationGradeEvent
        val info = BaseUpdateInfo.get(update)

        return MessageUpdateBunch(event, info)
    }

    private fun changeContactLinks(update: Update): MessageUpdateBunch<*> {
        val event = ChangeContactLinksEvent
        val info = BaseUpdateInfo.get(update)

        return MessageUpdateBunch(event, info)
    }

    private fun finishProfileEditing(update: Update): MessageUpdateBunch<*> {
        val event = FinishProfileEditingEvent
        val info = BaseUpdateInfo.get(update)

        return MessageUpdateBunch(event, info)
    }
}