package com.github.ferumbot.specmarket.bots.models.dto

import com.github.ferumbot.specmarket.bots.state_machine.event.BotEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import kotlin.reflect.KClass

data class MessageUpdateBunch <T: Any> (

    val currentState: BotState,

    val causedEvent: BotEvent,

    val chatId: Long,

    val userId: Long,

    val extraInformation: T? = null,
) {

    inline fun <reified N: Any> extraInformationIsType(): Boolean {
        return extraInformation is N
    }

    fun <N: Any> castToType(): MessageUpdateBunch<N> {
        return MessageUpdateBunch(
            currentState, causedEvent, chatId, userId, extraInformation as? N
        )
    }
}
