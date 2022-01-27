package com.github.ferumbot.specmarket.services

import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.dto.UpdateProfessionDto
import com.github.ferumbot.specmarket.models.entities.specifications.Profession

interface ProfessionService {

    fun getAllAvailableProfessions(): Collection<Profession>

    fun searchProfessionsByFriendlyName(friendlyName: String): Collection<Profession>

    fun getProfessionByAlias(alias: String): Profession?

    fun getProfessionById(id: Long): Profession?

    fun createNewProfession(profession: ProfessionDto): Profession

    fun updateProfessionById(id: Long, profession: UpdateProfessionDto): Profession?

    fun updateProfessionByAlias(alias: String, profession: UpdateProfessionDto): Profession?

    fun deleteProfessionByAlias(alias: String)

    fun deleteProfessionById(id: Long)
}