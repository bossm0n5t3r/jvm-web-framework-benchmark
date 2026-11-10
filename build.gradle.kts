plugins { base }

val sqlFiles = files("init.sql")

// GUI-launched IDEs often lack Homebrew paths in PATH, so resolve the absolute path up front
val sqlfluffSearchDirs =
    providers.environmentVariable("PATH").orNull.orEmpty().split(File.pathSeparator) +
        listOf("/opt/homebrew/bin", "/usr/local/bin")

val sqlfluff: String =
    providers.gradleProperty("sqlfluff.path").orNull
        ?: sqlfluffSearchDirs
            .filter { it.isNotBlank() }
            .map { File(it, "sqlfluff") }
            .firstOrNull { it.canExecute() }
            ?.absolutePath
        ?: "sqlfluff"

val sqlfluffLint =
    tasks.register<Exec>("sqlfluffLint") {
        group = "verification"
        description = "Lint SQL files with sqlfluff"
        inputs.files(sqlFiles, ".sqlfluff")
        commandLine(sqlfluff, "lint", *sqlFiles.map { it.path }.toTypedArray())
    }

tasks.register<Exec>("sqlfluffFormat") {
    group = "formatting"
    description = "Format SQL files with sqlfluff"
    commandLine(sqlfluff, "format", *sqlFiles.map { it.path }.toTypedArray())
}

tasks.register<Exec>("sqlfluffFix") {
    group = "formatting"
    description = "Fix SQL files with sqlfluff"
    commandLine(sqlfluff, "fix", *sqlFiles.map { it.path }.toTypedArray())
}

tasks.named("check") { dependsOn(sqlfluffLint) }
