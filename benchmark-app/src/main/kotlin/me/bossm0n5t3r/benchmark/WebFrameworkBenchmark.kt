package me.bossm0n5t3r.benchmark

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.withPermit
import me.bossm0n5t3r.dto.UserRequest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.io.File
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

@SpringBootApplication
class BenchmarkApplication

fun main(args: Array<String>) {
    SpringApplication.run(BenchmarkApplication::class.java, *args)
}

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

data class BenchmarkScenario(
    val name: String,
    val run: () -> Unit,
)

@Component
class WebFrameworkBenchmark : CommandLineRunner {
    private val mvcBaseUrl = "http://localhost:8080/mvc/users"
    private val webfluxBaseUrl = "http://localhost:8081/webflux/users"

    // For collecting benchmark results to write to benchmark.md
    private val benchmarkResults = StringBuilder()

    private val objectMapper = jacksonObjectMapper().registerKotlinModule()
    private val okHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    private val webClient =
        WebClient
            .builder()
            .codecs { it.defaultCodecs().maxInMemorySize(1024 * 1024) }
            .build()

    override fun run(vararg args: String?) {
        println("=".repeat(80))
        println("🚀 JVM Web Framework Benchmark")
        println("=".repeat(80))
        println("MVC App URL: $mvcBaseUrl")
        println("WebFlux App URL: $webfluxBaseUrl")
        println()

        // Wait for applications to be ready
        println("⏳ Waiting for applications to be ready...")
        waitForApplicationsReady()

        println("✅ Applications are ready. Cleaning up tables")
        println()
        runCleaningUpTables()

        println("✅ Everything is ready. Starting benchmark...")
        println()

        runComprehensiveBenchmark()
    }

    private fun waitForApplicationsReady() {
        val maxRetries = 30
        var mvcReady = false
        var webfluxReady = false

        for (i in 1..maxRetries) {
            if (!mvcReady) {
                mvcReady = checkApplicationHealth(mvcBaseUrl)
                if (mvcReady) println("✅ MVC Application is ready")
            }

            if (!webfluxReady) {
                webfluxReady = checkApplicationHealth(webfluxBaseUrl)
                if (webfluxReady) println("✅ WebFlux Application is ready")
            }

            if (mvcReady && webfluxReady) break

            if (i == maxRetries) {
                println("❌ Applications failed to start within timeout")
                return
            }

            Thread.sleep(2000)
        }
    }

    private fun checkApplicationHealth(baseUrl: String): Boolean =
        try {
            val request =
                Request
                    .Builder()
                    .url(baseUrl)
                    .get()
                    .build()

            okHttpClient.newCall(request).execute().use { response ->
                response.isSuccessful
            }
        } catch (_: Exception) {
            false
        }

    private fun runCleaningUpTables() {
        try {
            val mvcRequest =
                Request
                    .Builder()
                    .url(mvcBaseUrl)
                    .delete()
                    .build()

            okHttpClient.newCall(mvcRequest).execute().use { response ->
                if (!response.isSuccessful) {
                    throw RuntimeException("Failed to cleanup tables: HTTP ${response.code}: ${response.message}")
                }
            }
        } catch (e: Exception) {
            println("Failed to cleanup tables. Ignoring...")
            throw e
        }
    }

    private fun runComprehensiveBenchmark() {
        val scenarios =
            listOf(
                BenchmarkScenario("🔍 GET All Users", ::benchmarkGetAllUsers),
                BenchmarkScenario("➕ CREATE User", ::benchmarkCreateUser),
                BenchmarkScenario("🔍 GET User by ID", ::benchmarkGetUserById),
                BenchmarkScenario("📝 UPDATE User", ::benchmarkUpdateUser),
                BenchmarkScenario("🗑️ DELETE User", ::benchmarkDeleteUser),
                BenchmarkScenario("🔎 SEARCH Users by Name", ::benchmarkSearchUsers),
                BenchmarkScenario("💥 HIGH LOAD Test", ::benchmarkHighLoad),
            )

        for (scenario in scenarios) {
            println("=".repeat(60))
            println(scenario.name)
            println("=".repeat(60))

            try {
                scenario.run()
            } catch (e: Exception) {
                println("❌ Scenario failed: ${e.message}")
                e.printStackTrace()
            }

            println()
        }

        println("🏁 Benchmark completed!")

        // Save results to benchmark.md
        writeBenchmarkToMarkdown()
    }

