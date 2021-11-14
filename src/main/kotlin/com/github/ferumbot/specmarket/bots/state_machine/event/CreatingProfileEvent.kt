package com.github.ferumbot.specmarket.bots.state_machine.event

interface CreatingProfileEvent: BotEvent

object StartRegistrationFlowEvent: CreatingProfileEvent {

    override val eventName: String = "Start registration flow event"

    override val commandAlias: String = "/start_registration_flow"

    override val friendlyName: String = "Start registration flow"
}

object OnUserInputFullNameEvent: CreatingProfileEvent {

    override val eventName: String = "On user input full name event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OnUserInputDepartmentEvent: CreatingProfileEvent {

    override val eventName: String = "On user input department event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OnUserInputProfessionEvent: CreatingProfileEvent {

    override val eventName: String = "On user input profession event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OnUserInputKeySkillsEvent: CreatingProfileEvent {

    override val eventName: String = "On user input key skills event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OnUserInputPortfolioLinkEvent: CreatingProfileEvent {

    override val eventName: String = "On user input portfolio link event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OnUserInputAboutMeEvent: CreatingProfileEvent {

    override val eventName: String = "On user input about me event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OnUserInputWorkingConditionsEvent: CreatingProfileEvent {

    override val eventName: String = "On user input working conditions event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OnUserInputEducationGradeEvent: CreatingProfileEvent {

    override val eventName: String = "On user input education grade event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OnUserInputContactLinksEvent: CreatingProfileEvent {

    override val eventName: String = "On user input contact links event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OpenHowItLooksLikeNowScreenEvent: CreatingProfileEvent {

    override val eventName: String = "Open how it looks like now screen event"

    override val commandAlias: String = "/open_how_it_looks_like_now"

    override val friendlyName: String = "How it looks now"
}

object ContinueCreatingProfileFlowEvent: CreatingProfileEvent {

    override val eventName: String = "Continue creating profile flow event"

    override val commandAlias: String = "/continue_creating_profile"

    override val friendlyName: String = "Continue creating profile"
}

object OnUserRegistrationFinishedEvent: CreatingProfileEvent {

    override val eventName: String = "On user registration finished event"

    override val commandAlias: String = "/finish_registration"

    override val friendlyName: String = "Finish registration"
}

