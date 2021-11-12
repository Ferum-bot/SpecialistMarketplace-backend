package com.github.ferumbot.specmarket.bots.services

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserInfo
import com.github.ferumbot.specmarket.bots.models.entity.embeded.UserBotState
import com.github.ferumbot.specmarket.bots.models.enums.TelegramUserSpecialistStatus
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.models.entities.Specialist
import org.springframework.data.domain.Pageable

interface TelegramUserService {

    fun userExists(info: BaseUpdateInfo): Boolean

    fun registerNewUser(info: RegisterNewUserInfo)

    fun updateUserInfo(info: RegisterNewUserInfo)

    fun getAndSetUserPreviousState(info: BaseUpdateInfo): BotState

    fun getUserCurrentState(info: BaseUpdateInfo): UserBotState

    fun setNewUserState(
        newState: BotState, currentPage: Int? = null, pageCount: Int? = null, info: BaseUpdateInfo
    )

    fun getUserSpecialistStatus(info: BaseUpdateInfo): TelegramUserSpecialistStatus

    fun getUserSpecialist(info: BaseUpdateInfo): Specialist?

    fun getUserSpecialistRequests(info: BaseUpdateInfo, page: Pageable): Collection<Specialist>
}