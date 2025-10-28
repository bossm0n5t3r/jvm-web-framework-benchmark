plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.kotlin.plugin.jpa)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

dependencies {
    api(project(":common"))

    // Spring Boot starters
    api(libs.spring.boot.starter.webflux)
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
    implementation("io.netty:netty-resolver-dns-native-macos:${libs.versions.netty.resolver.dns.native.macos.version.get()}:osx-aarch_64")
}
