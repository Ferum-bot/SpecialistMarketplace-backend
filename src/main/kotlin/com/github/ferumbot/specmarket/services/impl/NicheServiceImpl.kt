package com.github.ferumbot.specmarket.services.impl

import com.github.ferumbot.specmarket.models.dto.NicheDto
import com.github.ferumbot.specmarket.models.entities.Niche
import com.github.ferumbot.specmarket.repositories.NicheRepository
import com.github.ferumbot.specmarket.services.NicheService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NicheServiceImpl @Autowired constructor(
    private val repository: NicheRepository
): NicheService {

    @Transactional(readOnly = true)
    override fun getAllAvailableNiches(): Collection<Niche> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getNicheById(id: Long): Niche? {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getNicheByAlias(alias: String): Niche? {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun createNewNiche(niche: NicheDto): Niche {
        TODO("Not yet implemented")
    }
}