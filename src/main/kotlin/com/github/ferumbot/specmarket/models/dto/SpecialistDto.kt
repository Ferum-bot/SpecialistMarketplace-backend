package com.github.ferumbot.specmarket.models.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.github.ferumbot.specmarket.models.entities.Specialist

data class SpecialistDto(

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val fullName: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val department: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val professions: Collection<String> = emptyList(),

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val keySkills: Collection<String> = emptyList(),

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val portfolioLink: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val aboutMe: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val workingConditions: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val educationGrade: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val contactLinks: String? = null,
) {

    companion object {

        fun from(specialist: Specialist): SpecialistDto {
            return SpecialistDto(
                fullName = specialist.fullName,
                department = specialist.department,
                professions = specialist.professions.map { it.friendlyName },
                keySkills = specialist.keySkills.map { it.alias },
                portfolioLink = specialist.portfolioLink,
                aboutMe = specialist.aboutMe,
                workingConditions = specialist.workingConditions,
                educationGrade = specialist.educationGrade,
                contactLinks = specialist.contactLinks,
            )
        }
    }
}
