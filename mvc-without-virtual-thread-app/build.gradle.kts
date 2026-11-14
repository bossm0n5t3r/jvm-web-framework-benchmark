plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.kotlin.plugin.jpa)
    alias(libs.plugins.kotlin.plugin.allopen)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.ktfmt)
}

group = "me.bossm0n5t3r"

version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion =
            JavaLanguageVersion.of(
                libs.versions.jdk.version.get().toInt(),
            )
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

repositories { mavenCentral() }

dependencies {
    api(project(":common"))

    // Spring Boot starters
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    developmentOnly(libs.spring.boot.docker.compose)
    implementation(libs.kotlin.reflect)

    implementation(libs.apache.httpclient5)

    // Database dependencies
    runtimeOnly(libs.postgresql)
    testImplementation(libs.spring.boot.starter.test)
}

// spring-boot-docker-compose가 루트의 compose.yaml을 찾을 수 있도록 루트 디렉터리에서 실행합니다.
tasks.bootRun { workingDir = rootDir }

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") } }

tasks.withType<Test> { useJUnitPlatform() }

ktfmt { kotlinLangStyle() }
