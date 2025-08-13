# JVM Web Framework Benchmark

This module provides a comprehensive benchmarking tool to compare the performance of Spring MVC (blocking) vs Spring WebFlux (reactive) web frameworks.

## Overview

The `WebFrameworkBenchmark` tool performs load testing on both MVC and WebFlux applications, measuring:
- **Throughput** (requests per second)
- **Response Times** (average, min, max, P95, P99)
- **Success/Failure Rates**
- **Concurrency Handling**

## Prerequisites

Before running the benchmark, ensure both applications are running:

1. **MVC Application**: Should be running on `http://localhost:8080`
2. **WebFlux Application**: Should be running on `http://localhost:8081`

### Starting the Applications

```bash
# Terminal 1 - Start MVC App
./gradlew :mvc-app:bootRun

# Terminal 2 - Start WebFlux App  
./gradlew :webflux-app:bootRun
```

Make sure you have a PostgreSQL database running (using docker-compose):
```bash
docker-compose up -d
```

## Running the Benchmark

Once both applications are running, execute the benchmark:

```bash
./gradlew :benchmark-app:bootRun
```

## Benchmark Scenarios

The tool runs the following test scenarios:

### 1. 🔍 GET All Users
- **Requests**: 100
- **Concurrency**: 10
- Tests the `/users` endpoint for retrieving all users

### 2. ➕ CREATE User
- **Requests**: 50
- **Concurrency**: 5
- Tests user creation with unique data for each request

### 3. 🔍 GET User by ID
- **Requests**: 100
- **Concurrency**: 10
- Tests individual user retrieval by ID

### 4. 📝 UPDATE User
- **Requests**: 50
- **Concurrency**: 5
- Tests user updates with dynamic data

### 5. 🗑️ DELETE User
- **Requests**: 10
- **Concurrency**: 2
- Tests user deletion operations

### 6. 🔎 SEARCH Users by Name
- **Requests**: 100
- **Concurrency**: 10
- Tests the search functionality

### 7. 💥 HIGH LOAD Test
- **Requests**: 500
- **Concurrency**: 50
- Stress test with high concurrent load

## Sample Output

```
================================================================================
🚀 JVM Web Framework Benchmark
================================================================================
MVC App URL: http://localhost:8080/mvc/users
WebFlux App URL: http://localhost:8081/webflux/users

⏳ Waiting for applications to be ready...
✅ MVC Application is ready
✅ WebFlux Application is ready
✅ Applications are ready. Starting benchmark...

============================================================
🔍 GET All Users
============================================================
📊 Testing MVC - 100 requests, 10 concurrent
📊 Testing WebFlux - 100 requests, 10 concurrent
📈 Results Comparison:

Success Rate:
  MVC:     100.0% (100/100)
  WebFlux: 100.0% (100/100)

Throughput (requests/sec):
  MVC:     85.2
  WebFlux: 142.7
  Winner: 🏆 WebFlux (67.5% faster)

Response Times (ms):
  Average:
    MVC:     45.3
    WebFlux: 28.1
  P95:
    MVC:     78.5
    WebFlux: 52.3
  P99:
    MVC:     95.2
    WebFlux: 68.7
  Faster Response: 🚀 WebFlux

...
```

## Key Features

### Performance Metrics
- **Throughput**: Requests processed per second
- **Response Time Statistics**: Min, Max, Average, P95, P99
- **Success Rate**: Percentage of successful requests
- **Error Handling**: Failed request tracking and reporting

### HTTP Clients
- **MVC Testing**: Uses OkHttp for blocking requests
- **WebFlux Testing**: Uses Spring WebClient for reactive requests

### Concurrency Control
- Uses Kotlin coroutines with semaphore-based concurrency control
- Configurable concurrency levels per scenario

### Test Data Management
- Creates unique test data for each request to avoid conflicts
- Handles cleanup and error scenarios gracefully

## Configuration

The benchmark tool uses the following default configurations:

- **MVC Base URL**: `http://localhost:8080/mvc/users`
- **WebFlux Base URL**: `http://localhost:8081/webflux/users`
- **Connection Timeout**: 30 seconds
- **Request Timeout**: 30 seconds

To modify these settings, edit the `WebFrameworkBenchmark.kt` file.

## Understanding Results

### When to Use MVC
- Simple CRUD applications
- Traditional blocking I/O is sufficient
- Team familiarity with blocking paradigms
- Lower memory usage for low-concurrency scenarios

### When to Use WebFlux
- High-concurrency applications
- I/O-intensive operations
- Streaming data requirements
- Better resource utilization under load

## Troubleshooting

### Applications Not Ready
If you see "Applications failed to start within timeout":
1. Verify both apps are running on correct ports
2. Check database connectivity
3. Ensure no firewall blocking the ports

### Connection Errors
- Verify PostgreSQL is running: `docker-compose ps`
- Check application logs for startup errors
- Ensure ports 8080 and 8081 are not in use by other processes

### Memory Issues
If you encounter OutOfMemoryError during high load tests:
- Increase JVM heap size: `-Xmx2g`
- Reduce concurrency levels in benchmark scenarios
- Monitor system resources during testing

## Architecture

The benchmark tool is structured as follows:

```
benchmark-app/
├── src/main/kotlin/me/bossm0n5t3r/benchmark/
│   └── WebFrameworkBenchmark.kt    # Main benchmark logic
├── build.gradle.kts                # Dependencies and build config
└── README.md                      # This documentation
```

### Dependencies
- **Spring Boot**: Application framework
- **OkHttp**: HTTP client for MVC testing
- **WebClient**: Reactive HTTP client for WebFlux testing
- **Apache Commons Math**: Statistical calculations
- **Kotlin Coroutines**: Async/concurrent operations
- **Jackson**: JSON processing

## Contributing

To add new benchmark scenarios:

1. Create a new benchmark method following the pattern of existing methods
2. Add it to the `scenarios` list in `runComprehensiveBenchmark()`
3. Ensure proper error handling and cleanup
4. Update this documentation

## Performance Tips

### For Better Benchmark Accuracy
1. **Warm-up**: Let both applications run for a few minutes before benchmarking
2. **System Resources**: Ensure sufficient CPU and memory
3. **Network**: Run on localhost to minimize network latency
4. **Database**: Use SSD storage for better I/O performance
5. **JVM**: Use production JVM flags for both applications