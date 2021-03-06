package com.github.ferumbot.specmarket.bots.adapters.result

import com.github.ferumbot.specmarket.bots.adapters.result.local.LocalUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.models.dto.bunch.MessageUpdateResultBunch
import org.springframework.beans.factory.annotation.Autowired
import org.telegram.telegrambots.meta.api.methods.BotApiMethod

class FacadeResultUpdateAdapter @Autowired constructor(
    private val localAdapters: Collection<LocalUpdateResultAdapter>
): BotUpdateResultAdapter {

    override fun adaptResult(result: MessageUpdateResultBunch<*>): BotApiMethod<*>? {
        var method: BotApiMethod<*>? = null
        for (adapter in localAdapters) {
            if (adapter.isFor(result)) {
                method = adapter.adapt(result)
            }
        }

        return method
    }
}