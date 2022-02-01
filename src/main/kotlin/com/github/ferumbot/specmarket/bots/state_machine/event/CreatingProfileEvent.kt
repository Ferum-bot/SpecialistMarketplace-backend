package com.github.ferumbot.specmarket.bots.state_machine.event

interface CreatingProfileEvent: BotEvent

object StartRegistrationFlowEvent: CreatingProfileEvent {

    override val eventName: String = "Start registration flow event"

    override val commandAlias: String = "/start_registration_flow"

    override val friendlyName: String = "Начать регистрацию"
}

object RestartRegistrationFlowEvent: CreatingProfileEvent {

    override val eventName: String = "Restart registration flow event"

    override val commandAlias: String = "/restart_registration_flow"

    override val friendlyName: String = "Зарегистрироваться заново"
}

object OnUserInputFullNameEvent: CreatingProfileEvent {

    override val eventName: String = "On user input full name event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OnUserInputNicheEvent: CreatingProfileEvent {

    override val eventName: String = "On user input niche event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OnUserFinishInputNicheEvent: CreatingProfileEvent {

    override val eventName: String = "On user finish input niche event"

    override val commandAlias: String = "/finish_input_niche"

    override val friendlyName: String = "К следующему пункту"
}

object OnUserInputProfessionEvent: CreatingProfileEvent {

    override val eventName: String = "On user input profession event"

    override val commandAlias: String = ""

    override val friendlyName: String = ""
}

object OnUserFinishInputProfessionEvent: CreatingProfileEvent {

    override val eventName: String = "On user finish input profession event"

    override val commandAlias: String = "/finish_input_profession"

    override val friendlyName: String = "К следующему пункту"
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

    override val friendlyName: String = "Как выглядит профиль сейчас"
}

object ContinueCreatingProfileFlowEvent: CreatingProfileEvent {

    override val eventName: String = "Continue creating profile flow event"

    override val commandAlias: String = "/continue_creating_profile"

    override val friendlyName: String = "Продолжить создание профиля"
}

object OnUserRegistrationFinishedEvent: CreatingProfileEvent {

    override val eventName: String = "On user registration finished event"

    override val commandAlias: String = "/finish_registration"

    override val friendlyName: String = "Закончить регистрацию"
}

