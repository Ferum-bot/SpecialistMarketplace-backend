package com.github.ferumbot.specmarket.bots.state_machine.state

interface FilterState: BotState

object FilterScreenState: FilterState {

    override val screenName: String = "Filter screen state"

    override val previousState: BotState = StartScreenState
}

object CurrentSpecialistsScreenState: FilterState {

    override val screenName: String = "Current specialists screen state"

    override val previousState: BotState = StartScreenState
}

object CurrentSpecialistsContactsScreenState: FilterState {

    override val screenName: String = "Current specialists contacts screen state"

    override val previousState: BotState = StartScreenState
}