package com.github.ferumbot.specmarket.bots.adapters.result.local.impl

import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.NotImplementedScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.UnSupportedScreenState
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class NotAvailableStateAdapter(
    private val textProvider: MessageTextProvider,
    private val inlineButtonsProvider: InlineMessageButtonsProvider,
): LocalUpdateResultAdapter {

    override fun isFor(bunch: MessageUpdateResultBunch<*>): Boolean {
        val state = bunch.newState
        return (state is NotImplementedScreenState) || (state is UnSupportedScreenState)
    }

    override fun adapt(bunch: MessageUpdateResultBunch<*>): BotApiMethod<*> {
        val state = bunch.newState
        val info = bunch.resultData

        return when(state) {
            is NotImplementedScreenState -> getNotImplementedScreen(info)
            is UnSupportedScreenState -> getUnSupportedScreen(info)
            else -> LocalUpdateResultAdapter.unSupportedState(info)
        }
    }

    private fun getNotImplementedScreen(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideNotImplementedMessage()
        val keyboard = inlineButtonsProvider.provideNotImplementedScreenButtons()
        val chatId = info.chatId.toString()
        val message = SendMessage(chatId, text).apply {
            replyMarkup = keyboard
        }

        return message
    }

    private fun getUnSupportedScreen(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUnSupportedMessage()
        val keyboard = inlineButtonsProvider.provideUnSupportedScreenButtons()
        val chatId = info.chatId.toString()
        val message = SendMessage(chatId, text).apply {
            replyMarkup = keyboard
        }

        return message
    }
}