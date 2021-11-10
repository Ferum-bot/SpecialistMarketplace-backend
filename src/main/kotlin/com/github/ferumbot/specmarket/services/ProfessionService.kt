package com.github.ferumbot.specmarket.services

import com.github.ferumbot.specmarket.models.request.ProfessionDto
import com.github.ferumbot.specmarket.models.entities.Profession

interface ProfessionService {

    fun getAllAvailableProfessions(): Collection<Profession>

    fun searchProfessionsByFriendlyName(friendlyName: String): Collection<Profession>

    fun getProfessionByAlias(alias: String): Profession?

    fun getProfessionById(id: Long): Profession?

    fun createNewProfession(profession: ProfessionDto): Profession

    fun updateProfessionById(id: Long, profession: ProfessionDto): Profession?

    fun updateProfessionByAlias(alias: String, profession: ProfessionDto): Profession?

    fun deleteProfessionByAlias(alias: String): Boolean

    fun deleteProfessionById(id: Long): Boolean
}