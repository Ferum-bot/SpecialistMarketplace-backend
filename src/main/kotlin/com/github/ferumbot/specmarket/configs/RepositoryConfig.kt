package com.github.ferumbot.specmarket.configs

import com.github.ferumbot.specmarket.models.entities.filter.Course
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfile
import com.github.ferumbot.specmarket.models.entities.specifications.Filter
import com.github.ferumbot.specmarket.models.entities.specifications.KeySkills
import com.github.ferumbot.specmarket.models.entities.filter.Niche
import com.github.ferumbot.specmarket.models.entities.filter.Profession
import com.github.ferumbot.specmarket.models.entities.specialist.SpecialistProfileStatus
import com.github.ferumbot.specmarket.repositories.*
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackageClasses = [
    Filter::class,
    KeySkills::class,
    Profession::class,
    SpecialistProfile::class,
    SpecialistProfileStatus::class,
    Niche::class,
    Course::class,
])
@EnableJpaRepositories(basePackageClasses = [
    ProfessionRepository::class,
    KeySkillsRepository::class,
    SpecialistRepository::class,
    NicheRepository::class,
    SpecialistStatusRepository::class,
])
@ComponentScan(basePackageClasses = [
    ProfessionRepository::class,
    KeySkillsRepository::class,
    SpecialistRepository::class,
    NicheRepository::class,
    SpecialistStatusRepository::class,
])
class RepositoryConfig