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
| **Throughput (req/sec)** | 342.5 | 833.3 | 🏆 WebFlux (+58.9%) |
| **Avg Response Time (ms)** | 2.8 | 10.2 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 50.0 | - |
| **P99 Response Time (ms)** | 4.7 | 60.3 | - |

---

## ➕ CREATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 131.6 | 409.8 | 🏆 WebFlux (+67.9%) |
| **Avg Response Time (ms)** | 7.6 | 11.8 | 🚀 MVC |
| **P95 Response Time (ms)** | 10.2 | 69.2 | - |
| **P99 Response Time (ms)** | 83.0 | 70.0 | - |

---

## 🔍 GET User by ID

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 218.8 | 1369.9 | 🏆 WebFlux (+84.0%) |
| **Avg Response Time (ms)** | 4.6 | 6.9 | 🚀 MVC |
| **P95 Response Time (ms)** | 7.0 | 27.0 | - |
| **P99 Response Time (ms)** | 11.3 | 27.7 | - |

---

## 📝 UPDATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 166.1 | 625.0 | 🏆 WebFlux (+73.4%) |
| **Avg Response Time (ms)** | 6.0 | 7.8 | 🚀 MVC |
| **P95 Response Time (ms)** | 10.2 | 17.2 | - |
| **P99 Response Time (ms)** | 13.0 | 18.0 | - |

---

## 🗑️ DELETE User

**Test Configuration:**
- 📊 MVC: 10 requests, 5 concurrent
- 📊 WebFlux: 10 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (10/10) | 100.0% (10/10) | 🏆 WebFlux |
| **Throughput (req/sec)** | 86.2 | 222.2 | 🏆 WebFlux (+61.2%) |
| **Avg Response Time (ms)** | 11.6 | 8.9 | 🚀 WebFlux |
| **P95 Response Time (ms)** | 47.0 | 20.0 | - |
| **P99 Response Time (ms)** | 47.0 | 20.0 | - |

---

## 🔎 SEARCH Users by Name

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 333.3 | 1449.3 | 🏆 WebFlux (+77.0%) |
| **Avg Response Time (ms)** | 3.0 | 6.6 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 21.0 | - |
| **P99 Response Time (ms)** | 9.0 | 21.0 | - |

---

## 🌐 External API Call

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 1.9 | 8.7 | 🏆 WebFlux (+78.1%) |
| **Avg Response Time (ms)** | 523.4 | 573.6 | 🚀 MVC |
| **P95 Response Time (ms)** | 530.5 | 1032.2 | - |
| **P99 Response Time (ms)** | 563.0 | 1033.0 | - |

---

## 💥 HIGH LOAD Test

**Test Configuration:**
- 📊 MVC: 500 requests, 10 concurrent
- 📊 WebFlux: 500 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (500/500) | 100.0% (500/500) | 🏆 WebFlux |
| **Throughput (req/sec)** | 318.9 | 943.4 | 🏆 WebFlux (+66.2%) |
| **Avg Response Time (ms)** | 3.1 | 50.1 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 61.6 | - |
| **P99 Response Time (ms)** | 6.0 | 67.0 | - |

---

