package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo

data class RegisterNewUserUpdateInfo(

    override val chatId: Long,

    override val userId: Long,

    val firstName: String? = null,

    val lastName: String? = null,

    val userName: String? = null,

    val isBot: Boolean = false,

    val languageCode: String? = null
): BaseUpdateInfo
