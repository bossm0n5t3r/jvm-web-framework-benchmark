package me.bossm0n5t3r.util

object Constants {
    const val CONNECT_TIMEOUT_MILLIS = 5_000L
    const val MAX_CONNECTIONS = 50_000 // 동시성 계산 결과(10,000 RPS * 5s)를 반영
    const val MAX_CONNECTIONS_PER_ROUTE = 50_000 // 대부분의 요청이 단일 또는 소수의 호스트로 향하는 경우 Total과 동일하게 설정
    const val CONNECTION_REQUEST_TIMEOUT_MILLIS = 2_000L
    const val RESPONSE_TIMEOUT_MILLIS = 10_000L
    const val EVICTION_INTERVAL_MILLIS = 60_000L
}
