package com.github.ferumbot.specmarket.bots.models.dto.update_info

data class CreatingProfileUserInput(

    override val chatId: Long,

    override val userId: Long,

    val userInput: List<String>,
): BaseUpdateInfo
