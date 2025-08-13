# JVM Web Framework Benchmark: Spring MVC vs Spring WebFlux

이 프로젝트는 Spring MVC와 Spring WebFlux의 API 성능을 벤치마크하는 멀티 모듈 프로젝트입니다.

## 🏗️ 프로젝트 구조

```
jvm-web-framework-benchmark/
├── common/                               # 공통 모듈
│   └── src/main/kotlin/me/bossm0n5t3r/dto/
│       └── UserRequest.kt                # 공통 DTO
├── mvc-app/                              # Spring MVC 애플리케이션
│   └── src/main/kotlin/me/bossm0n5t3r/
│       ├── MvcApplication.kt             # Spring MVC 메인 애플리케이션
│       ├── entity/User.kt                # JPA 엔티티
│       ├── config/JpaConfig.kt           # JPA 설정
│       ├── mvc/UserController.kt         # Spring MVC REST 컨트롤러
│       └── repository/jpa/UserRepository.kt # JPA 리포지토리
├── webflux-app/                          # Spring WebFlux 애플리케이션
│   └── src/main/kotlin/me/bossm0n5t3r/
│       ├── WebFluxApplication.kt         # Spring WebFlux 메인 애플리케이션
│       ├── entity/ReactiveUser.kt        # R2DBC 엔티티
│       ├── config/R2dbcConfig.kt         # R2DBC 설정
│       ├── webflux/UserHandler.kt        # Spring WebFlux 핸들러
│       ├── webflux/UserRouter.kt         # Spring WebFlux 라우터
│       └── repository/r2dbc/ReactiveUserRepository.kt # R2DBC 리포지토리
├── benchmark-app/                        # 성능 벤치마크 애플리케이션
│   └── src/main/kotlin/me/bossm0n5t3r/benchmark/
│       └── WebFrameworkBenchmark.kt     # 벤치마크 테스트 실행기
├── docker-compose.yaml                   # PostgreSQL 데이터베이스 설정
├── init.sql                              # 데이터베이스 초기화 스크립트
└── build.gradle.kts                      # 루트 빌드 설정
```

## 🚀 시작하기

### 모듈 설명

- **common**: 공통으로 사용되는 DTO 클래스들을 포함
- **mvc-app**: Spring MVC 기반의 전통적인 블로킹 웹 애플리케이션 (포트: 8080)
- **webflux-app**: Spring WebFlux 기반의 리액티브 웹 애플리케이션 (포트: 8081)
- **benchmark-app**: 두 애플리케이션의 성능을 비교하는 벤치마크 도구

### Spring Boot Docker Compose 통합

이 프로젝트는 **Spring Boot Docker Compose** 기능을 사용하여 개발 중에 필요한 서비스들을 자동으로 시작하고 관리합니다.

### 1. 애플리케이션 실행 방법

각 모듈은 독립적으로 실행할 수 있습니다:

```bash
# Spring MVC 애플리케이션 실행 (포트 8080)
./gradlew mvc-app:bootRun

# Spring WebFlux 애플리케이션 실행 (포트 8081)
./gradlew webflux-app:bootRun

# 두 애플리케이션을 동시에 실행 (벤치마크를 위해)
./gradlew mvc-app:bootRun &
./gradlew webflux-app:bootRun &
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

- Spring MVC: `http://localhost:8080`
- Spring WebFlux: `http://localhost:8081`

## 📡 API 엔드포인트

### Spring MVC 엔드포인트 (전통적인 블로킹 방식) - 포트 8080

- `GET http://localhost:8080/mvc/users` - 모든 사용자 조회
- `GET http://localhost:8080/mvc/users/{id}` - ID로 사용자 조회
- `GET http://localhost:8080/mvc/users/search?name={name}` - 이름으로 사용자 검색
- `GET http://localhost:8080/mvc/users/email/{email}` - 이메일로 사용자 조회
- `POST http://localhost:8080/mvc/users` - 사용자 생성
- `PUT http://localhost:8080/mvc/users/{id}` - 사용자 업데이트
- `DELETE http://localhost:8080/mvc/users/{id}` - 사용자 삭제

### Spring WebFlux 엔드포인트 (리액티브 방식) - 포트 8081

