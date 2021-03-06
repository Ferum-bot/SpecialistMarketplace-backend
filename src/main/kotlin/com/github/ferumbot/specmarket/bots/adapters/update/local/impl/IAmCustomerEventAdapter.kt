package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.*
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenAboutEachSpecialistScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenIDoNotKnowWhatIWantScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenLeaveBidScreenEvent
import org.telegram.telegrambots.meta.api.objects.Update

class IAmCustomerEventAdapter: LocalUpdateAdapter {

    companion object {

        private val OPEN_I_DO_NOT_KNOW_WHAT_I_WANT_NAME = OpenIDoNotKnowWhatIWantScreenEvent.friendlyName
        private val OPEN_ABOUT_EACH_SPECIALIST_NAME = OpenAboutEachSpecialistScreenEvent.friendlyName
        private val OPEN_ABOUT_EACH_SPECIALIST_COMMAND = OpenAboutEachSpecialistScreenEvent.commandAlias
        private val OPEN_LEAVE_BID_NAME = OpenLeaveBidScreenEvent.friendlyName
        private val OPEN_LEAVE_BID_COMMAND = OpenLeaveBidScreenEvent.commandAlias

        private val handlingEvents = listOf(
            OPEN_I_DO_NOT_KNOW_WHAT_I_WANT_NAME, OPEN_ABOUT_EACH_SPECIALIST_NAME,
            OPEN_LEAVE_BID_NAME, OPEN_LEAVE_BID_COMMAND,
            OPEN_ABOUT_EACH_SPECIALIST_COMMAND,
        )
    }

    override fun isFor(update: Update): Boolean {
       if (!update.isCommand()) {
           return false
       }
        return handlingEvents.contains(update.getCommandAlias())
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val commandName = update.getCommandAlias()

        return when(commandName) {
            OPEN_ABOUT_EACH_SPECIALIST_NAME, OPEN_ABOUT_EACH_SPECIALIST_COMMAND ->
                openAboutEachSpecialist(update)
            OPEN_LEAVE_BID_NAME, OPEN_LEAVE_BID_COMMAND ->
                openLeaveBid(update)
            OPEN_I_DO_NOT_KNOW_WHAT_I_WANT_NAME ->
                openIDoNotKnowWhatIWant(update)
            else -> LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun openIDoNotKnowWhatIWant(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = OpenIDoNotKnowWhatIWantScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.from(chatId, userId))
    }

    private fun openAboutEachSpecialist(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = OpenAboutEachSpecialistScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.from(chatId, userId))
    }

    private fun openLeaveBid(update: Update): MessageUpdateBunch<*> {
        val info = BaseUpdateInfo.from(update)
        val event = OpenLeaveBidScreenEvent

        return MessageUpdateBunch(event, info)
    }
}