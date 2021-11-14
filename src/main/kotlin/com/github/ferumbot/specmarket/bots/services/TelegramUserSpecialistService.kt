package com.github.ferumbot.specmarket.bots.services

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo

interface TelegramUserSpecialistService {

    fun updateFullName(info: BaseUpdateInfo, newFullName: String)

    fun updateDepartment(info: BaseUpdateInfo, newDepartment: String)

    fun addProfession(info: BaseUpdateInfo, professionAlias: String)

    fun removeProfession(info: BaseUpdateInfo, professionAlias: String)

    fun clearProfessions(info: BaseUpdateInfo)

    fun addKeySkills(info: BaseUpdateInfo, skillsAlias: List<String>)

    fun clearKeySkills(info: BaseUpdateInfo)

    fun updatePortfolioLink(info: BaseUpdateInfo, newPortfolioLink: String)

    fun updateAboutMe(info: BaseUpdateInfo, newAboutMe: String)

    fun updateWorkingConditions(info: BaseUpdateInfo, newWorkingConditions: String)

    fun updateEducationGrade(info: BaseUpdateInfo, newEducationGrade: String)

    fun updateContactLinks(info: BaseUpdateInfo, newContactLinks: String)

    fun updateCompletelyFilled(info: BaseUpdateInfo, completelyFilled: Boolean)
}