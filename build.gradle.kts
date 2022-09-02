import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "2.6.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.adarshr.test-logger") version "3.2.0"
    kotlin("jvm") version "1.6.10"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.allopen") version "1.4.32"
    kotlin("plugin.jpa") version "1.4.32"
    kotlin("kapt") version "1.4.32"
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

extra["testcontainersVersion"] = "1.17.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.3")
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.3")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.3")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.7.3")
    implementation("org.springframework.boot:spring-boot-starter-logging:2.7.3")
    implementation("org.springframework.cloud:spring-cloud-gcp-starter-sql-postgresql:1.2.8.RELEASE")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.7")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.10")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.liquibase:liquibase-core:4.15.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")
    implementation("com.google.cloud.sql:postgres-socket-factory:1.6.3")
    implementation("com.pinterest:ktlint:0.47.0")
    runtimeOnly("com.h2database:h2:2.1.214")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.7.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.3")
    testImplementation("io.projectreactor:reactor-test:3.4.22")
    testImplementation("org.testcontainers:junit-jupiter:1.17.3")
    testImplementation("org.testcontainers:postgresql:1.17.3")
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
