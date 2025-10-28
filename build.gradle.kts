import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.ktlint) apply false
}

val jdkVersion = libs.versions.jdk.version.get()
val kotlinJvmId = libs.plugins.kotlin.jvm.get().pluginId
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

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    // Configure the ktlint extension in each subproject
    configure<KtlintExtension> {
        version.set(ktlintVersion)
    }
}
