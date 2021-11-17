package com.github.ferumbot.specmarket.bots.models.dto.update_info

import org.telegram.telegrambots.meta.api.objects.Update

data class OpenAnotherPageInfo(

    override val chatId: Long,

    override val userId: Long,

    val pageNumber: Int,

    val additionalData: String? = null
): BaseUpdateInfo {

    companion object {

        fun from(update: Update, page: Int, data: String? = null): OpenAnotherPageInfo {
            val info = BaseUpdateInfo.get(update)
            return OpenAnotherPageInfo(
                info.chatId, info.userId, page, data
            )
        }
    }
}
