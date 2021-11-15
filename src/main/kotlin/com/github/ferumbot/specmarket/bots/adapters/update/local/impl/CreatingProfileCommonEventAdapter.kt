package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getCommandAlias
import com.github.ferumbot.specmarket.bots.core.getUserId
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.ContinueCreatingProfileFlowEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OnUserRegistrationFinishedEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenHowItLooksLikeNowScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.RestartRegistrationFlowEvent
import org.telegram.telegrambots.meta.api.objects.Update

class CreatingProfileCommonEventAdapter: LocalUpdateAdapter {

    companion object {

        private val RESTART_REGISTRATION_NAME = RestartRegistrationFlowEvent.friendlyName
        private val RESTART_REGISTRATION_COMMAND = RestartRegistrationFlowEvent.commandAlias

        private val CONTINUE_CREATING_PROFILE_NAME = ContinueCreatingProfileFlowEvent.friendlyName
        private val CONTINUE_CREATING_PROFILE_COMMAND = ContinueCreatingProfileFlowEvent.commandAlias

        private val REGISTRATION_FINISHED_NAME = OnUserRegistrationFinishedEvent.friendlyName
        private val REGISTRATION_FINISHED_COMMAND = OnUserRegistrationFinishedEvent.commandAlias

        private val OPEN_HOW_IT_LOOKS_LIKE_NOW_NAME = OpenHowItLooksLikeNowScreenEvent.friendlyName
        private val OPEN_HOW_IT_LOOKS_LIKE_NOW_COMMAND = OpenHowItLooksLikeNowScreenEvent.commandAlias

        private val handlingEvents = listOf(
            RESTART_REGISTRATION_NAME, RESTART_REGISTRATION_COMMAND,
            CONTINUE_CREATING_PROFILE_NAME, CONTINUE_CREATING_PROFILE_COMMAND,
            REGISTRATION_FINISHED_NAME, REGISTRATION_FINISHED_COMMAND,
            OPEN_HOW_IT_LOOKS_LIKE_NOW_NAME, OPEN_HOW_IT_LOOKS_LIKE_NOW_COMMAND
        )
    }

    override fun isFor(update: Update): Boolean {
        return update.getCommandAlias() in handlingEvents
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val command = update.getCommandAlias()

        return when(command) {
            RESTART_REGISTRATION_NAME, RESTART_REGISTRATION_COMMAND ->
                restartRegistrationFlow(update)
            CONTINUE_CREATING_PROFILE_NAME, CONTINUE_CREATING_PROFILE_COMMAND ->
                continueCreatingProfileFlow(update)
            REGISTRATION_FINISHED_NAME, REGISTRATION_FINISHED_COMMAND ->
                registrationFlowFinished(update)
            OPEN_HOW_IT_LOOKS_LIKE_NOW_NAME, OPEN_HOW_IT_LOOKS_LIKE_NOW_COMMAND ->
                openHotItLooksNow(update)
            else ->
                LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun restartRegistrationFlow(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = RestartRegistrationFlowEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun continueCreatingProfileFlow(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = ContinueCreatingProfileFlowEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun registrationFlowFinished(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = OnUserRegistrationFinishedEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun openHotItLooksNow(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = OpenHowItLooksLikeNowScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }
}