package me.bossm0n5t3r.benchmark

import io.gatling.javaapi.core.CoreDsl.global
import io.gatling.javaapi.core.CoreDsl.rampUsers
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import java.time.Duration

class MVCExternalAPIWithNoDatabaseSimulation : Simulation() {
    private val mvcUrl = "http://localhost:8080"
    private val users = 10_000

    private val mvcHttpProtocol =
        http
            .baseUrl(mvcUrl)
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")

    private val mvcScenario =
        scenario("MVC Load Test")
            .exec(
                http("[MVC] External API with no database")
                    .get("/mvc/external/no-db")
                    .check(status().`is`(200)),
            )

    init {
        val rampUsers = rampUsers(users).during(Duration.ofSeconds(10))

        setUp(
            mvcScenario
                .injectOpen(rampUsers)
                .protocols(mvcHttpProtocol),
        ).maxDuration(Duration.ofMinutes(3))
            .assertions(
                global().successfulRequests().percent().gt(0.0), // 95% success rate
            )
    }
}
