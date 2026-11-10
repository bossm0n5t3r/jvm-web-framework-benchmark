package me.bossm0n5t3r.benchmark

import io.gatling.javaapi.core.CoreDsl.global
import io.gatling.javaapi.core.CoreDsl.rampUsers
import io.gatling.javaapi.core.CoreDsl.responseTimeInMillis
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import java.time.Duration
import me.bossm0n5t3r.benchmark.Constants.APPLICATION_JSON
import me.bossm0n5t3r.benchmark.Constants.SIMULATION_P95_RESPONSE_TIME_IN_MILLIS
import me.bossm0n5t3r.benchmark.Constants.SIMULATION_RESPONSE_TIME_IN_MILLIS
import me.bossm0n5t3r.benchmark.Constants.SIMULATION_SUCCESS_PERCENT

/** 앱 시뮬레이션이 모두 같은 부하, 체크, assertion을 쓰도록 하는 공통 템플릿입니다. */
abstract class AppSimulation(
    name: String,
    baseUrl: String,
    path: String,
) : Simulation() {
    private val users = 10_000

    private val httpProtocol =
        http.baseUrl(baseUrl).acceptHeader(APPLICATION_JSON).contentTypeHeader(APPLICATION_JSON)

    private val scenario =
        scenario("$name Load Test")
            .exec(
                http("[$name] External API with no database")
                    .get(path)
                    .check(status().`is`(200))
                    .check(responseTimeInMillis().lte(SIMULATION_RESPONSE_TIME_IN_MILLIS)),
            )

    init {
        val rampUsers = rampUsers(users).during(Duration.ofSeconds(10))

        setUp(
                scenario.injectOpen(rampUsers).protocols(httpProtocol),
            )
            .maxDuration(Duration.ofMinutes(3))
            .assertions(
                global().successfulRequests().percent().gt(SIMULATION_SUCCESS_PERCENT),
                global().responseTime().percentile(95.0).lt(SIMULATION_P95_RESPONSE_TIME_IN_MILLIS),
            )
    }
}
