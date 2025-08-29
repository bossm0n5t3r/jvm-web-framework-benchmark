# JVM Web Framework Benchmark: Spring MVC vs Spring WebFlux

이 프로젝트는 Spring MVC와 Spring WebFlux의 API 성능을 벤치마크하는 멀티 모듈 프로젝트입니다.

## 🏗️ 프로젝트 구조

```
jvm-web-framework-benchmark/
├── common/                               # 공통 모듈
│   └── src/main/kotlin/me/bossm0n5t3r/
│       ├── dto/                          # 공통 DTO 클래스들
│       │   ├── User.kt                   # 사용자 DTO
│       │   ├── UserRequest.kt            # 사용자 요청 DTO
│       │   ├── Weather.kt                # 날씨 DTO
│       │   ├── StockPrice.kt             # 주식 가격 DTO
│       │   ├── OrderStatus.kt            # 주문 상태 DTO
│       │   └── Metric.kt                 # 메트릭 DTO
│       ├── table/                        # 데이터베이스 테이블 설정
│       │   ├── UserTable.kt              # 사용자 테이블
│       │   └── ExternalApiResponseTable.kt # 외부 API 응답 테이블
│       └── util/                         # 공통 유틸리티 클래스
├── mvc-app/                              # Spring MVC 애플리케이션 (Virtual Thread 사용, 포트: 8080)
│   └── src/main/kotlin/me/bossm0n5t3r/
│       ├── MvcApplication.kt             # Spring MVC 메인 애플리케이션
│       ├── entity/                       # JPA 엔티티
│       │   ├── User.kt                   # 사용자 JPA 엔티티
│       │   └── ExternalApiResponse.kt    # 외부 API 응답 JPA 엔티티
│       ├── config/                       # 설정 클래스
│       │   ├── JpaConfig.kt              # JPA 설정
│       │   └── VirtualThreadConfig.kt    # Virtual Thread 설정
│       ├── controller/                   # MVC 컨트롤러
│       │   ├── UserController.kt         # 사용자 REST 컨트롤러
│       │   └── ExternalApiController.kt  # 외부 API 컨트롤러
│       ├── repository/                   # JPA 리포지토리
│       │   ├── UserRepository.kt         # 사용자 리포지토리
│       │   └── ExternalApiResponseRepository.kt # 외부 API 응답 리포지토리
│       └── service/
│           └── ExternalApiService.kt     # 외부 API 서비스
├── mvc-without-virtual-thread-app/       # Spring MVC 애플리케이션 (Virtual Thread 미사용, 포트: 8083)
│   └── src/main/kotlin/me/bossm0n5t3r/
│       ├── MvcWithoutVirtualThreadApplication.kt # Spring MVC 메인 애플리케이션 (Virtual Thread 미사용)
│       ├── entity/                       # JPA 엔티티
│       │   ├── User.kt                   # 사용자 JPA 엔티티
│       │   └── ExternalApiResponse.kt    # 외부 API 응답 JPA 엔티티
│       ├── config/                       # 설정 클래스
│       │   └── JpaConfig.kt              # JPA 설정
│       ├── controller/                   # MVC 컨트롤러
│       │   ├── UserController.kt         # 사용자 REST 컨트롤러
│       │   └── ExternalApiController.kt  # 외부 API 컨트롤러
│       ├── repository/                   # JPA 리포지토리
│       │   ├── UserRepository.kt         # 사용자 리포지토리
│       │   └── ExternalApiResponseRepository.kt # 외부 API 응답 리포지토리
│       └── service/
│           └── ExternalApiService.kt     # 외부 API 서비스
├── webflux-app/                          # Spring WebFlux 애플리케이션 (포트: 8081)
│   └── src/main/kotlin/me/bossm0n5t3r/
│       ├── WebFluxApplication.kt         # Spring WebFlux 메인 애플리케이션
│       ├── entity/                       # R2DBC 엔티티
│       │   ├── ReactiveUser.kt           # 사용자 R2DBC 엔티티
│       │   └── ReactiveExternalApiResponse.kt # 외부 API 응답 R2DBC 엔티티
│       ├── config/                       # 설정 클래스
│       │   ├── R2dbcConfig.kt            # R2DBC 설정
│       │   └── WebClientConfig.kt        # WebClient 설정
│       ├── webflux/                      # WebFlux 핸들러/라우터
│       │   ├── UserHandler.kt            # 사용자 핸들러
│       │   ├── UserRouter.kt             # 사용자 라우터
│       │   ├── ExternalHandler.kt        # 외부 API 핸들러
│       │   └── ExternalRouter.kt         # 외부 API 라우터
│       └── repository/                   # R2DBC 리포지토리
│           ├── ReactiveUserRepository.kt # 사용자 리포지토리
│           └── ReactiveExternalApiResponseRepository.kt # 외부 API 응답 리포지토리
├── external-app/                         # 외부 API 시뮬레이션 애플리케이션 (포트: 8082)
│   └── src/main/kotlin/me/bossm0n5t3r/
│       ├── ExternalApplication.kt        # 외부 API 메인 애플리케이션
│       └── controller/
│           └── ExternalApiController.kt  # 외부 API 시뮬레이션 컨트롤러
├── benchmark-app/                        # 성능 벤치마크 애플리케이션
│   ├── README.md                         # 벤치마크 상세 가이드
│   └── src/main/kotlin/me/bossm0n5t3r/benchmark/
│       ├── Constants.kt                  # 벤치마크 상수 정의
│       ├── WebFrameworkBenchmark.kt      # 커스텀 벤치마크 도구
│       ├── ExternalAppSimulation.kt      # Gatling 외부 API 시뮬레이션
│       ├── MVCSimulation.kt              # Gatling MVC 시뮬레이션
│       ├── MVCWithoutVirtualThreadSimulation.kt # Gatling MVC (Virtual Thread 미사용) 시뮬레이션
│       ├── WebFluxSimulation.kt          # Gatling WebFlux 시뮬레이션
│       └── WebFluxWithoutCoroutinesSimulation.kt # Gatling WebFlux (Coroutines 미사용) 시뮬레이션
├── reports/                              # Gatling 성능 테스트 보고서
│   └── gatling/
│       ├── external-app/                 # 외부 API 성능 보고서
│       ├── mvc/                          # MVC 성능 보고서
│       ├── mvc-without-virtual-thread/   # MVC (Virtual Thread 미사용) 성능 보고서
│       ├── webflux/                      # WebFlux 성능 보고서
│       └── webflux-without-coroutines/   # WebFlux (Coroutines 미사용) 성능 보고서
├── http/                                 # HTTP 요청 테스트 파일들
├── docker-compose.yaml                   # PostgreSQL 데이터베이스 설정
├── init.sql                              # 데이터베이스 초기화 스크립트
├── benchmark.md                          # 벤치마크 결과 리포트
└── build.gradle.kts                      # 루트 빌드 설정
```

