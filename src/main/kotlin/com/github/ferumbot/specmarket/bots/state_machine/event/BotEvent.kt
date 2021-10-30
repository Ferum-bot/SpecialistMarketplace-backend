package com.github.ferumbot.specmarket.bots.state_machine.event

interface BotEvent {

    /**
     * Unique identifier for each event.
     * @important Must be unique.
     */
    val eventName: String
}
