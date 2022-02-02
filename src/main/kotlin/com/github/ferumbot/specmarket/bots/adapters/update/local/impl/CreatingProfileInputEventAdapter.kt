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

class CreatingProfileInputEventAdapter(
    private val userService: TelegramBotFlowService
): LocalUpdateAdapter {

    companion object {

        private val FINISH_INPUT_PROFESSION = OnUserFinishInputProfessionEvent.commandAlias
        private val FINISH_INPUT_NICHE = OnUserFinishInputNicheEvent.commandAlias

        private val handlingStates = listOf(
            UserInputFullNameScreenState, UserInputNicheScreenState,
            UserInputProfessionScreenState, UserInputKeySkillsScreenState,
            UserInputPortfolioLinkScreenState, UserInputAboutMeScreenState,
            UserInputWorkingConditionsScreenState, UserInputEducationGradeScreenState,
            UserInputContactLinksScreenState,
        )

        private val handlingEvents = listOf(
            FINISH_INPUT_PROFESSION, FINISH_INPUT_NICHE
        )
    }

    override fun isFor(update: Update): Boolean {
        val info = BaseUpdateInfo.from(update)
        val currentState = userService.getUserCurrentState(info)
        val message = update.getCommandAlias()
        return currentState.currentState in handlingStates || message in handlingEvents
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val info = BaseUpdateInfo.from(update)
        val currentState = userService.getUserCurrentState(info).currentState as CreatingProfileState
        val message = update.getCommandAlias()

        if (message == FINISH_INPUT_PROFESSION) {
            return getFinishInputProfession(info)
        }
        if (message == FINISH_INPUT_NICHE) {
            return getFinishInputNiche(info)
        }

        return when(currentState) {
            is UserInputFullNameScreenState ->
                getSimpleUserInput(info, update, OnUserInputFullNameEvent)
            is UserInputNicheScreenState ->
                getSimpleUserInput(info, update, OnUserInputNicheEvent)
            is UserInputProfessionScreenState ->
                getSimpleUserInput(info, update, OnUserInputProfessionEvent)
            is UserInputKeySkillsScreenState ->
                getCollectionUserInput(info, update, OnUserInputKeySkillsEvent)
            is UserInputPortfolioLinkScreenState ->
                getSimpleUserInput(info, update, OnUserInputPortfolioLinkEvent)
            is UserInputAboutMeScreenState ->
                getSimpleUserInput(info, update, OnUserInputAboutMeEvent)
            is UserInputWorkingConditionsScreenState ->
                getSimpleUserInput(info, update, OnUserInputWorkingConditionsEvent)
            is UserInputEducationGradeScreenState ->
                getSimpleUserInput(info, update, OnUserInputEducationGradeEvent)
            is UserInputContactLinksScreenState ->
                getSimpleUserInput(info, update, OnUserInputContactLinksEvent)
            else ->
                LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun getFinishInputProfession(info: BaseUpdateInfo): MessageUpdateBunch<*> {
        val event = OnUserFinishInputProfessionEvent
        return MessageUpdateBunch(event, info)
    }

    private fun getFinishInputNiche(info: BaseUpdateInfo): MessageUpdateBunch<*> {
        val event = OnUserFinishInputNicheEvent
        return MessageUpdateBunch(event, info)
    }

    private fun getSimpleUserInput(
        info: BaseUpdateInfo, update: Update, event: CreatingProfileEvent
    ): MessageUpdateBunch<*> {
        val input = update.getCommandAlias()
        val inputInfo = BaseDataInfo.from(info, input)

        return MessageUpdateBunch(event, inputInfo)
    }

    private fun getCollectionUserInput(
        info: BaseUpdateInfo, update: Update, event: CreatingProfileEvent
    ): MessageUpdateBunch<*> {
        val input = update.getCommandAlias().split(',', ignoreCase = true)
        val inputInfo = BaseDataInfo.from(info, input)

        return MessageUpdateBunch(event, inputInfo)
    }
}