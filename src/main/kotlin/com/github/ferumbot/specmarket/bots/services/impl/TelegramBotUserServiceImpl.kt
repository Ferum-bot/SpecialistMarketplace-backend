package com.github.ferumbot.specmarket.bots.services.impl

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserInfo
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.models.entity.embeded.UserBotState
import com.github.ferumbot.specmarket.bots.models.enums.TelegramUserSpecialistStatus
import com.github.ferumbot.specmarket.bots.models.enums.TelegramUserSpecialistStatus.*
import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import com.github.ferumbot.specmarket.bots.services.TelegramBotUserService
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.exceptions.SpecialistNotExists
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
import com.github.ferumbot.specmarket.repositories.SpecialistRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TelegramBotUserServiceImpl @Autowired constructor(
    private val userRepository: TelegramUserRepository,
    private val specialistRepository: SpecialistRepository,
): TelegramBotUserService {

    @Transactional(readOnly = true)
    override fun userExists(info: BaseUpdateInfo): Boolean {
        val userId = info.userId
        return userRepository.existsTelegramUserByTelegramUserId(userId)
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

    override fun getUserSpecialistStatus(info: BaseUpdateInfo): TelegramUserSpecialistStatus {
        val userEntity = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        val specialistEntity = userEntity.specialist
            ?: return NOT_AUTHORIZED

        return if (specialistEntity.isCompletelyFilled) {
            AUTHORIZED
        } else {
            PARTIALLY_AUTHORIZED
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

    private fun registerNewUser(info: BaseUpdateInfo): TelegramUser {
        val entity = TelegramUser(
            telegramUserId = info.userId,
            personalTelegramChatId = info.chatId,
            isBot = false,
        )
        return userRepository.save(entity)
    }
}