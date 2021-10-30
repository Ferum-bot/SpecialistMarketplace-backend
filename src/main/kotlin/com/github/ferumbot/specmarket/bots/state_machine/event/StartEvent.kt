package com.github.ferumbot.specmarket.bots.state_machine.event

interface StartEvent: BotEvent

object OpenContactWithUsScreenEvent: StartEvent {
    override val eventName: String = "Open contact with us screen event"
}

object OpenIAmCustomerScreenEvent: StartEvent {
    override val eventName: String = "Open I am customer screen event"
}

object OpenIAmSpecialistScreenEvent: StartEvent {
    override val eventName: String = "Open I am specialist screen event"
}

object OpenAllSpecialistsScreenEvent: StartEvent {
    override val eventName: String = "Open all specialists screen event"
}

object OpenMyProfileScreenEvent: StartEvent {
    override val eventName: String = "Open my profile screen event"
}

