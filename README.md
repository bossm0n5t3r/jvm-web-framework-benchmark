# JVM Web Framework Benchmark

Spring MVC와 Spring WebFlux의 성능을 비교하기 위한 멀티 모듈 벤치마크 프로젝트입니다.

MVC(virtual thread 유무)와 WebFlux(코루틴 유무) 조합을 동일한 API/DB/외부 호출 조건에서 비교하고, Gatling 리포트로 결과를 남깁니다.

## 구성

- `common`: 공통 DTO/테이블/유틸
- `mvc-app`: Spring MVC + JPA + Virtual Thread (8080)
- `mvc-without-virtual-thread-app`: Spring MVC + JPA (8083)
- `webflux-app`: Spring WebFlux + R2DBC (8081)
- `external-app`: 외부 API 시뮬레이터 (8082)
- `benchmark-app`: Gatling 시뮬레이션 및 커스텀 벤치마크
- `reports`: Gatling 리포트 결과

## 빠른 시작

```bash
# MVC (Virtual Thread)
./gradlew mvc-app:bootRun

# MVC (Virtual Thread 미사용)
./gradlew mvc-without-virtual-thread-app:bootRun

# WebFlux
./gradlew webflux-app:bootRun

# 외부 API 시뮬레이터
./gradlew external-app:bootRun
```

애플리케이션 실행 시 Spring Boot Docker Compose가 PostgreSQL을 자동으로 띄웁니다. 수동 관리가 필요하면 `docker-compose.yaml`을 사용하세요.

## 벤치마크

```bash
# Gatling 시뮬레이션 실행
./gradlew gatlingRun
```

- 시뮬레이션 상세 설명: `benchmark-app/README.md`
- 최신 결과 요약: `benchmark.md`
- HTML 리포트: `reports/gatling/`
  - [external-app](/reports/gatling/external-app/index.html)
  - [mvc](/reports/gatling/mvc/index.html)
  - [mvc-without-virtual-thread](/reports/gatling/mvc-without-virtual-thread/index.html)
  - [webflux](/reports/gatling/webflux/index.html)
  - [webflux-without-coroutines](/reports/gatling/webflux-without-coroutines/index.html)

## 기술 스택

- Kotlin 2.3.0, Spring Boot 4.0.1, JDK 25
- Spring MVC, Spring WebFlux
- PostgreSQL 18, JPA(Hibernate), R2DBC
- Gatling, Docker Compose
