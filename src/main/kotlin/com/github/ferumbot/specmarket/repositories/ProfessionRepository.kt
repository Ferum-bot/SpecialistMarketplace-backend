package com.github.ferumbot.specmarket.repositories

import com.github.ferumbot.specmarket.models.entities.Profession
import org.springframework.data.jpa.repository.JpaRepository

interface ProfessionRepository: JpaRepository<Profession, Long> {

    fun getByAlias(alias: String): Profession?

    fun deleteByAlias(alias: String)
}