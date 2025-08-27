package me.bossm0n5t3r.config

import me.bossm0n5t3r.util.Constants.CONNECTION_REQUEST_TIMEOUT_MILLIS
import me.bossm0n5t3r.util.Constants.CONNECT_TIMEOUT_MILLIS
import me.bossm0n5t3r.util.Constants.MAX_CONNECTIONS
import me.bossm0n5t3r.util.Constants.MAX_CONNECTIONS_PER_ROUTE
import me.bossm0n5t3r.util.Constants.RESPONSE_TIMEOUT_MILLIS
import org.apache.hc.client5.http.config.ConnectionConfig
import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.core5.util.Timeout
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {
    /**
     * Configure RestClient bean
     */
    @Bean
    fun restClient(): RestClient {
        // 1. 연결 타임아웃: 외부 API 서버와 연결을 맺는 데 걸리는 시간
        val connectionConfig =
            ConnectionConfig
                .custom()
                .setConnectTimeout(Timeout.ofMilliseconds(CONNECT_TIMEOUT_MILLIS)) // 2초로 단축. 네트워크 상태가 좋다면 더 짧게 설정 가능
                .build()

        // 2. 커넥션 풀 설정
        val connectionManager: PoolingHttpClientConnectionManager =
            PoolingHttpClientConnectionManagerBuilder
                .create()
                .setDefaultConnectionConfig(connectionConfig)
                .setMaxConnTotal(MAX_CONNECTIONS) // 동시성 계산 결과(10,000 RPS * 5s)를 반영
                .setMaxConnPerRoute(MAX_CONNECTIONS_PER_ROUTE) // 대부분의 요청이 단일 또는 소수의 호스트로 향하는 경우 Total과 동일하게 설정
                .build()

        // 3. 요청 레벨 타임아웃 설정
        val requestConfig =
            RequestConfig
                .custom()
                // 커넥션 풀에서 커넥션을 가져오는 대기 시간. 풀이 고갈되었을 때 무한정 기다리지 않도록 짧게 설정 (Fail-Fast)
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(CONNECTION_REQUEST_TIMEOUT_MILLIS))
                // 소켓 타임아웃: 데이터 패킷 사이의 최대 대기 시간. API 최대 응답 시간(5초)보다 여유 있게 설정
                .setResponseTimeout(Timeout.ofMilliseconds(RESPONSE_TIMEOUT_MILLIS))
                .build()

        val httpClient =
            HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                // 4. Stale 커넥션 방지: evictIdleConnections()는 deprecated 되었으므로 connectionManager 자체에서 관리
                .setDefaultRequestConfig(requestConfig)
                .build()

        val factory = HttpComponentsClientHttpRequestFactory(httpClient)

        return RestClient
            .builder()
            .requestFactory(factory)
            .build()
    }
}
