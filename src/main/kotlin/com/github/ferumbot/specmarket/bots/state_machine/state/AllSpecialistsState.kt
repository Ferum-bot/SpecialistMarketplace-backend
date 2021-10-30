package com.github.ferumbot.specmarket.bots.state_machine.state

interface AllSpecialistsState: BotState

object AllSpecialistInfoScreenState: AllSpecialistsState {
    override val screenName: String = "All specialist information screen"
}

object IDoNotKnowWhoISearchScreenState: AllSpecialistsState {
    override val screenName: String = "I don't know who I search screen"
}

object LeaveBidInfoScreenState: AllSpecialistsState {
    override val screenName: String = "Leave bid information screen"
}