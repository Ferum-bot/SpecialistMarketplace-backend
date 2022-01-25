package com.github.ferumbot.specmarket.bots.interceptors.impl

import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.interceptors.ExceptionInterceptor
import com.github.ferumbot.specmarket.bots.models.dto.holders.ApiMethodHolder
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import java.lang.StringBuilder

@Component
class DebugExceptionInterceptor: ExceptionInterceptor {

    override fun intercept(error: Exception, causedUpdate: Update, currentApiMethod: ApiMethodHolder) {
        error.printStackTrace()

        val chatId = causedUpdate.getChatId().toString()
        val text = StringBuilder()
            .append("An exception raised!\n")
            .append("Localized message: ${error.localizedMessage} \n")
            .append("Message: ${error.message} \n")
            .append("Suppressed: ${error.suppressed} \n")
            .append("Stack trace: ${error.stackTrace} \n")
            .toString()
        val sendMessage = SendMessage(chatId, text)

        currentApiMethod.apiMethod = sendMessage
    }
}