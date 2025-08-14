dependencies {
    api(project(":common"))

    // Spring Boot starters (without database dependencies)
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.kotlin.reflect)

    testImplementation(libs.spring.boot.starter.test)
}
