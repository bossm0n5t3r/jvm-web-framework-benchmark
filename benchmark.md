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
| **Throughput (req/sec)** | 347.2 | 793.7 | 🏆 WebFlux (+56.3%) |
| **Avg Response Time (ms)** | 2.8 | 10.6 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 55.6 | - |
| **P99 Response Time (ms)** | 4.7 | 65.6 | - |

---

## ➕ CREATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 131.6 | 442.5 | 🏆 WebFlux (+70.3%) |
| **Avg Response Time (ms)** | 7.6 | 11.0 | 🚀 MVC |
| **P95 Response Time (ms)** | 9.2 | 64.0 | - |
| **P99 Response Time (ms)** | 82.0 | 65.0 | - |

---

## 🔍 GET User by ID

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 237.5 | 1234.6 | 🏆 WebFlux (+80.8%) |
| **Avg Response Time (ms)** | 4.2 | 7.6 | 🚀 MVC |
| **P95 Response Time (ms)** | 6.0 | 25.0 | - |
| **P99 Response Time (ms)** | 12.3 | 26.0 | - |

---

## 📝 UPDATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 143.7 | 649.4 | 🏆 WebFlux (+77.9%) |
| **Avg Response Time (ms)** | 6.9 | 7.5 | 🚀 MVC |
| **P95 Response Time (ms)** | 11.2 | 23.0 | - |
| **P99 Response Time (ms)** | 18.0 | 23.0 | - |

---

## 🗑️ DELETE User

**Test Configuration:**
- 📊 MVC: 10 requests, 5 concurrent
- 📊 WebFlux: 10 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (10/10) | 100.0% (10/10) | 🏆 WebFlux |
| **Throughput (req/sec)** | 89.3 | 243.9 | 🏆 WebFlux (+63.4%) |
| **Avg Response Time (ms)** | 11.2 | 8.0 | 🚀 WebFlux |
| **P95 Response Time (ms)** | 52.0 | 16.0 | - |
| **P99 Response Time (ms)** | 52.0 | 16.0 | - |

---

## 🔎 SEARCH Users by Name

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 0.0% (0/100) | 🏆 MVC |
| **Throughput (req/sec)** | 293.3 | 0.0 | 🏆 MVC (+100.0%) |
| **Avg Response Time (ms)** | 3.4 | 0.0 | 🚀 WebFlux |
| **P95 Response Time (ms)** | 6.0 | 0.0 | - |
| **P99 Response Time (ms)** | 11.0 | 0.0 | - |

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
| **Throughput (req/sec)** | 382.6 | 1126.1 | 🏆 WebFlux (+66.0%) |
| **Avg Response Time (ms)** | 2.6 | 41.8 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.6 | 52.0 | - |
| **P99 Response Time (ms)** | 6.0 | 62.0 | - |

---

