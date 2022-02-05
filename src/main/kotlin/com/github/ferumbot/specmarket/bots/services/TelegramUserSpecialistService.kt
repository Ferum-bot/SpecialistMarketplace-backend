package com.github.ferumbot.specmarket.bots.services

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses

interface TelegramUserSpecialistService {

    fun updateFullName(info: BaseUpdateInfo, newFullName: String): SpecialistProfile

    fun addNiche(info: BaseUpdateInfo, nicheAlias: String): SpecialistProfile

    fun removeNiche(info: BaseUpdateInfo, nicheAlias: String): SpecialistProfile

    fun clearNiches(info: BaseUpdateInfo): SpecialistProfile

    fun addProfession(info: BaseUpdateInfo, professionAlias: String): SpecialistProfile

    fun removeProfession(info: BaseUpdateInfo, professionAlias: String): SpecialistProfile

    fun clearProfessions(info: BaseUpdateInfo): SpecialistProfile

    fun addKeySkills(info: BaseUpdateInfo, skillsAlias: Collection<String>): SpecialistProfile

    fun clearKeySkills(info: BaseUpdateInfo): SpecialistProfile

    fun updatePortfolioLink(info: BaseUpdateInfo, newPortfolioLink: String): SpecialistProfile

    fun updateAboutMe(info: BaseUpdateInfo, newAboutMe: String): SpecialistProfile

    fun updateWorkingConditions(info: BaseUpdateInfo, newWorkingConditions: String): SpecialistProfile

    fun updateEducationGrade(info: BaseUpdateInfo, newEducationGrade: String): SpecialistProfile

    fun updateContactLinks(info: BaseUpdateInfo, newContactLinks: String): SpecialistProfile

    fun updateVisibility(info: BaseUpdateInfo, visibility: Boolean): SpecialistProfile

    fun updateStatus(info: BaseUpdateInfo, status: ProfileStatuses): SpecialistProfile
}