import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("plugin.spring") version "1.4.21"
    kotlin("jvm") version "1.6.0"
    id("org.flywaydb.flyway") version "9.4.0"
    id("application")
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.41"
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.codehaus.janino:janino:3.1.8")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.0")

    // Database
    implementation("org.flywaydb:flyway-core:9.4.0")
    implementation("org.postgresql:postgresql:42.5.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

flyway {
    url = "jdbc:postgresql://localhost:5434/jpaexample?user=postgres&password=postgrespsw"
}
