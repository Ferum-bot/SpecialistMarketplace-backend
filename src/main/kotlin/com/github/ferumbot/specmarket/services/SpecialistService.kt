package com.github.ferumbot.specmarket.services

import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.entities.Specialist


interface SpecialistService {

    fun getAllSpecialists(pageNumber: Int, pageSize: Int): Collection<SpecialistDto>

    fun getSpecialistByProfessionId(professionId: Long, pageNumber: Int, pageSize: Int): Collection<SpecialistDto>

    fun getSpecialistByProfessionAlias(alias: String, pageNumber: Int, pageSize: Int): Collection<SpecialistDto>

}