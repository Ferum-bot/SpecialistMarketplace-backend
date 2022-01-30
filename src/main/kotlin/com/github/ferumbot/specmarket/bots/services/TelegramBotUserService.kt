package com.github.ferumbot.specmarket.bots.services

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserInfo
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.models.entity.embeded.UserBotState
import com.github.ferumbot.specmarket.bots.models.enums.TelegramUserSpecialistStatus
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile

interface TelegramBotUserService {

    fun userExists(info: BaseUpdateInfo): Boolean

    fun registerNewUser(info: RegisterNewUserInfo): TelegramUser

    fun updateUserInfo(info: RegisterNewUserInfo): TelegramUser

    fun getAndSetUserPreviousState(info: BaseUpdateInfo): BotState

    fun getUserCurrentState(info: BaseUpdateInfo): UserBotState

    fun setNewUserState(
        newState: BotState, info: BaseUpdateInfo, currentPage: Int? = null, pageCount: Int? = null
    )

    fun getUserSpecialistStatus(info: BaseUpdateInfo): TelegramUserSpecialistStatus

    fun getUserSpecialist(info: BaseUpdateInfo): SpecialistProfile?

    fun getUserSpecialistRequests(info: BaseUpdateInfo, pageNumber: Int, pageSize: Int): Collection<SpecialistProfile>

    fun countUserSpecialistRequests(info: BaseUpdateInfo): Int

    fun addSpecialistToUserRequests(info: BaseUpdateInfo, specialistId: Long): TelegramUser
}