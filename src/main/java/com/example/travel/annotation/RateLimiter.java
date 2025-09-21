package com.example.travel.annotation;

import java.lang.annotation.*;

/**
 * 限流注解
 * 用于标记需要进行限流控制的方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    
    /**
     * 限流类型
     */
    RateLimitType type() default RateLimitType.IP;
    
    /**
     * 时间窗口内的最大请求数
     */
    int maxRequests() default 100;
    
    /**
     * 时间窗口大小（秒）
     */
    int timeWindow() default 60;
    
    /**
     * 限流提示信息
     */
    String message() default "请求过于频繁，请稍后再试";
}