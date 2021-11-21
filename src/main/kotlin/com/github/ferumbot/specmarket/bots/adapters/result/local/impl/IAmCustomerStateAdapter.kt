package com.github.ferumbot.specmarket.bots.adapters.result.local.impl

import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.ProfessionsInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.AboutEachSpecialistScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.IAmCustomerInfoScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.IAmCustomerState
import com.github.ferumbot.specmarket.bots.state_machine.state.IDoNotKnowWhatIWantScreenState
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.keyboard_buttons.KeyboardMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class IAmCustomerStateAdapter(
    private val textProvider: MessageTextProvider,
    private val keyboardButtonsProvider: KeyboardMessageButtonsProvider,
    private val inlineButtonsProvider: InlineMessageButtonsProvider,
): LocalUpdateResultAdapter {

    override fun isFor(bunch: MessageUpdateResultBunch<*>): Boolean {
        return bunch.newState is IAmCustomerState
    }

    override fun adapt(bunch: MessageUpdateResultBunch<*>): BotApiMethod<*> {
        val state = bunch.newState as IAmCustomerState
        val info = bunch.resultData

        return when(state) {
            is IAmCustomerInfoScreenState -> getIAmCustomerInfoMethod(info)
            is IDoNotKnowWhatIWantScreenState -> getIDoNotKnowWhatIWantMethod(info)
            is AboutEachSpecialistScreenState -> getAboutEachSpecialistMethod(info as ProfessionsInfo)
            else -> LocalUpdateResultAdapter.unSupportedState(info)
        }
    }

    private fun getIAmCustomerInfoMethod(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideIAmCustomerInfoMessage()
        val buttons = keyboardButtonsProvider.provideIAmCustomerInfoScreenButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getIDoNotKnowWhatIWantMethod(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideIDoNotKnowWhatIWantMessage()
        val buttons = inlineButtonsProvider.provideIDoNotKnowWhatIWantScreenButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getAboutEachSpecialistMethod(info: ProfessionsInfo): BotApiMethod<*> {
        val professions = info.professions
        val text = textProvider.provideAboutEachSpecialistMessage(professions)
        val chatId = info.chatId.toString()

        return SendMessage(chatId, text)
    }
}