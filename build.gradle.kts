import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    id("com.adarshr.test-logger") version "3.2.0"
    kotlin("jvm") version "1.6.21"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    kotlin("plugin.spring") version "1.6.21"
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
    implementation("org.springframework.boot:spring-boot-starter-graphql:3.0.0")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.0.0")
    implementation("com.google.cloud.sql:postgres-socket-factory:1.9.0")
    implementation("org.postgresql:postgresql:42.5.2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("com.google.cloud:spring-cloud-gcp-dependencies:3.4.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.postgresql:postgresql:42.5.2")
    implementation("org.liquibase:liquibase-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("com.google.cloud.sql:postgres-socket-factory:1.9.0")
    implementation("com.pinterest:ktlint:0.48.2")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql:1.17.6")
    testImplementation("org.springframework.graphql:spring-graphql-test")
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
