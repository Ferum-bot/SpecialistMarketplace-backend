package com.github.ferumbot.specmarket.bots.models.entity.embeded

import com.github.ferumbot.specmarket.bots.models.entity.converters.BotStateConverter
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.bots.state_machine.state.UnRegisteredState
import com.github.ferumbot.specmarket.bots.state_machine.state.UnSupportedScreenState
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable

@Embeddable
data class UserBotState(

    @Convert(converter = BotStateConverter::class)
    @Column(name = "current_bot_state", nullable = false)
    var currentState: BotState = UnRegisteredState,

    @Column(name = "current_page_number")
    var currentPageNumber: Int? = null,

    @Column(name = "total_available_pages")
    var totalAvailablePages: Int? = null,
) {

    companion object {

        fun unSupported(): UserBotState {
            return UserBotState(UnSupportedScreenState)
        }
    }
}
