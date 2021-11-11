package com.github.ferumbot.specmarket.models.response

import com.fasterxml.jackson.annotation.JsonInclude

data class ErrorResponse(

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val cause: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val totalExceptions: Collection<String> = emptyList(),
)
