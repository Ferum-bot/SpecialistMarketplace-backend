package com.github.ferumbot.specmarket.bots.state_machine.event

interface AllSpecialistsEvent: BotEvent

object OpenFilterScreenEvent: AllSpecialistsEvent {

    override val eventName: String = "Open filter specialists screen event"

    override val commandAlias: String = "/open_filter"

    override val friendlyName: String = "Open specialist filter"
}

object OpenIDoNotKnowWhoISearchScreenEvent: AllSpecialistsEvent {

    override val eventName: String = "Open I don't know who i search screen event"

    override val commandAlias: String = "/open_i_do_not_know_who_i_search"

    override val friendlyName: String = "I don't know who I search"
}