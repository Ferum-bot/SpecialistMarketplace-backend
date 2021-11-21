package com.github.ferumbot.specmarket.bots.services

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.models.entities.Specialist

interface TelegramUserSpecialistService {

    fun updateFullName(info: BaseUpdateInfo, newFullName: String): Specialist

    fun updateDepartment(info: BaseUpdateInfo, newDepartment: String): Specialist

    fun addProfession(info: BaseUpdateInfo, professionAlias: String): Specialist

    fun removeProfession(info: BaseUpdateInfo, professionAlias: String): Specialist

    fun clearProfessions(info: BaseUpdateInfo): Specialist

    fun addKeySkills(info: BaseUpdateInfo, skillsAlias: Collection<String>): Specialist

    fun clearKeySkills(info: BaseUpdateInfo): Specialist

    fun updatePortfolioLink(info: BaseUpdateInfo, newPortfolioLink: String): Specialist

    fun updateAboutMe(info: BaseUpdateInfo, newAboutMe: String): Specialist

    fun updateWorkingConditions(info: BaseUpdateInfo, newWorkingConditions: String): Specialist

    fun updateEducationGrade(info: BaseUpdateInfo, newEducationGrade: String): Specialist

    fun updateContactLinks(info: BaseUpdateInfo, newContactLinks: String): Specialist

    fun updateCompletelyFilled(info: BaseUpdateInfo, completelyFilled: Boolean): Specialist

    fun updateVisibility(info: BaseUpdateInfo, visibility: Boolean): Specialist
}