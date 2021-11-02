package com.github.ferumbot.specmarket.bots.state_machine.state

interface BotState {

    /**
     * Unique identifier for each state.
     * @important Must be unique.
     */
    val screenName: String

    /**
     * Previous screen state. Needed to
     * navigate back between screens.
     */
    val previousState: BotState
}

object NotImplementedScreenState: BotState {

    override val screenName: String = "Not implemented screen"

    override val previousState: BotState = StartScreenState
}

object UnSupportedScreenState: BotState {

    override val screenName: String = "Not supported screen"

    override val previousState: BotState = StartScreenState
}