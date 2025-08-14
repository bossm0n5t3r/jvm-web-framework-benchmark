package me.bossm0n5t3r.dto

data class Metric(
    val totalUsers: Int,
    val activeUsers: Int,
    val revenue: Double,
    val conversionRate: String,
    val serverLoad: String,
    val responseTime: String,
    val timestamp: String,
    val requestId: Long,
)
