package com.github.ferumbot.specmarket.repositories

import com.github.ferumbot.specmarket.models.entities.Specialist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SpecialistRepository: JpaRepository<Specialist, Long> {
}