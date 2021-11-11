package com.github.ferumbot.specmarket.repositories

import com.github.ferumbot.specmarket.models.entities.Profession
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProfessionRepository: JpaRepository<Profession, Long> {

    fun getByAlias(alias: String): Profession?

    fun deleteByAlias(alias: String)

    @Query(
        value = "SELECT * FROM profession WHERE friendly_name LIKE CONCAT('%', :friendly_name, '%') ORDER BY alias",
        nativeQuery = true,
    )
    fun searchProfessionsByFriendlyName(
        @Param("friendly_name")
        friendlyName: String,
    ): Collection<Profession>
}