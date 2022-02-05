package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.core.getCommandAlias
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseDataInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.GetSpecialistContactsInfo
import com.github.ferumbot.specmarket.bots.models.dto.update_info.OpenAnotherPageInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.*
import com.github.ferumbot.specmarket.core.extensions.second
import org.telegram.telegrambots.meta.api.objects.Update

class FilterEventAdapter: LocalUpdateAdapter {

    companion object {

        private val OPEN_ANOTHER_SPECIALIST_PAGE = OpenAnotherSpecialistsPageScreenEvent.commandAlias
        private val OPEN_CURRENT_SPECIALIST_PAGE = OpenCurrentSpecialistsScreenEvent.commandAlias

        private val APPLY_PROFESSION_FILTER = ApplyProfessionFilterEvent.commandAlias
        private val APPLY_NICHE_FILTER = ApplyNicheFilterEvent.commandAlias

        private val OPEN_FILTER_COMMAND = OpenProfessionFilterScreenEvent.commandAlias
        private val OPEN_FILTER_NAME = OpenProfessionFilterScreenEvent.friendlyName

        private val GET_SPECIALISTS_CONTACTS_COMMAND = GetSpecialistsContactsEvent.commandAlias


        private val handlingEvents = listOf(
            OPEN_FILTER_COMMAND, OPEN_FILTER_NAME
        )
    }

    override fun isFor(update: Update): Boolean {
        val alias = update.getCommandAlias()

        if (alias.isApplyProfessionFilterCommand()) {
            return true
        }
        if (alias.isApplyNicheFilterCommand()) {
            return true
        }
        if (alias.isOpenAnotherSpecialistsPageCommand()) {
            return true
        }
        if (alias.isOpenCurrentSpecialistsCommand()) {
            return true
        }
        if (alias.isGetSpecialistsContactsCommand()) {
            return true
        }

        return alias in handlingEvents
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        val alias = update.getCommandAlias()

        if (alias.isApplyProfessionFilterCommand()) {
            return getApplyProfessionFilter(update)
        }
        if (alias.isApplyNicheFilterCommand()) {
            return getApplyNicheFilter(update)
        }
        if (alias.isOpenAnotherSpecialistsPageCommand()) {
            return getOpenAnotherSpecialistsPage(update)
        }
        if (alias.isOpenCurrentSpecialistsCommand()) {
            return getOpenCurrentSpecialists(update)
        }
        if (alias.isGetSpecialistsContactsCommand()) {
            return getSpecialistContacts(update)
        }

        return when(alias) {
            OPEN_FILTER_COMMAND, OPEN_FILTER_NAME ->
                getOpenFilter(update)
            else ->
                LocalUpdateAdapter.unSupportedUpdate(update)
        }
    }

    private fun getOpenAnotherSpecialistsPage(update: Update): MessageUpdateBunch<*> {
        val separator = ':'
        val input = update.getCommandAlias()
        val page = input.split(separator).second().toInt()
        val professionAlias = input.split(separator).last()
        val event = OpenAnotherSpecialistsPageScreenEvent
        val info = OpenAnotherPageInfo.from(update, page, professionAlias)

        return MessageUpdateBunch(event, info)
    }

    private fun getOpenCurrentSpecialists(update: Update): MessageUpdateBunch<*> {
        val event = OpenCurrentSpecialistsScreenEvent
        val info = BaseUpdateInfo.from(update)

        return MessageUpdateBunch(event, info)
    }

    private fun getSpecialistContacts(update: Update): MessageUpdateBunch<*> {
        val input = update.getCommandAlias()
        val specialistId = input.split(':').last().toLong()
        val event = GetSpecialistsContactsEvent
        val info = GetSpecialistContactsInfo.from(update, specialistId)

        return MessageUpdateBunch(event, info)
    }

    private fun getOpenFilter(update: Update): MessageUpdateBunch<*> {
        val event = OpenProfessionFilterScreenEvent
        val info = BaseUpdateInfo.from(update)

        return MessageUpdateBunch(event, info)
    }

    private fun getApplyProfessionFilter(update: Update): MessageUpdateBunch<*> {
        val separator = ApplyProfessionFilterEvent.separator
        val input = update.getCommandAlias()
        val professionAlias = input.getFilterAlias(separator)
        val event = ApplyProfessionFilterEvent
        val info = BaseDataInfo.from(update, professionAlias)

        return MessageUpdateBunch(event, info)
    }

    private fun getApplyNicheFilter(update: Update): MessageUpdateBunch<*> {
        val separator = ApplyNicheFilterEvent.separator
        val input = update.getCommandAlias()
        val nicheAlias = input.getFilterAlias(separator)
        val event = ApplyNicheFilterEvent
        val info = BaseDataInfo.from(update, nicheAlias)

        return MessageUpdateBunch(event, info)
    }

    private fun String.isOpenAnotherSpecialistsPageCommand(): Boolean {
        return startsWith(OPEN_ANOTHER_SPECIALIST_PAGE, ignoreCase = true)
    }

    private fun String.isOpenCurrentSpecialistsCommand(): Boolean {
        return startsWith(OPEN_CURRENT_SPECIALIST_PAGE, ignoreCase = true)
    }

    private fun String.isGetSpecialistsContactsCommand(): Boolean {
        return startsWith(GET_SPECIALISTS_CONTACTS_COMMAND, ignoreCase = true)
    }

    private fun String.isApplyProfessionFilterCommand(): Boolean {
        return startsWith(APPLY_PROFESSION_FILTER, ignoreCase = true)
    }

    private fun String.isApplyNicheFilterCommand(): Boolean {
        return startsWith(APPLY_NICHE_FILTER, ignoreCase = true)
    }

    private fun String.getFilterAlias(separator: String): String {
        return split(separator)
            .toMutableList()
            .apply { removeFirst() }
            .joinToString(separator)
    }
}