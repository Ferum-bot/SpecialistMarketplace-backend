package com.github.ferumbot.specmarket.bots.state_machine.event

interface IAmCustomerEvent: BotEvent

object OpenIDoNotKnowWhatIWantScreenEvent: IAmCustomerEvent {
    override val eventName: String = "Open I don't know what I want screen event"
}

object OpenAboutEachSpecialistScreenEvent: IAmCustomerEvent {
    override val eventName: String = "Open about each specialist screen event"
}