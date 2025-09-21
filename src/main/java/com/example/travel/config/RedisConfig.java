package com.example.travel.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>
 * Redis 配置类
 * </p>
 *
 * @author Ya Shi
 * @since 2024/3/12 14:21
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 设置objectMapper:转换java对象的时候使用
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping( LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,  JsonTypeInfo.As.WRAPPER_ARRAY);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // 设置key/value值的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

    /**
     * 限流Redis Lua脚本
     * 基于滑动窗口算法实现限流
     */
    @Bean
    public RedisScript<Long> rateLimitScript() {
        String luaScript = "local key = KEYS[1]\n" +
                "local now = tonumber(ARGV[1])\n" +
                "local windowStart = tonumber(ARGV[2])\n" +
                "local maxRequests = tonumber(ARGV[3])\n" +
                "\n" +
                "-- 移除过期的时间戳\n" +
                "redis.call('zremrangebyscore', key, 0, windowStart)\n" +
                "\n" +
                "-- 获取当前窗口内的请求数量\n" +
                "local count = redis.call('zcount', key, windowStart, now)\n" +
                "\n" +
                "-- 如果超过限制，返回0\n" +
                "if count >= maxRequests then\n" +
                "    return 0\n" +
                "end\n" +
                "\n" +
                "-- 添加当前请求时间戳\n" +
                "redis.call('zadd', key, now, now)\n" +
                "\n" +
                "-- 设置过期时间\n" +
                "redis.call('expire', key, ARGV[4])\n" +
                "\n" +
                "return 1";

        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText(luaScript);
        script.setResultType(Long.class);
        return script;
    }
}
