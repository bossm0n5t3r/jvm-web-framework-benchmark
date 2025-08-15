# 🚀 JVM Web Framework Benchmark

## Configuration

| Framework | URL |
|-----------|-----|
| MVC | `http://localhost:8080/mvc` |
| WebFlux | `http://localhost:8081/webflux` |
| External | `http://localhost:8082/api/external` |

## Benchmark Execution Log

- ⏳ Waiting for applications to be ready...
- ✅ WebFlux Application is ready
- ✅ MVC Application is ready
- ✅ External Application is ready
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
| **Throughput (req/sec)** | 336.7 | 833.3 | 🏆 WebFlux (+59.6%) |
| **Avg Response Time (ms)** | 2.9 | 9.9 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 48.0 | - |
| **P99 Response Time (ms)** | 5.7 | 58.3 | - |

---

## ➕ CREATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 141.6 | 409.8 | 🏆 WebFlux (+65.4%) |
| **Avg Response Time (ms)** | 6.9 | 11.7 | 🚀 MVC |
| **P95 Response Time (ms)** | 13.0 | 69.0 | - |
| **P99 Response Time (ms)** | 82.0 | 70.0 | - |

---

## 🔍 GET User by ID

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 296.7 | 1562.5 | 🏆 WebFlux (+81.0%) |
| **Avg Response Time (ms)** | 3.3 | 5.9 | 🚀 MVC |
| **P95 Response Time (ms)** | 5.0 | 24.0 | - |
| **P99 Response Time (ms)** | 10.6 | 25.0 | - |

---

## 📝 UPDATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 211.9 | 588.2 | 🏆 WebFlux (+64.0%) |
| **Avg Response Time (ms)** | 4.7 | 8.3 | 🚀 MVC |
| **P95 Response Time (ms)** | 6.0 | 18.0 | - |
| **P99 Response Time (ms)** | 11.0 | 19.0 | - |

---

## 🗑️ DELETE User

**Test Configuration:**
- 📊 MVC: 10 requests, 5 concurrent
- 📊 WebFlux: 10 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (10/10) | 100.0% (10/10) | 🏆 WebFlux |
| **Throughput (req/sec)** | 78.1 | 208.3 | 🏆 WebFlux (+62.5%) |
| **Avg Response Time (ms)** | 12.7 | 9.2 | 🚀 WebFlux |
| **P95 Response Time (ms)** | 50.0 | 22.0 | - |
| **P99 Response Time (ms)** | 50.0 | 22.0 | - |

---

## 🔎 SEARCH Users by Name

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 321.5 | 1351.4 | 🏆 WebFlux (+76.2%) |
| **Avg Response Time (ms)** | 3.1 | 7.1 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.6 | 25.6 | - |
| **P99 Response Time (ms)** | 9.0 | 27.0 | - |

---

## 🌐 External API Call

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 66.4 | 231.5 | 🏆 WebFlux (+71.3%) |
| **Avg Response Time (ms)** | 15.1 | 21.4 | 🚀 MVC |
| **P95 Response Time (ms)** | 19.6 | 110.0 | - |
| **P99 Response Time (ms)** | 96.0 | 110.0 | - |

---

## 💥 HIGH LOAD Test

**Test Configuration:**
- 📊 MVC: 500 requests, 10 concurrent
- 📊 WebFlux: 500 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (500/500) | 100.0% (500/500) | 🏆 WebFlux |
| **Throughput (req/sec)** | 316.9 | 912.4 | 🏆 WebFlux (+65.3%) |
| **Avg Response Time (ms)** | 3.2 | 52.1 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 63.0 | - |
| **P99 Response Time (ms)** | 6.0 | 67.0 | - |

---

