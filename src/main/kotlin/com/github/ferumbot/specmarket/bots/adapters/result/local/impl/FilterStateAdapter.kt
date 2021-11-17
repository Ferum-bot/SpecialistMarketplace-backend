package com.github.ferumbot.specmarket.bots.adapters.result.local.impl

import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.ProfessionsInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.SpecialistsPageInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.CurrentSpecialistsScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.FilterScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.FilterState
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class FilterStateAdapter(
    private val textProvider: MessageTextProvider,
    private val inlineButtonsProvider: InlineMessageButtonsProvider,
): LocalUpdateResultAdapter {

    override fun isFor(bunch: MessageUpdateResultBunch<*>): Boolean {
        return bunch.newState is FilterState
    }

    override fun adapt(bunch: MessageUpdateResultBunch<*>): BotApiMethod<*> {
        val state = bunch.newState as FilterState
        val info = bunch.resultData

        return when(state) {
            is FilterScreenState -> getFilterScreen(info as ProfessionsInfo)
            is CurrentSpecialistsScreenState -> getCurrentSpecialistsScreen(info as SpecialistsPageInfo)
            else -> LocalUpdateResultAdapter.unSupportedState(info)
        }
    }

    private fun getFilterScreen(info: ProfessionsInfo): BotApiMethod<*> {
        val professions = info.professions
        val text = textProvider.provideFilterScreenInfoMessage(professions)
        val chatId = info.chatId.toString()

        return SendMessage(chatId, text)
    }

    private fun getCurrentSpecialistsScreen(info: SpecialistsPageInfo): BotApiMethod<*> {
        val specialists = info.specialists
        val currentPage = info.currentPageNumber
        val pageCount = info.totalPageCount
        val text = textProvider.provideCurrentSpecialistsInfoMessage(specialists)
        val buttons = inlineButtonsProvider.provideCurrentSpecialistsButton(currentPage, pageCount)
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }
}