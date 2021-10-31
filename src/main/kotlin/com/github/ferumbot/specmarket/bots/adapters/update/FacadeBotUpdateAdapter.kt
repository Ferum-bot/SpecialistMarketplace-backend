package com.github.ferumbot.specmarket.bots.adapters.update

import com.github.ferumbot.specmarket.bots.adapters.update.local.LocalUpdateAdapter
import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateBunch
import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateResultBunch
import org.springframework.beans.factory.annotation.Autowired
import org.telegram.telegrambots.meta.api.objects.Update

class FacadeBotUpdateAdapter @Autowired constructor(
    private val interactor: BotInteractor<MessageUpdateBunch<*>, MessageUpdateResultBunch<*>>,
    private val localAdapters: Collection<LocalUpdateAdapter>,
): BotUpdateAdapter {

    override fun adaptBotUpdate(update: Update): MessageUpdateResultBunch<*> {
        lateinit var bunch: MessageUpdateBunch<*>
        for (adapter in localAdapters) {
            if (adapter.isFor(update)) {
                bunch = adapter.adapt(update)
                break
            }
        }

        return interactor.handleTransfer(bunch)
    }
}