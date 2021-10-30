package com.github.ferumbot.specmarket.bots.state_machine.event

interface AllSpecialistsEvent: BotEvent

object OpenFilterScreenEvent: AllSpecialistsEvent {
    override val eventName: String = "Open filter specialists screen event"
}

object OpenIDoNotKnowWhoISearchScreenEvent: AllSpecialistsEvent {
    override val eventName: String = "Open I don't know who i search screen event"
}