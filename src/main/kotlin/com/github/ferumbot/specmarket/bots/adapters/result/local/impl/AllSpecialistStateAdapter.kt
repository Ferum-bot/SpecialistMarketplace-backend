package com.github.ferumbot.specmarket.bots.adapters.result.local.impl

import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.AllSpecialistInfoScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.AllSpecialistsState
import com.github.ferumbot.specmarket.bots.state_machine.state.IDoNotKnowWhoISearchScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.LeaveBidInfoScreenState
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.keyboard_buttons.KeyboardMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class AllSpecialistStateAdapter(
    private val textProvider: MessageTextProvider,
    private val keyboardButtonsProvider: KeyboardMessageButtonsProvider,
    private val inlineButtonsProvider: InlineMessageButtonsProvider,
): LocalUpdateResultAdapter {

    override fun isFor(bunch: MessageUpdateResultBunch<*>): Boolean {
        return bunch.newState is AllSpecialistsState
    }

    override fun adapt(bunch: MessageUpdateResultBunch<*>): BotApiMethod<*> {
        val state = bunch.newState as AllSpecialistsState
        val info = bunch.resultData

        return when(state) {
            is AllSpecialistInfoScreenState -> getAllSpecialistInfoMethod(info)
            is IDoNotKnowWhoISearchScreenState -> getIDoNotKnowWhoISearchMethod(info)
            is LeaveBidInfoScreenState -> getLeaveBidInfoMethod(info)
            else -> LocalUpdateResultAdapter.unSupportedState(info)
        }
    }

    private fun getAllSpecialistInfoMethod(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideAllSpecialistInfoMessage()
        val buttons = keyboardButtonsProvider.provideAllSpecialistScreenButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getIDoNotKnowWhoISearchMethod(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideIDoNotKnowWhoISearchMessage()
        val buttons = keyboardButtonsProvider.provideIDoNotKnowWhoISearchButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getLeaveBidInfoMethod(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideLeaveBidInfoMessage()
        val buttons = inlineButtonsProvider.provideBackButton()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }
}