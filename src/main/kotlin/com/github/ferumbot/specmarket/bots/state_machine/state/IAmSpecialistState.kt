package com.github.ferumbot.specmarket.bots.state_machine.state

interface IAmSpecialistState: BotState

object IAmSpecialistInfoScreenState: IAmSpecialistState {

    override val screenName: String = "I am specialist information screen"

    override val previousState: BotState = StartScreenState
}

object MyCVInfoScreenState: IAmSpecialistState {

    override val screenName: String = "My cv information screen state"

    override val previousState: BotState = IAmSpecialistInfoScreenState
}
