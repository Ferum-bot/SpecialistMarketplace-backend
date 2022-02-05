package com.github.ferumbot.specmarket.bots.state_machine.event

interface FilterEvent: BotEvent

object OpenProfessionFilterScreenEvent: FilterEvent {

    override val eventName: String = "Open profession filter screen event"

    override val commandAlias: String = "/open_profession_filter"

    override val friendlyName: String = "Открыть фильтр"
}

object ApplyProfessionFilterEvent: FilterEvent {

    override val eventName: String = "Apply profession filter event"

    override val commandAlias: String = "/prof"

    override val friendlyName: String = ""

    const val separator = "_"
}

object OpenNicheFilterScreenEvent: FilterEvent {

    override val eventName: String = "Open niche filter screen event"

    override val commandAlias: String = "/open_niche_filter"

    override val friendlyName: String = "Выбрать нишу"
}

object ApplyNicheFilterEvent: FilterEvent {

    override val eventName: String = "Apply niche filter event"

    override val commandAlias: String = "/nich"

    override val friendlyName: String = ""

    const val separator = "_"
}

object OpenCurrentSpecialistsScreenEvent: FilterEvent {

    override val eventName: String = "Open current specialist screen event"

    override val commandAlias: String = "/open_filtered_specialists"

    override val friendlyName: String = "Найти специалисты"
}

object OpenAnotherSpecialistsPageScreenEvent: FilterEvent {

    override val eventName: String = "Open another specialists page screen event"

    override val commandAlias: String = "/open_another_specialists_page"

    override val friendlyName: String = "Следующая страница"
}

object GetSpecialistsContactsEvent: FilterEvent {

    override val eventName: String = "Get specialists contacts screen event"

    override val commandAlias: String = "/get_specialists_contacts"

    override val friendlyName: String = "Получить контакты"
}