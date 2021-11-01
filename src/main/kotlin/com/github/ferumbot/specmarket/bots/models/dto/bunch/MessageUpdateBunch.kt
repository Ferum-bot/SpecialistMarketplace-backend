package com.github.ferumbot.specmarket.bots.models.dto.bunch

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.BotEvent

data class MessageUpdateBunch <T: BaseUpdateInfo> (

    val causedEvent: BotEvent,

    val extraInformation: T,
) {

    inline fun <reified N: BaseUpdateInfo> extraInformationIsType(): Boolean {
        return extraInformation is N
    }

    fun <N: BaseUpdateInfo> castToType(): MessageUpdateBunch<N> {
        return MessageUpdateBunch(causedEvent, extraInformation as N)
    }
}
