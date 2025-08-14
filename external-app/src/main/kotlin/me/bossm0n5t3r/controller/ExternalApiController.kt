package me.bossm0n5t3r.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicLong
import kotlin.random.Random

/**
 * External API Controller that simulates external service calls
 * Each endpoint returns different responses to simulate real external APIs
 */
@RestController
@RequestMapping("/api/external")
class ExternalApiController {
    private val requestCounter = AtomicLong(0)
    private val random = Random.Default

    /**
     * Health check endpoint with timestamp
     */
    @GetMapping("/health")
    fun health(): Map<String, Any> =
        mapOf(
            "status" to "OK",
            "timestamp" to LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            "requestCount" to requestCounter.incrementAndGet(),
            "uptime" to "${random.nextInt(1, 1000)} seconds",
        )

    /**
     * Get random user data
     */
    @GetMapping("/user/{id}")
    fun getUser(
        @PathVariable id: String,
    ): ResponseEntity<Map<String, Any>> {
        val names = listOf("Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Henry")
        val departments = listOf("Engineering", "Marketing", "Sales", "HR", "Finance", "Operations")

        return ResponseEntity.ok(
            mapOf(
                "id" to id,
                "name" to names.random(),
                "email" to "${names.random().lowercase()}@company.com",
                "department" to departments.random(),
                "salary" to random.nextInt(50000, 150000),
                "timestamp" to LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                "requestId" to requestCounter.incrementAndGet(),
            ),
        )
    }

    /**
     * Get weather data with random values
     */
    @GetMapping("/weather")
    fun getWeather(
        @RequestParam(defaultValue = "Seoul") city: String,
    ): Map<String, Any> {
        val conditions = listOf("Sunny", "Cloudy", "Rainy", "Snowy", "Partly Cloudy", "Windy")

        return mapOf(
            "city" to city,
            "temperature" to random.nextInt(-10, 35),
            "condition" to conditions.random(),
            "humidity" to random.nextInt(20, 90),
            "windSpeed" to random.nextDouble(0.0, 25.0),
            "timestamp" to LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            "requestId" to requestCounter.incrementAndGet(),
        )
    }

    /**
     * Get stock price with fluctuating values
     */
    @GetMapping("/stock/{symbol}")
    fun getStockPrice(
        @PathVariable symbol: String,
    ): Map<String, Any> {
        val basePrice =
            when (symbol.uppercase()) {
                "AAPL" -> 150.0
                "GOOGL" -> 2500.0
                "TSLA" -> 800.0
                "MSFT" -> 300.0
                else -> 100.0
            }

        val fluctuation = random.nextDouble(-0.1, 0.1) // ±10%
        val currentPrice = basePrice * (1 + fluctuation)

        return mapOf(
            "symbol" to symbol.uppercase(),
            "price" to String.format("%.2f", currentPrice),
            "change" to String.format("%.2f", basePrice * fluctuation),
            "changePercent" to String.format("%.2f%%", fluctuation * 100),
            "volume" to random.nextInt(1000000, 10000000),
            "timestamp" to LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            "requestId" to requestCounter.incrementAndGet(),
        )
    }

    /**
     * Get order status with random processing states
     */
    @GetMapping("/order/{orderId}")
    fun getOrderStatus(
        @PathVariable orderId: String,
    ): Map<String, Any> {
        val statuses = listOf("PENDING", "PROCESSING", "SHIPPED", "DELIVERED", "CANCELLED")
        val products = listOf("Laptop", "Phone", "Tablet", "Headphones", "Watch", "Camera")

        return mapOf(
            "orderId" to orderId,
            "status" to statuses.random(),
            "product" to products.random(),
            "quantity" to random.nextInt(1, 5),
            "totalAmount" to random.nextInt(100, 2000),
            "estimatedDelivery" to LocalDateTime.now().plusDays(random.nextLong(1, 7)).format(DateTimeFormatter.ISO_LOCAL_DATE),
            "timestamp" to LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            "requestId" to requestCounter.incrementAndGet(),
        )
    }

    /**
     * Process payment with random success/failure
     */
    @PostMapping("/payment")
    fun processPayment(
        @RequestBody paymentRequest: Map<String, Any>,
    ): ResponseEntity<Map<String, Any>> {
        val isSuccess = random.nextBoolean() // 50% success rate
        val transactionId = "TXN${System.currentTimeMillis()}"

        val response =
            if (isSuccess) {
                mapOf(
                    "transactionId" to transactionId,
                    "status" to "SUCCESS",
                    "amount" to (paymentRequest["amount"] ?: 0),
                    "currency" to (paymentRequest["currency"] ?: "USD"),
                    "processingTime" to "${random.nextInt(100, 3000)}ms",
                    "timestamp" to LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    "requestId" to requestCounter.incrementAndGet(),
                )
            } else {
                mapOf(
                    "transactionId" to transactionId,
                    "status" to "FAILED",
                    "errorCode" to "ERR_${random.nextInt(1000, 9999)}",
                    "errorMessage" to listOf("Insufficient funds", "Card expired", "Invalid CVV", "Network timeout").random(),
                    "timestamp" to LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    "requestId" to requestCounter.incrementAndGet(),
                )
            }

        return ResponseEntity.ok(response)
    }

    /**
     * Get random metrics/analytics data
     */
    @GetMapping("/metrics")
    fun getMetrics(): Map<String, Any> =
        mapOf(
            "totalUsers" to random.nextInt(10000, 100000),
            "activeUsers" to random.nextInt(1000, 10000),
            "revenue" to random.nextDouble(10000.0, 500000.0),
            "conversionRate" to String.format("%.2f%%", random.nextDouble(1.0, 10.0)),
            "serverLoad" to String.format("%.1f%%", random.nextDouble(10.0, 90.0)),
            "responseTime" to "${random.nextInt(50, 500)}ms",
            "timestamp" to LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            "requestId" to requestCounter.incrementAndGet(),
        )
}
