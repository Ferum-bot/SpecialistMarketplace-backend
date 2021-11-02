package com.github.ferumbot.specmarket.bots.state_machine.event

interface CommonEvent: BotEvent

object GoBackEvent: CommonEvent {

    override val eventName: String = "Go back event"

    override val commandAlias: String = "/back"
}

object OpenStartScreenEvent: CommonEvent {

    override val eventName: String = "Go to start screen event"

    override val commandAlias: String = "/main_page"
}

object RegisterNewUserEvent: CommonEvent {

    override val eventName: String = "Register new user event"

    override val commandAlias: String = "/start"
}

object UnSupportedEvent: CommonEvent {

    override val eventName: String = "Un supported event"

    override val commandAlias: String = ""
}