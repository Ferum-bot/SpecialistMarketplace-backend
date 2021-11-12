package com.github.ferumbot.specmarket.bots.models.dto.update_info

data class UserSpecialistRequests(

    override val chatId: Long,

    override val userId: Long,
    

    val requests: Collection<S>
): BaseUpdateInfo
