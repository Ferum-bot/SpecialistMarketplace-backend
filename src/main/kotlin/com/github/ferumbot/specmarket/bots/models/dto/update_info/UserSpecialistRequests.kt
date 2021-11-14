package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.models.dto.SpecialistDto

data class UserSpecialistRequests(

    override val chatId: Long,

    override val userId: Long,

    val requests: Collection<SpecialistDto>,

    val currentPageNumber: Int,

    val totalPageCount: Int,
): BaseUpdateInfo
