package com.github.ferumbot.specmarket.models.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import org.springframework.http.HttpStatus

data class ApiResponse<T: Any?>(

    val statusCode: Int,

    val statusMessage: String,

    @JsonInclude(NON_NULL)
    val additionalMessage: String? = null,

    @JsonInclude(NON_NULL)
    val errorMessage: String? = null,

    @JsonInclude(NON_NULL)
    val data: T?,
) {

    companion object {

        fun <T: Any?> success(data: T): ApiResponse<T> {
            return ApiResponse(
                statusCode = HttpStatus.OK.value(),
                statusMessage = "OK",
                data = data,
            )
        }

        fun done(extraMessage: () -> String): ApiResponse<*> {
            return ApiResponse(
                statusCode = HttpStatus.OK.value(),
                statusMessage = "OK",
                additionalMessage = extraMessage.invoke(),
                data = null,
            )
        }

        fun notFound(extraMessage: () -> String): ApiResponse<*> {
            return ApiResponse(
                statusCode = HttpStatus.NOT_FOUND.value(),
                statusMessage = "NOT FOUND",
                errorMessage = extraMessage.invoke(),
                data = null
            )
        }

        fun notAcceptable(extraMessage: () -> String): ApiResponse<*> {
            return ApiResponse(
                statusCode = HttpStatus.NOT_ACCEPTABLE.value(),
                statusMessage = "NOT ACCEPTABLE",
                errorMessage = extraMessage.invoke(),
                data = null,
            )
        }
    }
}
