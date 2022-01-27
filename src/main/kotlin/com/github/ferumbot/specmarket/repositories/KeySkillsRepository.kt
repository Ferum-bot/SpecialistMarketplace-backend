package com.github.ferumbot.specmarket.repositories

import com.github.ferumbot.specmarket.models.entities.specifications.KeySkills
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KeySkillsRepository: JpaRepository<KeySkills, Long>