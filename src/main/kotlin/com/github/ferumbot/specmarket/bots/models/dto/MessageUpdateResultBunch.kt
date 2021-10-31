package com.github.ferumbot.specmarket.bots.models.dto

import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.bots.state_machine.state.StartScreenState

data class MessageUpdateResultBunch <T: Any> (

    val newState: BotState = StartScreenState,

    val resultData: T? = null,
) {

    fun <N: Any> copyWith(): MessageUpdateResultBunch<N> {
        return MessageUpdateResultBunch(newState, resultData as? N)
    }
}
