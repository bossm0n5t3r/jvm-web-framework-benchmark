package me.bossm0n5t3r.benchmark

data class BenchmarkScenario(
    val name: String,
    val run: () -> Unit,
)
