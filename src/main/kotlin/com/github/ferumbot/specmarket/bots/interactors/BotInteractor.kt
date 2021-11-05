package com.github.ferumbot.specmarket.bots.interactors

interface BotInteractor <IN: Any, OUT: Any?> {

    fun handleTransfer(input: IN): OUT
}