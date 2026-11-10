package me.bossm0n5t3r.benchmark

import me.bossm0n5t3r.benchmark.Constants.KTOR_URL

class KtorSimulation :
    AppSimulation(
        name = "Ktor",
        baseUrl = KTOR_URL,
        path = "/ktor/external/no-db",
    )
