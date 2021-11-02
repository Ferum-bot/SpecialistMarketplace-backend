package com.github.ferumbot.specmarket.bots.state_machine.event

interface IAmCustomerEvent: BotEvent

object OpenIDoNotKnowWhatIWantScreenEvent: IAmCustomerEvent {

    override val eventName: String = "Open I don't know what I want screen event"

    override val commandAlias: String = "/open_i_do_not_know_what_i_want"
}

object OpenAboutEachSpecialistScreenEvent: IAmCustomerEvent {

    override val eventName: String = "Open about each specialist screen event"

    override val commandAlias: String = "/open_about_each_specialist"
}