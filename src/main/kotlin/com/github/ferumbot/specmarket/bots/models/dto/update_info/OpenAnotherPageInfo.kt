package com.github.ferumbot.specmarket.bots.models.dto.update_info

data class OpenAnotherPageInfo(

    override val chatId: Long,

    override val userId: Long,

    val pageNumber: Int,

    val additionalData: String? = null
): BaseUpdateInfo
