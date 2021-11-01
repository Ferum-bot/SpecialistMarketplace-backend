package com.github.ferumbot.specmarket.bots.models.dto.bunch

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.bots.state_machine.state.StartScreenState

data class MessageUpdateResultBunch <T: BaseUpdateInfo> (

    val newState: BotState = StartScreenState,

    val resultData: T,
) {

    inline fun <reified N: BaseUpdateInfo> extraInformationIsType(): Boolean {
        return resultData is N
    }

    fun <N: BaseUpdateInfo> copyWith(): MessageUpdateResultBunch<N> {
        return MessageUpdateResultBunch(newState, resultData as N)
    }
}
