dependencies {
    api(project(":common"))

    // Spring Boot starters
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    developmentOnly(libs.spring.boot.docker.compose)
    implementation(libs.kotlin.reflect)

    // Database dependencies
    runtimeOnly(libs.postgresql)
    testImplementation(libs.spring.boot.starter.test)
}
