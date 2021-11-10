package com.github.ferumbot.specmarket.models.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL

data class ApiResponse<T: Any?>(

    val statusCode: Int,

    val statusMessage: String,

    @JsonInclude(NON_NULL)
    val additionalMessage: String?,

    @JsonInclude(NON_NULL)
    val errorMessage: String?,

    @JsonInclude(NON_NULL)
    val data: T?,
)
