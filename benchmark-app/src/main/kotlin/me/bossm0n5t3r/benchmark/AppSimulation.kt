package me.bossm0n5t3r.benchmark

import io.gatling.javaapi.core.CoreDsl.constantUsersPerSec
import io.gatling.javaapi.core.CoreDsl.details
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
import me.bossm0n5t3r.benchmark.Constants.WARM_UP_DURATION_IN_SECONDS
import me.bossm0n5t3r.benchmark.Constants.WARM_UP_USERS_PER_SEC

/** 앱 시뮬레이션이 모두 같은 부하, 체크, assertion을 쓰도록 하는 공통 템플릿입니다. */
abstract class AppSimulation(
    name: String,
    baseUrl: String,
    private val path: String,
) : Simulation() {
    private val users = 10_000
    private val requestName = "[$name] External API with no database"

    private val httpProtocol =
        http.baseUrl(baseUrl).acceptHeader(APPLICATION_JSON).contentTypeHeader(APPLICATION_JSON)

    private fun request(name: String) =
        http(name)
            .get(path)
            .check(status().`is`(200))
            .check(responseTimeInMillis().lte(SIMULATION_RESPONSE_TIME_IN_MILLIS))

    // JIT, 클래스 로딩, 초기 연결 수립 비용이 본 측정에 섞이지 않도록 저부하로 먼저 요청을 보냅니다.
    // 요청 이름을 따로 둬서 assertion과 리포트의 본 측정 통계에서 분리합니다.
    private val warmUpScenario = scenario("$name Warm-up").exec(request("[$name] Warm-up"))

    private val scenario = scenario("$name Load Test").exec(request(requestName))

    init {
        val warmUpUsers =
            constantUsersPerSec(WARM_UP_USERS_PER_SEC)
                .during(Duration.ofSeconds(WARM_UP_DURATION_IN_SECONDS))
        val rampUsers = rampUsers(users).during(Duration.ofSeconds(10))

        // andThen은 워밍업 요청이 모두 끝난 뒤에 본 측정을 시작합니다.
        setUp(
                warmUpScenario.injectOpen(warmUpUsers).andThen(scenario.injectOpen(rampUsers)),
            )
            .protocols(httpProtocol)
            .maxDuration(Duration.ofMinutes(3))
            .assertions(
                details(requestName).successfulRequests().percent().gt(SIMULATION_SUCCESS_PERCENT),
                details(requestName)
                    .responseTime()
                    .percentile(95.0)
                    .lt(SIMULATION_P95_RESPONSE_TIME_IN_MILLIS),
            )
    }
}
