plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.kotlin.plugin.jpa)
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

    // Spring Boot starters
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.spring.boot.starter.data.r2dbc)
    developmentOnly(libs.spring.boot.docker.compose)

    // Database dependencies
    runtimeOnly(libs.r2dbc.postgresql)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.reactor)

    implementation(libs.spring.boot.starter)
    implementation(libs.kotlin.reflect)
    testImplementation(libs.spring.boot.starter.test)

    // Netty resolver for macOS
    implementation(
        "io.netty:netty-resolver-dns-native-macos:${libs.versions.netty.resolver.dns.native.macos.version.get()}:osx-aarch_64",
    )
}

// Spring Boot BOM이 간접 의존성인 kotlinx-coroutines-core-jvm/-reactive를 BOM 버전으로 고정하므로,
// 직접 선언한 core/reactor와 버전이 어긋나지 않도록 BOM의 코루틴 버전을 catalog 버전에 맞춥니다.
extra["kotlin-coroutines.version"] = libs.versions.kotlinx.coroutines.get()

// spring-boot-docker-compose가 루트의 compose.yaml을 찾을 수 있도록 루트 디렉터리에서 실행합니다.
tasks.bootRun { workingDir = rootDir }

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") } }

tasks.withType<Test> { useJUnitPlatform() }

ktfmt { kotlinLangStyle() }
