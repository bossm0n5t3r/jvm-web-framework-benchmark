package me.bossm0n5t3r.benchmark

import me.bossm0n5t3r.benchmark.Constants.WEBFLUX_URL

class WebFluxSimulation :
    AppSimulation(
        name = "WebFlux",
        baseUrl = WEBFLUX_URL,
        path = "/webflux/external/no-db",
    )
