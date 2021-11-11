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
    }
}
