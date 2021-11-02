package com.github.ferumbot.specmarket.bots.services.impl

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserUpdateInfo
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.machine.StateMachine
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.bots.state_machine.state.UnSupportedScreenState
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TelegramUserServiceImpl(
    private val repository: TelegramUserRepository
): TelegramUserService {

    override fun registerNewUser(info: RegisterNewUserUpdateInfo) {
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

    override fun getAndSetUserPreviousState(info: BaseUpdateInfo): BotState {
        val entity = repository.findByTelegramUserId(info.userId)
        entity ?: return UnSupportedScreenState
        val currentStateName = entity.currentBotState
        val currentState = StateMachine.getStateByName(currentStateName)
        val prevState = currentState.previousState
        entity.currentBotState = prevState.screenName
        repository.saveAndFlush(entity)
        return prevState
    }

    override fun setNewUserState(newState: BotState, info: BaseUpdateInfo) {
        val entity = repository.findByTelegramUserId(info.userId)
        entity ?: return
        entity.currentBotState = newState.screenName
        repository.saveAndFlush(entity)
    }
}