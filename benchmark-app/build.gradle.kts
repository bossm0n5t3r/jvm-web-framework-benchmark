dependencies {
    api(project(":common"))

    // Spring Boot starter for an application framework
    implementation(libs.spring.boot.starter)
    implementation(libs.kotlin.reflect)

    // HTTP Client dependencies for making requests
    implementation(libs.spring.boot.starter.webflux) // WebClient for reactive HTTP calls
    implementation(libs.okhttp) // OkHttp for blocking HTTP calls

    // JSON processing
    implementation(libs.jackson.module.kotlin)

    // Statistics and metrics
    implementation(libs.commons.math3)

    // Coroutines for async operations
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.reactor)

    // Testing
    testImplementation(libs.spring.boot.starter.test)
}

tasks.bootRun {
    enabled = false
}
