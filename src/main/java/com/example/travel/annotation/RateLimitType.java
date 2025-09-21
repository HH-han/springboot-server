package com.example.travel.annotation;

/**
 * 限流类型枚举
 */
public enum RateLimitType {
    /**
     * 基于IP地址限流
     */
    IP,
    
    /**
     * 基于用户ID限流
     */
    USER,
    
    /**
     * 全局限流
     */
    GLOBAL
}