plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)
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

repositories { mavenCentral() }

dependencies {
    api(project(":common"))

    // Spring Boot starters (without database dependencies)
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.kotlin.reflect)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.reactor)

    testImplementation(libs.spring.boot.starter.test)
}

// Spring Boot BOM이 간접 의존성인 kotlinx-coroutines-core-jvm/-reactive를 BOM 버전으로 고정하므로,
// 직접 선언한 core/reactor와 버전이 어긋나지 않도록 BOM의 코루틴 버전을 catalog 버전에 맞춥니다.
extra["kotlin-coroutines.version"] = libs.versions.kotlinx.coroutines.get()

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") } }

tasks.withType<Test> { useJUnitPlatform() }

ktfmt { kotlinLangStyle() }
