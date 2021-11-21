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
    override fun getSpecialistsByProfessionId(
        professionId: Long, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = repository.findAllByProfessionId(professionId, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun getSpecialistsByProfessionAlias(
        alias: String, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto> {
        val page = PageRequest.of(pageNumber - 1, pageSize)
        val result = repository.findAllByProfessionAlias(alias, page)

        return result.content.map { SpecialistDto.from(it) }
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsByProfessionId(professionId: Long): Int {
        return repository.countSpecialistsByProfessionId(professionId)
    }

    @Transactional(readOnly = true)
    override fun countSpecialistsByProfessionAlias(alias: String): Int {
        return repository.countSpecialistsByProfessionAlias(alias)
    }
}