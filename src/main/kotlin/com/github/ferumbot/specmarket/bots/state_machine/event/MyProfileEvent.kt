package com.github.ferumbot.specmarket.bots.state_machine.event

interface MyProfileEvent: BotEvent

object OpenMyRequestsScreenEvent: MyProfileEvent {

    override val eventName: String = "Open my requests screen event"

    override val commandAlias: String = "/open_my_requests"

    override val friendlyName: String = "Мои заказы"
}

object OpenAnotherMyRequestsPageScreenEvent: MyProfileEvent {

    override val eventName: String = "Open another my requests screen event"

    override val commandAlias: String = "/open_my_requests_page:"

    override val friendlyName: String = "Мои заказы (другая страница)"
}

object DeleteMyRequestFromHistoryScreenEvent: MyProfileEvent {

    override val eventName: String = "Delete my specialist request from history screen event"

    override val commandAlias: String = "/delete_my_request_number:"

    override val friendlyName: String = "Удалить заявку из истории"
}

object OpenEditInfoScreenEvent: MyProfileEvent {

    override val eventName: String = "Open edit information screen event"

    override val commandAlias: String = "/open_edit_information"

    override val friendlyName: String = "Редактировать информацию"
}

object ChangeProfileSpecialistVisibilityScreenEvent: MyProfileEvent {

    override val eventName: String = "Change profile specialist visibility screen event"

    override val commandAlias: String = "/change_profile_visibility"

    override val friendlyName: String = "Поменять видимость профиля"
}