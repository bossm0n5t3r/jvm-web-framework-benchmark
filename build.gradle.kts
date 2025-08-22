plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.plugin.spring) apply false
    alias(libs.plugins.kotlin.plugin.jpa) apply false
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.gatling.gradle) apply false
}

val jdkVersion = libs.versions.jdk.version.get()

val kotlinJvmId = libs.plugins.kotlin.jvm.get().pluginId
val kotlinSpringId = libs.plugins.kotlin.plugin.spring.get().pluginId
val kotlinJpaId = libs.plugins.kotlin.plugin.jpa.get().pluginId
val springBootId = libs.plugins.spring.boot.get().pluginId
val springDependencyManagementId = libs.plugins.spring.dependency.management.get().pluginId
val ktlintId = libs.plugins.ktlint.get().pluginId

val ktlintVersion = libs.versions.pinterest.ktlint.get()

group = "me.bossm0n5t3r"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin(kotlinJvmId)
        plugin(kotlinSpringId)
        plugin(kotlinJpaId)
        plugin(springBootId)
        plugin(springDependencyManagementId)
        plugin(ktlintId)
    }

    repositories {
        mavenCentral()
    }

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion = JavaLanguageVersion.of(jdkVersion)
        }
    }

    group = rootProject.group
    version = rootProject.version

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    // Configure the ktlint extension in each subproject
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        version.set(ktlintVersion)
    }
}
