package com.github.ferumbot.specmarket.services.impl

import com.github.ferumbot.specmarket.exceptions.UndefinedProfileStatus
import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses
import com.github.ferumbot.specmarket.repositories.SpecialistRepository
import com.github.ferumbot.specmarket.repositories.SpecialistStatusRepository
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
}