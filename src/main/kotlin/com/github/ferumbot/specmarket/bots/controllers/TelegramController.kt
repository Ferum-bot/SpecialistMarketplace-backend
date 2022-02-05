package com.github.ferumbot.specmarket.bots.controllers

import com.github.ferumbot.specmarket.bots.TelegramBot
import com.github.ferumbot.specmarket.bots.controllers.models.SendMessageToChatRequest
import com.github.ferumbot.specmarket.bots.models.dto.TelegramUserDto
import com.github.ferumbot.specmarket.bots.services.TelegramUsersService
import com.github.ferumbot.specmarket.configs.SwaggerConfig
import com.github.ferumbot.specmarket.core.annotations.SwaggerVisible
import com.github.ferumbot.specmarket.core.extensions.ifNull
import com.github.ferumbot.specmarket.models.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@RestController
@SwaggerVisible
@RequestMapping("telegram")
@Tag(name = "Telegram", description = SwaggerConfig.TELEGRAM_CONTROLLER_DESCRIPTION)
class TelegramController @Autowired constructor(
    private val telegramBot: TelegramBot,
    private val service: TelegramUsersService,
) {

    @PostMapping("/webhook")
    @Operation(summary = "Вебхук для Telegram API")
    fun onWebhookUpdateReceived(
        @RequestBody
        update: Update
    ): BotApiMethod<*>? {
        return telegramBot.onWebhookUpdateReceived(update)
    }

    @GetMapping("/users/all")
    @Operation(summary = "Получить всех пользователей бота")
    fun getAllUsers(
        @Min(value = 1, message = "Page number must be greater than 1")
        @RequestParam(value = "page_number", required = true)
        pageNumber: Int,

        @Min(value = 1, message = "Page size must be greater than 1")
        @Max(value = 50, message = "Page size must be not greater than 50")
        @RequestParam(value = "page_size", required = true)
        pageSize: Int,
    ): ApiResponse<Collection<TelegramUserDto>> {
        val users = service.getAllUsers(pageNumber, pageSize)
        return ApiResponse.success(users)
    }

    @GetMapping("/user/id")
    @Operation(summary = "Ищет пользователя бота по его внутреннему id")
    fun getUserById(
        @RequestParam(value = "id", required = true)
        id: Long,
    ): ApiResponse<*> {
        val user = service.getUserById(id)

        user.ifNull {
            return ApiResponse.notFound { "Telegram user with id: $id not found!" }
        }

        return ApiResponse.success(user)
    }

    @GetMapping("/user/telegramId")
    @Operation(summary = "Ищет пользователя бота по его telegramId")
    fun getUserByTelegramId(
        @RequestParam(value = "id", required = true)
        id: Long,
    ): ApiResponse<*> {
        val user = service.getUserByTelegramUserId(id)

        user.ifNull {
            return ApiResponse.notFound { "Telegram user with telegram id: $id not found!" }
        }

        return ApiResponse.success(user)
    }

    @GetMapping("/user/chatId")
    @Operation(summary = "Ищет пользователя бота по chatId с этим пользователем")
    fun getUserByChatId(
        @RequestParam(value = "id", required = true)
        id: Long,
    ): ApiResponse<*> {
        val user = service.getUserByTelegramChatId(id)

        user.ifNull {
            return ApiResponse.notFound { "Telegram user with chat id: $id not found!" }
        }

        return ApiResponse.success(user)
    }

    @PostMapping("/sendMessage")
    @Operation(summary = "Отправляет сообщение от лица бота в конкретный чат по его id")
    fun sendMessageFromBotToChat(
        @RequestBody
        request: SendMessageToChatRequest
    ) {

    }
}