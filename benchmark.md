================================================================================
🚀 JVM Web Framework Benchmark
================================================================================
MVC App URL: http://localhost:8080/mvc/users
WebFlux App URL: http://localhost:8081/webflux/users

⏳ Waiting for applications to be ready...
✅ WebFlux Application is ready
✅ MVC Application is ready
✅ Applications are ready. Cleaning up tables
✅ Everything is ready. Starting benchmark...

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
MVC:     400.0
WebFlux: 456.6
Winner: 🏆 WebFlux (12.4% faster)

Response Times (ms):
Average:
MVC:     2.4
WebFlux: 14.8
P95:
MVC:     3.0
WebFlux: 92.0
P99:
MVC:     4.0
WebFlux: 161.3
Faster Response: 🚀 MVC

============================================================
➕ CREATE User
============================================================
📊 Testing MVC - 50 requests, 5 concurrent
📊 Testing WebFlux - 50 requests, 5 concurrent
📈 Results Comparison:

Success Rate:
MVC:     100.0% (50/50)
WebFlux: 64.0% (32/50)

Throughput (requests/sec):
MVC:     129.9
WebFlux: 248.1
Winner: 🏆 WebFlux (47.6% faster)

Response Times (ms):
Average:
MVC:     7.7
WebFlux: 9.6
P95:
MVC:     9.4
WebFlux: 75.4
P99:
MVC:     91.0
WebFlux: 76.0
Faster Response: 🚀 MVC

⚠️  Failed Requests:
WebFlux: 18

============================================================
🔍 GET User by ID
============================================================
📊 Testing MVC - 100 requests, 10 concurrent
📊 Testing WebFlux - 100 requests, 10 concurrent
📈 Results Comparison:

Success Rate:
MVC:     100.0% (100/100)
WebFlux: 100.0% (100/100)

Throughput (requests/sec):
MVC:     243.3
WebFlux: 1515.2
Winner: 🏆 WebFlux (83.9% faster)

Response Times (ms):
Average:
MVC:     4.1
WebFlux: 6.2
P95:
MVC:     6.9
WebFlux: 25.0
P99:
MVC:     13.0
WebFlux: 27.0
Faster Response: 🚀 MVC

============================================================
📝 UPDATE User
============================================================
📊 Testing MVC - 50 requests, 5 concurrent
📊 Testing WebFlux - 50 requests, 5 concurrent
📈 Results Comparison:

Success Rate:
MVC:     100.0% (50/50)
WebFlux: 100.0% (50/50)

Throughput (requests/sec):
MVC:     210.1
WebFlux: 595.2
Winner: 🏆 WebFlux (64.7% faster)

Response Times (ms):
Average:
MVC:     4.7
WebFlux: 8.3
P95:
MVC:     7.9
WebFlux: 23.0
P99:
MVC:     10.0
WebFlux: 23.0
Faster Response: 🚀 MVC

============================================================
🗑️ DELETE User
============================================================
📊 Testing MVC - 10 requests, 5 concurrent
📊 Testing WebFlux - 10 requests, 5 concurrent
📈 Results Comparison:

Success Rate:
MVC:     100.0% (10/10)
WebFlux: 0.0% (0/10)

Throughput (requests/sec):
MVC:     103.1
WebFlux: 0.0
Winner: 🏆 MVC (100.0% faster)

Response Times (ms):
Average:
MVC:     9.6
WebFlux: NaN
P95:
MVC:     44.0
WebFlux: NaN
P99:
MVC:     44.0
WebFlux: NaN
Faster Response: 🚀 MVC

⚠️  Failed Requests:
WebFlux: 10

============================================================
🔎 SEARCH Users by Name
============================================================
📊 Testing MVC - 100 requests, 10 concurrent
📊 Testing WebFlux - 100 requests, 10 concurrent
📈 Results Comparison:

Success Rate:
MVC:     100.0% (100/100)
WebFlux: 0.0% (0/100)

Throughput (requests/sec):
MVC:     331.1
WebFlux: 0.0
Winner: 🏆 MVC (100.0% faster)

Response Times (ms):
Average:
MVC:     3.0
WebFlux: NaN
P95:
MVC:     4.9
WebFlux: NaN
P99:
MVC:     10.9
WebFlux: NaN
Faster Response: 🚀 MVC

⚠️  Failed Requests:
WebFlux: 100

============================================================
💥 HIGH LOAD Test
============================================================
📊 Testing MVC - 500 requests, 10 concurrent
📊 Testing WebFlux - 500 requests, 10 concurrent
📈 Results Comparison:

Success Rate:
MVC:     100.0% (500/500)
WebFlux: 100.0% (500/500)

Throughput (requests/sec):
MVC:     355.1
WebFlux: 632.1
Winner: 🏆 WebFlux (43.8% faster)

Response Times (ms):
Average:
MVC:     2.8
WebFlux: 75.7
P95:
MVC:     4.0
WebFlux: 99.0
P99:
MVC:     6.0
WebFlux: 109.0
Faster Response: 🚀 MVC

