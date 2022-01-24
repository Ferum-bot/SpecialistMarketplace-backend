package com.github.ferumbot.specmarket.configs

import com.github.ferumbot.specmarket.models.entities.*
import com.github.ferumbot.specmarket.repositories.KeySkillsRepository
import com.github.ferumbot.specmarket.repositories.NicheRepository
import com.github.ferumbot.specmarket.repositories.ProfessionRepository
import com.github.ferumbot.specmarket.repositories.SpecialistRepository
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackageClasses = [
    Filter::class,
    KeySkills::class,
    Profession::class,
    Specialist::class,
    Niche::class,
])
@EnableJpaRepositories(basePackageClasses = [
    ProfessionRepository::class,
    KeySkillsRepository::class,
    SpecialistRepository::class,
    NicheRepository::class,
])
@ComponentScan(basePackageClasses = [
    ProfessionRepository::class,
    KeySkillsRepository::class,
    SpecialistRepository::class,
    NicheRepository::class,
])
class RepositoryConfig