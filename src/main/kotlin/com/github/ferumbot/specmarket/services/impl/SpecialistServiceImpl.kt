package com.github.ferumbot.specmarket.services.impl

import com.github.ferumbot.specmarket.core.extensions.transform
import com.github.ferumbot.specmarket.exceptions.NicheNotExists
import com.github.ferumbot.specmarket.exceptions.ProfessionNotExists
import com.github.ferumbot.specmarket.exceptions.SpecialistNotExists
import com.github.ferumbot.specmarket.exceptions.UndefinedProfileStatus
import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses
import com.github.ferumbot.specmarket.models.entities.specifications.KeySkills
import com.github.ferumbot.specmarket.models.request.CreateSpecialistRequest
import com.github.ferumbot.specmarket.repositories.*
import com.github.ferumbot.specmarket.services.SpecialistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SpecialistServiceImpl @Autowired constructor(
    private val specialistRepository: SpecialistRepository,
    private val statusRepository: SpecialistStatusRepository,
    private val professionRepository: ProfessionRepository,
    private val nicheRepository: NicheRepository,
    private val keySkillsRepository: KeySkillsRepository,
): SpecialistService {

    @Transactional(readOnly = true)
    override fun getAllSpecialists(pageNumber: Int, pageSize: Int): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = specialistRepository.findAll(page)

        return result.content.map { SpecialistDto.from(it)  }
    }

    @Transactional(readOnly = true)
    override fun getSpecialistById(id: Long): SpecialistProfile? {
        return specialistRepository.findById(id).orElseGet(null)
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsWithStatus(
        status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val statusEntity = statusRepository.findByAlias(status)
            ?: throw UndefinedProfileStatus()
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = specialistRepository.findAllByStatus(statusEntity, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getAllSpecialistsByProfession(
        id: Long, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = specialistRepository.findAllByProfessionId(id, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getAllSpecialistsByProfession(
        alias: String, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = specialistRepository.findAllByProfessionAlias(alias, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getAllSpecialistsByNiche(
        id: Long, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = specialistRepository.findAllByNicheId(id, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getAllSpecialistsByNiche(
        alias: String, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = specialistRepository.findAllByNicheAlias(alias, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsByProfessionWithStatus(
        id: Long, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        val result = specialistRepository.findAllByProfessionWithStatus(id, statusId, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsByProfessionWithStatus(
        alias: String, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        val result = specialistRepository.findAllByProfessionWithStatus(alias, statusId, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsByNicheWithStatus(
        id: Long, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        val result = specialistRepository.findAllByNicheWithStatus(id, statusId, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsByNicheWithStatus(
        alias: String, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        val result = specialistRepository.findAllByNicheWithStatus(alias, statusId, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsWithProfessionAndNiche(
        professionId: Long, nicheId: Long, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        val result = specialistRepository.findAllByProfessionAndNiche(
            professionId, nicheId, statusId, page
        )

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsWithProfessionAndNiche(
        professionAlias: String, nicheAlias: String, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        val result = specialistRepository.findAllByProfessionAndNiche(
            professionAlias, nicheAlias, statusId, page
        )

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsWithStatus(status: ProfileStatuses): Int {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun countAllSpecialistsByProfession(professionId: Long): Int {
        return specialistRepository.countAllByProfession(professionId)
    }

    @Transactional(readOnly = true)
    override fun countAllSpecialistsByProfession(alias: String): Int {
        return specialistRepository.countAllByProfession(alias)
    }

    @Transactional(readOnly = true)
    override fun countAllSpecialistsByNiche(nicheId: Long): Int {
        return specialistRepository.countAllByNiche(nicheId)
    }

    @Transactional(readOnly = true)
    override fun countAllSpecialistsByNiche(nicheAlias: String): Int {
        return specialistRepository.countAllByNiche(nicheAlias)
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsByProfessionWithStatus(
        professionId: Long, status: ProfileStatuses
    ): Int {
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        return specialistRepository.countAllByProfessionWithStatus(professionId, statusId)
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsByProfessionWithStatus(
        professionAlias: String, status: ProfileStatuses
    ): Int {
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        return specialistRepository.countAllByProfessionWithStatus(professionAlias, statusId)
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsByNicheWithStatus(
        nicheId: Long, status: ProfileStatuses
    ): Int {
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        return specialistRepository.countAllByNicheWithStatus(nicheId, statusId)
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsByNicheWithStatus(
        nicheAlias: String, status: ProfileStatuses
    ): Int {
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        return specialistRepository.countAllByNicheWithStatus(nicheAlias, statusId)
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsWithNicheAndProfession(
        nicheId: Long, professionId: Long, status: ProfileStatuses
    ): Int {
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        return specialistRepository.countAllByProfessionAndNiche(
            professionId, nicheId, statusId
        )
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsWithNicheAndProfession(
        nicheAlias: String, professionAlias: String, status: ProfileStatuses
    ): Int {
        val statusId = statusRepository.findByAlias(status)?.id
            ?: throw UndefinedProfileStatus()
        return specialistRepository.countAllByProfessionAndNiche(
            professionAlias, nicheAlias, statusId
        )
    }

    override fun changeSpecialistStatus(newStatus: ProfileStatuses, id: Long): SpecialistDto {
        val status = statusRepository.findByAlias(newStatus)
            ?: throw UndefinedProfileStatus("Undefined status: $newStatus")
        val specialist = specialistRepository.findById(id).orElseThrow {
            SpecialistNotExists(id)
        }

        specialist.status = status
        return specialistRepository.saveAndFlush(specialist).transform {
            SpecialistDto.from(it)
        }
    }

    override fun createSpecialist(specialist: CreateSpecialistRequest): SpecialistDto {
        val professions = specialist.professionIds.map { id ->
            professionRepository.findById(id).orElseThrow {
                ProfessionNotExists("Profession with id: $id not exists")
            }
        }
        val niches = specialist.nichesIds.map { id ->
            nicheRepository.findById(id).orElseThrow {
                NicheNotExists("Niche with id: $id not exists")
            }
        }
        val keySkills = specialist.keySkills.map { value ->
            val keySkill = KeySkills(value = value)
            keySkillsRepository.save(keySkill)
        }
        val status = statusRepository.findByAlias(ProfileStatuses.NOT_FILLED)
            ?: throw UndefinedProfileStatus("Undefined status: ${ProfileStatuses.NOT_FILLED.alias}")

        val entity = SpecialistProfile(
            fullName = specialist.fullName,
            professions = professions.toMutableList(),
            niches = niches.toMutableList(),
            keySkills = keySkills.toMutableList(),
            portfolioLink = specialist.portfolioLink,
            aboutMe = specialist.aboutMe,
            workingConditions = specialist.workingConditions,
            educationGrade = specialist.educationGrade,
            contactLinks = specialist.contactLinks,
            isVisible = specialist.isVisible,
            status = status,
        )

        return specialistRepository.saveAndFlush(entity).transform {
            SpecialistDto.from(it)
        }
    }

    override fun deleteSpecialist(id: Long) {
        val specialist = specialistRepository.findById(id).orElseThrow {
            SpecialistNotExists(id)
        }

        specialistRepository.delete(specialist)
    }
}