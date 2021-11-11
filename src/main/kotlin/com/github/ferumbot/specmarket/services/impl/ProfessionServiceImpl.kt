package com.github.ferumbot.specmarket.services.impl

import com.github.ferumbot.specmarket.models.entities.Profession
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.dto.UpdateProfessionDto
import com.github.ferumbot.specmarket.repositories.ProfessionRepository
import com.github.ferumbot.specmarket.services.ProfessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProfessionServiceImpl @Autowired constructor(
    private val repository: ProfessionRepository
): ProfessionService {

    @Transactional(readOnly = true)
    override fun getAllAvailableProfessions(): Collection<Profession> {
        return repository.findAll()
    }

    @Transactional(readOnly = true)
    override fun searchProfessionsByFriendlyName(friendlyName: String): Collection<Profession> {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = true)
    override fun getProfessionByAlias(alias: String): Profession? {
        return repository.getByAlias(alias)
    }

    @Transactional(readOnly = true)
    override fun getProfessionById(id: Long): Profession? {
        return repository.findById(id).orElse(null)
    }

    override fun createNewProfession(profession: ProfessionDto): Profession {
        val entity = Profession(
            friendlyName = profession.friendlyName,
            alias = profession.alias,
            shortDescription = profession.shortDescription,
            longDescription = profession.longDescription,
        )

        return repository.saveAndFlush(entity)
    }

    override fun updateProfessionById(id: Long, profession: UpdateProfessionDto): Profession? {
        val entity: Profession? = repository.findById(id).orElse(null)

        return entity?.run { updateEntity(this, profession) }
    }

    override fun updateProfessionByAlias(alias: String, profession: UpdateProfessionDto): Profession? {
        val entity = repository.getByAlias(alias)

        return entity?.run { updateEntity(this, profession) }
    }

    override fun deleteProfessionByAlias(alias: String): Boolean {
        repository.deleteByAlias(alias)
        return true
    }

    override fun deleteProfessionById(id: Long): Boolean {
        repository.deleteById(id)
        return true
    }

    private fun updateEntity(entity: Profession, update: UpdateProfessionDto): Profession {
        update.newFriendlyName?.run { entity.friendlyName = this }
        update.newAlias?.run { entity.alias = this }
        update.newShortDescription?.run { entity.shortDescription = this }
        update.newLongDescription?.run { entity.longDescription = this }
        return repository.saveAndFlush(entity)
    }
}