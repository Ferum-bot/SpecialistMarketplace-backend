package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.models.dto.SpecialistDto

data class SpecialistsPageInfo(

    override val chatId: Long,

    override val userId: Long,

    val specialists: Collection<SpecialistDto>,

    val currentPageNumber: Int,

    val totalPageCount: Int,
): BaseUpdateInfo {

    companion object {

        fun from(
            info: BaseUpdateInfo, specialists: Collection<SpecialistDto>, currentPage: Int, totalPages: Int
        ): SpecialistsPageInfo {
            return SpecialistsPageInfo(
                info.chatId, info.userId, specialists, currentPage, totalPages,
            )
        }
    }
}
