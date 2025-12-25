plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "jvm-web-framework-benchmark"

include("benchmark-app")
include("common")
include("external-app")
include("ktor-app")
include("mvc-app")
include("mvc-without-virtual-thread-app")
include("webflux-app")
