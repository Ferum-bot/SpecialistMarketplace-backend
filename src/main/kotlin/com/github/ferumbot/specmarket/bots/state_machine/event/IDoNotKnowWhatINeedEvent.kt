package com.github.ferumbot.specmarket.bots.state_machine.event

interface IDoNotKnowWhatINeedEvent: BotEvent

object OpenLeaveBidScreenEvent: IDoNotKnowWhatINeedEvent{

    override val eventName: String = "Open leave bid screen event"

    override val commandAlias: String = "/open_leave_bid"

    override val friendlyName: String = "Leave bid"
}

object OpenAboutEachSpecialistScreenEvent: IDoNotKnowWhatINeedEvent {

    override val eventName: String = "Open about each specialist screen event"

    override val commandAlias: String = "/open_about_each_specialist"

    override val friendlyName: String = "About each specialist"
}

object OpenAllSelectedSpecialistsScreenEvent: IDoNotKnowWhatINeedEvent {

    override val eventName: String = "Open all selected specialists screen event"

    override val commandAlias: String = "/open_all_selected_specialists"

    override val friendlyName: String = "All selected specialists"
}
