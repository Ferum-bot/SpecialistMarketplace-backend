package com.github.ferumbot.specmarket.bots.services

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserInfo
import com.github.ferumbot.specmarket.bots.models.enums.TelegramUserSpecialistStatus
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.models.entities.Specialist

interface TelegramUserService {

    fun userExists(info: BaseUpdateInfo): Boolean

    fun registerNewUser(info: RegisterNewUserInfo)

    fun updateUserInfo(info: RegisterNewUserInfo)

    fun getAndSetUserPreviousState(info: BaseUpdateInfo): BotState

    fun setNewUserState(newState: BotState, info: BaseUpdateInfo)

    fun getUserSpecialistStatus(info: BaseUpdateInfo): TelegramUserSpecialistStatus

    fun getUserSpecialist(info: BaseUpdateInfo): Specialist?
}