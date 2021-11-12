package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.OpenAnotherPageRequest
import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistRequests
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.event.MyProfileEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenAnotherMyRequestsPageScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenEditInfoScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenMyRequestsScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.EditProfileScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.MyRequestsScreenState
import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import org.springframework.data.domain.PageRequest

class MyProfileUpdateProcessor(
    private val userService: TelegramUserService,
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
            is OpenAnotherMyRequestsPageScreenEvent -> openAnotherMyRequestPageScreen(info as OpenAnotherPageRequest)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun openMyRequestsScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = MyRequestsScreenState
        val totalCount = userService.countUserSpecialistRequests(info)
        val firstPage = 1
        val startPage = PageRequest.of(firstPage, SPECIALIST_PER_SCREEN)
        val specialistRequests = userService.getUserSpecialistRequests(info, startPage)
            .map { SpecialistDto.from(it) }
        val resultInfo = UserSpecialistRequests(
            info.chatId, info.userId, specialistRequests, firstPage, totalCount
        )
        userService.setNewUserState(newState, info, firstPage, totalCount)

        return MessageUpdateResultBunch(newState, resultInfo)
    }

    private fun openEditMyProfile(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = EditProfileScreenState
        val userSpecialist = userService.getUserSpecialist(info)
        val resultInfo = UserSpecialistInfo.getFrom(info, userSpecialist!!)

        return MessageUpdateResultBunch(newState, resultInfo)
    }

    private fun openAnotherMyRequestPageScreen(info: OpenAnotherPageRequest): MessageUpdateResultBunch<*> {
        val newState = MyRequestsScreenState
        val pageToOpen = PageRequest.of(info.pageNumber, SPECIALIST_PER_SCREEN)
        val specialistRequests = userService.getUserSpecialistRequests(info, pageToOpen)
            .map { SpecialistDto.from(it) }
        val totalCount = userService.countUserSpecialistRequests(info)
        val resultInfo = UserSpecialistRequests(
            info.chatId, info.userId, specialistRequests, info.pageNumber, totalCount
        )
        userService.setNewUserState(MyRequestsScreenState, info, info.pageNumber, totalCount)

        return MessageUpdateResultBunch(newState, resultInfo)
    }
}