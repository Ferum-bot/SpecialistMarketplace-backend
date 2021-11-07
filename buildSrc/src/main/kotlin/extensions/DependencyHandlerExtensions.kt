package extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import dependencies.Dependencies
import dependencies.TestDependencies
import dependencies.RuntimeDependencies

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.runtimeOnly(dependencyNotation: String): Dependency? =
    add("runtimeOnly", dependencyNotation)

fun DependencyHandler.runtimeOnly(dependencyNotation: Any): Dependency? =
    add("runtimeOnly", dependencyNotation)

fun DependencyHandler.developmentOnly(dependencyNotation: String): Dependency? =
    add("developmentOnly", dependencyNotation)

fun DependencyHandler.developmentOnly(dependencyNotation: Any): Dependency? =
    add("developmentOnly", dependencyNotation)

fun DependencyHandler.api(dependencyNotation: String): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.annotationProcessor(dependencyNotation: String): Dependency? =
    add("annotationProcessor", dependencyNotation)

fun DependencyHandler.annotationProcessor(dependencyNotation: Any): Dependency? =
    add("annotationProcessor", dependencyNotation)

fun DependencyHandler.addAllStarters() {
    implementation(Dependencies.STARTER_WEB)
    implementation(Dependencies.STARTER_JPA)
    implementation(Dependencies.JACKSON)
    testImplementation(TestDependencies.STARTER_SPRING)
    annotationProcessor(Dependencies.STARTER_CONFIGURATION_PROCESSOR)
    developmentOnly(Dependencies.STARTER_DEV_TOOLS)
}

fun DependencyHandler.addKotlin() {
    implementation(Dependencies.KOTLIN_REFLECT)
    implementation(Dependencies.KOTLIN_STD)
}

fun DependencyHandler.addStateMachine() {
    implementation(Dependencies.STATE_MACHINE)
    testImplementation(TestDependencies.STATE_MACHINE)
}

fun DependencyHandler.addPostgres() {
    runtimeOnly(RuntimeDependencies.POSTGRES)
}

fun DependencyHandler.addBotsApi() {
    implementation(Dependencies.TELEGRAM_BOTS)
}

fun DependencyHandler.addJavaEmoji() {
    implementation(Dependencies.EMOJI_JAVA)
}
