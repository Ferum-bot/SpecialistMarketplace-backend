package com.github.ferumbot.specmarket.bots.processors.local.impl

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.bots.models.enums.TelegramUserProfileStatus.*
import com.github.ferumbot.specmarket.bots.processors.local.LocalUpdateProcessor
import com.github.ferumbot.specmarket.bots.services.TelegramBotFlowService
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.bots.state_machine.state.*
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile

class StartUpdateProcessor(
    private val userService: TelegramBotFlowService
): LocalUpdateProcessor {

    override fun canProcess(bunch: MessageUpdateBunch<*>): Boolean {
        return bunch.causedEvent is StartEvent
    }

    override fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*> {
        val event = bunch.causedEvent as StartEvent
        val info = bunch.extraInformation

        return when(event) {
            is OpenAllSpecialistsScreenEvent -> processAllSpecialistsScreen(info)
            is OpenContactWithUsScreenEvent -> processOpenContactWithUsScreen(info)
            is OpenIAmCustomerScreenEvent -> processOpenIAmCustomerScreen(info)
            is OpenIAmSpecialistScreenEvent -> processOpenIAmSpecialistScreen(info)
            is OpenMyProfileScreenEvent -> processOpenMyProfileScreen(info)
            else -> LocalUpdateProcessor.unSupportedEvent(info)
        }
    }

    private fun processAllSpecialistsScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = AllSpecialistInfoScreenState
        userService.setNewUserState(newState, info)
        clearUserFilter(info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processOpenContactWithUsScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = ContactWithUsScreenState
        userService.setNewUserState(newState, info)
        clearUserFilter(info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processOpenIAmCustomerScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = IAmCustomerInfoScreenState
        userService.setNewUserState(newState, info)
        clearUserFilter(info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processOpenIAmSpecialistScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val newState = IAmSpecialistInfoScreenState
        userService.setNewUserState(newState, info)
        clearUserFilter(info)
        return MessageUpdateResultBunch(newState, info)
    }

    private fun processOpenMyProfileScreen(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
        val currentUserStatus = userService.getUserSpecialistStatus(info)
        val notAuthorizedState = YouAreNotAuthorizedScreenState

        clearUserFilter(info)
        return when(currentUserStatus) {
            NOT_AUTHORIZED -> {
                userService.setNewUserState(notAuthorizedState, info)

                MessageUpdateResultBunch(notAuthorizedState, info)
            }
            NOT_FILLED -> {
                val goodState = YouAreNotFullAuthorizedScreenState
                val specialistEntity = userService.getUserSpecialist(info)

                getUpdateResultFor(info, specialistEntity, goodState)
            }
            AWAITING_CONFIRMATION, REJECTED, APPROVED -> {
                val goodState = YouAreAuthorizedScreenState
                val specialistEntity = userService.getUserSpecialist(info)

                getUpdateResultFor(info, specialistEntity, goodState)
            }
        }
    }

    private fun getUpdateResultFor(
        info: BaseUpdateInfo, specialistEntity: SpecialistProfile?, goodState: ProfileState
    ): MessageUpdateResultBunch<*> {
        val notAuthorizedState = YouAreNotAuthorizedScreenState
        return specialistEntity?.let { specialist ->
            val newInfo = UserSpecialistInfo.getFrom(info, specialist)
            userService.setNewUserState(goodState, info)

            MessageUpdateResultBunch(goodState, newInfo)
        } ?: MessageUpdateResultBunch(notAuthorizedState, info)
    }

    private fun clearUserFilter(info: BaseUpdateInfo) {
        userService.clearUserFilter(info)
    }
}