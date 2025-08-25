dependencies {
    api(project(":common"))

    // Spring Boot starters (without database dependencies)
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.kotlin.reflect)

    testImplementation(libs.spring.boot.starter.test)
}
