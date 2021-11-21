package com.github.ferumbot.specmarket.bots.services.impl

import com.github.ferumbot.specmarket.bots.models.dto.update_info.BaseUpdateInfo
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import com.github.ferumbot.specmarket.bots.services.TelegramUserSpecialistService
import com.github.ferumbot.specmarket.exceptions.ProfessionNotExists
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

    override fun updateFullName(info: BaseUpdateInfo, newFullName: String): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.fullName = newFullName

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateDepartment(info: BaseUpdateInfo, newDepartment: String): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.department = newDepartment

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun addProfession(info: BaseUpdateInfo, professionAlias: String): Specialist {
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

    override fun removeProfession(info: BaseUpdateInfo, professionAlias: String): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)
        val profession = professionRepository.getByAlias(professionAlias)
            ?: throw ProfessionNotExists()

        prepareUserSpecialist(user)
        user.specialist?.professions?.remove(profession)

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun clearProfessions(info: BaseUpdateInfo): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.professions?.clear()

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun addKeySkills(info: BaseUpdateInfo, skillsAlias: Collection<String>): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        val keySkills = skillsAlias.map { KeySkills(alias = it) }
        user.specialist?.keySkills?.addAll(keySkills)
        keySkillsRepository.saveAllAndFlush(keySkills)

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun clearKeySkills(info: BaseUpdateInfo): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.keySkills?.clear()

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updatePortfolioLink(info: BaseUpdateInfo, newPortfolioLink: String): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.portfolioLink = newPortfolioLink

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateAboutMe(info: BaseUpdateInfo, newAboutMe: String): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.aboutMe = newAboutMe

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateWorkingConditions(info: BaseUpdateInfo, newWorkingConditions: String): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.workingConditions = newWorkingConditions

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateEducationGrade(info: BaseUpdateInfo, newEducationGrade: String): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.educationGrade = newEducationGrade

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateContactLinks(info: BaseUpdateInfo, newContactLinks: String): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.contactLinks = newContactLinks

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateCompletelyFilled(info: BaseUpdateInfo, completelyFilled: Boolean): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.isCompletelyFilled = completelyFilled

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    override fun updateVisibility(info: BaseUpdateInfo, visibility: Boolean): Specialist {
        val user = userRepository.findByTelegramUserId(info.userId)
            ?: registerNewUser(info)

        prepareUserSpecialist(user)
        user.specialist?.isVisible = visibility

        userRepository.saveAndFlush(user)
        return user.specialist!!
    }

    private fun prepareUserSpecialist(user: TelegramUser) = user.run {
        if (specialist == null) {
            val specialist = Specialist()
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