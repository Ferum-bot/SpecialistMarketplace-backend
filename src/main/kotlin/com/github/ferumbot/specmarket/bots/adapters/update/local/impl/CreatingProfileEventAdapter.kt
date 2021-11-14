package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.core.getUserId
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.state.CreatingProfileState
import com.github.ferumbot.specmarket.bots.state_machine.state.allAvailableStates
import org.telegram.telegrambots.meta.api.objects.Update

class CreatingProfileEventAdapter(
    private val userService: TelegramUserService
): LocalUpdateAdapter {

    override fun isFor(update: Update): Boolean {
        val userId = update.getUserId()
        val chatId = update.getChatId()
        val info = BaseUpdateInfo.get(chatId, userId)

        val currentState = userService.getUserCurrentState(info)
        return currentState in CreatingProfileState.allAvailableStates
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        TODO("Not yet implemented")
    }
}