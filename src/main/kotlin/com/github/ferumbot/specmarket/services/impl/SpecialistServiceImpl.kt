package com.github.ferumbot.specmarket.services.impl

import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.entities.Specialist
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
    override fun getSpecialistById(id: Long): Specialist? {
        return repository.findById(id).orElseGet(null)
    }

    @Transactional(readOnly = true)
    override fun getAllSpecialistsByProfessionId(
        professionId: Long, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = repository.findAllByProfessionId(professionId, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getAllSpecialistsByProfessionAlias(
        alias: String, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = repository.findAllByProfessionAlias(alias, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getAvailableSpecialistsByProfessionId(
        professionId: Long, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = repository.findOnlyVisibleAndFinishedByProfessionId(professionId, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getAvailableSpecialistsByProfessionAlias(
        alias: String, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = repository.findOnlyVisibleAndFinishedByProfessionAlias(alias, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun countAllSpecialistsByProfessionId(professionId: Long): Int {
        return repository.countAllSpecialistsByProfessionId(professionId)
    }

    @Transactional(readOnly = true)
    override fun countAllSpecialistsByProfessionAlias(alias: String): Int {
        return repository.countAllSpecialistsByProfessionAlias(alias)
    }

    @Transactional(readOnly = true)
    override fun countAvailableSpecialistsByProfessionId(professionId: Long): Int {
        return repository.countOnlyVisibleAndFinishedSpecialistsByProfessionId(professionId)
    }

    @Transactional(readOnly = true)
    override fun countAvailableSpecialistsByProfessionAlias(alias: String): Int {
        return repository.countOnlyVisibleAndFinishedSpecialistsByProfessionAlias(alias)
    }
}