## 🚀 시작하기

### 모듈 설명

- **common**: 공통으로 사용되는 DTO 클래스들, 데이터베이스 테이블 설정, 유틸리티 클래스를 포함
- **mvc-app**: Spring MVC 기반의 전통적인 블로킹 웹 애플리케이션 (포트: 8080)
  - JPA + Hibernate를 사용한 데이터베이스 연동
  - Virtual Thread 지원으로 성능 최적화
  - 외부 API 호출 및 응답 저장 기능
- **mvc-without-virtual-thread-app**: Spring MVC 기반의 전통적인 블로킹 웹 애플리케이션 (포트: 8083)
  - JPA + Hibernate를 사용한 데이터베이스 연동
  - Virtual Thread 미사용으로 기존 Thread Pool 방식 사용
  - mvc-app와 동일한 기능이지만 Virtual Thread 성능 비교용
- **webflux-app**: Spring WebFlux 기반의 리액티브 웹 애플리케이션 (포트: 8081)
  - Spring Data R2DBC를 사용한 비동기 데이터베이스 연동
  - 완전한 논블로킹 I/O 처리
  - 외부 API 호출 및 응답 저장 기능
  - Coroutines 사용 및 미사용 방식 모두 지원
- **external-app**: 외부 API 시뮬레이션 애플리케이션 (포트: 8082)
  - 벤치마크 테스트용 외부 서비스 시뮬레이션
  - 데이터베이스 연결 없는 순수 API 응답 제공
  - 다양한 도메인(사용자, 날씨, 주식, 주문, 메트릭) 데이터 제공
- **benchmark-app**: 모든 애플리케이션의 성능을 비교하는 벤치마크 도구
  - **Gatling 시뮬레이션**: 5가지 시나리오별 부하 테스트 (MVC, MVC without Virtual Thread, WebFlux, WebFlux without Coroutines, External
    App)
  - 처리량, 응답시간, 성공률 등 다양한 메트릭 측정
  - HTML 리포트 생성 및 통계 분석

