package com.github.ferumbot.specmarket.bots.services.impl

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.RegisterNewUserUpdateInfo
import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import com.github.ferumbot.specmarket.bots.services.TelegramUserService
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TelegramUserServiceImpl(
    private val repository: TelegramUserRepository
): TelegramUserService {

    override fun registerNewUser(info: RegisterNewUserUpdateInfo) {
        TODO("Not yet implemented")
    }

    override fun getAndSetUserPreviousState(info: BaseUpdateInfo): BotState {
        TODO("Not yet implemented")
    }

    override fun setNewUserState(newState: BotState, info: BaseUpdateInfo) {
        TODO("Not yet implemented")
    }
}