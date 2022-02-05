package com.github.ferumbot.specmarket.services

import com.github.ferumbot.specmarket.models.dto.SpecialistStatusDto
import com.github.ferumbot.specmarket.models.request.CreateSpecialistStatusRequest

interface SpecialistStatusService {

    fun getAllStatuses(): Collection<SpecialistStatusDto>

    fun getStatusById(id: Long): SpecialistStatusDto?

    fun createNewStatus(status: CreateSpecialistStatusRequest): SpecialistStatusDto

    fun deleteStatus(id: Long)
}