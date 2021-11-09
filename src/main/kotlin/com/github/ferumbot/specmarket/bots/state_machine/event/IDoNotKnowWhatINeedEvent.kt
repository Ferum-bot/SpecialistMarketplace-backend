package com.github.ferumbot.specmarket.bots.state_machine.event

interface IDoNotKnowWhatINeedEvent: BotEvent

object OpenLeaveBidScreenEvent: IDoNotKnowWhatINeedEvent{

    override val eventName: String = "Open leave bid screen event"

    override val commandAlias: String = "/open_leave_bid"

    override val friendlyName: String = "Leave bid"
}

