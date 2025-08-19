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
| **Throughput (req/sec)** | 534.8 | 1098.9 | 🏆 WebFlux (+51.3%) |
| **Avg Response Time (ms)** | 1.8 | 7.0 | 🚀 MVC |
| **P95 Response Time (ms)** | 3.0 | 40.0 | - |
| **P99 Response Time (ms)** | 4.7 | 51.6 | - |

---

## ➕ CREATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 180.5 | 1136.4 | 🏆 WebFlux (+84.1%) |
| **Avg Response Time (ms)** | 5.5 | 4.0 | 🚀 WebFlux |
| **P95 Response Time (ms)** | 8.2 | 12.0 | - |
| **P99 Response Time (ms)** | 61.0 | 12.0 | - |

---

## 🔍 GET User by ID

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 512.8 | 3030.3 | 🏆 WebFlux (+83.1%) |
| **Avg Response Time (ms)** | 1.9 | 2.8 | 🚀 MVC |
| **P95 Response Time (ms)** | 3.0 | 4.0 | - |
| **P99 Response Time (ms)** | 3.0 | 4.7 | - |

---

## 📝 UPDATE User

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 289.0 | 961.5 | 🏆 WebFlux (+69.9%) |
| **Avg Response Time (ms)** | 3.5 | 5.0 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.2 | 7.2 | - |
| **P99 Response Time (ms)** | 5.0 | 8.0 | - |

---

## 🗑️ DELETE User

**Test Configuration:**
- 📊 MVC: 10 requests, 5 concurrent
- 📊 WebFlux: 10 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (10/10) | 100.0% (10/10) | 🏆 WebFlux |
| **Throughput (req/sec)** | 322.6 | 416.7 | 🏆 WebFlux (+22.6%) |
| **Avg Response Time (ms)** | 3.1 | 4.7 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 11.0 | - |
| **P99 Response Time (ms)** | 4.0 | 11.0 | - |

---

## 🔎 SEARCH Users by Name

**Test Configuration:**
- 📊 MVC: 100 requests, 10 concurrent
- 📊 WebFlux: 100 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (100/100) | 100.0% (100/100) | 🏆 WebFlux |
| **Throughput (req/sec)** | 431.0 | 2325.6 | 🏆 WebFlux (+81.5%) |
| **Avg Response Time (ms)** | 2.3 | 4.0 | 🚀 MVC |
| **P95 Response Time (ms)** | 3.0 | 6.6 | - |
| **P99 Response Time (ms)** | 3.7 | 8.7 | - |

---

## 🌐 External API Call

**Test Configuration:**
- 📊 MVC: 50 requests, 5 concurrent
- 📊 WebFlux: 50 requests, 5 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (50/50) | 100.0% (50/50) | 🏆 WebFlux |
| **Throughput (req/sec)** | 0.2 | 1.0 | 🏆 WebFlux (+80.0%) |
| **Avg Response Time (ms)** | 5026.1 | 5023.6 | 🚀 WebFlux |
| **P95 Response Time (ms)** | 5031.8 | 5034.0 | - |
| **P99 Response Time (ms)** | 5068.0 | 5036.0 | - |

---

## 💥 HIGH LOAD Test

**Test Configuration:**
- 📊 MVC: 500 requests, 10 concurrent
- 📊 WebFlux: 500 requests, 10 concurrent

### 📈 Results Comparison

| Metric | MVC | WebFlux | Winner |
|--------|-----|---------|--------|
| **Success Rate** | 100.0% (500/500) | 100.0% (500/500) | 🏆 WebFlux |
| **Throughput (req/sec)** | 315.1 | 932.8 | 🏆 WebFlux (+66.2%) |
| **Avg Response Time (ms)** | 3.2 | 50.8 | 🚀 MVC |
| **P95 Response Time (ms)** | 4.0 | 60.0 | - |
| **P99 Response Time (ms)** | 8.0 | 64.7 | - |

---

