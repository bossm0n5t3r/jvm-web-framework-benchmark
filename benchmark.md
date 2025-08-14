# 🚀 JVM Web Framework Benchmark

## Configuration

| Framework | URL |
|-----------|-----|
| MVC | `http://localhost:8080/mvc/users` |
| WebFlux | `http://localhost:8081/webflux/users` |

## Benchmark Execution Log

- ⏳ Waiting for applications to be ready...
- ✅ WebFlux Application is ready
- ✅ MVC Application is ready
- ✅ Applications are ready. Cleaning up tables
- ✅ Everything is ready. Starting benchmark...

# Benchmark Results

## 🔍 GET All Users

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 328.9 | 793.7 | 🏆 WebFlux (+58.6%) |
| **Avg Response Time (ms)** | 2.9 | 10.5 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 51.6 | - |
| **P99 Response Time (ms)** | 4.7 | 62.0 | - |

---

## ➕ CREATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 126.9 | 420.2 | 🏆 WebFlux (+69.8%) |
| **Avg Response Time (ms)** | 7.8 | 11.5 | 🚀 MVC |
| **P95 Response Time (ms)** | 10.2 | 68.2 | - |
| **P99 Response Time (ms)** | 86.0 | 69.0 | - |

---

## 🔍 GET User by ID

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 230.4 | 1470.6 | 🏆 WebFlux (+84.3%) |
| **Avg Response Time (ms)** | 4.3 | 6.5 | 🚀 MVC |
| **P95 Response Time (ms)** | 7.0 | 26.0 | - |
| **P99 Response Time (ms)** | 11.7 | 27.0 | - |

---

## 📝 UPDATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 192.3 | 735.3 | 🏆 WebFlux (+73.8%) |
| **Avg Response Time (ms)** | 5.2 | 6.7 | 🚀 MVC |
| **P95 Response Time (ms)** | 7.2 | 16.0 | - |
| **P99 Response Time (ms)** | 11.0 | 17.0 | - |

---

## 🗑️ DELETE User

**Test Configuration:**
- 📊 MVC: 10 requests, 5 concurrent
- 📊 WebFlux: 10 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (10/10) | 0.0% (0/10) | 🏆 MVC |
| **Throughput (req/sec)** | 81.3 | 0.0 | 🏆 MVC (+100.0%) |
| **Avg Response Time (ms)** | 12.3 | 0.0 | 🚀 WebFlux |
| **P95 Response Time (ms)** | 52.0 | 0.0 | - |
| **P99 Response Time (ms)** | 52.0 | 0.0 | - |

### ⚠️ Failed Requests

- **WebFlux**: 10 failures

---

## 🔎 SEARCH Users by Name

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 0.0% (0/100) | 🏆 MVC |
| **Throughput (req/sec)** | 325.7 | 0.0 | 🏆 MVC (+100.0%) |
| **Avg Response Time (ms)** | 3.0 | 0.0 | 🚀 WebFlux |
| **P95 Response Time (ms)** | 5.0 | 0.0 | - |
| **P99 Response Time (ms)** | 9.7 | 0.0 | - |

### ⚠️ Failed Requests

- **WebFlux**: 100 failures

---

## 💥 HIGH LOAD Test

**Test Configuration:**
- 📊 MVC: 500 requests, 10 concurrent
- 📊 WebFlux: 500 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (500/500) | 100.0% (500/500) | 🏆 WebFlux |
| **Throughput (req/sec)** | 356.1 | 1116.1 | 🏆 WebFlux (+68.1%) |
| **Avg Response Time (ms)** | 2.8 | 42.2 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 52.0 | - |
| **P99 Response Time (ms)** | 5.7 | 56.0 | - |

---

