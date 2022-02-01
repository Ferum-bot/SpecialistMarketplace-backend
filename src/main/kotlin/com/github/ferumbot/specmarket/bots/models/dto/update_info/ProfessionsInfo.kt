package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.models.dto.ProfessionDto

data class ProfessionsInfo(

    override val userId: Long,

    override val chatId: Long,

    val professions: Collection<ProfessionDto>,

    val isFirstMessage: Boolean = true,
): BaseUpdateInfo {

    companion object {

        fun from(
            info: BaseUpdateInfo,
            professions: Collection<ProfessionDto>,
            isFirstMessage: Boolean = true,
        ): ProfessionsInfo {
            return ProfessionsInfo(
                info.userId, info.chatId, professions, isFirstMessage
            )
        }
    }
}