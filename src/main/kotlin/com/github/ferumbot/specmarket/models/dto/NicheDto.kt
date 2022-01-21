package com.github.ferumbot.specmarket.models.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NicheDto(

    @NotBlank
    @NotNull
    val friendlyName: String,

    @NotBlank
    @NotNull
    val alias: String,

    @NotBlank
    @NotNull
    @Size(min = 10, max = 150)
    val shortDescription: String,

    @NotBlank
    @NotNull
    @Size(min = 20, max = 1500)
    val longDescription: String,
)
