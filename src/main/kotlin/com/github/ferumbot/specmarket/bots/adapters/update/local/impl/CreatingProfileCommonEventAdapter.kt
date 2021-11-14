package com.github.ferumbot.specmarket.bots.adapters.update.local.impl

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateBunch
import org.telegram.telegrambots.meta.api.objects.Update

class CreatingProfileCommonEventAdapter: LocalUpdateAdapter {

    override fun isFor(update: Update): Boolean {
        TODO("Not yet implemented")
    }

    override fun adapt(update: Update): MessageUpdateBunch<*> {
        TODO("Not yet implemented")
    }
}