package com.github.ferumbot.specmarket.bots.adapters.result.local.impl

import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.ProfessionsInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.*
import com.github.ferumbot.specmarket.bots.ui.inline_buttons.InlineMessageButtonsProvider
import com.github.ferumbot.specmarket.bots.ui.text.MessageTextProvider
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

class EditProfileStateAdapter(
    private val textProvider: MessageTextProvider,
    private val inlineButtonsProvider: InlineMessageButtonsProvider,
): LocalUpdateResultAdapter {

    override fun isFor(bunch: MessageUpdateResultBunch<*>): Boolean {
        return bunch.newState is EditProfileState
    }

    override fun adapt(bunch: MessageUpdateResultBunch<*>): BotApiMethod<*> {
        val state = bunch.newState as EditProfileState
        val info = bunch.resultData

        return when(state) {
            is UserChangeFullNameScreenState -> getUserChangeFullName(info)
            is UserChangeDepartmentScreenState -> getUserChangeNiche(info)
            is UserChangeProfessionScreenState -> getUserChangeProfession(info as ProfessionsInfo)
            is UserChangeKeySkillsScreenState -> getUserChangeKeySkills(info)
            is UserChangePortfolioLinkScreenState -> getUserChangePortfolioLink(info)
            is UserChangeAboutMeScreenState -> getUserChangeAboutMe(info)
            is UserChangeWorkingConditionsScreenState -> getUserChangeWorkingConditions(info)
            is UserChangeEducationGradeScreenState -> getUserChangeEducationGrade(info)
            is UserChangeContactLinksScreenState -> getUserChangeContactLinks(info)
            else -> LocalUpdateResultAdapter.unSupportedState(info)
        }
    }

    private fun getUserChangeFullName(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserChangeFullNameInfoMessage()
        return getDefaultMethod(text, info)
    }

    private fun getUserChangeNiche(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserChangeNicheInfoMessage(emptyList())
        return getDefaultMethod(text, info)
    }

    private fun getUserChangeProfession(info: ProfessionsInfo): BotApiMethod<*> {
        val professions = info.professions
        val text = textProvider.provideUserChangeProfessionsInfoMessage(professions)
        return getDefaultMethod(text, info)
    }

    private fun getUserChangeKeySkills(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserChangeKeySkillsInfoMessage()
        return getDefaultMethod(text, info)
    }

    private fun getUserChangePortfolioLink(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserChangePortfolioLinkInfoMessage()
        return getDefaultMethod(text, info)
    }

    private fun getUserChangeAboutMe(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserChangeAboutMeInfoMessage()
        return getDefaultMethod(text, info)
    }

    private fun getUserChangeWorkingConditions(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserChangeWorkingConditionsInfoMessage()
        return getDefaultMethod(text, info)
    }

    private fun getUserChangeEducationGrade(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserChangeEducationGradeInfoMessage()
        return getDefaultMethod(text, info)
    }

    private fun getUserChangeContactLinks(info: BaseUpdateInfo): BotApiMethod<*> {
        val text = textProvider.provideUserChangeContactLinksInfoMessage()
        return getDefaultMethod(text, info)
    }

    private fun getDefaultMethod(text: String, info: BaseUpdateInfo): BotApiMethod<*> {
        val buttons = inlineButtonsProvider.provideBackButton()
        val chatId = info.chatId.toString()
        val sendMessage = SendMessage(chatId, text).apply {
            replyMarkup = buttons
        }

        return sendMessage
    }
}