package me.bossm0n5t3r.benchmark

import io.gatling.javaapi.core.CoreDsl.atOnceUsers
import io.gatling.javaapi.core.CoreDsl.global
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.ScenarioBuilder
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import java.time.Duration
import java.util.UUID
import me.bossm0n5t3r.benchmark.Constants.APPLICATION_JSON
import me.bossm0n5t3r.benchmark.Constants.EXTERNAL_APP_URL

class ExternalAppSimulation : Simulation() {
    private val url = EXTERNAL_APP_URL
    private val users = 10_000
    private val cities = listOf("Seoul", "London", "New York", "Tokyo")
    private val stockSymbols = listOf("AAPL", "GOOGL", "TSLA", "MSFT")

    private val httpProtocol =
        http.baseUrl(url).acceptHeader(APPLICATION_JSON).contentTypeHeader(APPLICATION_JSON)

    // 가상 사용자마다 새 값을 뽑도록 무한 feeder를 씁니다.
    private val feeder = generateSequence {
        mapOf<String, Any>(
            "uuid" to UUID.randomUUID().toString(),
            "city" to cities.random(),
            "stockSymbol" to stockSymbols.random(),
        )
    }
        .iterator()

    private val scenario: ScenarioBuilder =
        scenario("External APP Load Test")
            .feed(feeder)
            .exec(
                http("Get User").get("/api/external/user/#{uuid}").check(status().`is`(200)),
                http("Get Weather")
                    .get("/api/external/weather?city=#{city}")
                    .check(status().`is`(200)),
                http("Get Stock Price")
                    .get("/api/external/stock/#{stockSymbol}")
                    .check(status().`is`(200)),
                http("Get Order Status")
                    .get("/api/external/order/#{uuid}")
                    .check(status().`is`(200)),
                http("Get Metrics").get("/api/external/metrics").check(status().`is`(200)),
            )

    init {
        setUp(
                scenario.injectOpen(atOnceUsers(users)).protocols(httpProtocol),
            )
            .maxDuration(Duration.ofMinutes(5))
            .assertions(
                global().successfulRequests().percent().gt(95.0), // 95% success rate
            )
    }
}
