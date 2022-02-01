package com.github.ferumbot.specmarket.bots.state_machine.state

interface EditProfileState: BotState

object UserChangeFullNameScreenState: EditProfileState {

    override val screenName: String = "User change full name screen state"

    override val previousState: BotState = StartScreenState
}

object UserNicheScreenState: EditProfileState {

    override val screenName: String = "User change niche screen state"

    override val previousState: BotState = StartScreenState
}

object UserChangeProfessionScreenState: EditProfileState {

    override val screenName: String = "User change profession screen state"

    override val previousState: BotState = StartScreenState
}

object UserChangeKeySkillsScreenState: EditProfileState {

    override val screenName: String = "User change key skills screen state"

    override val previousState: BotState = StartScreenState
}

object UserChangePortfolioLinkScreenState: EditProfileState {

    override val screenName: String = "User change portfolio link screen state"

    override val previousState: BotState = StartScreenState
}

object UserChangeAboutMeScreenState: EditProfileState {

    override val screenName: String = "User change about me screen state"

    override val previousState: BotState = StartScreenState
}

object UserChangeWorkingConditionsScreenState: EditProfileState {

    override val screenName: String = "User change working conditions screen state"

    override val previousState: BotState = StartScreenState
}

object UserChangeEducationGradeScreenState: EditProfileState {

    override val screenName: String = "User change education grade screen state"

    override val previousState: BotState = StartScreenState
}

object UserChangeContactLinksScreenState: EditProfileState {

    override val screenName: String = "User change contact links screen state"

    override val previousState: BotState = StartScreenState
}