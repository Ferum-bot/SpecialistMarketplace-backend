package com.github.ferumbot.specmarket.bots.adapters.result.local.impl

import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.ContactWithUsScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.GeneralState
import com.github.ferumbot.specmarket.bots.state_machine.state.StartScreenState
import com.github.ferumbot.specmarket.bots.ui.keyboard_buttons.KeyboardMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class GeneralStateAdapter(
    private val textProvider: MessageTextProvider,
    private val keyboardButtonsProvider: KeyboardMessageButtonsProvider,
): LocalUpdateResultAdapter {

    override fun isFor(bunch: MessageUpdateResultBunch<*>): Boolean {
        return bunch.newState is GeneralState
    }

    override fun adapt(bunch: MessageUpdateResultBunch<*>): BotApiMethod<*> {
        val state = bunch.newState
        val info = bunch.resultData

        return when(state) {
            is StartScreenState -> getStartScreenApiMethod(info)
            is ContactWithUsScreenState -> getContactWithUsScreenApiMethod(info)
            else -> LocalUpdateResultAdapter.unSupportedState(info)
        }
    }

    private fun getStartScreenApiMethod(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideStartScreenMessage()
        val keyboardButtons = keyboardButtonsProvider.provideStartScreenButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = keyboardButtons
        }

        return sendMessage
    }

    private fun getContactWithUsScreenApiMethod(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideContactWithUsMessage()
        val chatId = info.chatId.toString()

        return SendMessage(chatId, text)
    }
}