package com.github.ferumbot.specmarket.bots.state_machine.event

interface IAmCustomerEvent: BotEvent

object OpenIDoNotKnowWhatIWantScreenEvent: IAmCustomerEvent {

    override val eventName: String = "Open I don't know what I want screen event"

    override val commandAlias: String = "/open_i_do_not_know_what_i_want"

    override val friendlyName: String = "Я не знаю, кто мне нужен"
}