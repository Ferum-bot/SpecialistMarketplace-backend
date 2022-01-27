package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile

data class UserSpecialistInfo(

    override val chatId: Long,

    override val userId: Long,

    val specialist: SpecialistDto,
): BaseUpdateInfo {

    companion object {

        fun getFrom(info: BaseUpdateInfo, specialist: SpecialistProfile): UserSpecialistInfo {
            return UserSpecialistInfo(
                chatId = info.chatId,
                userId = info.userId,
                specialist = SpecialistDto.from(specialist),
            )
        }
    }
}
