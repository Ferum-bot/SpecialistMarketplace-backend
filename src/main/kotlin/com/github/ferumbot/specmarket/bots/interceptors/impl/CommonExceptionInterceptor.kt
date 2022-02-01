package com.github.ferumbot.specmarket.bots.interceptors.impl

import com.github.ferumbot.specmarket.bots.core.getChatId
import com.github.ferumbot.specmarket.bots.interceptors.ExceptionInterceptor
import com.github.ferumbot.specmarket.bots.models.dto.holders.ApiMethodHolder
import com.github.ferumbot.specmarket.exceptions.NicheNotExists
import com.github.ferumbot.specmarket.exceptions.ProfessionNotExists
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
@Profile("prod")
class CommonExceptionInterceptor: ExceptionInterceptor {

    override fun intercept(error: Exception, causedUpdate: Update, currentApiMethod: ApiMethodHolder) {
        when(error) {
            is ProfessionNotExists -> handleProfessionNotExists(causedUpdate, currentApiMethod)
            is NicheNotExists -> handleNicheNotExists(causedUpdate, currentApiMethod)
            else -> handleException(causedUpdate, currentApiMethod)
        }
    }

    private fun handleProfessionNotExists(update: Update, holder: ApiMethodHolder) {
        val message = "Такой профессии не существует!\n Пожалуйста, используйте только предлагаемые варианты!"
        val chatId = update.getChatId()
        val newResult = SendMessage(chatId.toString(), message)

        holder.apiMethod = newResult
    }

    private fun handleNicheNotExists(update: Update, holder: ApiMethodHolder) {
        val message = "Такой нишы не существует!\n Пожалуйста, используйте только предлагаемые варианты!"
        val chatId = update.getChatId()
        val newResult = SendMessage(chatId.toString(), message)

        holder.apiMethod = newResult
    }

    private fun handleException(update: Update, holder: ApiMethodHolder) {
        val message = "Что-то пошло не так! Пожалуйста, свяжитесь с @ma_popovv или @Danverrr"
        val chatId = update.getChatId()
        val newResult = SendMessage(chatId.toString(), message)

        holder.apiMethod = newResult
    }
}