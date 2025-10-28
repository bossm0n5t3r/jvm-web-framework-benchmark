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
