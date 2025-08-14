plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "jvm-web-framework-benchmark"

include("common")
include("mvc-app")
include("webflux-app")
include("benchmark-app")
include("external-app")
