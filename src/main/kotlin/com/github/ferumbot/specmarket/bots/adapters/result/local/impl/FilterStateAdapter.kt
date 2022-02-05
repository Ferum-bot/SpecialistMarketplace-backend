package com.github.ferumbot.specmarket.bots.adapters.result.local.impl

import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseDataInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.NichesInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.ProfessionsInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.SpecialistsPageInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.*
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
            is ProfessionFilterScreenState -> getProfessionFilterScreen(info as ProfessionsInfo)
            is NicheFilterScreenState -> getNicheFilterScreen(info as NichesInfo)
            is CurrentSpecialistsScreenState -> getCurrentSpecialistsScreen(info as SpecialistsPageInfo)
            is CurrentSpecialistsContactsScreenState -> getCurrentSpecialistContactsScreen(info as BaseDataInfo)
            else -> LocalUpdateResultAdapter.unSupportedState(info)
        }
    }

    private fun getProfessionFilterScreen(info: ProfessionsInfo): BotApiMethod<*> {
        val professions = info.professions
        val text = textProvider.provideProfessionFilterScreenInfoMessage(professions)
        val buttons = inlineButtonsProvider.provideFilterButtons()
        val chatId = info.chatId.toString()

        return SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }
    }

    private fun getNicheFilterScreen(info: NichesInfo): BotApiMethod<*> {
        val niches = info.niches
        val text = textProvider.provideNicheFilterScreenInfoMessage(niches)
        val buttons = inlineButtonsProvider.provideFilterButtons()
        val chatId = info.chatId.toString()

        return SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }
    }

    private fun getCurrentSpecialistsScreen(info: SpecialistsPageInfo): BotApiMethod<*> {
        val chatId = info.chatId.toString()
        val specialists = info.specialists
        if (specialists.isEmpty()) {
            val text = textProvider.provideCurrentSpecialistsEmptyInfoMessage()
            return SendMessage(chatId, text)
        }

        val specialistId = specialists.first().id!!
        val currentPage = info.currentPageNumber
        val pageCount = info.totalPageCount
        val text = textProvider.provideCurrentSpecialistsInfoMessage(specialists)
        val buttons = inlineButtonsProvider.provideCurrentSpecialistsButton(
            currentPage, pageCount, specialistId
        )
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getCurrentSpecialistContactsScreen(info: BaseDataInfo): BotApiMethod<*> {
        val contacts = info.simpleInput
        val text = textProvider.provideCurrentSpecialistContactsInfoMessage(contacts)
        val chatId = info.chatId.toString()

        return SendMessage(chatId, text)
    }
}