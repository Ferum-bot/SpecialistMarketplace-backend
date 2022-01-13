package com.github.ferumbot.specmarket.configs.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "postgres")
data class PostgresProperties(

    var databaseUrl: String = "",

    var userName: String = "",

    var password: String = "",
)
