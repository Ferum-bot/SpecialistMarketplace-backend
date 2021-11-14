package com.github.ferumbot.specmarket.bots.adapters.result.local.impl

import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistRequests
import com.github.ferumbot.specmarket.bots.state_machine.state.*
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.DefaultInlineButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.keyboard_buttons.KeyboardMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class MyProfileStateAdapter(
    private val textProvider: MessageTextProvider,
    private val keyboardButtonsProvider: KeyboardMessageButtonsProvider,
    private val inlineButtonsProvider: InlineMessageButtonsProvider,
): LocalUpdateResultAdapter {

    override fun isFor(bunch: MessageUpdateResultBunch<*>): Boolean {
        return bunch.newState is ProfileState
    }

    override fun adapt(bunch: MessageUpdateResultBunch<*>): BotApiMethod<*>? {
        val state = bunch.newState as ProfileState
        val info = bunch.resultData

        return when(state) {
            is YouAreNotAuthorizedScreenState -> getYouAreNotAuthorizedScreen(info)
            is YouAreNotFullAuthorizedScreenState -> getYouAreNotFullAuthorizedScreen(info as UserSpecialistInfo)
            is YouAreAuthorizedScreenState -> getYouAreAuthorizedScreen(info as UserSpecialistInfo)
            is MyRequestsScreenState -> getMyRequestsScreen(info as UserSpecialistRequests)
            is EditProfileScreenState -> getEditProfileScreen(info as UserSpecialistInfo)
            else -> LocalUpdateResultAdapter.unSupportedState(info)
        }
    }

    private fun getYouAreNotAuthorizedScreen(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideYouAreNotAuthorizedInfoMessage()
        val buttons = keyboardButtonsProvider.provideNotAuthorizedInfoScreenButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }


    private fun getYouAreNotFullAuthorizedScreen(info: UserSpecialistInfo): BotApiMethod<*> {
        val specialist = info.specialist
        val text = textProvider.provideYouArePartiallyAuthorizedInfoMessage(specialist)
        val buttons = keyboardButtonsProvider.providePartiallyAuthorizedInfoScreenButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getYouAreAuthorizedScreen(info: UserSpecialistInfo): BotApiMethod<*> {
        val specialist = info.specialist
        val text = textProvider.provideYouAreAuthorizedInfoMessage(specialist)
        val buttons = keyboardButtonsProvider.provideAuthorizedInfoScreenButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getMyRequestsScreen(info: UserSpecialistRequests): BotApiMethod<*> {
        val page = info.currentPageNumber
        val pageCount = info.totalPageCount
        val buttons = inlineButtonsProvider.provideSpecialistRequestsButtons(page, pageCount)
        val text = textProvider.provideSpecialistRequestInfoMessage(info.requests)
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getEditProfileScreen(info: UserSpecialistInfo): BotApiMethod<*> {
        val buttons = keyboardButtonsProvider.provideEditProfileInfoScreenButtons()
        val text = textProvider.provideEditProfileInfoMessage(info.specialist)
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }
}