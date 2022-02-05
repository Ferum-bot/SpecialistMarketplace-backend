package com.github.ferumbot.specmarket.bots.state_machine.state

interface FilterState: BotState

object ProfessionFilterScreenState: FilterState {

    override val screenName: String = "Profession filter screen state"

    override val previousState: BotState = StartScreenState
}

object NicheFilterScreenState: FilterState {

    override val screenName: String = "Niche filter screen state"

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