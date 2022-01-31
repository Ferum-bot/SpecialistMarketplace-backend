package com.github.ferumbot.specmarket.bots.state_machine.event

interface AllSpecialistsEvent: BotEvent

object OpenIDoNotKnowWhoISearchScreenEvent: AllSpecialistsEvent {

    override val eventName: String = "Open I don't know who i search screen event"

    override val commandAlias: String = "/open_i_do_not_know_who_i_search"

    override val friendlyName: String = "Я не знаю, кто мне нужен"
}