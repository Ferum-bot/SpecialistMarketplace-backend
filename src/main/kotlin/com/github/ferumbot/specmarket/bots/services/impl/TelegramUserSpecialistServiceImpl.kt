package com.github.ferumbot.specmarket.bots.services.impl

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import com.github.ferumbot.specmarket.bots.services.TelegramUserSpecialistService
import com.github.ferumbot.specmarket.exceptions.ProfessionNotExists
import com.github.ferumbot.specmarket.models.entities.specifications.KeySkills
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
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

    override fun updateFullName(info: BaseUpdateInfo, newFullName: String): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.fullName = newFullName

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateNiche(info: BaseUpdateInfo, newDepartment: String): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun addProfession(info: BaseUpdateInfo, professionAlias: String): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        val profession = professionRepository.getByAlias(professionAlias)
            ?: throw ProfessionNotExists()

        prepareUserSpecialist(user)
        if (user.specialist?.professions?.contains(profession) == false) {
            user.specialist?.professions?.add(profession)
        }

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun removeProfession(info: BaseUpdateInfo, professionAlias: String): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        val profession = professionRepository.getByAlias(professionAlias)
            ?: throw ProfessionNotExists()

        prepareUserSpecialist(user)
        user.specialist?.professions?.remove(profession)

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun clearProfessions(info: BaseUpdateInfo): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.professions?.clear()

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun addKeySkills(info: BaseUpdateInfo, skillsAlias: Collection<String>): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        val keySkills = skillsAlias.map { KeySkills(value = it) }
        user.specialist?.keySkills?.addAll(keySkills)
        keySkillsRepository.saveAllAndFlush(keySkills)

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun clearKeySkills(info: BaseUpdateInfo): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.keySkills?.clear()

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updatePortfolioLink(info: BaseUpdateInfo, newPortfolioLink: String): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.portfolioLink = newPortfolioLink

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateAboutMe(info: BaseUpdateInfo, newAboutMe: String): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.aboutMe = newAboutMe

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateWorkingConditions(info: BaseUpdateInfo, newWorkingConditions: String): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.workingConditions = newWorkingConditions

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateEducationGrade(info: BaseUpdateInfo, newEducationGrade: String): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.educationGrade = newEducationGrade

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateContactLinks(info: BaseUpdateInfo, newContactLinks: String): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.contactLinks = newContactLinks

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateCompletelyFilled(info: BaseUpdateInfo, completelyFilled: Boolean): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        return user.specialist!!
    }

    override fun updateVisibility(info: BaseUpdateInfo, visibility: Boolean): SpecialistProfile {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.isVisible = visibility

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    private fun prepareUserSpecialist(user: TelegramUser) = user.run {
        if (specialist == null) {
            val specialist = SpecialistProfile()
            specialist.telegramUser = user
            user.specialist = specialist
            specialistRepository.saveAndFlush(specialist)
        }
    }

    private fun registerNewUser(info: BaseUpdateInfo): TelegramUser {
        val user = TelegramUser(
            telegramUserId = info.userId,
            personalTelegramChatId = info.chatId,
            isBot = false
        )

        return userRepository.save(user)
    }
}