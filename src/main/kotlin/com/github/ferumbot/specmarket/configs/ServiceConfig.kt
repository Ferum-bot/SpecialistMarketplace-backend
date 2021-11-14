package com.github.ferumbot.specmarket.configs

import com.github.ferumbot.specmarket.services.impl.ProfessionServiceImpl
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [
    ProfessionServiceImpl::class,
])
class ServiceConfig