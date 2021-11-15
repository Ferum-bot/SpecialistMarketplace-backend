package com.github.ferumbot.specmarket.bots.interceptors.impl

import com.github.ferumbot.specmarket.bots.interceptors.ExceptionInterceptor
import com.github.ferumbot.specmarket.bots.models.dto.holders.ApiMethodHolder
import org.telegram.telegrambots.meta.api.objects.Update

class CommonExceptionInterceptor: ExceptionInterceptor {

    override fun intercept(error: Exception, causedUpdate: Update, currentApiMethod: ApiMethodHolder) {
        TODO("Not yet implemented")
    }
}