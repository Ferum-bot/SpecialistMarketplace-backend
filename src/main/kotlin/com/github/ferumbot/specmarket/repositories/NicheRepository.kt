package com.github.ferumbot.specmarket.repositories

import com.github.ferumbot.specmarket.models.entities.filter.Niche
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NicheRepository: JpaRepository<Niche, Long> {

    fun getByAlias(alias: String): Niche?
}