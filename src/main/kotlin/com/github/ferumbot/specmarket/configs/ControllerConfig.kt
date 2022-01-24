package com.github.ferumbot.specmarket.configs

import com.github.ferumbot.specmarket.controllers.AdminController
import com.github.ferumbot.specmarket.controllers.BaseInfoController
import com.github.ferumbot.specmarket.controllers.advice.ExceptionControllerAdvice
import com.github.ferumbot.specmarket.controllers.ProfessionController
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [
    AdminController::class,
    BaseInfoController::class,
    ExceptionControllerAdvice::class,
    ProfessionController::class,
])
class ControllerConfig