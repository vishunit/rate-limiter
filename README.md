# Redis Rate Limiter with Spring Boot

This is a simple implementation of **Fixed Window Rate Limiting** using **Spring Boot** and **Redis**. It limits the number of requests a user can make to a specific endpoint within a given time window (e.g., 5 requests per minute).

## 🚀 Features

- ⏱️ Fixed Window Rate Limiting Strategy
- 🧠 Redis as centralized rate limit store
- 📦 Lightweight Spring Boot REST API
- 🧪 Easily testable with curl/Postman


## ⚙️ Tech Stack

- Java 17+
- Spring Boot 3.x
- Redis
- Maven

## 📌 Endpoint

GET /api/hello?userId={userId}

**Example:**
curl "http://localhost:8080/api/hello?userId=testuser"


## 📋 Rate Limiting Rule

* Limit: 5 requests per user
* Window: 60 seconds
* Status:

  * ✅ Allowed: `"Hello, {userId}!"`
  * ❌ Blocked: `"Rate limit exceeded for user: {userId}"`

## 🔧 Setup & Run

### 🧑‍💻 Prerequisites

* Java 17+
* Redis running locally on port `6379`
* Maven installed

### ▶️ Steps

git clone https://github.com/<your-username>/redis-rate-limiter.git
cd redis-rate-limiter
./mvnw spring-boot:run


## 🛠 Configuration

Check the `application.properties`:

spring.redis.host=localhost
spring.redis.port=6379
server.port=8080

## 📂 Project Structure

src/main/java
 └── com/example/ratelimiter
      ├── RedisRateLimiterApplication.java
      ├── RateLimitedController.java
      └── RedisRateLimiter.java

## 📄 License

This project is licensed under the MIT License.

## 🙌 Contributions

Feel free to fork, contribute, or open issues!

## 💡 Future Enhancements

* Token Bucket rate limiting (via Redis Lua)
* User-specific or IP-based policies
* Redis Cluster and failover support


## 📝 GitHub "About" Section

A simple Spring Boot app demonstrating Redis-based fixed-window rate limiting. Control API usage per user with real-time Redis tracking.

Let me know if you’d like:
- GitHub Actions for auto build
- Dockerfile + Redis setup
- Token bucket upgrade via Lua
