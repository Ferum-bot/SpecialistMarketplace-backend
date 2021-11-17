package com.github.ferumbot.specmarket.bots.services.impl

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserInfo
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.models.entity.embeded.UserBotState
import com.github.ferumbot.specmarket.bots.models.enums.TelegramUserSpecialistStatus
import com.github.ferumbot.specmarket.bots.models.enums.TelegramUserSpecialistStatus.*
import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.machine.StateMachine
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.bots.state_machine.state.UnSupportedScreenState
import com.github.ferumbot.specmarket.models.entities.Specialist
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TelegramUserServiceImpl @Autowired constructor(
    private val repository: TelegramUserRepository
): TelegramUserService {

    @Transactional(readOnly = true)
    override fun userExists(info: BaseUpdateInfo): Boolean {
        val userId = info.userId
        return repository.existsTelegramUserByTelegramUserId(userId)
    }

    override fun registerNewUser(info: RegisterNewUserInfo) {
        val user = TelegramUser(
            isBot = info.isBot,
            languageCode = info.languageCode,
            personalTelegramChatId = info.chatId,
            telegramUserId = info.userId,
            firstName = info.firstName,
            lastName = info.lastName,
            userName = info.userName,
        )

        repository.saveAndFlush(user)
    }

    override fun updateUserInfo(info: RegisterNewUserInfo) {
        val foundUser = repository.findByTelegramUserId(info.userId)

        foundUser?.let { user ->
            user.isBot = info.isBot
            user.languageCode = info.languageCode
            user.firstName = info.firstName
            user.lastName = info.lastName
            user.userName = info.userName
            repository.saveAndFlush(user)
        }
    }

    override fun getAndSetUserPreviousState(info: BaseUpdateInfo): BotState {
        val entity = repository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        val currentState = entity.currentBotState.currentState
        val prevState = currentState.previousState
        entity.currentBotState.currentState = prevState
        repository.saveAndFlush(entity)
        return prevState
    }

    @Transactional(readOnly = true)
    override fun getUserCurrentState(info: BaseUpdateInfo): UserBotState {
        val entity = repository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        return entity.currentBotState
    }

    override fun setNewUserState(newState: BotState, info: BaseUpdateInfo, currentPage: Int?, pageCount: Int?) {
        val entity = repository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        entity.currentBotState.apply {
            currentState = newState
            currentPageNumber = currentPage
            totalAvailablePages = pageCount
        }

        repository.saveAndFlush(entity)
    }

    @Transactional(readOnly = true)
    override fun getUserSpecialistStatus(info: BaseUpdateInfo): TelegramUserSpecialistStatus {
        val userEntity = repository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        val specialistEntity = userEntity.specialist
            ?: return NOT_AUTHORIZED

        return if (specialistEntity.isCompletelyFilled) {
            AUTHORIZED
        } else {
            PARTIALLY_AUTHORIZED
        }
    }

    @Transactional(readOnly = true)
    override fun getUserSpecialist(info: BaseUpdateInfo): Specialist? {
        val user = repository.findByTelegramUserId(info.userId)
        return user?.specialist
    }

    @Transactional(readOnly = true)
    override fun getUserSpecialistRequests(info: BaseUpdateInfo, pageNumber: Int, pageSize: Int): Collection<Specialist> {
        val user = repository.findByTelegramUserId(info.userId)
        return user?.specialistsRequests ?: emptyList()
    }

    @Transactional(readOnly = true)
    override fun countUserSpecialistRequests(info: BaseUpdateInfo): Int {
        return repository.countUserSpecialistRequests(info.userId)
    }

    private fun registerNewUser(info: BaseUpdateInfo): TelegramUser {
        val entity = TelegramUser(
            telegramUserId = info.userId,
            personalTelegramChatId = info.chatId,
            isBot = false,
        )
        return repository.save(entity)
    }
}