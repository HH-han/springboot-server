package com.example.travel.aspect;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 日志切面
 * 用于记录控制器方法的请求和响应信息
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 定义切点：所有控制器方法
     */
    @Pointcut("execution(* com.example.travel.controller..*.*(..))")
    public void controllerPointcut() {}

    /**
     * 方法执行前记录日志
     */
    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                return;
            }

            HttpServletRequest request = attributes.getRequest();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            // 记录请求信息
            log.info("===================== 请求开始 =====================");
            log.info("请求地址: {} {}", request.getMethod(), request.getRequestURL());
            log.info("控制器方法: {}.{}", method.getDeclaringClass().getName(), method.getName());
            log.info("请求参数: {}", Arrays.toString(joinPoint.getArgs()));
            log.info("请求IP: {}", getClientIp(request));
            log.info("用户代理: {}", request.getHeader("User-Agent"));
        } catch (Exception e) {
            log.warn("记录请求日志异常: {}", e.getMessage());
        }
    }

    /**
     * 方法执行后记录日志
     */
    @AfterReturning(pointcut = "controllerPointcut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            // 记录响应信息
            log.info("方法返回: {}", JSON.toJSONString(result));
            log.info("===================== 请求结束 =====================");
        } catch (Exception e) {
            log.warn("记录响应日志异常: {}", e.getMessage());
        }
    }

    /**
     * 方法执行异常时记录日志
     */
    @AfterThrowing(pointcut = "controllerPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            // 记录异常信息
            log.error("方法执行异常: {}.{}", method.getDeclaringClass().getName(), method.getName());
            log.error("异常信息: {}", e.getMessage());
            log.error("异常堆栈: ", e);
            log.info("===================== 请求异常结束 =====================");
        } catch (Exception ex) {
            log.warn("记录异常日志异常: {}", ex.getMessage());
        }
    }

    /**
     * 环绕通知，记录方法执行时间
     */
    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            log.info("方法执行时间: {}ms - {}.{}",
                    (endTime - startTime),
                    method.getDeclaringClass().getSimpleName(),
                    method.getName());

            return result;
        } catch (Throwable e) {
            long endTime = System.currentTimeMillis();

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            log.error("方法执行异常耗时: {}ms - {}.{}",
                    (endTime - startTime),
                    method.getDeclaringClass().getSimpleName(),
                    method.getName());

            throw e;
        }
    }

    /**
     * 获取客户端IP地址
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
