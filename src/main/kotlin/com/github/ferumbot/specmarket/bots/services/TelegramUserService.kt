package com.github.ferumbot.specmarket.bots.services

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState

interface TelegramUserService {

    fun userExists(info: BaseUpdateInfo): Boolean

    fun registerNewUser(info: RegisterNewUserUpdateInfo)

    fun updateUserInfo(info: RegisterNewUserUpdateInfo)

    fun getAndSetUserPreviousState(info: BaseUpdateInfo): BotState

    fun setNewUserState(newState: BotState, info: BaseUpdateInfo)
}