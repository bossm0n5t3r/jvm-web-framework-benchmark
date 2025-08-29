plugins {
    alias(libs.plugins.gatling.gradle)
}

dependencies {
    // For Gatling Simulations
    implementation(project(":external-app"))
    implementation(project(":mvc-app"))
    implementation(project(":mvc-without-virtual-thread-app"))
    implementation(project(":webflux-app"))

    // gatling
    implementation(libs.gatling.test.framework)
    implementation(libs.gatling.charts.highcharts)
}

tasks.bootJar {
    enabled = false
}

// Gatling 리포트를 프로젝트 루트의 'gatling-reports' 디렉터리로 복사하는 태스크를 정의합니다.
val copyGatlingReports =
    tasks.register("copyGatlingReports") {
        doLast {
            val sourceDir =
                layout.buildDirectory
                    .dir("reports/gatling")
                    .get()
                    .asFile
            val targetRootDir =
                rootProject.layout.projectDirectory
                    .dir("reports/gatling")
                    .asFile

            // 소스 디렉터리가 없으면 작업을 중단합니다.
            if (!sourceDir.exists()) {
                return@doLast
            }

            sourceDir.listFiles()?.forEach { file ->
                val name = file.name
                var targetName: String? = null

                targetName =
                    when {
                        name.startsWith("externalappsimulation-") -> "external-app"
                        name.startsWith("mvcsimulation-") -> "mvc"
                        name.startsWith("mvcwithoutvirtualthreadsimulation-") -> "mvc-without-virtual-thread"
                        name.startsWith("webfluxsimulation-") -> "webflux"
                        name.startsWith("webfluxwithoutcoroutinessimulation-") -> "webflux-without-coroutines"
                        else -> null
                    }

                // 대상 디렉터리가 맞는지 확인하고 복사를 진행합니다.
                if (file.isDirectory && targetName != null) {
                    val targetDir = project.file("$targetRootDir/$targetName")

                    // 복사 대상 위치에 이미 디렉터리가 있다면 삭제합니다.
                    if (targetDir.exists()) {
                        targetDir.deleteRecursively()
                    }

                    // Gatling 리포트 디렉터리를 복사합니다.
                    copy {
                        from(file)
                        into(targetDir)
                    }
                }
            }
        }
    }

// 'gatlingRun' 태스크가 완료된 후 'copyGatlingReports' 태스크를 실행하도록 설정합니다.
tasks.named("gatlingRun") {
    finalizedBy(copyGatlingReports)
}
