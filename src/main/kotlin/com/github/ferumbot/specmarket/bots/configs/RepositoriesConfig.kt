package com.github.ferumbot.specmarket.bots.configs

import com.github.ferumbot.specmarket.bots.models.entity.TelegramChat
import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.bots.repositories.TelegramChatRepository
import com.github.ferumbot.specmarket.bots.repositories.TelegramUserRepository
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan(basePackageClasses = [
    TelegramChat::class,
    TelegramUser::class,
])
@ComponentScan(basePackageClasses = [
    TelegramChatRepository::class,
    TelegramUserRepository::class,
])
class RepositoriesConfig