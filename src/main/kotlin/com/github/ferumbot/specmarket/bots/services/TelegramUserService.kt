package com.github.ferumbot.specmarket.bots.services

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState

interface TelegramUserService {

    fun userExists(info: BaseUpdateInfo): Boolean

    fun registerNewUser(info: RegisterNewUserInfo)

    fun updateUserInfo(info: RegisterNewUserInfo)

    fun getAndSetUserPreviousState(info: BaseUpdateInfo): BotState

    fun setNewUserState(newState: BotState, info: BaseUpdateInfo)
}