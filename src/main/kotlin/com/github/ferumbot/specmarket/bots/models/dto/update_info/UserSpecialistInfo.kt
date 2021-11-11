package com.github.ferumbot.specmarket.bots.models.dto.update_info

import com.github.ferumbot.specmarket.models.entities.Specialist

data class UserSpecialistInfo(

    override val chatId: Long,

    override val userId: Long,

    val fullName: String? = null,

    val department: String? = null,

    val professions: List<String> = emptyList(),

    val keySkills: List<String> = emptyList(),

    val portfolioLink: String? = null,

    val aboutMe: String? = null,

    val workingConditions: String? = null,

    val educationGrade: String? = null,

    val contactLinks: String? = null
): BaseUpdateInfo {

    companion object {

        fun getFrom(info: BaseUpdateInfo, specialist: Specialist): UserSpecialistInfo {
            return UserSpecialistInfo(
                chatId = info.chatId,
                userId = info.userId,
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
