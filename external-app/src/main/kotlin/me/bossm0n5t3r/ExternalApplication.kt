package me.bossm0n5t3r

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Spring Boot Application for External API Module
 * This module provides simple API endpoints without database connections
 * for external service simulation
 */
@SpringBootApplication
class ExternalApplication

fun main(args: Array<String>) {
    runApplication<ExternalApplication>(*args)
}
