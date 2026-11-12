plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktfmt)
    alias(libs.plugins.gatling.gradle)
}

group = "me.bossm0n5t3r"

version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion =
            JavaLanguageVersion.of(
                libs.versions.jdk.version.get().toInt(),
            )
    }
}

repositories { mavenCentral() }

dependencies {
    // For Gatling Simulations
    implementation(project(":external-app"))
    implementation(project(":ktor-app"))
    implementation(project(":mvc-app"))
    implementation(project(":mvc-without-virtual-thread-app"))
    implementation(project(":webflux-app"))

    // gatling
    implementation(libs.gatling.test.framework)
    implementation(libs.gatling.charts.highcharts)
}

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") } }

tasks.withType<Test> { useJUnitPlatform() }

ktfmt { kotlinLangStyle() }

// Gatling 리포트를 프로젝트 루트의 'reports/gatling' 디렉터리로 복사하는 태스크를 정의합니다.
val copyGatlingReports: TaskProvider<Task> =
    tasks.register("copyGatlingReports") {
        description =
            "Copies the latest Gatling report of each simulation to the root reports/gatling directory"
        doLast {
            val sourceDir = layout.buildDirectory.dir("reports/gatling").get().asFile
            val targetRootDir = rootProject.layout.projectDirectory.dir("reports/gatling").asFile

            // 소스 디렉터리가 없으면 작업을 중단합니다.
            if (!sourceDir.exists()) {
                return@doLast
            }

            val reportDirs =
                sourceDir
                    .listFiles()
                    ?.filter { it.isDirectory }
                    ?.mapNotNull { file ->
                        val name = file.name
                        val targetName =
                            when {
                                name.startsWith("externalappsimulation-") -> "external-app"
                                name.startsWith("ktorsimulation-") -> "ktor"
                                name.startsWith("mvcsimulation-") -> "mvc"
                                name.startsWith("mvcwithoutvirtualthreadsimulation-") ->
                                    "mvc-without-virtual-thread"
                                name.startsWith("webfluxsimulation-") -> "webflux"
                                name.startsWith("webfluxwithoutcoroutinessimulation-") ->
                                    "webflux-without-coroutines"
                                else -> null
                            }
                        targetName?.let { it to file }
                    } ?: return@doLast

            // 같은 시뮬레이션을 여러 번 실행하면 리포트 디렉터리가 쌓이므로, 시뮬레이션별로 가장 최근 실행 결과만 복사합니다.
            // 디렉터리 이름의 접미사가 실행 시각(yyyyMMddHHmmssSSS)이라 이름순 최댓값이 가장 최근 결과입니다.
            reportDirs
                .groupBy({ it.first }, { it.second })
                .mapValues { (_, dirs) -> dirs.maxBy { it.name } }
                .forEach { (targetName, latestDir) ->
                    val targetDir = project.file("$targetRootDir/$targetName")

                    // 복사 대상 위치에 이미 디렉터리가 있다면 삭제합니다.
                    if (targetDir.exists()) {
                        targetDir.deleteRecursively()
                    }

                    // Gatling 리포트 디렉터리를 복사합니다.
                    copy {
                        from(latestDir)
                        into(targetDir)
                    }
                }
        }
    }

// 'gatlingRun' 태스크가 완료된 후 'copyGatlingReports' 태스크를 실행하도록 설정합니다.
tasks.named("gatlingRun") { finalizedBy(copyGatlingReports) }
