package com.github.ferumbot.specmarket.models.dto

import com.github.ferumbot.specmarket.models.entities.Profession
import javax.validation.constraints.NotBlank

data class ProfessionDto(

    @NotBlank
    val friendlyName: String,

    @NotBlank
    val alias: String,

    @NotBlank
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
