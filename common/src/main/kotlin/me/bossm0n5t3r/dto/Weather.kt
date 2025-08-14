package me.bossm0n5t3r.dto

data class Weather(
    val city: String,
    val temperature: Int,
    val condition: String,
    val humidity: Int,
    val windSpeed: Double,
    val timestamp: String,
    val requestId: Long,
)
