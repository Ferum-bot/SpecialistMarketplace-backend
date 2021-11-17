package com.github.ferumbot.specmarket.bots.state_machine.event

interface FilterEvent: BotEvent

object OpenFilterScreenEvent: FilterEvent {

    override val eventName: String = "Open filter screen event"

    override val commandAlias: String = "/open_filter"

    override val friendlyName: String = "Open filter"
}

object OpenCurrentSpecialistsScreenEvent: FilterEvent {

    override val eventName: String = "Open current specialist screen event"

    override val commandAlias: String = "/open_current_specialists"

    override val friendlyName: String = "Open current specialists"
}

object OpenAnotherSpecialistsPageScreenEvent: FilterEvent {

    override val eventName: String = "Open another specialists page screen event"

    override val commandAlias: String = "/open_another_specialists_page:"

    override val friendlyName: String = "Open another specialists page"
}