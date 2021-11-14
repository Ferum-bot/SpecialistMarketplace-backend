package com.github.ferumbot.specmarket.bots.state_machine.state

interface CreatingProfileState: BotState

object UserInputInvalidDataScreenState: CreatingProfileState {

    override val screenName: String = "User input invalid data screen state"

    override val previousState: BotState = StartScreenState
}

object UserInputFullNameScreenState: CreatingProfileState {

    override val screenName: String = "User input full name screen state"

    override val previousState: BotState = StartScreenState
}

object UserInputDepartmentScreenState: CreatingProfileState {

    override val screenName: String = "User input department screen state"

    override val previousState: BotState = UserInputFullNameScreenState
}

object UserInputProfessionScreenState: CreatingProfileState {

    override val screenName: String = "User input activity screen state"

    override val previousState: BotState = UserInputDepartmentScreenState
}

object UserInputKeySkillsScreenState: CreatingProfileState {

    override val screenName: String = "User input key skills screen state"

    override val previousState: BotState = UserInputProfessionScreenState
}

object UserInputPortfolioLinkScreenState: CreatingProfileState {

    override val screenName: String = "User input portfolio link screen state"

    override val previousState: BotState = UserInputKeySkillsScreenState
}

object UserInputAboutMeScreenState: CreatingProfileState {

    override val screenName: String = "User input about me screen state"

    override val previousState: BotState = UserInputPortfolioLinkScreenState
}

object UserInputWorkingConditionsScreenState: CreatingProfileState {

    override val screenName: String = "User input working conditions screen state"

    override val previousState: BotState = UserInputAboutMeScreenState
}

object UserInputEducationGradeScreenState: CreatingProfileState {

    override val screenName: String = "User input education grade screen state"

    override val previousState: BotState = UserInputWorkingConditionsScreenState
}

object UserInputContactLinksScreenState: CreatingProfileState {

    override val screenName: String = "User input contact links screen state"

    override val previousState: BotState = UserInputEducationGradeScreenState
}

object ShowHowProfileLooksNowScreenState: CreatingProfileState {

    override val screenName: String = "Show how profile looks now screen state"

    override val previousState: BotState = UserInputFullNameScreenState
}

object ShowProfilePreviewScreenState: CreatingProfileState {

    override val screenName: String = "Show profile preview screen state"

    override val previousState: BotState = UserInputContactLinksScreenState
}