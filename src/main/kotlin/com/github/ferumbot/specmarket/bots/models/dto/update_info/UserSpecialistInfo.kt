package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.entities.Specialist

data class UserSpecialistInfo(

    override val chatId: Long,

    override val userId: Long,

    val specialist: SpecialistDto,
): BaseUpdateInfo {

    companion object {

        fun getFrom(info: BaseUpdateInfo, specialist: Specialist): UserSpecialistInfo {
            val specialistDto = SpecialistDto(
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

            return UserSpecialistInfo(
                chatId = info.chatId,
                userId = info.userId,
                specialist = specialistDto,
            )
        }
    }
}
