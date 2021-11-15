package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.core.extensions.firstOrEmpty

data class BaseUserInputInfo(

    override val chatId: Long,

    override val userId: Long,

    val userInput: Collection<String>,
): BaseUpdateInfo {

    companion object {

        fun from(info: BaseUpdateInfo, input: String): BaseUserInputInfo {
            return BaseUserInputInfo(
                info.chatId, info.userId, listOf(input)
            )
        }

        fun from(info: BaseUpdateInfo, input: Collection<String>): BaseUserInputInfo {
            return BaseUserInputInfo(
                info.chatId, info.userId, input
            )
        }
    }

    val simpleInput = userInput.firstOrEmpty()
}
