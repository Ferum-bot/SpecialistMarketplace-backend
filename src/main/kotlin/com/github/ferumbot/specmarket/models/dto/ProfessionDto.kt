package com.github.ferumbot.specmarket.models.dto

import javax.validation.constraints.NotBlank

data class ProfessionDto(

    @NotBlank
    val friendlyName: String,

    @NotBlank
    val alias: String,

    @NotBlank
    val shortDescription: String,

    val longDescription: String? = null,
)