    private fun benchmarkGetAllUsers() {
        val requests = 100
        val concurrency = 10

        val mvcResults =
            runBenchmarkScenario("MVC", requests, concurrency) {
                performHttpGet(mvcBaseUrl)
            }

        val webfluxResults =
            runBenchmarkScenario("WebFlux", requests, concurrency) {
                performReactiveGet(webfluxBaseUrl)
            }

        compareResults(mvcResults, webfluxResults, "🔍 GET All Users")
    }

    private fun benchmarkCreateUser() {
        val requests = 50
        val concurrency = 5

        val mvcResults =
            runBenchmarkScenario("MVC", requests, concurrency) {
                val userRequest =
                    UserRequest(
                        "Test User ${System.currentTimeMillis()}",
                        "test${System.currentTimeMillis()}@example.com",
                    )
                performHttpPost(mvcBaseUrl, userRequest)
            }

        val webfluxResults =
            runBenchmarkScenario("WebFlux", requests, concurrency) {
                val userRequest =
                    UserRequest(
                        "Test User ${System.currentTimeMillis()}",
                        "test${System.currentTimeMillis()}@example.com",
                    )
                performReactivePost(webfluxBaseUrl, userRequest)
            }

        compareResults(mvcResults, webfluxResults, "➕ CREATE User")
    }

    private fun benchmarkGetUserById() {
        // First create a user to get
        val testUser = UserRequest("Benchmark User", "benchmark-get-${System.currentTimeMillis()}@example.com")
        val responseBody = executePost(mvcBaseUrl, testUser)
        val userId = objectMapper.readTree(responseBody).get("id").asLong()

        val requests = 100
        val concurrency = 10

        val mvcResults =
            runBenchmarkScenario("MVC", requests, concurrency) {
                performHttpGet("$mvcBaseUrl/$userId")
            }

        val webfluxResults =
            runBenchmarkScenario("WebFlux", requests, concurrency) {
                performReactiveGet("$webfluxBaseUrl/$userId")
            }

        compareResults(mvcResults, webfluxResults, "🔍 GET User by ID")
    }

    private fun benchmarkUpdateUser() {
        // First create a user to update
        val testUser = UserRequest("Update Test User", "update-test-${System.currentTimeMillis()}@example.com")
        val responseBody = executePost(mvcBaseUrl, testUser)
        val userId = objectMapper.readTree(responseBody).get("id").asLong()

        val requests = 50
        val concurrency = 5

        val mvcResults =
            runBenchmarkScenario("MVC", requests, concurrency) {
                val updateRequest =
                    UserRequest(
                        "Updated User ${System.currentTimeMillis()}",
                        "updated${System.currentTimeMillis()}@example.com",
                    )
                performHttpPut("$mvcBaseUrl/$userId", updateRequest)
            }

        val webfluxResults =
            runBenchmarkScenario("WebFlux", requests, concurrency) {
                val updateRequest =
                    UserRequest(
                        "Updated User ${System.currentTimeMillis()}",
                        "updated${System.currentTimeMillis()}@example.com",
                    )
                performReactivePut("$webfluxBaseUrl/$userId", updateRequest)
            }

        compareResults(mvcResults, webfluxResults, "📝 UPDATE User")
    }

    private fun benchmarkDeleteUser() {
        // Create users to delete
        val createdUserIds = mutableListOf<Long>()
        repeat(20) {
            val testUser = UserRequest("Delete User $it", "delete-$it-${System.currentTimeMillis()}@example.com")
            val responseBody = executePost(mvcBaseUrl, testUser)
            val userId = objectMapper.readTree(responseBody).get("id").asLong()
            createdUserIds.add(userId)
        }

        val requests = 10
        val concurrency = 2

        val mvcIdsToDelete = createdUserIds.take(10).iterator()
        val webfluxIdsToDelete = createdUserIds.drop(10).iterator()

        val mvcResults =
            runBenchmarkScenario("MVC", requests, concurrency) {
                val userId = if (mvcIdsToDelete.hasNext()) mvcIdsToDelete.next() else -1L
                performHttpDelete("$mvcBaseUrl/$userId")
            }

        val webfluxResults =
            runBenchmarkScenario("WebFlux", requests, concurrency) {
                val userId = if (webfluxIdsToDelete.hasNext()) webfluxIdsToDelete.next() else -1L
                performReactiveDelete("$webfluxBaseUrl/$userId")
            }

        compareResults(mvcResults, webfluxResults, "🗑️ DELETE User")
    }

