package com.github.ferumbot.specmarket.configs

import com.github.ferumbot.specmarket.configs.properties.PostgresProperties
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    fun provideDataSource(properties: PostgresProperties): DataSource {
        val dataSource = HikariDataSource()

        dataSource.driverClassName = "org.postgresql.Driver"
        dataSource.jdbcUrl = properties.databaseUrl
        dataSource.username = properties.userName
        dataSource.password = properties.password

        return dataSource
    }
}