package com.github.ferumbot.specmarket.repositories

import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfileStatus
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpecialistStatusRepository: JpaRepository<SpecialistProfileStatus, Long> {

    fun findByAlias(alias: ProfileStatuses): SpecialistProfileStatus?
}