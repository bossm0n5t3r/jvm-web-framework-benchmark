plugins {
    alias(libs.plugins.gatling.gradle)
}

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

    // gatling
    implementation(libs.gatling.test.framework)
    implementation(libs.gatling.charts.highcharts)

    // Testing
    testImplementation(libs.spring.boot.starter.test)
}

tasks.bootRun {
    enabled = false
}

// Gatling 리포트를 프로젝트 루트의 'gatling-reports' 디렉터리로 복사하는 태스크를 정의합니다.
val copyGatlingReports =
    tasks.register<Copy>("copyGatlingReports") {
        from(layout.buildDirectory.dir("reports/gatling"))
        into(rootProject.layout.projectDirectory.dir("reports/gatling"))
    }

// 'gatlingRun' 태스크가 완료된 후 'copyGatlingReports' 태스크를 실행하도록 설정합니다.
tasks.named("gatlingRun") {
    finalizedBy(copyGatlingReports)
}
