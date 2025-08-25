package me.bossm0n5t3r.config

import io.netty.channel.ChannelOption
import me.bossm0n5t3r.util.Constants.CONNECTION_REQUEST_TIMEOUT_MILLIS
import me.bossm0n5t3r.util.Constants.CONNECT_TIMEOUT_MILLIS
import me.bossm0n5t3r.util.Constants.EVICTION_INTERVAL_MILLIS
import me.bossm0n5t3r.util.Constants.MAX_CONNECTIONS
import me.bossm0n5t3r.util.Constants.RESPONSE_TIMEOUT_MILLIS
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration

/**
 * Configuration for WebClient used in reactive external API calls
 */
@Configuration
class WebClientConfig {
    @Bean
    fun webClient(): WebClient {
        // 대용량 트래픽 처리를 위한 Connection Pool 설정
        val connectionProvider =
            ConnectionProvider
                .builder("webclient-connection-pool")
                // 예상 최대 동시 요청 수에 맞춰 크게 설정 (RestClient의 setMaxConnTotal/PerRoute와 동일)
                .maxConnections(MAX_CONNECTIONS)
                // 커넥션 풀에서 커넥션을 얻기까지 대기할 최대 시간 (RestClient의 setConnectionRequestTimeout과 동일)
                .pendingAcquireTimeout(Duration.ofMillis(CONNECTION_REQUEST_TIMEOUT_MILLIS))
                .evictInBackground(Duration.ofMillis(EVICTION_INTERVAL_MILLIS)) // 백그라운드에서 유휴 커넥션 정리 주기
                .build()

        val httpClient =
            HttpClient
                .create(connectionProvider)
                // 연결 타임아웃: 2초 (RestClient의 setConnectTimeout과 동일)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS.toInt())
                .responseTimeout(Duration.ofMillis(RESPONSE_TIMEOUT_MILLIS)) // 응답 타임아웃: 10초

        return WebClient
            .builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }
}
