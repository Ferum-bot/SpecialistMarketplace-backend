package com.github.ferumbot.specmarket.bots.interceptors

import com.github.ferumbot.specmarket.bots.models.dto.holders.ApiMethodHolder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class ExceptionInterceptorFacade @Autowired constructor(
    private val interceptors: Collection<ExceptionInterceptor>
) {

    fun onExceptionRaised(exception: Exception, update: Update): BotApiMethod<*>? {
        val resultHolder = ApiMethodHolder()
        interceptors.forEach { interceptor ->
            interceptor.intercept(exception, update, resultHolder)
        }
        return resultHolder.apiMethod
    }
}