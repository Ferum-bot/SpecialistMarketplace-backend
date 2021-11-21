package com.github.ferumbot.specmarket.bots.state_machine.event

interface FilterEvent: BotEvent

object OpenFilterScreenEvent: FilterEvent {

    override val eventName: String = "Open filter screen event"

    override val commandAlias: String = "/open_filter"

    override val friendlyName: String = "Open filter"
}

object OpenCurrentSpecialistsScreenEvent: FilterEvent {

    override val eventName: String = "Open current specialist screen event"

    override val commandAlias: String = "/filterBy"

    override val friendlyName: String = "Open current specialists"
}

object OpenAnotherSpecialistsPageScreenEvent: FilterEvent {

    override val eventName: String = "Open another specialists page screen event"

    override val commandAlias: String = "/open_another_specialists_page"

    override val friendlyName: String = "Open another specialists page"
}

object GetSpecialistsContactsEvent: FilterEvent {

    override val eventName: String = "Get specialists contacts screen event"

    override val commandAlias: String = "/get_specialists_contacts"

    override val friendlyName: String = "Get specialists contacts"
}