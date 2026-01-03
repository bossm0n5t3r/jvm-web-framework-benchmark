package me.bossm0n5t3r.benchmark

import io.gatling.javaapi.core.CoreDsl.global
import io.gatling.javaapi.core.CoreDsl.rampUsers
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl
import io.gatling.javaapi.http.HttpDsl.http
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
        scenario("WebFlux Load Test")
            .exec(
                http("[WebFlux] External API with no database")
                    .get("/webflux/external/no-db")
                    .check(HttpDsl.status().`is`(200)),
            )

    init {
        val rampUsers = rampUsers(users).during(Duration.ofSeconds(10))

        setUp(
            scenario
                .injectOpen(rampUsers)
                .protocols(httpProtocol),
        ).maxDuration(Duration.ofMinutes(3))
            .assertions(
                global().successfulRequests().percent().gt(60.0),
            )
    }
}
