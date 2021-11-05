package com.github.ferumbot.specmarket.bots.state_machine.event

interface ChatMemberEvent: BotEvent

object BotsChatMemberUpdateEvent: ChatMemberEvent {

    override val eventName: String = "Bot's chat member update event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}