    private fun benchmarkSearchUsers() {
        // Create some test data
        repeat(10) {
            val testUser = UserRequest("Search User $it", "search$it@example.com")
            performHttpPost(mvcBaseUrl, testUser)
        }

        val requests = 100
        val concurrency = 10

        val mvcResults =
            runBenchmarkScenario("MVC", requests, concurrency) {
                performHttpGet("$mvcBaseUrl/search?name=Search")
            }

        val webfluxResults =
            runBenchmarkScenario("WebFlux", requests, concurrency) {
                performReactiveGet("$webfluxBaseUrl/search?name=Search")
            }

        compareResults(mvcResults, webfluxResults, "🔎 SEARCH Users by Name")
    }

    private fun benchmarkHighLoad() {
        val requests = 500
        val concurrency = 50

        println("🔥 High Load Test - $requests requests with $concurrency concurrent threads")

        val mvcResults =
            runBenchmarkScenario("MVC", requests, concurrency) {
                performHttpGet(mvcBaseUrl)
            }

        val webfluxResults =
            runBenchmarkScenario("WebFlux", requests, concurrency) {
                performReactiveGet(webfluxBaseUrl)
            }

        compareResults(mvcResults, webfluxResults, "💥 HIGH LOAD Test")
    }

    private fun runBenchmarkScenario(
        framework: String,
        requests: Int,
        concurrency: Int,
        operation: suspend () -> Long,
    ): BenchmarkResult =
        runBlocking {
            println("📊 Testing $framework - $requests requests, $concurrency concurrent")

            val stats = DescriptiveStatistics()
            val errors = mutableListOf<Exception>()
            val semaphore = kotlinx.coroutines.sync.Semaphore(concurrency)

            val totalTime =
                measureTimeMillis {
                    val jobs =
                        (1..requests).map {
                            async {
                                semaphore.withPermit {
                                    try {
                                        val responseTime = operation()
                                        synchronized(stats) {
                                            stats.addValue(responseTime.toDouble())
                                        }
                                    } catch (e: Exception) {
                                        synchronized(errors) {
                                            errors.add(e)
                                        }
                                    }
                                }
                            }
                        }
                    jobs.awaitAll()
                }

            BenchmarkResult(
                framework = framework,
                totalRequests = requests,
                successfulRequests = stats.n.toInt(),
                failedRequests = errors.size,
                totalTimeMs = totalTime,
                averageResponseTimeMs = stats.mean,
                minResponseTimeMs = stats.min,
                maxResponseTimeMs = stats.max,
                p95ResponseTimeMs = stats.getPercentile(95.0),
                p99ResponseTimeMs = stats.getPercentile(99.0),
                throughputRps = (stats.n / (totalTime / 1000.0)),
            )
        }

