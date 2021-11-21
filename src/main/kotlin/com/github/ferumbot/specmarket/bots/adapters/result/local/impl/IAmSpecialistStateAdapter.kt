package com.github.ferumbot.specmarket.bots.adapters.result.local.impl

import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.IAmSpecialistInfoScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.IAmSpecialistState
import com.github.ferumbot.specmarket.bots.state_machine.state.MyCVInfoScreenState
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.keyboard_buttons.KeyboardMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class IAmSpecialistStateAdapter(
    private val textProvider: MessageTextProvider,
    private val keyboardButtonsProvider: KeyboardMessageButtonsProvider,
): LocalUpdateResultAdapter {

    override fun isFor(bunch: MessageUpdateResultBunch<*>): Boolean {
        return bunch.newState is IAmSpecialistState
    }

    override fun adapt(bunch: MessageUpdateResultBunch<*>): BotApiMethod<*> {
        val state = bunch.newState as IAmSpecialistState
        val info = bunch.resultData

        return when(state) {
            is IAmSpecialistInfoScreenState -> getIAmSpecialistInfoMethod(info)
            is MyCVInfoScreenState -> getMyCVInfoScreenState(info)
            else -> LocalUpdateResultAdapter.unSupportedState(info)
        }
    }

    private fun getIAmSpecialistInfoMethod(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideIAmSpecialistInfoMessage()
        val buttons = keyboardButtonsProvider.provideIAmSpecialistInfoScreenButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getMyCVInfoScreenState(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideMyCVInfoMessage()
        val buttons = keyboardButtonsProvider.provideMyCVInfoScreenButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }
}