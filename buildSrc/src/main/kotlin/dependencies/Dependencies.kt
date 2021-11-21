package dependencies

object Dependencies {

    const val STARTER_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val STARTER_WEB = "org.springframework.boot:spring-boot-starter-web"
    const val STARTER_CONFIGURATION_PROCESSOR = "org.springframework.boot:spring-boot-configuration-processor"
    const val STARTER_DEV_TOOLS = "org.springframework.boot:spring-boot-devtools"
    const val STARTER_ACTUATOR = "org.springframework.boot:spring-boot-starter-actuator"

    const val STATE_MACHINE = "org.springframework.statemachine:spring-statemachine-core:${Versions.STATE_MACHINE}"

    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin"

    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
    const val KOTLIN_STD = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"

    const val TELEGRAM_BOTS = "org.telegram:telegrambots:${Versions.TELEGRAM_BOT}"

    const val EMOJI_JAVA = "com.vdurmont:emoji-java:${Versions.EMOJI_JAVA}"

    const val MICROMETER_PROMETHEUS = "io.micrometer:micrometer-registry-prometheus"
}