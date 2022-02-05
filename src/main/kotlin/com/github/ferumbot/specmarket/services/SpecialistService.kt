package com.github.ferumbot.specmarket.services

import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses
import com.github.ferumbot.specmarket.models.request.CreateSpecialistRequest

interface SpecialistService {

    fun getAllSpecialists(pageNumber: Int, pageSize: Int): Collection<SpecialistDto>

    fun getSpecialistById(id: Long): SpecialistProfile?

    fun getSpecialistsWithStatus(
        status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun getAllSpecialistsByProfession(
        id: Long, pageNumber: Int, pageSize: Int,
    ): Collection<SpecialistDto>

    fun getAllSpecialistsByProfession(
        alias: String, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun getAllSpecialistsByNiche(
        id: Long, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun getAllSpecialistsByNiche(
        alias: String, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun getSpecialistsByProfessionWithStatus(
        id: Long, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun getSpecialistsByProfessionWithStatus(
        alias: String, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun getSpecialistsByNicheWithStatus(
        id: Long, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun getSpecialistsByNicheWithStatus(
        alias: String, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun getSpecialistsWithProfessionAndNiche(
        professionId: Long, nicheId: Long, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun getSpecialistsWithProfessionAndNiche(
        professionAlias: String, nicheAlias: String, status: ProfileStatuses, pageNumber: Int, pageSize: Int
    ): Collection<SpecialistDto>

    fun countAllSpecialistsByProfession(professionId: Long): Int

    fun countAllSpecialistsByProfession(alias: String): Int

    fun countAllSpecialistsByNiche(nicheId: Long): Int

    fun countAllSpecialistsByNiche(nicheAlias: String): Int

    fun countSpecialistsByProfessionWithStatus(professionId: Long, status: ProfileStatuses): Int

    fun countSpecialistsByProfessionWithStatus(professionAlias: String, status: ProfileStatuses): Int

    fun countSpecialistsByNicheWithStatus(nicheId: Long, status: ProfileStatuses): Int

    fun countSpecialistsByNicheWithStatus(nicheAlias: String, status: ProfileStatuses): Int

    fun countSpecialistsWithNicheAndProfession(
        nicheId: Long, professionId: Long, status: ProfileStatuses
    ): Int

    fun countSpecialistsWithNicheAndProfession(
        nicheAlias: String, professionAlias: String, status: ProfileStatuses
    ): Int

    fun changeSpecialistStatus(newStatus: ProfileStatuses, id: Long): SpecialistDto

    fun createSpecialist(specialist: CreateSpecialistRequest): SpecialistDto

    fun deleteSpecialist(id: Long)
}