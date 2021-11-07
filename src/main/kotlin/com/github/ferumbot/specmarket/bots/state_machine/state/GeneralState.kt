package com.github.ferumbot.specmarket.bots.state_machine.state

interface GeneralState: BotState

object StartScreenState: GeneralState {

    override val screenName: String = "Start screen"

    override val previousState: BotState = StartScreenState
}

object ContactWithUsScreenState: GeneralState {

    override val screenName: String = "Contact with us screen"

    override val previousState: BotState = StartScreenState
}

object UnRegisteredState: GeneralState {

    override val screenName: String = "Un register user state"

    override val previousState: BotState = StartScreenState
}