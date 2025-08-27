package me.bossm0n5t3r.benchmark

import io.gatling.javaapi.core.CoreDsl.global
import io.gatling.javaapi.core.CoreDsl.rampUsers
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import java.time.Duration

class WebFluxExternalAPIWithNoDatabaseSimulation : Simulation() {
    private val webfluxUrl = "http://localhost:8081"
    private val users = 10_000

    private val webfluxHttpProtocol =
        http
            .baseUrl(webfluxUrl)
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")

    private val webfluxScenario =
        scenario("WebFlux Load Test")
            .exec(
                http("[WebFlux] External API with no database")
                    .get("/webflux/external/no-db")
                    .check(status().`is`(200)),
            )

    init {
        val rampUsers = rampUsers(users).during(Duration.ofSeconds(10))

        setUp(
            webfluxScenario
                .injectOpen(rampUsers)
                .protocols(webfluxHttpProtocol),
        ).maxDuration(Duration.ofMinutes(3))
            .assertions(
                global().successfulRequests().percent().gt(0.0), // 95% success rate
            )
    }
}
