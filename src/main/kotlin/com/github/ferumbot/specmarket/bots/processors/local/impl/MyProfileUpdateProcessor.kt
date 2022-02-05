package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.OpenAnotherPageInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.SpecialistsPageInfo
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramBotFlowService
import com.github.ferumbot.specmarket.bots.services.TelegramUserSpecialistService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.EditProfileScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.MyRequestsScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.YouAreAuthorizedScreenState
import com.github.ferumbot.specmarket.models.dto.SpecialistDto

class MyProfileUpdateProcessor(
    private val userService: TelegramBotFlowService,
    private val specialistService: TelegramUserSpecialistService,
): LocalUpdateProcessor {

    companion object {

        private const val SPECIALIST_PER_SCREEN = 1
    }

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is MyProfileEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent as MyProfileEvent
        val info = bunch.extraInformation

        return when(event) {
            is OpenMyRequestsScreenEvent -> openMyRequestsScreen(info)
            is OpenEditInfoScreenEvent -> openEditMyProfile(info)
            is OpenAnotherMyRequestsPageScreenEvent -> openAnotherMyRequestPageScreen(info as OpenAnotherPageInfo)
            is ChangeProfileSpecialistVisibilityScreenEvent -> changeProfileVisibilityEvent(info)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun openMyRequestsScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = MyRequestsScreenState
        val totalCount = userService.countUserSpecialistRequests(info)
        val firstPage = 1
        val specialistRequests = userService.getUserSpecialistRequests(info, firstPage, SPECIALIST_PER_SCREEN)
            .map { SpecialistDto.from(it) }
        val resultInfo = SpecialistsPageInfo.from(
            info, specialistRequests, firstPage, totalCount
        )
        userService.setNewUserState(newState, info, firstPage, totalCount)

        return MessageUpdateResultBunch(newState, resultInfo)
    }

    private fun openEditMyProfile(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = EditProfileScreenState
        val userSpecialist = userService.getUserSpecialist(info)
        val resultInfo = UserSpecialistInfo.getFrom(info, userSpecialist!!)
        userService.setNewUserState(newState, info)

        return MessageUpdateResultBunch(newState, resultInfo)
    }

    private fun openAnotherMyRequestPageScreen(info: OpenAnotherPageInfo): MessageUpdateResultBunch<*> {
        val newState = MyRequestsScreenState
        val specialistRequests = userService.getUserSpecialistRequests(info, info.pageNumber, SPECIALIST_PER_SCREEN)
            .map { SpecialistDto.from(it) }
        val totalCount = userService.countUserSpecialistRequests(info)
        val resultInfo = SpecialistsPageInfo(
            info.chatId, info.userId, specialistRequests, info.pageNumber, totalCount
        )
        userService.setNewUserState(MyRequestsScreenState, info, info.pageNumber, totalCount)

        return MessageUpdateResultBunch(newState, resultInfo)
    }

    private fun changeProfileVisibilityEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val state = YouAreAuthorizedScreenState
        val specialist = userService.getUserSpecialist(info)
        val newVisibility = specialist?.isVisible != true
        specialistService.updateVisibility(info, newVisibility)
        userService.setNewUserState(state, info)

        val userSpecialist = userService.getUserSpecialist(info)
        val resultInfo = UserSpecialistInfo.getFrom(info, userSpecialist!!)

        return MessageUpdateResultBunch(state, resultInfo)
    }
}