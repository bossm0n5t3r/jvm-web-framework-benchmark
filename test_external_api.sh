#!/bin/bash

echo "Testing External API Integration with Virtual Threads"
echo "================================================="

# Test calling external health API
echo "1. Calling external health API..."
curl -X POST http://localhost:8080/mvc/users/external/health \
  -H "Content-Type: application/json" \
  -w "\nHTTP Status: %{http_code}\n" \
  2>/dev/null

echo ""
echo "2. Retrieving stored API responses..."
curl -X GET http://localhost:8080/mvc/users/external/responses \
  -H "Content-Type: application/json" \
  -w "\nHTTP Status: %{http_code}\n" \
  2>/dev/null

echo ""
echo "3. Testing external user API with user ID '123'..."
curl -X POST http://localhost:8080/mvc/users/external/user/123 \
  -H "Content-Type: application/json" \
  -w "\nHTTP Status: %{http_code}\n" \
  2>/dev/null

echo ""
echo "4. Testing external weather API for Seoul..."
curl -X POST "http://localhost:8080/mvc/users/external/weather?city=Seoul" \
  -H "Content-Type: application/json" \
  -w "\nHTTP Status: %{http_code}\n" \
  2>/dev/null

echo ""
echo "Test completed!"