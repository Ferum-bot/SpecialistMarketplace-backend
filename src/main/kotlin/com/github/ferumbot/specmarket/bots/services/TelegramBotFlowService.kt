package com.github.ferumbot.specmarket.bots.services

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserInfo
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.models.entity.embeded.UserBotState
import com.github.ferumbot.specmarket.bots.models.enums.TelegramUserProfileStatus
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.models.dto.NicheDto
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.dto.SpecialistStatusDto
import com.github.ferumbot.specmarket.models.entities.filter.Profession
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile

interface TelegramBotFlowService {

    fun userExists(info: BaseUpdateInfo): Boolean

    fun getUser(info: BaseUpdateInfo): TelegramUser

    fun registerNewUser(info: RegisterNewUserInfo): TelegramUser

    fun updateUserInfo(info: RegisterNewUserInfo): TelegramUser

    fun getAndSetUserPreviousState(info: BaseUpdateInfo): BotState

    fun getUserCurrentState(info: BaseUpdateInfo): UserBotState

    fun setNewUserState(
        newState: BotState, info: BaseUpdateInfo, currentPage: Int? = null, pageCount: Int? = null
    )

    fun getUserSpecialistStatus(info: BaseUpdateInfo): TelegramUserProfileStatus

    fun getUserSpecialist(info: BaseUpdateInfo): SpecialistProfile?

    fun getUserSpecialistRequests(info: BaseUpdateInfo, pageNumber: Int, pageSize: Int): Collection<SpecialistProfile>

    fun setProfessionToUserFilter(professionAlias: String, info: BaseUpdateInfo): TelegramUser

    fun setNicheToUserFilter(nicheAlias: String, info: BaseUpdateInfo): TelegramUser

    fun getProfessionFromUserFilter(info: BaseUpdateInfo): ProfessionDto?

    fun getNicheFromUserFilter(info: BaseUpdateInfo): NicheDto?

    fun countUserSpecialistRequests(info: BaseUpdateInfo): Int

    fun addSpecialistToUserRequests(info: BaseUpdateInfo, specialistId: Long): TelegramUser

    fun deleteSpecialistFromRequests(info: BaseUpdateInfo, specialistId: Long): TelegramUser

    fun clearUserSpecialistsRequests(info: BaseUpdateInfo): TelegramUser
}