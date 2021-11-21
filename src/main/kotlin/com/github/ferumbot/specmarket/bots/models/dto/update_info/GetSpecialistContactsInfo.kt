package com.github.ferumbot.specmarket.bots.models.dto.update_info

import org.telegram.telegrambots.meta.api.objects.Update

data class GetSpecialistContactsInfo(

    override val chatId: Long,

    override val userId: Long,

    val specialistId: Long
): BaseUpdateInfo {

    companion object {

        fun from(update: Update, specialistId: Long): GetSpecialistContactsInfo {
            val info = BaseUpdateInfo.from(update)
            return GetSpecialistContactsInfo(
                info.chatId, info.userId, specialistId
            )
        }
    }
}
