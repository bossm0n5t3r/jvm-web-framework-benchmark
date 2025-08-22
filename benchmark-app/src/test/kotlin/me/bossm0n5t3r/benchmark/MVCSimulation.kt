package me.bossm0n5t3r.benchmark

import io.gatling.javaapi.core.CoreDsl.StringBody
import io.gatling.javaapi.core.CoreDsl.atOnceUsers
import io.gatling.javaapi.core.CoreDsl.exec
import io.gatling.javaapi.core.CoreDsl.global
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import java.time.Duration

class MVCSimulation : Simulation() {
    private val baseUrl = "http://localhost:8080"

    // HTTP Protocol configuration
    private val httpProtocol =
        http
            .baseUrl(baseUrl)
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")
            .userAgentHeader("Gatling/MvcSimulation")

    // Scenario for MVC endpoints
    private val mvcScenario =
        scenario("MVC Load Test")
            .repeat(10, "loopCount")
            .on(
                exec(
                    http("GET All Users")
                        .get("/mvc/users")
                        .check(status().`is`(200)),
                ).pause(Duration.ofMillis(10)) // Small pause between requests
                    .exec(
                        http("Create User")
                            .post("/mvc/users")
                            .body(StringBody("""{"name":"TestUser","email":"test@example.com"}"""))
                            .check(status().`is`(201)),
                    ).pause(Duration.ofMillis(10))
                    .exec(
                        http("GET User by ID")
                            .get("/mvc/users/1")
                            .check(status().`in`(200, 404)), // 404 is acceptable if user doesn't exist
                    ).pause(Duration.ofMillis(10))
                    .exec(
                        http("Update User")
                            .put("/mvc/users/1")
                            .body(StringBody("""{"name":"UpdatedUser","email":"updated@example.com"}"""))
                            .check(status().`in`(200, 404)),
                    ).pause(Duration.ofMillis(10))
                    .exec(
                        http("Search Users")
                            .get("/mvc/users?name=Test")
                            .check(status().`is`(200)),
                    ),
            )

    init {
        // Set up the simulation with 10,000 users making 10 calls each within 1 second
        setUp(
            mvcScenario
                .injectOpen(
                    atOnceUsers(10000), // Inject 10,000 users at once
                ).protocols(httpProtocol),
        ).maxDuration(Duration.ofSeconds(30)) // Maximum test duration
            .assertions(
                global().responseTime().percentile3().lt(5000), // 95th percentile < 5 seconds
                global().successfulRequests().percent().gt(95.0), // 95% success rate
            )
    }
}
