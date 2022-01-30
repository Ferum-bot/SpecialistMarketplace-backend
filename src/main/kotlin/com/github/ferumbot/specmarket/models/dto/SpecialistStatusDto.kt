package com.github.ferumbot.specmarket.models.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfileStatus

data class SpecialistStatusDto(

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val id: Long?,

    val alias: String,

    val name: String,
) {

    companion object {

        fun from(status: SpecialistProfileStatus): SpecialistStatusDto {
            return SpecialistStatusDto(
                id = status.id,
                alias = status.alias.alias,
                name = status.name,
            )
        }
    }
}
