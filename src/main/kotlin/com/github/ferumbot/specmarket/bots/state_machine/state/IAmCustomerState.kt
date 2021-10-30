package com.github.ferumbot.specmarket.bots.state_machine.state

interface IAmCustomerState: BotState

object IAmCustomerInfoScreenState: IAmCustomerState {
    override val screenName: String = "I am customer information screen"
}

object IDoNotKnowWhatIWantScreenState: IAmCustomerState {
    override val screenName: String = "I don't know what I want screen"
}

object AboutEachSpecialistScreenState: IAmCustomerState {
    override val screenName: String = "About each specialist screen"
}