### Spring Boot Docker Compose 통합

이 프로젝트는 **Spring Boot Docker Compose** 기능을 사용하여 개발 중에 필요한 서비스들을 자동으로 시작하고 관리합니다.

### 1. 애플리케이션 실행 방법

각 모듈은 독립적으로 실행할 수 있습니다:

```bash
# Spring MVC 애플리케이션 실행 (Virtual Thread 사용, 포트 8080)
./gradlew mvc-app:bootRun

# Spring MVC 애플리케이션 실행 (Virtual Thread 미사용, 포트 8083)
./gradlew mvc-without-virtual-thread-app:bootRun

# Spring WebFlux 애플리케이션 실행 (포트 8081)
./gradlew webflux-app:bootRun

# 외부 API 시뮬레이션 애플리케이션 실행 (포트 8082)
./gradlew external-app:bootRun

# 벤치마크를 위해 모든 애플리케이션 동시 실행
./gradlew mvc-app:bootRun &
./gradlew mvc-without-virtual-thread-app:bootRun &
./gradlew webflux-app:bootRun &
./gradlew external-app:bootRun &
```

애플리케이션 시작 시:

- Docker Compose가 자동으로 실행되어 PostgreSQL 데이터베이스를 시작합니다
- 데이터베이스 연결 정보가 자동으로 설정됩니다
- 애플리케이션이 종료되면 Docker 서비스도 자동으로 정리됩니다

### 2. 수동 데이터베이스 관리 (선택사항)

필요한 경우 Docker Compose를 수동으로 관리할 수 있습니다:

```bash
# 데이터베이스 시작
docker-compose up -d

# 데이터베이스 상태 확인
docker-compose ps

# 데이터베이스 종료
docker-compose down
```

### 3. 접근 URL

- Spring MVC (Virtual Thread 사용): `http://localhost:8080`
- Spring WebFlux: `http://localhost:8081`
- External API 시뮬레이션: `http://localhost:8082`
- Spring MVC (Virtual Thread 미사용): `http://localhost:8083`

## 📡 API 엔드포인트

### Spring MVC 엔드포인트 (전통적인 블로킹 방식) - 포트 8080

#### 사용자 관리 API

- `GET http://localhost:8080/mvc/users` - 모든 사용자 조회
- `GET http://localhost:8080/mvc/users/{id}` - ID로 사용자 조회
- `GET http://localhost:8080/mvc/users/search?name={name}` - 이름으로 사용자 검색
- `GET http://localhost:8080/mvc/users/email/{email}` - 이메일로 사용자 조회
- `POST http://localhost:8080/mvc/users` - 사용자 생성
- `PUT http://localhost:8080/mvc/users/{id}` - 사용자 업데이트
- `DELETE http://localhost:8080/mvc/users/{id}` - 사용자 삭제

#### 외부 API 호출

- `GET http://localhost:8080/mvc/external/user/{id}` - 외부 API에서 사용자 정보 조회
- `GET http://localhost:8080/mvc/external/weather?city={city}` - 외부 API에서 날씨 정보 조회
- `GET http://localhost:8080/mvc/external/stock/{symbol}` - 외부 API에서 주식 정보 조회
- `GET http://localhost:8080/mvc/external/order/{orderId}` - 외부 API에서 주문 상태 조회
- `GET http://localhost:8080/mvc/external/metrics` - 외부 API에서 메트릭 정보 조회

### Spring WebFlux 엔드포인트 (리액티브 방식) - 포트 8081

#### 사용자 관리 API

- `GET http://localhost:8081/webflux/users` - 모든 사용자 조회
- `GET http://localhost:8081/webflux/users/{id}` - ID로 사용자 조회
- `GET http://localhost:8081/webflux/users/search?name={name}` - 이름으로 사용자 검색
- `GET http://localhost:8081/webflux/users/email/{email}` - 이메일로 사용자 조회
- `POST http://localhost:8081/webflux/users` - 사용자 생성
- `PUT http://localhost:8081/webflux/users/{id}` - 사용자 업데이트
- `DELETE http://localhost:8081/webflux/users/{id}` - 사용자 삭제

#### 외부 API 호출

- `GET http://localhost:8081/webflux/external/user/{id}` - 외부 API에서 사용자 정보 조회
- `GET http://localhost:8081/webflux/external/weather?city={city}` - 외부 API에서 날씨 정보 조회
- `GET http://localhost:8081/webflux/external/stock/{symbol}` - 외부 API에서 주식 정보 조회
- `GET http://localhost:8081/webflux/external/order/{orderId}` - 외부 API에서 주문 상태 조회
- `GET http://localhost:8081/webflux/external/metrics` - 외부 API에서 메트릭 정보 조회

