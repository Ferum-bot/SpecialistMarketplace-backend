import extensions.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
	kotlin("plugin.jpa") version "1.5.31"
}

group = Config.GROUP
version = Config.VERSION
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {

	addAllStarters()

	addKotlin()

	addStateMachine()

	addPostgres()

	addBotsApi()

	addJavaEmoji()

	addPrometheus()
}

springBoot {
	mainClass.set(Config.MAIN_CLASS_PATH)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<Jar> {
	manifest {
		attributes["Start-Class"] = Config.MAIN_CLASS_PATH
	}
}

tasks.withType<BootJar> {
	manifest {
		attributes["Start-Class"] = Config.MAIN_CLASS_PATH
	}
}