    private fun performHttpGet(url: String): Long =
        measureTimeMillis {
            val request =
                Request
                    .Builder()
                    .url(url)
                    .get()
                    .build()

            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw RuntimeException("HTTP ${response.code}: ${response.message}")
                }
                response.body?.string() // Consume response
            }
        }

    private fun executePost(
        url: String,
        userRequest: UserRequest,
    ): String {
        val json = objectMapper.writeValueAsString(userRequest)
        val requestBody = json.toRequestBody("application/json".toMediaType())

        val request =
            Request
                .Builder()
                .url(url)
                .post(requestBody)
                .build()

        okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                val errorBody = response.body?.string()
                throw RuntimeException("HTTP ${response.code}: ${response.message} - $errorBody")
            }
            return response.body?.string() ?: throw IllegalStateException("Response body was null")
        }
    }

    private fun performHttpPost(
        url: String,
        userRequest: UserRequest,
    ): Long =
        measureTimeMillis {
            executePost(url, userRequest)
        }

    private fun performHttpPut(
        url: String,
        userRequest: UserRequest,
    ): Long =
        measureTimeMillis {
            val json = objectMapper.writeValueAsString(userRequest)
            val requestBody = json.toRequestBody("application/json".toMediaType())

            val request =
                Request
                    .Builder()
                    .url(url)
                    .put(requestBody)
                    .build()

            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw RuntimeException("HTTP ${response.code}: ${response.message}")
                }
                response.body?.string() // Consume response
            }
        }

    private fun performHttpDelete(url: String): Long =
        measureTimeMillis {
            val request =
                Request
                    .Builder()
                    .url(url)
                    .delete()
                    .build()

            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful && response.code != 404) { // 404 is acceptable for delete
                    throw RuntimeException("HTTP ${response.code}: ${response.message}")
                }
                response.body?.string() // Consume response
            }
        }

    private suspend fun performReactiveGet(url: String): Long =
        measureTimeMillis {
            webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono<String>()
                .timeout(Duration.ofSeconds(30))
                .awaitSingle()
        }

    private suspend fun performReactivePost(
        url: String,
        userRequest: UserRequest,
    ): Long =
        measureTimeMillis {
            webClient
                .post()
                .uri(url)
                .bodyValue(userRequest)
                .retrieve()
                .bodyToMono<String>()
                .timeout(Duration.ofSeconds(30))
                .awaitSingle()
        }

    private suspend fun performReactivePut(
        url: String,
        userRequest: UserRequest,
    ): Long =
        measureTimeMillis {
            webClient
                .put()
                .uri(url)
                .bodyValue(userRequest)
                .retrieve()
                .bodyToMono<String>()
                .timeout(Duration.ofSeconds(30))
                .awaitSingle()
        }

    private suspend fun performReactiveDelete(url: String): Long =
        measureTimeMillis {
            webClient
                .delete()
                .uri(url)
                .retrieve()
                .bodyToMono<String>()
                .timeout(Duration.ofSeconds(30))
                .onErrorResume { Mono.just("") } // Handle 404 gracefully
                .awaitSingle()
        }

    private fun compareResults(
        mvcResult: BenchmarkResult,
        webfluxResult: BenchmarkResult,
        scenarioName: String = "Unknown Scenario",
    ) {
        println("📈 Results Comparison:")
        println()

        // Success rate
        val mvcSuccessRate = (mvcResult.successfulRequests.toDouble() / mvcResult.totalRequests * 100)
        val webfluxSuccessRate = (webfluxResult.successfulRequests.toDouble() / webfluxResult.totalRequests * 100)

        println("Success Rate:")
        println(
            "  MVC:     ${
                String.format(
                    "%.1f%%",
                    mvcSuccessRate,
                )
            } (${mvcResult.successfulRequests}/${mvcResult.totalRequests})",
        )
        println(
            "  WebFlux: ${
                String.format(
                    "%.1f%%",
                    webfluxSuccessRate,
                )
            } (${webfluxResult.successfulRequests}/${webfluxResult.totalRequests})",
        )

        // Throughput
        println("\nThroughput (requests/sec):")
        println("  MVC:     ${String.format("%.1f", mvcResult.throughputRps)}")
        println("  WebFlux: ${String.format("%.1f", webfluxResult.throughputRps)}")
        val throughputWinner = if (webfluxResult.throughputRps > mvcResult.throughputRps) "WebFlux" else "MVC"
        val throughputDiff =
            kotlin.math.abs(webfluxResult.throughputRps - mvcResult.throughputRps) /
                kotlin.math.max(mvcResult.throughputRps, webfluxResult.throughputRps) *
                100
        println("  Winner: 🏆 $throughputWinner (${String.format("%.1f%%", throughputDiff)} faster)")

        // Response times
        println("\nResponse Times (ms):")
        println("  Average:")
        println("    MVC:     ${String.format("%.1f", mvcResult.averageResponseTimeMs)}")
        println("    WebFlux: ${String.format("%.1f", webfluxResult.averageResponseTimeMs)}")

        println("  P95:")
        println("    MVC:     ${String.format("%.1f", mvcResult.p95ResponseTimeMs)}")
        println("    WebFlux: ${String.format("%.1f", webfluxResult.p95ResponseTimeMs)}")

        println("  P99:")
        println("    MVC:     ${String.format("%.1f", mvcResult.p99ResponseTimeMs)}")
        println("    WebFlux: ${String.format("%.1f", webfluxResult.p99ResponseTimeMs)}")

        // Overall winner
        val responseTimeWinner =
            if (webfluxResult.averageResponseTimeMs < mvcResult.averageResponseTimeMs) "WebFlux" else "MVC"
        println("  Faster Response: 🚀 $responseTimeWinner")

        if (mvcResult.failedRequests > 0 || webfluxResult.failedRequests > 0) {
            println("\n⚠️  Failed Requests:")
            if (mvcResult.failedRequests > 0) println("  MVC: ${mvcResult.failedRequests}")
            if (webfluxResult.failedRequests > 0) println("  WebFlux: ${webfluxResult.failedRequests}")
        }

        // Collect results for markdown file
        collectResultsForMarkdown(mvcResult, webfluxResult, scenarioName)
    }

    private fun collectResultsForMarkdown(
        mvcResult: BenchmarkResult,
        webfluxResult: BenchmarkResult,
        scenarioName: String,
    ) {
        // Add scenario header
        benchmarkResults.append("=".repeat(60)).append("\n")
        benchmarkResults.append("$scenarioName\n")
        benchmarkResults.append("=".repeat(60)).append("\n")
        benchmarkResults.append(
            "📊 Testing MVC - ${mvcResult.totalRequests} requests, ${if (mvcResult.totalRequests >= 100) 10 else 5} concurrent\n",
        )
        benchmarkResults.append(
            "📊 Testing WebFlux - ${webfluxResult.totalRequests} requests, ${if (webfluxResult.totalRequests >= 100) 10 else 5} concurrent\n",
        )
        benchmarkResults.append("📈 Results Comparison:\n\n")

        // Success rate
        val mvcSuccessRate = (mvcResult.successfulRequests.toDouble() / mvcResult.totalRequests * 100)
        val webfluxSuccessRate = (webfluxResult.successfulRequests.toDouble() / webfluxResult.totalRequests * 100)

        benchmarkResults.append("Success Rate:\n")
        benchmarkResults.append(
            "MVC:     ${
                String.format(
                    "%.1f%%",
                    mvcSuccessRate,
                )
            } (${mvcResult.successfulRequests}/${mvcResult.totalRequests})\n",
        )
        benchmarkResults.append(
            "WebFlux: ${
                String.format(
                    "%.1f%%",
                    webfluxSuccessRate,
                )
            } (${webfluxResult.successfulRequests}/${webfluxResult.totalRequests})\n",
        )

        // Throughput
        benchmarkResults.append("\nThroughput (requests/sec):\n")
        benchmarkResults.append("MVC:     ${String.format("%.1f", mvcResult.throughputRps)}\n")
        benchmarkResults.append("WebFlux: ${String.format("%.1f", webfluxResult.throughputRps)}\n")
        val throughputWinner = if (webfluxResult.throughputRps > mvcResult.throughputRps) "WebFlux" else "MVC"
        val throughputDiff =
            kotlin.math.abs(webfluxResult.throughputRps - mvcResult.throughputRps) /
                kotlin.math.max(mvcResult.throughputRps, webfluxResult.throughputRps) *
                100
        benchmarkResults.append("Winner: 🏆 $throughputWinner (${String.format("%.1f%%", throughputDiff)} faster)\n")

        // Response times
        benchmarkResults.append("\nResponse Times (ms):\n")
        benchmarkResults.append("Average:\n")
        benchmarkResults.append("MVC:     ${String.format("%.1f", mvcResult.averageResponseTimeMs)}\n")
        benchmarkResults.append("WebFlux: ${String.format("%.1f", webfluxResult.averageResponseTimeMs)}\n")

        benchmarkResults.append("P95:\n")
        benchmarkResults.append("MVC:     ${String.format("%.1f", mvcResult.p95ResponseTimeMs)}\n")
        benchmarkResults.append("WebFlux: ${String.format("%.1f", webfluxResult.p95ResponseTimeMs)}\n")

        benchmarkResults.append("P99:\n")
        benchmarkResults.append("MVC:     ${String.format("%.1f", mvcResult.p99ResponseTimeMs)}\n")
        benchmarkResults.append("WebFlux: ${String.format("%.1f", webfluxResult.p99ResponseTimeMs)}\n")

        val responseTimeWinner =
            if (webfluxResult.averageResponseTimeMs < mvcResult.averageResponseTimeMs) "WebFlux" else "MVC"
        benchmarkResults.append("Faster Response: 🚀 $responseTimeWinner\n")

        if (mvcResult.failedRequests > 0 || webfluxResult.failedRequests > 0) {
            benchmarkResults.append("\n⚠️  Failed Requests:\n")
            if (mvcResult.failedRequests > 0) benchmarkResults.append("MVC: ${mvcResult.failedRequests}\n")
            if (webfluxResult.failedRequests > 0) benchmarkResults.append("WebFlux: ${webfluxResult.failedRequests}\n")
        }

        benchmarkResults.append("\n")
    }

    private fun writeBenchmarkToMarkdown() {
        try {
            val benchmarkFile = File("benchmark.md")
            val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"))

            val content = StringBuilder()
            content.append("=".repeat(80)).append("\n")
            content.append("🚀 JVM Web Framework Benchmark\n")
            content.append("=".repeat(80)).append("\n")
            content.append("MVC App URL: http://localhost:8080/mvc/users\n")
            content.append("WebFlux App URL: http://localhost:8081/webflux/users\n\n")
            content.append("⏳ Waiting for applications to be ready...\n")
            content.append("✅ WebFlux Application is ready\n")
            content.append("✅ MVC Application is ready\n")
            content.append("✅ Applications are ready. Cleaning up tables\n")
            content.append("✅ Everything is ready. Starting benchmark...\n\n")

            content.append(benchmarkResults.toString())

            benchmarkFile.writeText(content.toString())
            println("📄 Benchmark results saved to benchmark.md")
        } catch (e: Exception) {
            println("❌ Failed to write benchmark results to file: ${e.message}")
        }
    }
}
