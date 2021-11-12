package com.github.ferumbot.specmarket.bots.state_machine.state

interface ProfileState: BotState

object YouAreNotAuthorizedScreenState: ProfileState {

    override val screenName: String = "You are not authorized screen state"

    override val previousState: BotState = StartScreenState
}

object YouAreNotFullAuthorizedScreenState: ProfileState {

    override val screenName: String = "You are not full authorized screen state"

    override val previousState: BotState = StartScreenState
}

object YouAreAuthorizedScreenState: ProfileState {

    override val screenName: String = "You are authorized screen state"

    override val previousState: BotState = StartScreenState
}

object MyRequestsScreenState: ProfileState {

    override val screenName: String = "My requests screen state"

    override val previousState: BotState = StartScreenState
}

object EditProfileScreenState: ProfileState {

    override val screenName: String = "Edit profile screen state"

    override val previousState: BotState = StartScreenState
}