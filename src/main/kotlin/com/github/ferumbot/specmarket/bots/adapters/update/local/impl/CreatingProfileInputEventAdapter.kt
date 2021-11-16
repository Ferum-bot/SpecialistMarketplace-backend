package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getCommandAlias
import com.github.ferumbot.specmarket.bots.core.getUserId
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUserInputInfo
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.*
import org.telegram.telegrambots.meta.api.objects.Update

class CreatingProfileInputEventAdapter(
    private val userService: TelegramUserService
): LocalUpdateAdapter {

    companion object {

        private val handlingStates = listOf(
            UserInputFullNameScreenState, UserInputDepartmentScreenState,
            UserInputProfessionScreenState, UserInputKeySkillsScreenState,
            UserInputPortfolioLinkScreenState, UserInputAboutMeScreenState,
            UserInputWorkingConditionsScreenState, UserInputEducationGradeScreenState,
            UserInputContactLinksScreenState,
        )
    }

    override fun isFor(update: Update): Boolean {
        val info = BaseUpdateInfo.get(update)
        val currentState = userService.getUserCurrentState(info)
        return currentState.currentState in handlingStates
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val info = BaseUpdateInfo.get(update)
        val currentState = userService.getUserCurrentState(info).currentState as CreatingProfileState

        return when(currentState) {
            is UserInputFullNameScreenState ->
                getSimpleUserInput(info, update, OnUserInputFullNameEvent)
            is UserInputDepartmentScreenState ->
                getSimpleUserInput(info, update, OnUserInputDepartmentEvent)
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

    private fun getSimpleUserInput(
        info: BaseUpdateInfo, update: Update, event: CreatingProfileEvent
    ): MessageUpdateBunch<*> {
        val input = update.getCommandAlias()
        val inputInfo = BaseUserInputInfo.from(info, input)

        return MessageUpdateBunch(event, inputInfo)
    }

    private fun getCollectionUserInput(
        info: BaseUpdateInfo, update: Update, event: CreatingProfileEvent
    ): MessageUpdateBunch<*> {
        val input = update.getCommandAlias().split(',', ignoreCase = true)
        val inputInfo = BaseUserInputInfo.from(info, input)

        return MessageUpdateBunch(event, inputInfo)
    }
}