package com.github.ferumbot.specmarket.bots.services.impl

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import com.github.ferumbot.specmarket.bots.services.TelegramUserSpecialistService
import com.github.ferumbot.specmarket.models.entities.KeySkills
import com.github.ferumbot.specmarket.models.entities.Specialist
import com.github.ferumbot.specmarket.repositories.KeySkillsRepository
import com.github.ferumbot.specmarket.repositories.ProfessionRepository
import com.github.ferumbot.specmarket.repositories.SpecialistRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TelegramUserSpecialistServiceImpl(
    private val userRepository: TelegramUserRepository,
    private val professionRepository: ProfessionRepository,
    private val specialistRepository: SpecialistRepository,
    private val keySkillsRepository: KeySkillsRepository,
): TelegramUserSpecialistService {

    override fun updateFullName(info: BaseUpdateInfo, newFullName: String) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return

        prepareUserSpecialist(user)
        user.specialist?.fullName = newFullName

        userRepository.saveAndFlush(user)
    }

    override fun updateDepartment(info: BaseUpdateInfo, newDepartment: String) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return

        prepareUserSpecialist(user)
        user.specialist?.department = newDepartment

        userRepository.saveAndFlush(user)
    }

    override fun addProfession(info: BaseUpdateInfo, professionAlias: String) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return
        val profession = professionRepository.getByAlias(professionAlias)
            ?: return

        prepareUserSpecialist(user)
        if (user.specialist?.professions?.contains(profession) == false) {
            user.specialist?.professions?.add(profession)
        }

        userRepository.saveAndFlush(user)
    }

    override fun removeProfession(info: BaseUpdateInfo, professionAlias: String) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return
        val profession = professionRepository.getByAlias(professionAlias)
            ?: return

        prepareUserSpecialist(user)
        user.specialist?.professions?.remove(profession)

        userRepository.saveAndFlush(user)
    }

    override fun clearProfessions(info: BaseUpdateInfo) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return

        prepareUserSpecialist(user)
        user.specialist?.professions?.clear()

        userRepository.saveAndFlush(user)
    }

    override fun addKeySkills(info: BaseUpdateInfo, skillsAlias: List<String>) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return

        prepareUserSpecialist(user)
        val keySkills = skillsAlias.map { KeySkills(alias = it) }
        user.specialist?.keySkills?.addAll(keySkills)
        keySkillsRepository.saveAllAndFlush(keySkills)

        userRepository.saveAndFlush(user)
    }

    override fun clearKeySkills(info: BaseUpdateInfo) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return

        prepareUserSpecialist(user)
        user.specialist?.keySkills?.clear()

        userRepository.saveAndFlush(user)
    }

    override fun updatePortfolioLink(info: BaseUpdateInfo, newPortfolioLink: String) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return

        prepareUserSpecialist(user)
        user.specialist?.portfolioLink = newPortfolioLink

        userRepository.saveAndFlush(user)
    }

    override fun updateAboutMe(info: BaseUpdateInfo, newAboutMe: String) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return

        prepareUserSpecialist(user)
        user.specialist?.aboutMe = newAboutMe

        userRepository.saveAndFlush(user)
    }

    override fun updateWorkingConditions(info: BaseUpdateInfo, newWorkingConditions: String) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return

        prepareUserSpecialist(user)
        user.specialist?.workingConditions = newWorkingConditions

        userRepository.saveAndFlush(user)
    }

    override fun updateEducationGrade(info: BaseUpdateInfo, newEducationGrade: String) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return

        prepareUserSpecialist(user)
        user.specialist?.educationGrade = newEducationGrade

        userRepository.saveAndFlush(user)
    }

    override fun updateContactLinks(info: BaseUpdateInfo, newContactLinks: String) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return

        prepareUserSpecialist(user)
        user.specialist?.contactLinks = newContactLinks

        userRepository.saveAndFlush(user)
    }

    override fun updateCompletelyFilled(info: BaseUpdateInfo, completelyFilled: Boolean) {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: return

        prepareUserSpecialist(user)
        user.specialist?.isCompletelyFilled = completelyFilled

        userRepository.saveAndFlush(user)
    }

    private fun prepareUserSpecialist(user: TelegramUser) = user.run {
        if (specialist == null) {
            val specialist = Specialist()
            specialist.telegramUser = user
            user.specialist = specialist
            specialistRepository.saveAndFlush(specialist)
        }
    }
}