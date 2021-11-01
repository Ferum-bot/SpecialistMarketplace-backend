package com.github.ferumbot.specmarket.bots.state_machine.event

interface CommonEvent: BotEvent

object GoBackEvent: CommonEvent {
    override val eventName: String = "Go back event"
}

object OpenStartScreenEvent: CommonEvent {
    override val eventName: String = "Go to start screen event"
}

object OpenLeaveBidScreenEvent: CommonEvent {
    override val eventName: String = "Open leave bid screen event"
}

object RegisterNewUserEvent: CommonEvent {
    override val eventName: String = "Register new user event"
}

object UnSupportedEvent: CommonEvent {
    override val eventName: String = "Un supported event"
}