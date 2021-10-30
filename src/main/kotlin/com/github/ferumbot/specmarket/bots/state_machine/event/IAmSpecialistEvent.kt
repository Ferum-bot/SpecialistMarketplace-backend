package com.github.ferumbot.specmarket.bots.state_machine.event

interface IAmSpecialistEvent: BotEvent

object SubmitYourApplicationEvent: IAmSpecialistEvent {
    override val eventName: String = "Submit your application event"
}