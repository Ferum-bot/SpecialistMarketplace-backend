package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.models.dto.ProfessionDto

data class ProfessionsInfo(

    override val userId: Long,

    override val chatId: Long,

    val professions: Collection<ProfessionDto>
): BaseUpdateInfo {

    companion object {

        fun from(info: BaseUpdateInfo, professions: Collection<ProfessionDto>): ProfessionsInfo {
            return ProfessionsInfo(info.userId, info.chatId, professions)
        }
    }
}