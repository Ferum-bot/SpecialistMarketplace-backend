package com.github.ferumbot.specmarket.configs

import com.github.ferumbot.specmarket.services.impl.NicheServiceImpl
import com.github.ferumbot.specmarket.services.impl.ProfessionServiceImpl
import com.github.ferumbot.specmarket.services.impl.SpecialistServiceImpl
import com.github.ferumbot.specmarket.services.impl.SpecialistStatusServiceImpl
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [
    ProfessionServiceImpl::class,
    SpecialistServiceImpl::class,
    NicheServiceImpl::class,
    SpecialistStatusServiceImpl::class,
])
class ServiceConfig