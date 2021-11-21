package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.*
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenIAmSpecialistScreenEvent
import org.telegram.telegrambots.meta.api.objects.Update

class IAmSpecialistEventAdapter: LocalUpdateAdapter {

    companion object {

        private val I_AM_SPECIALIST_INFO_SCREEN_NAME = OpenIAmSpecialistScreenEvent.friendlyName

        private val handlingEvents = listOf(
            I_AM_SPECIALIST_INFO_SCREEN_NAME
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
            I_AM_SPECIALIST_INFO_SCREEN_NAME -> openIAmSpecialistInfo(update)
            else -> LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun openIAmSpecialistInfo(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = OpenIAmSpecialistScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.from(chatId, userId))
    }
}