package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.models.dto.NicheDto

data class NichesInfo(

    override val userId: Long,

    override val chatId: Long,

    val niches: Collection<NicheDto>
): BaseUpdateInfo {

    companion object {

        fun from(info: BaseUpdateInfo, niches: Collection<NicheDto>): NichesInfo {
            return NichesInfo(info.userId, info.chatId, niches)
        }
    }
}
