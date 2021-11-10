package com.github.ferumbot.specmarket.bots.state_machine.event

interface MyProfileEvent: BotEvent

object OpenMyRequestsScreenEvent: MyProfileEvent {

    override val eventName: String = "Open my requests screen event"

    override val commandAlias: String = "/open_my_requests"

    override val friendlyName: String = "Open my requests"
}

object OpenEditInfoScreenEvent: MyProfileEvent {

    override val eventName: String = "Open edit information screen event"

    override val commandAlias: String = "/open_edit_information"

    override val friendlyName: String = "Open edit information"
}