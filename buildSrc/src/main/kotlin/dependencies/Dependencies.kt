package dependencies

object Dependencies {

    const val STARTER_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val STARTER_WEB = "org.springframework.boot:spring-boot-starter-web"

    const val STATE_MACHINE = "org.springframework.statemachine:spring-statemachine-core:${Versions.STATE_MACHINE}"

    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin"

    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
    const val KOTLIN_STD = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
}