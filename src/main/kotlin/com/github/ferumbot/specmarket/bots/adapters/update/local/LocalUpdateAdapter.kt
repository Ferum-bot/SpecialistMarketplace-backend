package com.github.ferumbot.specmarket.bots.adapters.update.local

import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateBunch
import org.telegram.telegrambots.meta.api.objects.Update

interface LocalUpdateAdapter {

    fun isFor(update: Update): Boolean

    fun adapt(update: Update): MessageUpdateBunch<*>
}