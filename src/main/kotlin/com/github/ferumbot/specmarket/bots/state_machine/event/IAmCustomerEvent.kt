package com.github.ferumbot.specmarket.bots.state_machine.event

interface IAmCustomerEvent: BotEvent

object OpenIDoNotKnowWhatIWantScreenEvent: IAmCustomerEvent {

    override val eventName: String = "Open I don't know what I want screen event"

    override val commandAlias: String = "/open_i_do_not_know_what_i_want"

    override val friendlyName: String = "I don't know what I want"
}

object OpenAboutEachSpecialistScreenEvent: IAmCustomerEvent {

    override val eventName: String = "Open about each specialist screen event"

    override val commandAlias: String = "/open_about_each_specialist"

    override val friendlyName: String = "About each specialist"
}

object OpenAllSelectedSpecialistsScreenEvent: IAmCustomerEvent {

    override val eventName: String = "Open all selected specialists screen event"

    override val commandAlias: String = "/open_all_selected_specialists"

    override val friendlyName: String = "All selected specialists"
}