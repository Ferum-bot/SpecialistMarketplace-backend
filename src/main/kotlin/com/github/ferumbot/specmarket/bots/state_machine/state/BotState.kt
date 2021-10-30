package com.github.ferumbot.specmarket.bots.state_machine.state

interface BotState {

    /**
     * Unique identifier for each state.
     * @important Must be unique.
     */
    val screenName: String
}

object NotImplementedScreenState: BotState {
    override val screenName: String = "Not implemented screen"
}