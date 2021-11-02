package com.github.ferumbot.specmarket.bots.processors.local

import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.state_machine.state.UnSupportedScreenState

interface LocalUpdateProcessor {

    companion object {

        fun unSupportedEvent(info: BaseUpdateInfo): MessageUpdateResultBunch<*> {
            val newState = UnSupportedScreenState
            return MessageUpdateResultBunch(newState, info)
        }
    }

    fun canProcess(bunch: MessageUpdateBunch<*>): Boolean

    fun process(bunch: MessageUpdateBunch<*>): MessageUpdateResultBunch<*>
}