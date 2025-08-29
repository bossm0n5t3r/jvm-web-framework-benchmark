package me.bossm0n5t3r.benchmark

import io.gatling.javaapi.core.CoreDsl
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl
import me.bossm0n5t3r.benchmark.Constants.APPLICATION_JSON
import me.bossm0n5t3r.benchmark.Constants.WEBFLUX_URL
import java.time.Duration

class WebFluxSimulation : Simulation() {
    private val url = WEBFLUX_URL
    private val users = 10_000

    private val httpProtocol =
        HttpDsl.http
            .baseUrl(url)
            .acceptHeader(APPLICATION_JSON)
            .contentTypeHeader(APPLICATION_JSON)

    private val scenario =
        CoreDsl
            .scenario("WebFlux Load Test")
            .exec(
                HttpDsl
                    .http("[WebFlux] External API with no database")
                    .get("/webflux/external/no-db")
                    .check(HttpDsl.status().`is`(200)),
            )

    init {
        val rampUsers = CoreDsl.rampUsers(users).during(Duration.ofSeconds(10))

        setUp(
            scenario
                .injectOpen(rampUsers)
                .protocols(httpProtocol),
        ).maxDuration(Duration.ofMinutes(3))
            .assertions(
                CoreDsl
                    .global()
                    .successfulRequests()
                    .percent()
                    .gt(60.0),
            )
    }
}
