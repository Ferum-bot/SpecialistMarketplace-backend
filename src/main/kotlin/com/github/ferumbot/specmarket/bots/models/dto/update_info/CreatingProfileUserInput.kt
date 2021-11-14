package com.github.ferumbot.specmarket.bots.models.dto.update_info

data class CreatingProfileUserInput(

    override val chatId: Long,

    override val userId: Long,

    val userInput: Collection<String>,
): BaseUpdateInfo {

    companion object {

        fun from(info: BaseUpdateInfo, input: String): CreatingProfileUserInput {
            return CreatingProfileUserInput(
                info.chatId, info.userId, listOf(input)
            )
        }

        fun from(info: BaseUpdateInfo, input: Collection<String>): CreatingProfileUserInput {
            return CreatingProfileUserInput(
                info.chatId, info.userId, input
            )
        }

    }
}
