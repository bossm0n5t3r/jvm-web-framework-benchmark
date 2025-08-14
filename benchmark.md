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
| **Throughput (req/sec)** | 357.1 | 909.1 | 🏆 WebFlux (+60.7%) |
| **Avg Response Time (ms)** | 2.7 | 8.7 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 40.6 | - |
| **P99 Response Time (ms)** | 5.7 | 54.3 | - |

---

## ➕ CREATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 128.9 | 458.7 | 🏆 WebFlux (+71.9%) |
| **Avg Response Time (ms)** | 7.7 | 10.5 | 🚀 MVC |
| **P95 Response Time (ms)** | 11.0 | 50.0 | - |
| **P99 Response Time (ms)** | 79.0 | 51.0 | - |

---

## 🔍 GET User by ID

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 238.1 | 1886.8 | 🏆 WebFlux (+87.4%) |
| **Avg Response Time (ms)** | 4.2 | 4.9 | 🚀 MVC |
| **P95 Response Time (ms)** | 7.6 | 12.0 | - |
| **P99 Response Time (ms)** | 9.7 | 12.7 | - |

---

## 📝 UPDATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 164.5 | 666.7 | 🏆 WebFlux (+75.3%) |
| **Avg Response Time (ms)** | 6.1 | 7.2 | 🚀 MVC |
| **P95 Response Time (ms)** | 8.5 | 18.2 | - |
| **P99 Response Time (ms)** | 12.0 | 19.0 | - |

---

## 🗑️ DELETE User

**Test Configuration:**
- 📊 MVC: 10 requests, 5 concurrent
- 📊 WebFlux: 10 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (10/10) | 100.0% (10/10) | 🏆 WebFlux |
| **Throughput (req/sec)** | 94.3 | 208.3 | 🏆 WebFlux (+54.7%) |
| **Avg Response Time (ms)** | 10.6 | 9.3 | 🚀 WebFlux |
| **P95 Response Time (ms)** | 42.0 | 15.0 | - |
| **P99 Response Time (ms)** | 42.0 | 15.0 | - |

---

## 🔎 SEARCH Users by Name

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 327.9 | 1587.3 | 🏆 WebFlux (+79.3%) |
| **Avg Response Time (ms)** | 3.1 | 6.0 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.6 | 13.0 | - |
| **P99 Response Time (ms)** | 9.3 | 19.7 | - |

---

## 💥 HIGH LOAD Test

**Test Configuration:**
- 📊 MVC: 500 requests, 10 concurrent
- 📊 WebFlux: 500 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (500/500) | 100.0% (500/500) | 🏆 WebFlux |
| **Throughput (req/sec)** | 314.3 | 1101.3 | 🏆 WebFlux (+71.5%) |
| **Avg Response Time (ms)** | 3.2 | 42.8 | 🚀 MVC |
| **P95 Response Time (ms)** | 5.0 | 57.6 | - |
| **P99 Response Time (ms)** | 8.0 | 65.7 | - |

---

