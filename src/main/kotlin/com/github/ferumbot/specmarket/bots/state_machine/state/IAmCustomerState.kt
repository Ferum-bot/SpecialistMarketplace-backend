package com.github.ferumbot.specmarket.bots.state_machine.state

interface IAmCustomerState: BotState

object IAmCustomerInfoScreenState: IAmCustomerState {

    override val screenName: String = "I am customer information screen"

    override val previousState: BotState = StartScreenState
}

object IDoNotKnowWhatIWantScreenState: IAmCustomerState {

    override val screenName: String = "I don't know what I want screen"

    override val previousState: BotState = IAmCustomerInfoScreenState
}

object AboutEachSpecialistScreenState: IAmCustomerState {

    override val screenName: String = "About each specialist screen"

    override val previousState: BotState = IAmCustomerInfoScreenState
}