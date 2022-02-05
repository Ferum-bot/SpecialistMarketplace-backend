package com.github.ferumbot.specmarket.bots.services.impl

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserInfo
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.models.entity.embeded.UserBotState
import com.github.ferumbot.specmarket.bots.models.enums.TelegramUserProfileStatus
import com.github.ferumbot.specmarket.bots.models.enums.TelegramUserProfileStatus.*
import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import com.github.ferumbot.specmarket.bots.services.TelegramBotFlowService
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.core.extensions.transform
import com.github.ferumbot.specmarket.exceptions.NicheNotExists
import com.github.ferumbot.specmarket.exceptions.ProfessionNotExists
import com.github.ferumbot.specmarket.exceptions.SpecialistNotExists
import com.github.ferumbot.specmarket.models.dto.NicheDto
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses
import com.github.ferumbot.specmarket.repositories.NicheRepository
import com.github.ferumbot.specmarket.repositories.ProfessionRepository
import com.github.ferumbot.specmarket.repositories.SpecialistRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TelegramBotFlowServiceImpl @Autowired constructor(
    private val userRepository: TelegramUserRepository,
    private val specialistRepository: SpecialistRepository,
    private val professionRepository: ProfessionRepository,
    private val nicheRepository: NicheRepository,
): TelegramBotFlowService {

    @Transactional(readOnly = true)
    override fun userExists(info: BaseUpdateInfo): Boolean {
        val userId = info.userId
        return userRepository.existsTelegramUserByTelegramUserId(userId)
    }

    @Transactional(readOnly = true)
    override fun getUser(info: BaseUpdateInfo): TelegramUser {
        val userId = info.userId
        return if (!userExists(info)) {
            registerNewUser(info)
        } else {
            userRepository.findByTelegramUserId(userId)!!
        }
    }

    override fun registerNewUser(info: RegisterNewUserInfo): TelegramUser {
        val user = TelegramUser(
            isBot = info.isBot,
            languageCode = info.languageCode,
            personalTelegramChatId = info.chatId,
            telegramUserId = info.userId,
            firstName = info.firstName,
            lastName = info.lastName,
            userName = info.userName,
        )

        return userRepository.saveAndFlush(user)
    }

    override fun updateUserInfo(info: RegisterNewUserInfo): TelegramUser {
        val foundUser = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        return foundUser.let { user ->
            user.isBot = info.isBot
            user.languageCode = info.languageCode
            user.firstName = info.firstName
            user.lastName = info.lastName
            user.userName = info.userName
            userRepository.saveAndFlush(user)
        }
    }

    override fun getAndSetUserPreviousState(info: BaseUpdateInfo): BotState {
        val entity = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        val currentState = entity.currentBotState.currentState
        val prevState = currentState.previousState
        entity.currentBotState.currentState = prevState
        userRepository.saveAndFlush(entity)
        return prevState
    }

    override fun getUserCurrentState(info: BaseUpdateInfo): UserBotState {
        val entity = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        return entity.currentBotState
    }

    override fun setNewUserState(newState: BotState, info: BaseUpdateInfo, currentPage: Int?, pageCount: Int?) {
        val entity = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        entity.currentBotState.apply {
            currentState = newState
            currentPageNumber = currentPage
            totalAvailablePages = pageCount
        }

        userRepository.saveAndFlush(entity)
    }

    override fun getUserSpecialistStatus(info: BaseUpdateInfo): TelegramUserProfileStatus {
        val userEntity = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        val specialistStatus = userEntity.specialist?.status?.alias
            ?: return NOT_AUTHORIZED

        return when(specialistStatus) {
            ProfileStatuses.NOT_FILLED -> NOT_FILLED
            ProfileStatuses.AWAITING_CONFIRMATION -> AWAITING_CONFIRMATION
            ProfileStatuses.REJECTED -> REJECTED
            ProfileStatuses.APPROVED -> APPROVED
        }
    }

    override fun getUserSpecialist(info: BaseUpdateInfo): SpecialistProfile? {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        return user.specialist
    }

    override fun getUserSpecialistRequests(info: BaseUpdateInfo, pageNumber: Int, pageSize: Int): Collection<SpecialistProfile> {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        return user.specialistsRequests
    }

    override fun setProfessionToUserFilter(professionAlias: String, info: BaseUpdateInfo): TelegramUser {
        val profession = professionRepository.getByAlias(professionAlias)
            ?: throw ProfessionNotExists()
        val user = getUser(info)
        user.currentBotState.currentProfessionFilter = profession

        return userRepository.saveAndFlush(user)
    }

    override fun setNicheToUserFilter(nicheAlias: String, info: BaseUpdateInfo): TelegramUser {
        val niche = nicheRepository.getByAlias(nicheAlias)
            ?: throw NicheNotExists()
        val user = getUser(info)
        user.currentBotState.currentNicheFilter = niche

        return userRepository.saveAndFlush(user)
    }

    @Transactional(readOnly = true)
    override fun getProfessionFromUserFilter(info: BaseUpdateInfo): ProfessionDto? {
        val user = getUser(info)
        return user.currentBotState.currentProfessionFilter
            ?.transform { ProfessionDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getNicheFromUserFilter(info: BaseUpdateInfo): NicheDto? {
        val user = getUser(info)
        return user.currentBotState.currentNicheFilter
            ?.transform { NicheDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun countUserSpecialistRequests(info: BaseUpdateInfo): Int {
        return userRepository.countUserSpecialistRequests(info.userId)
    }

    override fun addSpecialistToUserRequests(info: BaseUpdateInfo, specialistId: Long): TelegramUser {
        val specialist = specialistRepository.findById(specialistId).orElseGet {
            throw SpecialistNotExists(specialistId)
        }
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        user.specialistsRequests.add(specialist)
        return userRepository.saveAndFlush(user)
    }

    override fun deleteSpecialistFromRequests(info: BaseUpdateInfo, specialistId: Long): TelegramUser {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        user.specialistsRequests.removeIf { it.id == specialistId }
        return userRepository.saveAndFlush(user)
    }

    override fun clearUserSpecialistsRequests(info: BaseUpdateInfo): TelegramUser {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        user.specialistsRequests.clear()
        return userRepository.saveAndFlush(user)
    }

    private fun registerNewUser(info: BaseUpdateInfo): TelegramUser {
        val entity = TelegramUser(
            telegramUserId = info.userId,
            personalTelegramChatId = info.chatId,
            isBot = false,
        )
        return userRepository.save(entity)
    }
}