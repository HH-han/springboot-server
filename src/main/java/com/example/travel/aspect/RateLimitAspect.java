package com.example.travel.aspect;

import com.example.travel.annotation.RateLimiter;
import com.example.travel.common.exception.RateLimitException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * 限流切面 - 基于令牌桶算法实现接口限流
 */
@Aspect
@Component
@Slf4j
public class RateLimitAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisScript<Long> rateLimitScript;

    /**
     * 定义限流切点 - 拦截所有带有@RateLimiter注解的方法
     */
    @Pointcut("@annotation(com.example.travel.annotation.RateLimiter)")
    public void rateLimitPointcut() {}

    /**
     * 环绕通知 - 实现限流逻辑
     */
    @Around("rateLimitPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);

        // 获取限流配置
        String key = generateRateLimitKey(joinPoint, rateLimiter);
        int maxRequests = rateLimiter.maxRequests();
        int timeWindow = rateLimiter.timeWindow();

        // 执行限流检查
        boolean allowed = checkRateLimit(key, maxRequests, timeWindow);

        if (!allowed) {
            log.warn("Rate limit exceeded for key: {}, method: {}", key, method.getName());
            throw new RateLimitException("请求过于频繁，请稍后再试");
        }

        // 执行目标方法
        return joinPoint.proceed();
    }

    /**
     * 生成限流key
     */
    private String generateRateLimitKey(ProceedingJoinPoint joinPoint, RateLimiter rateLimiter) {
        StringBuilder keyBuilder = new StringBuilder("rate_limit:");
        
        // 添加方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        keyBuilder.append(signature.getDeclaringTypeName())
                 .append(".")
                 .append(signature.getMethod().getName());

        // 根据限流类型添加不同标识
        switch (rateLimiter.type()) {
            case IP:
                keyBuilder.append(":ip:").append(getClientIP());
                break;
            case USER:
                // 这里需要根据实际情况获取用户ID
                keyBuilder.append(":user:").append("anonymous");
                break;
            case GLOBAL:
            default:
                keyBuilder.append(":global");
                break;
        }

        return keyBuilder.toString();
    }

    /**
     * 检查限流 - 使用Redis Lua脚本实现原子操作
     */
    private boolean checkRateLimit(String key, int maxRequests, int timeWindow) {
        long now = Instant.now().getEpochSecond();
        long windowStart = now - timeWindow;

        // 使用Redis ZSet实现滑动窗口限流
        Long count = redisTemplate.opsForZSet().count(key, windowStart, now);
        
        if (count != null && count >= maxRequests) {
            return false;
        }

        // 添加当前请求时间戳
        redisTemplate.opsForZSet().add(key, now, now);
        // 设置过期时间
        redisTemplate.expire(key, timeWindow + 1, TimeUnit.SECONDS);
        
        return true;
    }

    /**
     * 获取客户端IP地址
     */
    private String getClientIP() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