- `GET http://localhost:8081/webflux/users` - 모든 사용자 조회
- `GET http://localhost:8081/webflux/users/{id}` - ID로 사용자 조회
- `GET http://localhost:8081/webflux/users/search?name={name}` - 이름으로 사용자 검색
- `GET http://localhost:8081/webflux/users/email/{email}` - 이메일로 사용자 조회
- `POST http://localhost:8081/webflux/users` - 사용자 생성
- `PUT http://localhost:8081/webflux/users/{id}` - 사용자 업데이트
- `DELETE http://localhost:8081/webflux/users/{id}` - 사용자 삭제

## 📊 벤치마크 실행

성능 벤치마크 실행 방법:

### 1. 준비 단계

먼저 두 애플리케이션을 모두 실행해야 합니다:

```bash
# 두 애플리케이션을 백그라운드로 실행
./gradlew mvc-app:bootRun &
./gradlew webflux-app:bootRun &

# 애플리케이션이 완전히 시작될 때까지 대기 (약 30초)
```

### 2. 벤치마크 실행

```bash
./gradlew benchmark-app:bootRun
```

벤치마크는 다음 시나리오들의 성능을 측정합니다:

- **Get All Users**: 전체 사용자 목록 조회
- **Create User**: 사용자 생성 성능
- **Get User By ID**: ID로 사용자 조회
- **Update User**: 사용자 정보 업데이트
- **Delete User**: 사용자 삭제
- **Search Users**: 이름으로 사용자 검색
- **High Load**: 높은 동시성 부하 테스트

### 3. 결과 확인

벤치마크 실행 후 `benchmark.md` 파일이 생성되어 상세한 성능 비교 결과를 확인할 수 있습니다.

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

### Spring MVC (Traditional Blocking)

- **Database**: JPA + Hibernate (JDBC)
- **Connection Pool**: HikariCP
- **Threading Model**: Thread-per-request
- **Entity**: `User` (JPA annotations)
- **Repository**: `UserRepository` (JpaRepository)

### Spring WebFlux (Reactive Non-blocking)

- **Database**: Spring Data R2DBC
- **Connection Pool**: R2DBC Connection Pool
- **Threading Model**: Event Loop (Reactor Netty)
- **Entity**: `ReactiveUser` (R2DBC annotations)
- **Repository**: `ReactiveUserRepository` (ReactiveCrudRepository)

## 📈 성능 비교 포인트

1. **처리량 (Throughput)**: 초당 처리 가능한 요청 수
2. **응답 시간 (Response Time)**: 요청부터 응답까지의 시간
3. **리소스 사용률**: CPU, 메모리 사용량
4. **동시 접속 처리 능력**: 많은 사용자 동시 접속 시 성능

## 🛠️ 기술 스택

### 프레임워크 및 언어

- **Kotlin 2.2.0** - 주 개발 언어
- **Spring Boot 3.5.4** - 애플리케이션 프레임워크
- **JDK 21** - Java 런타임

### 웹 프레임워크

- **Spring MVC** - 전통적인 서블릿 기반 웹 프레임워크
- **Spring WebFlux** - 리액티브 웹 프레임워크

### 데이터베이스

- **PostgreSQL 15** - 관계형 데이터베이스
- **Spring Data JPA** - MVC 애플리케이션용 ORM
- **Spring Data R2DBC** - WebFlux 애플리케이션용 리액티브 데이터베이스 접근
- **PostgreSQL JDBC Driver 42.7.4** - 동기 데이터베이스 드라이버
- **R2DBC PostgreSQL 1.0.7.RELEASE** - 비동기 데이터베이스 드라이버

### 개발 도구

- **Spring Boot Docker Compose** - 자동 Docker 서비스 관리
- **Gradle** - 빌드 도구 및 멀티 모듈 관리
- **ktlint** - Kotlin 코드 스타일 검사

### 벤치마킹

- **OkHttp 4.12.0** - HTTP 클라이언트 (블로킹)
- **Spring WebClient** - 리액티브 HTTP 클라이언트
- **Jackson Module Kotlin** - JSON 처리
- **Apache Commons Math3 3.6.1** - 통계 계산
- **Kotlinx Coroutines** - 비동기 처리

### 인프라

- **Docker Compose** - 컨테이너 오케스트레이션
- **PostgreSQL Docker Image** - 데이터베이스 컨테이너

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
