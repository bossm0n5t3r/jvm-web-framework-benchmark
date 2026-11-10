package me.bossm0n5t3r.benchmark

import me.bossm0n5t3r.benchmark.Constants.WEBFLUX_URL

class WebFluxWithoutCoroutinesSimulation :
    AppSimulation(
        name = "WebFlux without Coroutines",
        baseUrl = WEBFLUX_URL,
        path = "/webflux/external/no-db/no-coroutines",
    )
