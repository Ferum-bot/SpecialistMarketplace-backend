package com.github.ferumbot.specmarket.bots.state_machine.state

interface IAmSpecialistState: BotState

object IAmSpecialistInfoScreenState: IAmSpecialistState {

    override val screenName: String = "I am specialist information screen"

    override val previousState: BotState = IAmSpecialistInfoScreenState
}
