package me.bossm0n5t3r.dto

data class OrderStatus(
    val orderId: String,
    val status: String,
    val product: String,
    val quantity: Int,
    val totalAmount: Int,
    val estimatedDelivery: String,
    val timestamp: String,
    val requestId: Long,
)
