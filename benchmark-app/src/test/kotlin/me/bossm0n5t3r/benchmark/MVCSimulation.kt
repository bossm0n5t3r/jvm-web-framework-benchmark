package me.bossm0n5t3r.benchmark

import io.gatling.javaapi.core.CoreDsl.global
import io.gatling.javaapi.core.CoreDsl.rampUsers
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import org.springframework.http.MediaType
import java.time.Duration

class MVCSimulation : Simulation() {
    private val url = "http://localhost:8080"
    private val users = 10_000

    private val httpProtocol =
        http
            .baseUrl(url)
            .acceptHeader(MediaType.APPLICATION_JSON_VALUE)
            .contentTypeHeader(MediaType.APPLICATION_JSON_VALUE)

    private val scenario =
        scenario("MVC Load Test")
            .exec(
                http("[MVC] External API with no database")
                    .get("/mvc/external/no-db")
                    .check(status().`is`(200)),
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
