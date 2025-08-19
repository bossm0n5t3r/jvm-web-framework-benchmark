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
| **Throughput (req/sec)** | 322.6 | 826.4 | 🏆 WebFlux (+61.0%) |
| **Avg Response Time (ms)** | 3.0 | 9.9 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 51.6 | - |
| **P99 Response Time (ms)** | 5.7 | 62.3 | - |

---

## ➕ CREATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 161.3 | 423.7 | 🏆 WebFlux (+61.9%) |
| **Avg Response Time (ms)** | 6.2 | 11.1 | 🚀 MVC |
| **P95 Response Time (ms)** | 6.2 | 69.0 | - |
| **P99 Response Time (ms)** | 89.0 | 70.0 | - |

---

## 🔍 GET User by ID

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 227.8 | 1408.5 | 🏆 WebFlux (+83.8%) |
| **Avg Response Time (ms)** | 4.4 | 6.7 | 🚀 MVC |
| **P95 Response Time (ms)** | 6.6 | 27.0 | - |
| **P99 Response Time (ms)** | 12.3 | 28.0 | - |

---

## 📝 UPDATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 160.3 | 724.6 | 🏆 WebFlux (+77.9%) |
| **Avg Response Time (ms)** | 6.2 | 6.7 | 🚀 MVC |
| **P95 Response Time (ms)** | 9.2 | 17.2 | - |
| **P99 Response Time (ms)** | 15.0 | 19.0 | - |

---

## 🗑️ DELETE User

**Test Configuration:**
- 📊 MVC: 10 requests, 5 concurrent
- 📊 WebFlux: 10 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (10/10) | 100.0% (10/10) | 🏆 WebFlux |
| **Throughput (req/sec)** | 91.7 | 263.2 | 🏆 WebFlux (+65.1%) |
| **Avg Response Time (ms)** | 10.9 | 7.2 | 🚀 WebFlux |
| **P95 Response Time (ms)** | 50.0 | 17.0 | - |
| **P99 Response Time (ms)** | 50.0 | 17.0 | - |

---

## 🔎 SEARCH Users by Name

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 314.5 | 1515.2 | 🏆 WebFlux (+79.2%) |
| **Avg Response Time (ms)** | 3.2 | 6.4 | 🚀 MVC |
| **P95 Response Time (ms)** | 5.0 | 21.0 | - |
| **P99 Response Time (ms)** | 12.0 | 22.7 | - |

---

## 🌐 External API Call

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 0.6 | 3.1 | 🏆 WebFlux (+79.4%) |
| **Avg Response Time (ms)** | 1584.1 | 1630.9 | 🚀 MVC |
| **P95 Response Time (ms)** | 1596.7 | 2137.0 | - |
| **P99 Response Time (ms)** | 1719.0 | 2138.0 | - |

---

## 💥 HIGH LOAD Test

**Test Configuration:**
- 📊 MVC: 500 requests, 10 concurrent
- 📊 WebFlux: 500 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (500/500) | 100.0% (500/500) | 🏆 WebFlux |
| **Throughput (req/sec)** | 443.3 | 941.6 | 🏆 WebFlux (+52.9%) |
| **Avg Response Time (ms)** | 2.2 | 50.3 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 59.0 | - |
| **P99 Response Time (ms)** | 5.0 | 67.7 | - |

---

