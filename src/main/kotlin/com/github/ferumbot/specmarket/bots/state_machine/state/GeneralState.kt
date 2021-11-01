package com.github.ferumbot.specmarket.bots.state_machine.state

interface GeneralState: BotState

object StartScreenState: GeneralState {
    override val screenName: String = "Start screen"
}

object ContactWithUsScreenState: GeneralState {
    override val screenName: String = "Contact with us screen"
}

object UnRegisteredState: GeneralState {
    override val screenName: String = "Un register user state"
}