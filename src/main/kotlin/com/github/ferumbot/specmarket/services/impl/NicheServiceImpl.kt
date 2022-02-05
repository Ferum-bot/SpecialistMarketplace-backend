package com.github.ferumbot.specmarket.services.impl

import com.github.ferumbot.specmarket.models.dto.NicheDto
import com.github.ferumbot.specmarket.models.entities.filter.Niche
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
        return repository.findAll()
    }

    @Transactional(readOnly = true)
    override fun getNicheById(id: Long): Niche? {
        val result = repository.findById(id)
        return if (result.isPresent) {
            result.get()
        } else {
            null
        }
    }

    @Transactional(readOnly = true)
    override fun getNicheByAlias(alias: String): Niche? {
        return repository.getByAlias(alias)
    }

    @Transactional
    override fun createNewNiche(niche: NicheDto): Niche {
        val newNiche = Niche(
            friendlyName = niche.friendlyName,
            alias = niche.alias,
            shortDescription = niche.shortDescription,
            longDescription = niche.longDescription,
        )

        return repository.save(newNiche)
    }
}