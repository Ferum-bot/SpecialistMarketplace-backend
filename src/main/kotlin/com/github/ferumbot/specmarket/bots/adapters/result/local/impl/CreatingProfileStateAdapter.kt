package com.github.ferumbot.specmarket.bots.adapters.result.local.impl

import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.ProfessionsInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.*
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.keyboard_buttons.KeyboardMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class CreatingProfileStateAdapter(
    private val textProvider: MessageTextProvider,
    private val inlineButtonsProvider: InlineMessageButtonsProvider,
    private val keyboardButtonsProvider: KeyboardMessageButtonsProvider,
): LocalUpdateResultAdapter {

    override fun isFor(bunch: MessageUpdateResultBunch<*>): Boolean {
        return bunch.newState is CreatingProfileState
    }

    override fun adapt(bunch: MessageUpdateResultBunch<*>): BotApiMethod<*>? {
        val state = bunch.newState as CreatingProfileState
        val info = bunch.resultData

        return when(state) {
            is UserInputInvalidDataScreenState -> getUserInputInvalidData(info)
            is UserInputFullNameScreenState -> getUserInputFullName(info)
            is UserInputNicheScreenState -> getUserInputNiches(info)
            is UserInputProfessionScreenState -> getUserInputProfession(info as ProfessionsInfo)
            is UserInputKeySkillsScreenState -> getUserInputKeySkills(info)
            is UserInputPortfolioLinkScreenState -> getUserInputPortfolioLink(info)
            is UserInputAboutMeScreenState -> getUserInputAboutMe(info)
            is UserInputWorkingConditionsScreenState -> getUserInputWorkingConditions(info)
            is UserInputEducationGradeScreenState -> getUserInputEducationGrade(info)
            is UserInputContactLinksScreenState -> getUserInputContactLinks(info)
            is ShowHowProfileLooksNowScreenState -> getShowHowProfileLooksKnow(info as UserSpecialistInfo)
            is ShowProfilePreviewScreenState -> getShowProfilePreview(info as UserSpecialistInfo)
            else -> LocalUpdateResultAdapter.unSupportedState(info)
        }
    }

    private fun getUserInputInvalidData(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserInputInvalidDataMessage()
        val buttons = inlineButtonsProvider.provideCreatingProfileButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getUserInputFullName(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserInputFullNameInfoMessage()
        val buttons = inlineButtonsProvider.provideCreatingProfileButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getUserInputNiches(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserInputNichesInfoMessage(emptyList())
        val buttons = inlineButtonsProvider.provideCreatingProfileButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getUserInputProfession(info: ProfessionsInfo): BotApiMethod<*> {
        val availableProfessions = info.professions
        val text = textProvider.provideUserInputProfessionsInfoMessage(availableProfessions)
        val buttons = inlineButtonsProvider.provideCreatingProfileButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getUserInputKeySkills(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserInputKeySkillsInfoMessage()
        val buttons = inlineButtonsProvider.provideCreatingProfileButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getUserInputPortfolioLink(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserInputPortfolioLinkInfoMessage()
        val buttons = inlineButtonsProvider.provideCreatingProfileButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getUserInputAboutMe(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserInputAboutMeInfoMessage()
        val buttons = inlineButtonsProvider.provideCreatingProfileButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getUserInputWorkingConditions(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserInputWorkingConditionsInfoMessage()
        val buttons = inlineButtonsProvider.provideCreatingProfileButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getUserInputEducationGrade(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserInputEducationGradeInfoMessage()
        val buttons = inlineButtonsProvider.provideCreatingProfileButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getUserInputContactLinks(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserInputContactLinksInfoMessage()
        val buttons = inlineButtonsProvider.provideCreatingProfileButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getShowHowProfileLooksKnow(info: UserSpecialistInfo): BotApiMethod<*> {
        val text = textProvider.provideHowProfileLooksNowMessage(info.specialist)
        val buttons = inlineButtonsProvider.provideHowProfileLooksLikeKnowButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }

    private fun getShowProfilePreview(info: UserSpecialistInfo): BotApiMethod<*> {
        val text = textProvider.provideProfilePreviewMessage(info.specialist)
        val buttons = keyboardButtonsProvider.provideShowProfileButtons()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }


}