### Spring MVC 엔드포인트 (Virtual Thread 미사용) - 포트 8083

#### 사용자 관리 API

- `GET http://localhost:8083/mvc/users` - 모든 사용자 조회
- `GET http://localhost:8083/mvc/users/{id}` - ID로 사용자 조회
- `GET http://localhost:8083/mvc/users/search?name={name}` - 이름으로 사용자 검색
- `GET http://localhost:8083/mvc/users/email/{email}` - 이메일로 사용자 조회
- `POST http://localhost:8083/mvc/users` - 사용자 생성
- `PUT http://localhost:8083/mvc/users/{id}` - 사용자 업데이트
- `DELETE http://localhost:8083/mvc/users/{id}` - 사용자 삭제

#### 외부 API 호출

- `GET http://localhost:8083/mvc/external/user/{id}` - 외부 API에서 사용자 정보 조회
- `GET http://localhost:8083/mvc/external/weather?city={city}` - 외부 API에서 날씨 정보 조회
- `GET http://localhost:8083/mvc/external/stock/{symbol}` - 외부 API에서 주식 정보 조회
- `GET http://localhost:8083/mvc/external/order/{orderId}` - 외부 API에서 주문 상태 조회
- `GET http://localhost:8083/mvc/external/metrics` - 외부 API에서 메트릭 정보 조회

### 외부 API 시뮬레이션 엔드포인트 - 포트 8082

#### 기본 상태 확인

- `GET http://localhost:8082/api/external/health` - 헬스 체크 (상태, 타임스탬프, 요청 카운트)

#### 사용자 정보

- `GET http://localhost:8082/api/external/user/{id}` - 랜덤 사용자 정보 반환
  - 응답: 사용자 ID, 이름, 이메일, 부서, 급여, 타임스탬프

#### 날씨 정보

- `GET http://localhost:8082/api/external/weather?city={city}` - 날씨 정보 반환 (기본값: Seoul)
  - 응답: 도시명, 온도, 날씨 상태, 습도, 풍속, 타임스탬프

#### 주식 정보

- `GET http://localhost:8082/api/external/stock/{symbol}` - 주식 가격 정보 반환
  - 지원 심볼: AAPL, GOOGL, TSLA, MSFT (기타는 기본값)
  - 응답: 심볼, 현재가, 변동폭, 변동률, 거래량, 타임스탬프

#### 주문 상태

- `GET http://localhost:8082/api/external/order/{orderId}` - 주문 상태 정보 반환
  - 응답: 주문 ID, 상태, 상품명, 수량, 총액, 예상 배송일, 타임스탬프

#### 메트릭/분석 데이터

- `GET http://localhost:8082/api/external/metrics` - 시스템 메트릭 정보 반환
  - 응답: 총 사용자 수, 활성 사용자 수, 수익, 전환율, 서버 부하, 응답 시간, 타임스탬프

## 📊 벤치마크 실행

이 프로젝트는 두 가지 벤치마크 도구를 제공합니다:

### 방법 1: 커스텀 벤치마크 도구 (WebFrameworkBenchmark.kt)

#### 1-1. 준비 단계

벤치마크 실행을 위해 필요한 애플리케이션들을 실행합니다:

```bash
# 기본 비교용 (MVC vs WebFlux)
./gradlew mvc-app:bootRun &
./gradlew webflux-app:bootRun &

# 모든 애플리케이션 포함 비교 (권장)
./gradlew mvc-app:bootRun &
./gradlew mvc-without-virtual-thread-app:bootRun &
./gradlew webflux-app:bootRun &
./gradlew external-app:bootRun &

# 모든 애플리케이션이 완전히 시작될 때까지 대기 (약 30-60초)
```

### 방법 2: Gatling 시뮬레이션

#### 2-1. Gatling 시뮬레이션 실행

```bash
./gradlew gatlingRun

> Task :benchmark-app:gatlingRun
Choose a simulation number:
     [0] me.bossm0n5t3r.benchmark.ExternalAppSimulation # 외부 API 애플리케이션 테스트
     [1] me.bossm0n5t3r.benchmark.MVCSimulation # MVC 애플리케이션 테스트 (Virtual Thread 사용)
     [2] me.bossm0n5t3r.benchmark.MVCWithoutVirtualThreadSimulation # MVC 애플리케이션 테스트 (Virtual Thread 미사용)
     [3] me.bossm0n5t3r.benchmark.WebFluxSimulation # WebFlux 애플리케이션 테스트
     [4] me.bossm0n5t3r.benchmark.WebFluxWithoutCoroutinesSimulation # WebFlux 애플리케이션 테스트 (Coroutines 미사용)
# 숫자 입력
```

