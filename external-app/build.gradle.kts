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
