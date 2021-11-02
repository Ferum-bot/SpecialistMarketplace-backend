package com.github.ferumbot.specmarket.bots.state_machine.event

interface IAmSpecialistEvent: BotEvent

object SubmitYourApplicationEvent: IAmSpecialistEvent {

    override val eventName: String = "Submit your application event"

    override val commandAlias: String = "/open_submit_your_application"
}