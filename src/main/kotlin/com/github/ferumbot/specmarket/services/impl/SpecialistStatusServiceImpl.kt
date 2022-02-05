package com.github.ferumbot.specmarket.services.impl

import com.github.ferumbot.specmarket.core.extensions.getOrNull
import com.github.ferumbot.specmarket.core.extensions.transform
import com.github.ferumbot.specmarket.exceptions.UndefinedProfileStatus
import com.github.ferumbot.specmarket.models.dto.SpecialistStatusDto
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfileStatus
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses
import com.github.ferumbot.specmarket.models.request.CreateSpecialistStatusRequest
import com.github.ferumbot.specmarket.repositories.SpecialistStatusRepository
import com.github.ferumbot.specmarket.services.SpecialistStatusService
import org.springframework.stereotype.Service

@Service
class SpecialistStatusServiceImpl(
    private val repository: SpecialistStatusRepository
): SpecialistStatusService {

    override fun getAllStatuses(): Collection<SpecialistStatusDto> {
        return repository.findAll().map {
            SpecialistStatusDto.from(it)
        }
    }

    override fun getStatusById(id: Long): SpecialistStatusDto? {
        return repository.findById(id).getOrNull()?.transform {
            SpecialistStatusDto.from(it)
        }
    }

    override fun createNewStatus(status: CreateSpecialistStatusRequest): SpecialistStatusDto {
        val alias = if (ProfileStatuses.isPresented(status.alias)) {
            ProfileStatuses.fromAlias(status.alias)
        } else {
            throw UndefinedProfileStatus("Undefined profile alias: ${status.alias}")
        }
        val entity = SpecialistProfileStatus(
            alias = alias,
            name = status.name
        )

        return repository.saveAndFlush(entity).transform {
            SpecialistStatusDto.from(it)
        }
    }

    override fun deleteStatus(id: Long) {
        val entity = repository.findById(id).orElseThrow {
            UndefinedProfileStatus("Undefined profile status with id: $id§")
        }
        repository.delete(entity)
    }
}