package com.github.ferumbot.specmarket.services

import com.github.ferumbot.specmarket.models.dto.NicheDto
import com.github.ferumbot.specmarket.models.entities.filter.Niche

interface NicheService {

    fun getAllAvailableNiches(): Collection<Niche>

    fun getNicheById(id: Long): Niche?

    fun getNicheByAlias(alias: String): Niche?

    fun createNewNiche(niche: NicheDto): Niche
}