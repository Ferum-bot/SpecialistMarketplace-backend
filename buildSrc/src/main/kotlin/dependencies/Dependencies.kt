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

    const val SPRING_FOX_STARTER = "io.springfox:springfox-boot-starter:${Versions.SWAGGER_FOX}"
    const val SPRING_FOX_UI = "io.springfox:springfox-swagger-ui:${Versions.SWAGGER_FOX}"

    const val SPRING_OPEN_API = "org.springdoc:springdoc-openapi-ui:${Versions.SWAGGER_DOC}"
    const val SPRING_OPEN_API_SECURITY = "org.springdoc:springdoc-openapi-security:${Versions.SWAGGER_DOC}"
    const val SPRING_OPEN_API_KOTLIN = "org.springdoc:springdoc-openapi-kotlin:${Versions.SWAGGER_DOC}"
}