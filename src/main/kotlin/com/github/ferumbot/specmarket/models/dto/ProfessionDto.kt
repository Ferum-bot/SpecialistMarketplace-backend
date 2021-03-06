package com.github.ferumbot.specmarket.models.dto

import com.github.ferumbot.specmarket.models.entities.filter.Profession
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ProfessionDto(

    @NotBlank
    @NotNull
    val friendlyName: String,

    @NotBlank
    @NotNull
    val alias: String,

    @NotBlank
    @NotNull
    val shortDescription: String,

    val longDescription: String? = null,
) {

    companion object {

        fun from(profession: Profession): ProfessionDto {
            return profession.run {
                ProfessionDto(
                    friendlyName, alias, shortDescription, longDescription
                )
            }
        }
    }
}
