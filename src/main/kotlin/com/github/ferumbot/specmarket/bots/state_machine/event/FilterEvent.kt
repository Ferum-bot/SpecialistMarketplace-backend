package com.github.ferumbot.specmarket.bots.state_machine.event

interface FilterEvent: BotEvent

object OpenFilterScreenEvent: FilterEvent {

    override val eventName: String = "Open filter screen event"

    override val commandAlias: String = "/open_filter"

    override val friendlyName: String = "Open filter"
}