벤치마크는 다음 시나리오들의 성능을 측정합니다:

- **Get All Users**: 전체 사용자 목록 조회
- **Create User**: 사용자 생성 성능
- **Get User By ID**: ID로 사용자 조회
- **Update User**: 사용자 정보 업데이트
- **Delete User**: 사용자 삭제
- **Search Users**: 이름으로 사용자 검색
- **External API Call**: 외부 API 호출 성능 (외부 서비스 통신 시뮬레이션)
- **High Load**: 높은 동시성 부하 테스트

### 3. 결과 확인

벤치마크 실행 후 `benchmark.md` 파일이 생성되어 상세한 성능 비교 결과를 확인할 수 있습니다.

#### 최신 벤치마크 결과 요약

현재 프로젝트의 `benchmark.md` 파일에서 확인할 수 있는 주요 결과:

- **처리량(Throughput)**: 대부분의 시나리오에서 WebFlux가 MVC보다 60-80% 높은 처리량을 보임
- **응답 시간(Response Time)**: 낮은 부하에서는 MVC가 더 빠른 평균 응답 시간을 보이지만, 고부하에서는 WebFlux가 일관된 성능을 유지
- **외부 API 호출**: WebFlux가 논블로킹 I/O의 장점을 활용하여 71% 높은 처리량을 달성
- **고부하 테스트**: 500개 요청, 10개 동시 연결에서 WebFlux가 65% 더 높은 처리량을 보임

자세한 결과는 프로젝트 루트의 `benchmark.md` 파일을 참조하세요.

## 🔧 설정 정보

### 데이터베이스 설정

- **Database**: PostgreSQL 15
- **Host**: localhost:5432
- **Database Name**: benchmark
- **Username**: benchmark_user
- **Password**: benchmark_pass

### 애플리케이션 프로필

- **Default**: 기본 설정
- **dev**: 개발용 (상세한 로깅)
- **prod**: 운영용 (최소한의 로깅)
- **benchmark**: 벤치마크용 (오류 로그만)

특정 프로필로 실행:

```bash
./gradlew bootRun --args="--spring.profiles.active=dev"
```

## 🧪 API 테스트

### curl을 사용한 테스트

```bash
# 사용자 생성 (MVC - 포트 8080)
curl -X POST http://localhost:8080/mvc/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Test User", "email": "test@example.com"}'

# 사용자 생성 (WebFlux - 포트 8081)
curl -X POST http://localhost:8081/webflux/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Test User", "email": "test@example.com"}'

# 모든 사용자 조회 (MVC)
curl http://localhost:8080/mvc/users

# 모든 사용자 조회 (WebFlux)
curl http://localhost:8081/webflux/users

# 사용자 조회 (ID로)
curl http://localhost:8080/mvc/users/1
curl http://localhost:8081/webflux/users/1

# 사용자 검색 (이름으로)
curl "http://localhost:8080/mvc/users/search?name=John"
curl "http://localhost:8081/webflux/users/search?name=John"

# 사용자 업데이트
curl -X PUT http://localhost:8080/mvc/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name": "Updated User", "email": "updated@example.com"}'

# 사용자 삭제
curl -X DELETE http://localhost:8080/mvc/users/1
```

## 🏛️ 아키텍처

### Spring MVC with Virtual Thread (Modern Blocking) - 포트 8080

- **Database**: JPA + Hibernate (JDBC)
- **Connection Pool**: HikariCP
- **Threading Model**: Virtual Thread (Project Loom)
- **Entity**: `User` (JPA annotations)
- **Repository**: `UserRepository` (JpaRepository)
- **Performance**: 고성능 블로킹 I/O, 메모리 효율적인 경량 스레드

### Spring MVC without Virtual Thread (Traditional Blocking) - 포트 8083

- **Database**: JPA + Hibernate (JDBC)
- **Connection Pool**: HikariCP
- **Threading Model**: Platform Thread (Thread-per-request)
- **Entity**: `User` (JPA annotations)
- **Repository**: `UserRepository` (JpaRepository)
- **Performance**: 전통적인 블로킹 I/O, 높은 메모리 사용량

