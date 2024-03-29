import com.adarshr.gradle.testlogger.theme.ThemeType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.adarshr.test-logger") version "4.0.0"
    kotlin("jvm") version "1.9.23"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
    id("com.autonomousapps.dependency-analysis") version "1.30.0"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

extra["testcontainersVersion"] = "1.19.7"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-graphql:3.2.4")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.2.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("com.google.cloud:spring-cloud-gcp-dependencies:5.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.pinterest:ktlint:0.51.0-FINAL")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql:42.7.3")
    runtimeOnly("org.liquibase:liquibase-core")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql:1.19.7")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

testlogger {
    theme = ThemeType.STANDARD
}
