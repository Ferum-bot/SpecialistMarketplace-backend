package com.github.ferumbot.specmarket.services

import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.entities.Specialist

interface SpecialistService {

    fun getAllSpecialists(pageNumber: Int, pageSize: Int): Collection<SpecialistDto>

    fun getSpecialistById(id: Long): Specialist?

    fun getAllSpecialistsByProfessionId(
        professionId: Long, pageNumber: Int, pageSize: Int,
    ): Collection<SpecialistDto>

    fun getAllSpecialistsByProfessionAlias(
        alias: String, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun getAvailableSpecialistsByProfessionId(
        professionId: Long, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun getAvailableSpecialistsByProfessionAlias(
        alias: String, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun countSpecialistsByProfessionId(professionId: Long): Int

    fun countSpecialistsByProfessionAlias(alias: String): Int
}