package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getUserId
import com.github.ferumbot.specmarket.bots.core.isText
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenIAmSpecialistScreenEvent
import org.apache.tomcat.jni.Local
import org.telegram.telegrambots.meta.api.objects.Update

class IAmSpecialistEventAdapter: LocalUpdateAdapter {

    companion object {

        private val I_AM_SPECIALIST_INFO_SCREEN_NAME = OpenIAmSpecialistScreenEvent.friendlyName

        private val handlingEvents = listOf(
            I_AM_SPECIALIST_INFO_SCREEN_NAME
        )
    }

    override fun isFor(update: Update): Boolean {
        if (!update.isText()) {
            return false
        }
        return handlingEvents.contains(update.message.text)
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val commandName = update.message.text

        return when(commandName) {
            I_AM_SPECIALIST_INFO_SCREEN_NAME -> openIAmSpecialistInfo(update)
            else -> LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun openIAmSpecialistInfo(update: Update): MessageUpdateBunch<*> {
        val chatId = update.getChatId()
        val userId = update.getUserId()
        val event = OpenIAmSpecialistScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }
}