### Spring WebFlux (Reactive Non-blocking) - 포트 8081

- **Database**: Spring Data R2DBC
- **Connection Pool**: R2DBC Connection Pool
- **Threading Model**: Event Loop (Reactor Netty)
- **Entity**: `ReactiveUser` (R2DBC annotations)
- **Repository**: `ReactiveUserRepository` (ReactiveCrudRepository)
- **Performance**: 완전한 비동기 논블로킹 I/O, 높은 동시성 처리

## 📈 성능 비교 포인트

1. **처리량 (Throughput)**: 초당 처리 가능한 요청 수
2. **응답 시간 (Response Time)**: 요청부터 응답까지의 시간
3. **리소스 사용률**: CPU, 메모리 사용량
4. **동시 접속 처리 능력**: 많은 사용자 동시 접속 시 성능

## 🛠️ 기술 스택

### 프레임워크 및 언어

- **Kotlin 2.2.10** - 주 개발 언어
- **Spring Boot 3.5.4** - 애플리케이션 프레임워크
- **JDK 24** - Java 런타임

### 웹 프레임워크

- **Spring MVC** - 전통적인 서블릿 기반 웹 프레임워크
- **Spring WebFlux** - 리액티브 웹 프레임워크 (Reactor Netty 기반)

### 데이터베이스

- **PostgreSQL 15** - 관계형 데이터베이스
- **Spring Data JPA** - MVC 애플리케이션용 ORM (Hibernate 기반)
- **Spring Data R2DBC** - WebFlux 애플리케이션용 리액티브 데이터베이스 접근
- **PostgreSQL JDBC Driver 42.7.7** - 동기 데이터베이스 드라이버
- **R2DBC PostgreSQL 1.0.7.RELEASE** - 비동기 데이터베이스 드라이버

### 개발 도구 및 플러그인

- **Spring Boot Docker Compose** - 자동 Docker 서비스 관리
- **Gradle** - 빌드 도구 및 멀티 모듈 관리
- **ktlint 13.0.0** - Kotlin 코드 스타일 검사 (Pinterest ktlint 1.7.1 사용)
- **Kotlin JVM Plugin** - JVM 대상 Kotlin 컴파일
- **Kotlin Spring Plugin** - Spring 프레임워크 통합
- **Kotlin JPA Plugin** - JPA 엔티티 지원

### 벤치마킹 및 분석

- **Spring WebClient** - 리액티브 HTTP 클라이언트 (WebFlux 벤치마킹용)
- **RestTemplate/RestClient** - 블로킹 HTTP 클라이언트 (MVC 벤치마킹용)
- **Jackson Module Kotlin 2.19.2** - JSON 처리 및 직렬화
- **Kotlinx DataFrame 1.0.0-Beta2** - 데이터 분석 및 통계 계산
- **Kotlinx Coroutines 1.10.2** - 비동기 처리 및 동시성

### 인프라

- **Docker Compose** - 컨테이너 오케스트레이션
- **PostgreSQL Docker Image** - 데이터베이스 컨테이너
- **Netty DNS Resolver** - macOS용 네이티브 DNS 리졸버 (성능 최적화)

## 🧹 정리

### 자동 정리 (Spring Boot Docker Compose 사용 시)

Spring Boot Docker Compose를 사용하는 경우:

- 애플리케이션 종료 시 (Ctrl+C) Docker 서비스가 자동으로 정리됩니다
- 별도의 수동 정리가 필요하지 않습니다

### 수동 정리 (필요한 경우)

수동으로 Docker Compose를 관리했거나 완전한 정리가 필요한 경우:

```bash
# 데이터베이스 종료
docker-compose down

# 볼륨까지 삭제 (데이터 완전 삭제)
docker-compose down -v
```

## 📝 결과 분석

벤치마크 실행 후, 다음과 같은 관점에서 결과를 분석해보세요:

1. **낮은 동시성**: 적은 수의 동시 사용자에서는 Spring MVC가 더 나은 성능을 보일 수 있음
2. **높은 동시성**: 많은 동시 사용자가 있을 때 Spring WebFlux의 비동기 처리 장점이 드러남
3. **I/O 집약적 작업**: 데이터베이스 작업이 많은 경우 WebFlux의 논블로킹 I/O 장점
4. **메모리 사용량**: WebFlux는 일반적으로 더 적은 메모리 사용

## 🤝 기여

이 프로젝트에 기여하고 싶으시다면:

1. Fork the project
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request
