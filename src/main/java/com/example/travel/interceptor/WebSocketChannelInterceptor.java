package com.example.travel.interceptor;

import com.example.travel.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

/**
 * WebSocket通道拦截器
 * 用于处理连接、断开等事件
 */
@Component
@Slf4j
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public WebSocketChannelInterceptor(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    /**
     * 发送消息前拦截
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        
        if (accessor != null) {
            StompCommand command = accessor.getCommand();
            
            // 处理CONNECT命令，进行JWT身份验证
            if (command == StompCommand.CONNECT) {
                List<String> authHeaders = accessor.getNativeHeader("Authorization");
                if (authHeaders != null && !authHeaders.isEmpty()) {
                    String authHeader = authHeaders.get(0);
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        String token = authHeader.substring(7);
                        try {
                            // 验证JWT token
                            jwtUtils.validateToken(token);
                            String username = jwtUtils.getUsernameFromToken(token);
                            
                            // 加载用户详情
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            
                            // 创建认证对象
                            Authentication authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                            
                            // 设置认证信息到SecurityContext
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            
                            // 设置Principal到STOMP访问器
                            accessor.setUser(authentication);
                            
                            log.info("WebSocket连接认证成功: {}", username);
                        } catch (Exception e) {
                            log.error("WebSocket连接认证失败: {}", e.getMessage());
                            // 认证失败，可以抛出异常或返回null来拒绝连接
                            return null;
                        }
                    }
                }
            }
            
            Principal principal = accessor.getUser();
            
            if (command != null) {
                switch (command) {
                    case CONNECT:
                        log.info("用户连接: {}", principal != null ? principal.getName() : "anonymous");
                        break;
                    case DISCONNECT:
                        log.info("用户断开连接: {}", principal != null ? principal.getName() : "anonymous");
                        break;
                    case SUBSCRIBE:
                        log.info("用户订阅: {} -> {}", 
                                principal != null ? principal.getName() : "anonymous", 
                                accessor.getDestination());
                        break;
                    case UNSUBSCRIBE:
                        log.info("用户取消订阅: {} -> {}", 
                                principal != null ? principal.getName() : "anonymous", 
                                accessor.getDestination());
                        break;
                    case SEND:
                        log.info("用户发送消息: {} -> {}", 
                                principal != null ? principal.getName() : "anonymous", 
                                accessor.getDestination());
                        break;
                    default:
                        break;
                }
            }
        }
        
        return message;
    }

    /**
     * 发送消息后拦截
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        // 可以在这里处理发送后的逻辑
    }

    /**
     * 发送完成后拦截
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        if (ex != null) {
            log.error("消息发送失败: {}", ex.getMessage());
        }
    }
}