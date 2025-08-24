package me.bossm0n5t3r.config

import org.apache.hc.client5.http.config.ConnectionConfig
import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.classic.HttpClients
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
        val connectionConfig = ConnectionConfig.custom().setConnectTimeout(Timeout.ofSeconds(5)).build()

        val connectionManager =
            PoolingHttpClientConnectionManagerBuilder
                .create()
                .setDefaultConnectionConfig(connectionConfig)
                .setMaxConnTotal(10000) // 예상 최대 동시 요청 수에 맞춰 크게 설정
                .build()

        // 타임아웃 설정
        val requestConfig =
            RequestConfig
                .custom()
                .setConnectionRequestTimeout(Timeout.ofSeconds(5)) // 커넥션 풀에서 커넥션을 얻기까지 대기할 최대 시간
                .setResponseTimeout(Timeout.ofSeconds(10)) // 응답 타임아웃: 10초
                .build()

        val httpClient =
            HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .evictIdleConnections(Timeout.ofMinutes(1)) // 1분 이상 유휴 상태인 커넥션을 주기적으로 제거
                .build()

        val factory = HttpComponentsClientHttpRequestFactory(httpClient)

        return RestClient
            .builder()
            .requestFactory(factory)
            .build()
    }
}
