package com.github.ferumbot.specmarket.repositories

import com.github.ferumbot.specmarket.models.entities.Specialist
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SpecialistRepository: JpaRepository<Specialist, Long> {

    @Query(
        value = "SELECT * FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = :profession_id)",

        countQuery = "SELECT COUNT(*) FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = :profession_id)",

        nativeQuery = true
    )
    fun findAllByProfessionId(
        @Param(value = "profession_id")
        professionId: Long,

        page: Pageable,
    ): Page<Specialist>


    @Query(
        value = "SELECT * FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = :profession_id)" +
                "AND specialist.is_visible = true AND specialist.is_completely_filled = true",

        countQuery = "SELECT COUNT(*) FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = :profession_id)" +
                "AND specialist.is_visible = true AND specialist.is_completely_filled = true",

        nativeQuery = true
    )
    fun findOnlyVisibleAndFinishedByProfessionId(
        @Param(value = "profession_id")
        professionId: Long,

        page: Pageable,
    ): Page<Specialist>

    @Query(
        value = "SELECT * FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = " +
                "ANY(SELECT id FROM profession WHERE profession.alias = :profession_alias))",

        countQuery = "SELECT COUNT(*) FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = " +
                "ANY(SELECT id FROM profession WHERE profession.alias = :profession_alias))",

        nativeQuery = true
    )
    fun findAllByProfessionAlias(
        @Param(value = "profession_alias")
        alias: String,

        page: Pageable,
    ): Page<Specialist>


    @Query(
        value = "SELECT * FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = " +
                "ANY(SELECT id FROM profession WHERE profession.alias = :profession_alias))" +
                "AND specialist.is_visible = true and specialist.is_completely_filled = true",

        countQuery = "SELECT COUNT(*) FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = " +
                "ANY(SELECT id FROM profession WHERE profession.alias = :profession_alias))" +
                "AND specialist.is_visible = true and specialist.is_completely_filled = true",

        nativeQuery = true
    )
    fun findOnlyVisibleAndFinishedByProfessionAlias(
        @Param(value = "profession_alias")
        alias: String,

        page: Pageable
    ): Page<Specialist>


    @Query(
        value = "SELECT COUNT(*) FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = :profession_id)",

        nativeQuery = true,
    )
    fun countSpecialistsByProfessionId(
        @Param(value = "profession_id")
        professionId: Long,
    ): Int


    @Query(
        value = "SELECT COUNT(*) FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = " +
                "ANY(SELECT id FROM profession WHERE profession.alias = :profession_alias))",

        nativeQuery = true,
    )
    fun countSpecialistsByProfessionAlias(
        @Param(value = "profession_alias")
        alias: String,
    ): Int
}