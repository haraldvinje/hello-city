import com.adarshr.gradle.testlogger.theme.ThemeType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.adarshr.test-logger") version "4.0.0"
    kotlin("jvm") version "2.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
    kotlin("plugin.spring") version "2.1.10"
    kotlin("plugin.jpa") version "2.1.0"
    id("com.autonomousapps.dependency-analysis") version "2.6.1"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

extra["testcontainersVersion"] = "1.20.4"

val mockitoAgent = configurations.create("mockitoAgent")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-graphql:3.4.1")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.4.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("com.google.cloud:spring-cloud-gcp-dependencies:5.9.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.pinterest:ktlint:0.51.0-FINAL")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql:42.7.4")
    runtimeOnly("org.liquibase:liquibase-core")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql:1.20.4")
    testImplementation("org.mockito:mockito-core:5.14.2")
    mockitoAgent("org.mockito:mockito-core:5.14.2") { isTransitive = false }
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
}

testlogger {
    theme = ThemeType.STANDARD
}
