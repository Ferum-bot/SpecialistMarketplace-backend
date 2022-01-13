package com.github.ferumbot.specmarket.bots.state_machine.event

interface EditProfileEvent: BotEvent

/**
 * These events produced when user choose
 * which profile attribute edit.
 */
object ChangeFullNameEvent: EditProfileEvent {

    override val eventName: String = "Change full name event"

    override val friendlyName: String = "Изменить имя и фамилию"

    override val commandAlias: String = "/change_full_name"
}

object ChangeDepartmentEvent: EditProfileEvent {

    override val eventName: String = "Change department event"

    override val friendlyName: String = "Изменить отдел"

    override val commandAlias: String = "/change_department"
}

object ChangeProfessionEvent: EditProfileEvent {

    override val eventName: String = "Change profession event"

    override val friendlyName: String = "Изменить профессию"

    override val commandAlias: String = "/change_profession"
}

object ChangeKeySkillsEvent: EditProfileEvent {

    override val eventName: String = "Change key skills event"

    override val friendlyName: String = "Изменить навыки"

    override val commandAlias: String = "/change_key_skills"
}

object ChangePortfolioLinkEvent: EditProfileEvent {

    override val eventName: String = "Change portfolio link event"

    override val friendlyName: String = "Изменить ссылку на резюме"

    override val commandAlias: String = "/change_portfolio_link"
}

object ChangeAboutMeEvent: EditProfileEvent {

    override val eventName: String = "Change about me event"

    override val friendlyName: String = "Изменить информацию обо мне"

    override val commandAlias: String = "/change_about_me"
}

object ChangeWorkingConditionsEvent: EditProfileEvent {

    override val eventName: String = "Change working conditions event"

    override val friendlyName: String = "Изменить условия работы"

    override val commandAlias: String = "/change_working_conditions"
}

object ChangeEducationGradeEvent: EditProfileEvent {

    override val eventName: String = "Change education grade event"

    override val friendlyName: String = "Изменить образование"

    override val commandAlias: String = "/change_education_grade"
}

object ChangeContactLinksEvent: EditProfileEvent {

    override val eventName: String = "Change contact links event"

    override val friendlyName: String = "Изменить контакты"

    override val commandAlias: String = "/change_contact_links"
}

object FinishProfileEditingEvent: EditProfileEvent {

    override val eventName: String = "Finish profile editing event"

    override val friendlyName: String = "Закончить редактирование профиля"

    override val commandAlias: String = "/finish_profile_editing"
}


/**
 * These events produced when user input new
 * profile attribute.
 */
object OnUserChangedFullNameEvent: EditProfileEvent {

    override val eventName: String = "On user changed full name event"

    override val friendlyName: String = ""

    override val commandAlias: String = ""
}

object OnUserChangedDepartmentEvent: EditProfileEvent {

    override val eventName: String = "On user changed department event"

    override val friendlyName: String = ""

    override val commandAlias: String = ""
}

object OnUserChangedProfessionEvent: EditProfileEvent {

    override val eventName: String = "On user changed profession event"

    override val friendlyName: String = ""

    override val commandAlias: String = ""
}

object OnUserChangedKeySkillsEvent: EditProfileEvent {

    override val eventName: String = "On user changed key skills event"

    override val friendlyName: String = ""

    override val commandAlias: String = ""
}

object OnUserChangedPortfolioLinkEvent: EditProfileEvent {

    override val eventName: String = "On user changed portfolio link event"

    override val friendlyName: String = ""

    override val commandAlias: String = ""
}

object OnUserChangedAboutMeEvent: EditProfileEvent {

    override val eventName: String = "On user changed about me event"

    override val friendlyName: String = ""

    override val commandAlias: String = ""
}

object OnUserChangedWorkingConditionsEvent: EditProfileEvent {

    override val eventName: String = "On user changed working conditions event"

    override val friendlyName: String = ""

    override val commandAlias: String = ""
}

object OnUserChangedEducationGradeEvent: EditProfileEvent {

    override val eventName: String = "On user changed education grade event"

    override val friendlyName: String = ""

    override val commandAlias: String = ""
}

object OnUserChangedContactLinksEvent: EditProfileEvent {

    override val eventName: String = "On user change contact links event"

    override val friendlyName: String = ""

    override val commandAlias: String = ""
}