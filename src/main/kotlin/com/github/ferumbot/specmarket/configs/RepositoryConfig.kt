package com.github.ferumbot.specmarket.configs

import com.github.ferumbot.specmarket.models.entities.Filter
import com.github.ferumbot.specmarket.models.entities.KeySkills
import com.github.ferumbot.specmarket.models.entities.Profession
import com.github.ferumbot.specmarket.models.entities.Specialist
import com.github.ferumbot.specmarket.repositories.ProfessionRepository
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
])
@EnableJpaRepositories(basePackageClasses = [
    ProfessionRepository::class,
])
@ComponentScan(basePackageClasses = [
    ProfessionRepository::class,
])
class RepositoryConfig