package com.github.ferumbot.specmarket.bots.state_machine.event

interface BotEvent {

    /**
     * Unique identifier for each event.
     * @important Must be unique.
     */
    val eventName: String

    /**
     * Unique command alias needed to identify
     * user input command event
     * @important Must be unique
     */
    val commandAlias: String

    /**
     * Unique command friendly name needed to identify
     * what button user pressed
     * @important Must be unique
     */
    val friendlyName: String
}
