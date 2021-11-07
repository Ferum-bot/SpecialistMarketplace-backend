package com.github.ferumbot.specmarket.bots.state_machine.state

interface AllSpecialistsState: BotState

object AllSpecialistInfoScreenState: AllSpecialistsState {

    override val screenName: String = "All specialist information screen"

    override val previousState: BotState = StartScreenState
}

object IDoNotKnowWhoISearchScreenState: AllSpecialistsState {

    override val screenName: String = "I don't know who I search screen"

    override val previousState: BotState = AllSpecialistInfoScreenState
}

object LeaveBidInfoScreenState: AllSpecialistsState {

    override val screenName: String = "Leave bid information screen"

    override val previousState: BotState = IDoNotKnowWhoISearchScreenState
}