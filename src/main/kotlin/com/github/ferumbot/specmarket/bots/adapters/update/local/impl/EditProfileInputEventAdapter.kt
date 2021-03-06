package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getCommandAlias
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseDataInfo
import com.github.ferumbot.specmarket.bots.services.TelegramBotFlowService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.*
import org.telegram.telegrambots.meta.api.objects.Update

class EditProfileInputEventAdapter(
    private val userService: TelegramBotFlowService
): LocalUpdateAdapter {

    companion object {

        private val FINISH_CHANGING_PROFESSIONS = OnUserFinishedChangingProfessionEvent.commandAlias
        private val FINISH_CHANGING_NICHES = OnUserFinishedChangingNicheEvent.commandAlias

        private val handlingStates = listOf(
            UserChangeFullNameScreenState, UserChangeNicheScreenState,
            UserChangeProfessionScreenState, UserChangeKeySkillsScreenState,
            UserChangePortfolioLinkScreenState, UserChangeAboutMeScreenState,
            UserChangeWorkingConditionsScreenState, UserChangeEducationGradeScreenState,
            UserChangeContactLinksScreenState
        )

        private val handlingEvents = listOf(
            FINISH_CHANGING_PROFESSIONS, FINISH_CHANGING_NICHES
        )
    }

    override fun isFor(update: Update): Boolean {
        val info = BaseUpdateInfo.from(update)
        val state = userService.getUserCurrentState(info)
        val message = update.getCommandAlias()
        return state.currentState in handlingStates || message in handlingEvents
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val info = BaseUpdateInfo.from(update)
        val state = userService.getUserCurrentState(info).currentState
        val message = update.getCommandAlias()

        if (message == FINISH_CHANGING_PROFESSIONS) {
            return getFinishChangingProfessions(info)
        }
        if (message == FINISH_CHANGING_NICHES) {
            return getFinishChangingNiches(info)
        }

        return when(state) {
            is UserChangeFullNameScreenState ->
                getSimpleUserInput(info, update, OnUserChangedFullNameEvent)
            is UserChangeNicheScreenState ->
                getSimpleUserInput(info, update, OnUserChangedNicheEvent)
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

    private fun getFinishChangingProfessions(info: BaseUpdateInfo): MessageUpdateBunch<*> {
        val event = OnUserFinishedChangingProfessionEvent
        return MessageUpdateBunch(event, info)
    }

    private fun getFinishChangingNiches(info: BaseUpdateInfo): MessageUpdateBunch<*> {
        val event = OnUserFinishedChangingNicheEvent
        return MessageUpdateBunch(event, info)
    }

    private fun getSimpleUserInput(
        info: BaseUpdateInfo, update: Update, event: EditProfileEvent
    ): MessageUpdateBunch<*> {
        val input = update.getCommandAlias()
        val inputInfo = BaseDataInfo.from(info, input)
        return MessageUpdateBunch(event, inputInfo)
    }

    private fun getCollectionUserInput(
        info: BaseUpdateInfo, update: Update, event: EditProfileEvent
    ): MessageUpdateBunch<*> {
        val input = update.getCommandAlias().split(',', ignoreCase = true)
        val inputInfo = BaseDataInfo.from(info, input)
        return MessageUpdateBunch(event, inputInfo)
    }
}