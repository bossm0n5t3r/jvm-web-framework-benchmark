package me.bossm0n5t3r.controller

import me.bossm0n5t3r.dto.Metric
import me.bossm0n5t3r.dto.OrderStatus
import me.bossm0n5t3r.dto.StockPrice
import me.bossm0n5t3r.dto.User
import me.bossm0n5t3r.dto.Weather
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    ): ResponseEntity<User> {
        val names = listOf("Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Henry")
        val departments = listOf("Engineering", "Marketing", "Sales", "HR", "Finance", "Operations")

        return ResponseEntity.ok(
            User(
                id = id,
                name = names.random(),
                email = "${names.random().lowercase()}@company.com",
                department = departments.random(),
                salary = random.nextInt(50000, 150000),
                timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                requestId = requestCounter.incrementAndGet(),
            ),
        )
    }

    /**
     * Get weather data with random values
     */
    @GetMapping("/weather")
    fun getWeather(
        @RequestParam(defaultValue = "Seoul") city: String,
    ): Weather {
        val conditions = listOf("Sunny", "Cloudy", "Rainy", "Snowy", "Partly Cloudy", "Windy")

        return Weather(
            city = city,
            temperature = random.nextInt(-10, 35),
            condition = conditions.random(),
            humidity = random.nextInt(20, 90),
            windSpeed = random.nextDouble(0.0, 25.0),
            timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            requestId = requestCounter.incrementAndGet(),
        )
    }

    /**
     * Get stock price with fluctuating values
     */
    @GetMapping("/stock/{symbol}")
    fun getStockPrice(
        @PathVariable symbol: String,
    ): StockPrice {
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

        return StockPrice(
            symbol = symbol.uppercase(),
            price = String.format("%.2f", currentPrice),
            change = String.format("%.2f", basePrice * fluctuation),
            changePercent = String.format("%.2f%%", fluctuation * 100),
            volume = random.nextInt(1000000, 10000000),
            timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            requestId = requestCounter.incrementAndGet(),
        )
    }

    /**
     * Get order status with random processing states
     */
    @GetMapping("/order/{orderId}")
    fun getOrderStatus(
        @PathVariable orderId: String,
    ): OrderStatus {
        val statuses = listOf("PENDING", "PROCESSING", "SHIPPED", "DELIVERED", "CANCELLED")
        val products = listOf("Laptop", "Phone", "Tablet", "Headphones", "Watch", "Camera")

        return OrderStatus(
            orderId = orderId,
            status = statuses.random(),
            product = products.random(),
            quantity = random.nextInt(1, 5),
            totalAmount = random.nextInt(100, 2000),
            estimatedDelivery = LocalDateTime.now().plusDays(random.nextLong(1, 7)).format(DateTimeFormatter.ISO_LOCAL_DATE),
            timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            requestId = requestCounter.incrementAndGet(),
        )
    }

    /**
     * Get random metrics/analytics data
     */
    @GetMapping("/metrics")
    fun getMetrics(): Metric =
        Metric(
            totalUsers = random.nextInt(10000, 100000),
            activeUsers = random.nextInt(1000, 10000),
            revenue = random.nextDouble(10000.0, 500000.0),
            conversionRate = String.format("%.2f%%", random.nextDouble(1.0, 10.0)),
            serverLoad = String.format("%.1f%%", random.nextDouble(10.0, 90.0)),
            responseTime = "${random.nextInt(50, 500)}ms",
            timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            requestId = requestCounter.incrementAndGet(),
        )
}
