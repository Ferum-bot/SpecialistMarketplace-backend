package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.core.extensions.firstOrEmpty
import org.telegram.telegrambots.meta.api.objects.Update

data class BaseDataInfo(

    override val chatId: Long,

    override val userId: Long,

    val userInput: Collection<String>,
): BaseUpdateInfo {

    companion object {

        fun from(update: Update, input: String): BaseDataInfo {
            val info = BaseUpdateInfo.from(update)
            return BaseDataInfo(
                info.chatId, info.userId, listOf(input)
            )
        }

        fun from(info: BaseUpdateInfo, input: String): BaseDataInfo {
            return BaseDataInfo(
                info.chatId, info.userId, listOf(input)
            )
        }

        fun from(info: BaseUpdateInfo, input: Collection<String>): BaseDataInfo {
            return BaseDataInfo(
                info.chatId, info.userId, input
            )
        }
    }

    val simpleInput = userInput.firstOrEmpty()
}
