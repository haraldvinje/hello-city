import com.adarshr.gradle.testlogger.theme.ThemeType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.3"
    id("com.adarshr.test-logger") version "3.2.0"
    kotlin("jvm") version "1.9.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    kotlin("plugin.spring") version "1.9.0"
    kotlin("plugin.jpa") version "1.9.0"
    id("com.autonomousapps.dependency-analysis") version "1.20.0"
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

extra["testcontainersVersion"] = "1.18.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-graphql:3.1.2")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.1.2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("com.google.cloud:spring-cloud-gcp-dependencies:4.7.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.pinterest:ktlint:0.50.0")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
    runtimeOnly("org.liquibase:liquibase-core")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql:1.18.3")
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
