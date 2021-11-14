package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.services.impl.TelegramChatServiceImpl
import com.github.ferumbot.specmarket.bots.services.impl.TelegramUserServiceImpl
import com.github.ferumbot.specmarket.bots.services.impl.TelegramUserSpecialistServiceImpl
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [
    TelegramChatServiceImpl::class,
    TelegramUserServiceImpl::class,
    TelegramUserSpecialistServiceImpl::class,
])
class ServiceConfig