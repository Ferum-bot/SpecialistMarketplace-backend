package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getCommandAlias
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.OpenAnotherPageInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenAnotherSpecialistsPageScreenEvent
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenFilterScreenEvent
import org.telegram.telegrambots.meta.api.objects.Update

class FilterEventAdapter: LocalUpdateAdapter {

    companion object {

        private val OPEN_ANOTHER_SPECIALIST_PAGE = OpenAnotherSpecialistsPageScreenEvent.commandAlias

        private val OPEN_FILTER_COMMAND = OpenFilterScreenEvent.commandAlias
        private val OPEN_FILTER_NAME = OpenFilterScreenEvent.friendlyName

        private val handlingEvents = listOf(
            OPEN_FILTER_COMMAND, OPEN_FILTER_NAME
        )
    }

    override fun isFor(update: Update): Boolean {
        val alias = update.getCommandAlias()

        if (alias.isOpenAnotherSpecialistsPageCommand()) {
            return true
        }

        return alias in handlingEvents
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val alias = update.getCommandAlias()

        if (alias.isOpenAnotherSpecialistsPageCommand()) {
            return getOpenAnotherSpecialistsPage(update)
        }

        return when(alias) {
            OPEN_FILTER_COMMAND, OPEN_FILTER_NAME ->
                getOpenFilter(update)
            else ->
                LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun getOpenAnotherSpecialistsPage(update: Update): MessageUpdateBunch<*> {
        val input = update.getCommandAlias()
        val page = input.pageToOpen
        val professionAlias =
        val event = OpenAnotherSpecialistsPageScreenEvent
        val info = OpenAnotherPageInfo
    }

    private fun getOpenFilter(update: Update): MessageUpdateBunch<*> {
        val event = OpenFilterScreenEvent
        val info = BaseUpdateInfo.get(update)

        return MessageUpdateBunch(event, info)
    }

    private fun String.isOpenAnotherSpecialistsPageCommand(): Boolean {
        return startsWith(OPEN_ANOTHER_SPECIALIST_PAGE, ignoreCase = true)
    }

    private val String.pageToOpen: Int
    get() {
        val separator = ':'
        val separatorIndex = indexOf(separator)
        if (separatorIndex == -1) {
            return 1
        }

        return substring(separatorIndex + 1).toInt()
    }
}