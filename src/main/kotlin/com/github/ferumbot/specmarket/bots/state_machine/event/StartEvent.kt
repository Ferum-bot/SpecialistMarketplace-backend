package com.github.ferumbot.specmarket.bots.state_machine.event

interface StartEvent: BotEvent

object OpenContactWithUsScreenEvent: StartEvent {

    override val eventName: String = "Open contact with us screen event"

    override val commandAlias: String = "/open_contact_with_us"
}

object OpenIAmCustomerScreenEvent: StartEvent {

    override val eventName: String = "Open I am customer screen event"

    override val commandAlias: String = "/open_i_am_customer"
}

object OpenIAmSpecialistScreenEvent: StartEvent {

    override val eventName: String = "Open I am specialist screen event"

    override val commandAlias: String = "/open_i_am_specialist"
}

object OpenAllSpecialistsScreenEvent: StartEvent {

    override val eventName: String = "Open all specialists screen event"

    override val commandAlias: String = "/open_all_specialist"
}

object OpenMyProfileScreenEvent: StartEvent {

    override val eventName: String = "Open my profile screen event"

    override val commandAlias: String = "/open_my_profile"
}

