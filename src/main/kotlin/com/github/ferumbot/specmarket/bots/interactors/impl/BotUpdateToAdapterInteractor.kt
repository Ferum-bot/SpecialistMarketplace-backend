package com.github.ferumbot.specmarket.bots.interactors.impl

import com.github.ferumbot.specmarket.bots.adapters.update.BotUpdateAdapter
import com.github.ferumbot.specmarket.bots.adapters.result.BotUpdateResultAdapter
import com.github.ferumbot.specmarket.bots.interactors.BotInteractor
import org.springframework.beans.factory.annotation.Autowired
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

class BotUpdateToAdapterInteractor @Autowired constructor(
    private val inputAdapter: BotUpdateAdapter,
    private val outputAdapter: BotUpdateResultAdapter,
): BotInteractor<Update, BotApiMethod<*>> {

    override fun handleTransfer(input: Update): BotApiMethod<*> {
        val result = inputAdapter.adaptBotUpdate(input)
        outputAdapter.adaptResult(result)
        TODO("Not yet implemented")
    }
}