package com.example.ratelimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@SpringBootApplication
public class RedisRateLimiterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisRateLimiterApplication.class, args);
    }
}

@RestController
@RequestMapping("/api")
class RateLimitedController {

    @Autowired
    private RedisRateLimiter redisRateLimiter;

    @GetMapping("/hello")
    public String hello(@RequestParam String userId) {
        if (redisRateLimiter.isAllowed(userId)) {
            return "Hello, " + userId + "!";
        } else {
            return "Rate limit exceeded for user: " + userId;
        }
    }
}

@Service
class RedisRateLimiter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final int MAX_REQUESTS = 5; // per minute
    private final int WINDOW_SECONDS = 60;

    public boolean isAllowed(String userId) {
        String key = "rate_limit:" + userId;
        Long current = redisTemplate.opsForValue().increment(key);

        if (current == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(WINDOW_SECONDS));
        }

        return current <= MAX_REQUESTS;
    }
}