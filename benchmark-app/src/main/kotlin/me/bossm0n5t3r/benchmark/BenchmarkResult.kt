package me.bossm0n5t3r.benchmark

data class BenchmarkResult(
    val framework: String,
    val totalRequests: Int,
    val successfulRequests: Int,
    val failedRequests: Int,
    val totalTimeMs: Long,
    val averageResponseTimeMs: Double,
    val minResponseTimeMs: Double,
    val maxResponseTimeMs: Double,
    val p95ResponseTimeMs: Double,
    val p99ResponseTimeMs: Double,
    val throughputRps: Double,
)
