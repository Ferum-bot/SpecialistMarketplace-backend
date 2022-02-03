package com.github.ferumbot.specmarket.repositories

import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfileStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SpecialistRepository: JpaRepository<SpecialistProfile, Long> {

    fun findAllByStatus(
        status: SpecialistProfileStatus, pageable: Pageable,
    ): Page<SpecialistProfile>


    @Query(
        value = "SELECT * FROM specialist_profile WHERE specialist_profile.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = :profession_id)",

        countQuery = "SELECT COUNT(*) FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = :profession_id)",

        nativeQuery = true
    )
    fun findAllByProfessionId(
        @Param(value = "profession_id")
        professionId: Long,

        page: Pageable,
    ): Page<SpecialistProfile>


    @Query(
        value = "SELECT * FROM specialist_profile WHERE specialist_profile.id = " +
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
    ): Page<SpecialistProfile>


    @Query(
        value = "SELECT * FROM specialist_profile WHERE specialist_profile.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_niches WHERE niche_id = :niche_id)",
        countQuery = "SELECT COUNT(*) FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_niches WHERE niche_id = :niche_id)",
        nativeQuery = true,
    )
    fun findAllByNicheId(
        @Param(value = "niche_id")
        nicheId: Long,

        page: Pageable
    ): Page<SpecialistProfile>


    @Query(
        value = "SELECT * FROM specialist_profile WHERE specialist_profile.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_niches WHERE niche_id = " +
                "ANY(SELECT id FROM niche WHERE niche.alias = :niche_alias))",
        countQuery = "SELECT COUNT(*) FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_niches WHERE niche_id = " +
                "ANY(SELECT id FROM niche WHERE niche.alias = :niche_alias))",
        nativeQuery = true,
    )
    fun findAllByNicheAlias(
        @Param(value = "niche_alias")
        alias: String,

        page: Pageable
    ): Page<SpecialistProfile>


    @Query(
        value = "SELECT * FROM specialist_profile WHERE specialist_profile.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = :profession_id)" +
                "AND specialist_profile.status_id = :status_id",
        countQuery = "SELECT COUNT(*) FROM specialist_profile WHERE specialist_profile.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = :profession_id)" +
                "AND specialist_profile.status_id = :status_id",
        nativeQuery = true,
    )
    fun findAllByProfessionWithStatus(
        @Param(value = "profession_id")
        professionId: Long,

        @Param(value = "status_id")
        statusId: Long,

        page: Pageable,
    ): Page<SpecialistProfile>


    @Query(
        value = "SELECT * FROM specialist_profile WHERE specialist_profile.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = " +
                "ANY(SELECT id FROM profession WHERE profession.alias = :profession_alias))" +
                "AND specialist_profile.status_id = :status_id",
        countQuery = "SELECT COUNT(*) FROM specialist_profile WHERE specialist_profile.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = " +
                "ANY(SELECT id FROM profession WHERE profession.alias = :profession_alias))" +
                "AND specialist_profile.status_id = :status_id",
        nativeQuery = true,
    )
    fun findAllByProfessionWithStatus(
        @Param(value = "profession_alias")
        professionAlias: String,

        @Param(value = "status_id")
        statusId: Long,

        page: Pageable,
    ): Page<SpecialistProfile>


    @Query(
        value = "SELECT * FROM specialist_profile WHERE specialist_profile.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_niches WHERE niche_id = :niche_id)" +
                "AND specialist_profile.status_id = :status_id",
        countQuery = "SELECT COUNT(*) FROM specialist_profile WHERE specialist_profile.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_niches WHERE niche_id = :niche_id)" +
                "AND specialist_profile.status_id = :status_id",
        nativeQuery = true,
    )
    fun findAllByNicheWithStatus(
        @Param(value = "niche_id")
        nicheId: Long,

        @Param(value = "status_id")
        statusId: Long,

        page: Pageable,
    ): Page<SpecialistProfile>


    @Query(
        value = "SELECT * FROM specialist_profile WHERE specialist_profile.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_niches WHERE niche_id = " +
                "ANY(SELECT id FROM niche WHERE niche.alias = :niche_alias))" +
                "AND specialist_profile.status_id = :status_id",
        countQuery = "SELECT COUNT(*) FROM specialist_profile WHERE specialist_profile.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_niches WHERE niche_id = " +
                "ANY(SELECT id FROM niche WHERE niche.alias = :niche_alias))" +
                "AND specialist_profile.status_id = :status_id",
        nativeQuery = true,
    )
    fun findAllByNicheWithStatus(
        @Param(value = "niche_alias")
        nicheAlias: String,

        @Param(value = "status_id")
        statusId: Long,

        page: Pageable,
    ): Page<SpecialistProfile>


    @Query(
        value = "SELECT * FROM specialist_profile WHERE ",
        countQuery = "SELECT COUNT(*) FROM specialist",
        nativeQuery = true,
    )
    fun findAllByProfessionAndNiche(
        @Param(value = "profession_id")
        professionId: Long,

        @Param(value = "niche_id")
        nicheId: Long,

        @Param(value = "status_id")
        statusId: Long,

        page: Pageable
    ): Page<SpecialistProfile>


    @Query(
        value = "SELECT * FROM specialist",
        countQuery = "SELECT COUNT(*) FROM specialist",
        nativeQuery = true,
    )
    fun findAllByProfessionAndNiche(
        @Param(value = "profession_alias")
        professionAlias: String,

        @Param(value = "niche_alias")
        nicheAlias: String,

        @Param(value = "status_id")
        statusId: Long,

        page: Pageable
    ): Page<SpecialistProfile>


    @Query(
        value = "SELECT COUNT(*) FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = ?1)",

        nativeQuery = true,
    )
    fun countAllByProfession(
        @Param(value = "profession_id")
        id: Long,
    ): Int


    @Query(
        value = "SELECT COUNT(*) FROM specialist WHERE specialist.id = " +
                "ANY(SELECT specialist_id FROM specialists_to_professions WHERE profession_id = " +
                "ANY(SELECT id FROM profession WHERE profession.alias = ?1))",

        nativeQuery = true,
    )
    fun countAllByProfession(
        @Param(value = "profession_alias")
        alias: String,
    ): Int


    @Query(
        value = "SELECT * FROM specialist",
        countQuery = "SELECT COUNT(*) FROM specialist",
        nativeQuery = true,
    )
    fun countAllByNiche(
        @Param(value = "niche_id")
        id: Long,
    ): Int


    @Query(
        value = "SELECT * FROM specialist",
        countQuery = "SELECT COUNT(*) FROM specialist",
        nativeQuery = true,
    )
    fun countAllByNiche(
        @Param(value = "niche_alias")
        alias: String,
    ): Int


    @Query(
        value = "SELECT * FROM specialist",
        countQuery = "SELECT COUNT(*) FROM specialist",
        nativeQuery = true,
    )
    fun countAllByProfessionWithStatus(
        @Param(value = "profession_id")
        professionId: Long,

        @Param(value = "status_id")
        statusId: Long,
    ): Int


    @Query(
        value = "SELECT * FROM specialist",
        countQuery = "SELECT COUNT(*) FROM specialist",
        nativeQuery = true,
    )
    fun countAllByProfessionWithStatus(
        @Param(value = "profession_alias")
        professionAlias: String,

        @Param(value = "status_id")
        statusId: Long
    ): Int


    @Query(
        value = "SELECT * FROM specialist",
        countQuery = "SELECT COUNT(*) FROM specialist",
        nativeQuery = true,
    )
    fun countAllByNicheWithStatus(
        @Param(value = "niche_id")
        nicheId: Long,

        @Param(value = "status_id")
        statusId: Long
    ): Int


    @Query(
        value = "SELECT * FROM specialist",
        countQuery = "SELECT COUNT(*) FROM specialist",
        nativeQuery = true,
    )
    fun countAllByNicheWithStatus(
        @Param(value = "niche_alias")
        nicheAlias: String,

        @Param(value = "status_id")
        statusId: Long
    ): Int


    @Query(
        value = "SELECT * FROM specialist",
        countQuery = "SELECT COUNT(*) FROM specialist",
        nativeQuery = true,
    )
    fun countAllByProfessionAndNiche(
        @Param(value = "profession_id")
        professionId: Long,

        @Param(value = "niche_id")
        nicheId: Long,

        @Param(value = "status_id")
        statusId: Long,
    ): Int


    @Query(
        value = "SELECT * FROM specialist",
        countQuery = "SELECT COUNT(*) FROM specialist",
        nativeQuery = true,
    )
    fun countAllByProfessionAndNiche(
        @Param(value = "profession_alias")
        professionAlias: String,

        @Param(value = "niche_alias")
        nicheAlias: String,

        @Param(value = "status_id")
        statusId: Long,
    ): Int
}