package com.github.ferumbot.specmarket.models.dto

import com.fasterxml.jackson.annotation.JsonInclude

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
)
