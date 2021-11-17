package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getCommandAlias
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseInputInfo
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.*
import org.telegram.telegrambots.meta.api.objects.Update

class EditProfileInputEventAdapter(
    private val userService: TelegramUserService
): LocalUpdateAdapter {

    companion object {

        private val handlingStates = listOf(
            UserChangeFullNameScreenState, UserChangeDepartmentScreenState,
            UserChangeProfessionScreenState, UserChangeKeySkillsScreenState,
            UserChangePortfolioLinkScreenState, UserChangeAboutMeScreenState,
            UserChangeWorkingConditionsScreenState, UserChangeEducationGradeScreenState,
            UserChangeContactLinksScreenState
        )
    }

    override fun isFor(update: Update): Boolean {
        val info = BaseUpdateInfo.get(update)
        val state = userService.getUserCurrentState(info)
        return state.currentState in handlingStates
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val info = BaseUpdateInfo.get(update)
        val state = userService.getUserCurrentState(info).currentState

        return when(state) {
            is UserChangeFullNameScreenState ->
                getSimpleUserInput(info, update, OnUserChangedFullNameEvent)
            is UserChangeDepartmentScreenState ->
                getSimpleUserInput(info, update, OnUserChangedDepartmentEvent)
            is UserChangeProfessionScreenState ->
                getSimpleUserInput(info, update, OnUserChangedProfessionEvent)
            is UserChangeKeySkillsScreenState ->
                getCollectionUserInput(info, update, OnUserChangedKeySkillsEvent)
            is UserChangePortfolioLinkScreenState ->
                getSimpleUserInput(info, update, OnUserChangedPortfolioLinkEvent)
            is UserChangeAboutMeScreenState ->
                getSimpleUserInput(info, update, OnUserChangedAboutMeEvent)
            is UserChangeWorkingConditionsScreenState ->
                getSimpleUserInput(info, update, OnUserChangedWorkingConditionsEvent)
            is UserChangeEducationGradeScreenState ->
                getSimpleUserInput(info, update, OnUserChangedEducationGradeEvent)
            is UserChangeContactLinksScreenState ->
                getSimpleUserInput(info, update, OnUserChangedContactLinksEvent)
            else -> LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun getSimpleUserInput(
        info: BaseUpdateInfo, update: Update, event: EditProfileEvent
    ): MessageUpdateBunch<*> {
        val input = update.getCommandAlias()
        val inputInfo = BaseInputInfo.from(info, input)
        return MessageUpdateBunch(event, inputInfo)
    }

    private fun getCollectionUserInput(
        info: BaseUpdateInfo, update: Update, event: EditProfileEvent
    ): MessageUpdateBunch<*> {
        val input = update.getCommandAlias().split(',', ignoreCase = true)
        val inputInfo = BaseInputInfo.from(info, input)
        return MessageUpdateBunch(event, inputInfo)
    }
}