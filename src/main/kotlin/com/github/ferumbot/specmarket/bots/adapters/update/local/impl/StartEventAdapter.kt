package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.*
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import org.telegram.telegrambots.meta.api.objects.Update

class StartEventAdapter: LocalUpdateAdapter {

    companion object {

        private val OPEN_CONTACT_WITH_US_NAME = OpenContactWithUsScreenEvent.friendlyName
        private val OPEN_I_AM_CUSTOMER_NAME = OpenIAmCustomerScreenEvent.friendlyName
        private val OPEN_I_AM_SPECIALIST_NAME = OpenIAmSpecialistScreenEvent.friendlyName
        private val OPEN_ALL_SPECIALISTS_NAME = OpenAllSpecialistsScreenEvent.friendlyName
        private val OPEN_MY_PROFILE_NAME = OpenMyProfileScreenEvent.friendlyName

        private val handlingEvents = listOf(
            OPEN_CONTACT_WITH_US_NAME, OPEN_I_AM_CUSTOMER_NAME, OPEN_I_AM_SPECIALIST_NAME,
            OPEN_ALL_SPECIALISTS_NAME, OPEN_MY_PROFILE_NAME
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
            OPEN_CONTACT_WITH_US_NAME -> openContactWithUs(update)
            OPEN_I_AM_CUSTOMER_NAME -> openIAmCustomer(update)
            OPEN_I_AM_SPECIALIST_NAME -> openIAmSpecialist(update)
            OPEN_ALL_SPECIALISTS_NAME -> openAllSpecialists(update)
            OPEN_MY_PROFILE_NAME -> openMyProfile(update)
            else -> LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun openContactWithUs(update: Update): MessageUpdateBunch<*> {
        val userId = update.getUserId()
        val chatId = update.getChatId()
        val event = OpenContactWithUsScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun openIAmCustomer(update: Update): MessageUpdateBunch<*> {
        val userId = update.getUserId()
        val chatId = update.getChatId()
        val event = OpenIAmCustomerScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun openIAmSpecialist(update: Update): MessageUpdateBunch<*> {
        val userId = update.getUserId()
        val chatId = update.getChatId()
        val event = OpenIAmSpecialistScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun openAllSpecialists(update: Update): MessageUpdateBunch<*> {
        val userId = update.getUserId()
        val chatId = update.getChatId()
        val event = OpenAllSpecialistsScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }

    private fun openMyProfile(update: Update): MessageUpdateBunch<*> {
        val userId = update.getUserId()
        val chatId = update.getChatId()
        val event = OpenMyProfileScreenEvent

        return MessageUpdateBunch(event, BaseUpdateInfo.get(chatId, userId))
    }
}