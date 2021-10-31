package com.github.ferumbot.specmarket.bots.adapters.result

import com.github.ferumbot.specmarket.bots.adapters.result.BotUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import com.github.ferumbot.specmarket.bots.models.dto.MessageUpdateResultBunch
import org.springframework.beans.factory.annotation.Autowired
import org.telegram.telegrambots.meta.api.methods.BotApiMethod

class FacadeResultUpdateAdapter @Autowired constructor(
    private val localAdapters: Collection<LocalUpdateResultAdapter>
): BotUpdateResultAdapter {

    override fun adaptResult(result: MessageUpdateResultBunch<*>): BotApiMethod<*> {
        lateinit var method: BotApiMethod<*>
        for (adapter in localAdapters) {
            if (adapter.isFor(result)) {
                method = adapter.adapt(result)
            }
        }

        return method
    }
}