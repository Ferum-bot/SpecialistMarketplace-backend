package com.github.ferumbot.specmarket.bots.interceptors

import com.github.ferumbot.specmarket.bots.models.dto.holders.ApiMethodHolder
import org.telegram.telegrambots.meta.api.objects.Update

interface ExceptionInterceptor {

    fun intercept(error: Exception, causedUpdate: Update, currentApiMethod: ApiMethodHolder)

}