package me.bossm0n5t3r.dto

data class User(
    val id: String,
    val name: String,
    val email: String,
    val department: String,
    val salary: Int,
    val timestamp: String,
    val requestId: Long,
)
