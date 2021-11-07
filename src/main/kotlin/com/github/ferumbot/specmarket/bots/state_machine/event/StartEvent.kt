package com.github.ferumbot.specmarket.bots.state_machine.event

interface StartEvent: BotEvent

object OpenContactWithUsScreenEvent: StartEvent {

    override val eventName: String = "Open contact with us screen event"

    override val commandAlias: String = "/open_contact_with_us"

    override val friendlyName: String = "Contact with us"
}

object OpenIAmCustomerScreenEvent: StartEvent {

    override val eventName: String = "Open I am customer screen event"

    override val commandAlias: String = "/open_i_am_customer"

    override val friendlyName: String = "I am Customer"
}

object OpenIAmSpecialistScreenEvent: StartEvent {

    override val eventName: String = "Open I am specialist screen event"

    override val commandAlias: String = "/open_i_am_specialist"

    override val friendlyName: String = "I am Specialist"
}

object OpenAllSpecialistsScreenEvent: StartEvent {

    override val eventName: String = "Open all specialists screen event"

    override val commandAlias: String = "/open_all_specialist"

    override val friendlyName: String = "All specialists"
}

object OpenMyProfileScreenEvent: StartEvent {

    override val eventName: String = "Open my profile screen event"

    override val commandAlias: String = "/open_my_profile"

    override val friendlyName: String = "My profile"
}

