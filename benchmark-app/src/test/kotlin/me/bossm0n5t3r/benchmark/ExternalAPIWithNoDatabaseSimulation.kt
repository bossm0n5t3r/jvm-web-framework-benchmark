package me.bossm0n5t3r.benchmark

import io.gatling.javaapi.core.CoreDsl.atOnceUsers
import io.gatling.javaapi.core.CoreDsl.global
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import java.time.Duration

class ExternalAPIWithNoDatabaseSimulation : Simulation() {
    private val mvcUrl = "http://localhost:8080"
    private val webfluxUrl = "http://localhost:8081"
    private val users = 1_000

    private val mvcHttpProtocol =
        http
            .baseUrl(mvcUrl)
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")

    private val webfluxHttpProtocol =
        http
            .baseUrl(webfluxUrl)
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")

    private val mvcScenario =
        scenario("MVC Load Test")
            .exec(
                http("[MVC] External API with no database")
                    .get("/mvc/external/no-db")
                    .check(status().`is`(200)),
            )

    private val webfluxScenario =
        scenario("WebFlux Load Test")
            .exec(
                http("[WebFlux] External API with no database")
                    .get("/webflux/external/no-db")
                    .check(status().`is`(200)),
            )

    init {
        setUp(
            mvcScenario
                .injectOpen(atOnceUsers(users))
                .protocols(mvcHttpProtocol),
            webfluxScenario
                .injectOpen(atOnceUsers(users))
                .protocols(webfluxHttpProtocol),
        ).maxDuration(Duration.ofMinutes(10))
            .assertions(
                global().successfulRequests().percent().gt(95.0), // 95% success rate
            )
    }
}
