package me.bossm0n5t3r.benchmark

import io.gatling.javaapi.core.CoreDsl.atOnceUsers
import io.gatling.javaapi.core.CoreDsl.global
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.ScenarioBuilder
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import me.bossm0n5t3r.benchmark.Constants.APPLICATION_JSON
import me.bossm0n5t3r.benchmark.Constants.EXTERNAL_APP_URL
import java.time.Duration
import java.util.UUID

class ExternalAppSimulation : Simulation() {
    private val url = EXTERNAL_APP_URL
    private val users = 10_000
    private val cities = listOf("Seoul", "London", "New York", "Tokyo")
    private val stockSymbols = listOf("AAPL", "GOOGL", "TSLA", "MSFT")

    private val httpProtocol =
        http
            .baseUrl(url)
            .acceptHeader(APPLICATION_JSON)
            .contentTypeHeader(APPLICATION_JSON)

    private val scenario: ScenarioBuilder
        get() {
            val uuid = UUID.randomUUID().toString()
            return scenario("External APP Load Test")
                .exec(
                    http("Get User")
                        .get("/api/external/user/$uuid")
                        .check(status().`is`(200)),
                    http("Get Weather")
                        .get("/api/external/weather?city=${cities.random()}")
                        .check(status().`is`(200)),
                    http("Get Stock Price")
                        .get("/api/external/stock/${stockSymbols.random()}")
                        .check(status().`is`(200)),
                    http("Get Order Status")
                        .get("/api/external/order/$uuid")
                        .check(status().`is`(200)),
                    http("Get Metrics")
                        .get("/api/external/metrics")
                        .check(status().`is`(200)),
                )
        }

    init {
        setUp(
            scenario
                .injectOpen(atOnceUsers(users))
                .protocols(httpProtocol),
        ).maxDuration(Duration.ofMinutes(5))
            .assertions(
                global().successfulRequests().percent().gt(95.0), // 95% success rate
            )
    }
}
