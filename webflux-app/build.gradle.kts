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
}
