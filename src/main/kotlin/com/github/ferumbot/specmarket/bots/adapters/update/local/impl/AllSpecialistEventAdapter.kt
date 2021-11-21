package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.*
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenFilterScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenIDoNotKnowWhoISearchScreenEvent
import org.telegram.telegrambots.meta.api.objects.Update

class AllSpecialistEventAdapter: LocalUpdateAdapter {

    companion object {

        private val OPEN_FILTER_SCREEN_NAME = OpenFilterScreenEvent.friendlyName
        private val OPEN_I_DO_NOT_KNOW_WHO_I_SEARCH_NAME = OpenIDoNotKnowWhoISearchScreenEvent.friendlyName

        private val handlingEvents = listOf(
            OPEN_FILTER_SCREEN_NAME, OPEN_I_DO_NOT_KNOW_WHO_I_SEARCH_NAME
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
            OPEN_FILTER_SCREEN_NAME -> openFilter(update)
            OPEN_I_DO_NOT_KNOW_WHO_I_SEARCH_NAME -> openIDoNotKnowWhoISearch(update)
            else -> LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun openFilter(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = OpenFilterScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.from(chatId, userId))
    }

    private fun openIDoNotKnowWhoISearch(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = OpenIDoNotKnowWhoISearchScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.from(chatId, userId))
    }
}