package me.bossm0n5t3r.dto

data class StockPrice(
    val symbol: String,
    val price: String,
    val change: String,
    val changePercent: String,
    val volume: Int,
    val timestamp: String,
    val requestId: Long,
)
