package com.github.ferumbot.specmarket.models.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile

data class SpecialistDto(

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val id: Long? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val fullName: String? = null,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val professions: Collection<String> = emptyList(),

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val niches: Collection<String> = emptyList(),

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

    val isVisible: Boolean = false,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val profileStatus: SpecialistStatusDto,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val telegramId: Long? = null,
) {

    companion object {

        fun from(specialist: SpecialistProfile): SpecialistDto {
            val statusDto = SpecialistStatusDto.from(specialist.status)
            return SpecialistDto(
                id = specialist.id,
                fullName = specialist.fullName,
                professions = specialist.professions.map { it.friendlyName },
                niches = specialist.niches.map { it.friendlyName },
                keySkills = specialist.keySkills.map { it.value },
                portfolioLink = specialist.portfolioLink,
                aboutMe = specialist.aboutMe,
                workingConditions = specialist.workingConditions,
                educationGrade = specialist.educationGrade,
                contactLinks = specialist.contactLinks,
                isVisible = specialist.isVisible,
                profileStatus = statusDto,
                telegramId = specialist.telegramUser?.id
            )
        }
    }
}
