package com.github.ferumbot.specmarket.services.impl

import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses
import com.github.ferumbot.specmarket.repositories.SpecialistRepository
import com.github.ferumbot.specmarket.services.SpecialistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SpecialistServiceImpl @Autowired constructor(
    private val repository: SpecialistRepository,
): SpecialistService {

    @Transactional(readOnly = true)
    override fun getAllSpecialists(pageNumber: Int, pageSize: Int): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = repository.findAll(page)

        return result.content.map { SpecialistDto.from(it)  }
    }

    @Transactional(readOnly = true)
    override fun getSpecialistById(id: Long): SpecialistProfile? {
        return repository.findById(id).orElseGet(null)
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsWithStatus(
        status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getAllSpecialistsByProfession(
        professionId: Long, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = repository.findAllByProfessionId(professionId, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getAllSpecialistsByProfession(
        alias: String, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = repository.findAllByProfessionAlias(alias, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getAllSpecialistsByNiche(
        id: Long, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getAllSpecialistsByNiche(
        alias: String, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsByProfessionWithStatus(
        id: Long, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsByProfessionWithStatus(
        alias: String, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsByNicheWithStatus(
        id: Long, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsByNicheWithStatus(
        alias: String, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsWithProfessionAndNiche(
        professionId: Long, nicheId: Long, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsWithProfessionAndNiche(
        professionAlias: String, nicheAlias: String, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun countAllSpecialistsByProfession(professionId: Long): Int {
        return repository.countAllSpecialistsByProfessionId(professionId)
    }

    @Transactional(readOnly = true)
    override fun countAllSpecialistsByProfession(alias: String): Int {
        return repository.countAllSpecialistsByProfessionAlias(alias)
    }

    @Transactional(readOnly = true)
    override fun countAllSpecialistsByNiche(nicheId: Long): Int {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun countAllSpecialistsByNiche(nicheAlias: String): Int {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsByProfessionWithStatus(
        professionId: Long, status: ProfileStatuses
    ): Int {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsByProfessionWithStatus(
        professionAlias: String, status: ProfileStatuses
    ): Int {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsByNicheWithStatus(
        nicheId: Long, status: ProfileStatuses
    ): Int {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsByNicheWithStatus(
        nicheAlias: String, status: ProfileStatuses
    ): Int {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsWithNicheAndProfession(
        nicheId: Long, professionId: Long, status: ProfileStatuses
    ): Int {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsWithNicheAndProfession(
        nicheAlias: String, professionAlias: String, status: ProfileStatuses
    ): Int {
        TODO("Not yet implemented")
    }
}