dependencies {
    api(project(":common"))

    // Spring Boot starter for an application framework
    implementation(libs.spring.boot.starter)
    implementation(libs.kotlin.reflect)

    // HTTP Client dependencies for making requests
    implementation(libs.spring.boot.starter.web) // RestClient for blocking HTTP calls
    implementation(libs.spring.boot.starter.webflux) // WebClient for reactive HTTP calls

    // JSON processing
    implementation(libs.jackson.module.kotlin)

    // Statistics and metrics
    implementation(libs.kotlinx.dataframe)

    // Coroutines for async operations
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.reactor)

    // Testing
    testImplementation(libs.spring.boot.starter.test)
}

tasks.bootRun {
    enabled = false
}
