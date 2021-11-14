package com.github.ferumbot.specmarket.bots.models.dto.update_info

data class OpenAnotherPageRequest(

    override val chatId: Long,

    override val userId: Long,

    val pageNumber: Int,
): BaseUpdateInfo
