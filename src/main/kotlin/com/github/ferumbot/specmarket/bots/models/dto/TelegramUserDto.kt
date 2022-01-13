package com.github.ferumbot.specmarket.bots.models.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState

data class TelegramUserDto(

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val id: Long? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val telegramUserId: Long,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val personalTelegramChatId: Long,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val firstName: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val lastName: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val userName: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val currentBotState: BotState,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val isBot: Boolean = false,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val languageCode: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val linkedSpecialistId: Long? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val specialistsRequestsId: Collection<Long?> = emptyList(),
) {

    companion object {

        fun from(user: TelegramUser): TelegramUserDto {

            return with(user) {
                TelegramUserDto(
                    id, telegramUserId, personalTelegramChatId, firstName,
                    lastName, userName, currentBotState.currentState, isBot,
                    languageCode, specialist?.id, specialistsRequests.map { it.id }
                )
            }
        }
    }
}
