package com.example.travel.interceptor;

import com.example.travel.entity.User;
import com.example.travel.service.UserService;
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
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
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
    private final UserService userService;

    public WebSocketChannelInterceptor(JwtUtils jwtUtils, UserDetailsService userDetailsService, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
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
                
                // 添加对缺失Authorization头的处理
                if (authHeaders == null || authHeaders.isEmpty()) {
                    log.error("WebSocket连接缺少Authorization头");
                    throw new AuthenticationCredentialsNotFoundException("缺少认证信息");
                }
                
                String authHeader = authHeaders.get(0);
                
                // 添加对非Bearer token的处理
                if (authHeader != null && !authHeader.startsWith("Bearer ")) {
                    log.error("WebSocket连接认证格式错误: {}", authHeader);
                    throw new AuthenticationCredentialsNotFoundException("认证格式错误，请使用Bearer token");
                }
                
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
                        // 改为抛出异常，让前端能够收到错误信息
                        throw new AuthenticationCredentialsNotFoundException("WebSocket认证失败: " + e.getMessage());
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
                        String subscribeUsername = principal != null ? principal.getName() : "anonymous";
                        String subscribeUserId = "anonymous".equals(subscribeUsername) ? "anonymous" : getUserIdFromUsername(subscribeUsername);
                        log.info("用户订阅: {} -> {}", 
                                subscribeUserId != null ? subscribeUserId : subscribeUsername, 
                                accessor.getDestination());
                        break;
                    case UNSUBSCRIBE:
                        String unsubscribeUsername = principal != null ? principal.getName() : "anonymous";
                        String unsubscribeUserId = "anonymous".equals(unsubscribeUsername) ? "anonymous" : getUserIdFromUsername(unsubscribeUsername);
                        log.info("用户取消订阅: {} -> {}", 
                                unsubscribeUserId != null ? unsubscribeUserId : unsubscribeUsername, 
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
     * 根据用户名获取用户ID
     * @param username 用户名
     * @return 用户ID，如果用户不存在则返回null
     */
    private String getUserIdFromUsername(String username) {
        try {
            User user = userService.findByUsername(username);
            if (user != null && user.getId() != null) {
                log.info("获取用户ID成功: {} -> {}", username, user.getId());
                return user.getId().toString();
            }

        } catch (Exception e) {
            log.warn("获取用户ID失败: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 发送消息后拦截
     * 用于处理消息发送后的逻辑，如统计发送状态、记录消息日志等
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        
        if (accessor != null) {
            StompCommand command = accessor.getCommand();
            Principal principal = accessor.getUser();
            log.info("postSend: {} -> {}", command, principal != null ? principal.getName() : "anonymous");
            
            // 只处理SEND命令的消息发送后逻辑
            if (command == StompCommand.SEND) {
                String destination = accessor.getDestination();
                String username = principal != null ? principal.getName() : "anonymous";
                
                if (sent) {
                    log.info("消息发送成功: {} -> {}", username, destination);
                    
                    // 消息发送成功的统计逻辑
                    if (destination != null) {
                        // 记录私聊消息发送统计
                        if (destination.startsWith("/queue/")) {
                            log.debug("私聊消息发送成功统计: {} -> {}", username, destination);
                            // 可以在这里添加私聊消息发送计数逻辑
                        }
                        
                        // 记录群聊消息发送统计  
                        if (destination.startsWith("/topic/")) {
                            log.debug("群聊消息发送成功统计: {} -> {}", username, destination);
                            // 可以在这里添加群聊消息发送计数逻辑
                        }
                        
                        // 记录系统通知发送统计
                        if (destination.startsWith("/topic/notifications")) {
                            log.debug("系统通知发送成功: {} -> {}", username, destination);
                        }
                    }
                } else {
                    log.warn("消息发送失败: {} -> {}", username, destination);
                    
                    // 消息发送失败的统计逻辑
                    // 记录发送失败次数，可用于监控系统健康状态
                    if (destination != null && destination.startsWith("/queue/")) {
                        log.error("私聊消息发送失败，可能需要重试: {} -> {}", username, destination);
                    }
                }
            }
        }
    }

    /**
     * 发送完成后拦截
     * 用于处理消息发送完成后的清理工作、性能监控和异常处理
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        
        // 添加null检查，防止NullPointerException
        if (accessor != null) {
            log.info("afterSendCompletion: {} -> {}", accessor.getCommand(), accessor.getUser());
            StompCommand command = accessor.getCommand();
            Principal principal = accessor.getUser();
            
            // 只处理SEND命令的消息发送完成逻辑
            if (command == StompCommand.SEND) {
                String destination = accessor.getDestination();
                String username = principal != null ? principal.getName() : "anonymous";
                
                if (ex != null) {
                    // 记录详细的发送失败信息
                    log.error("消息发送失败: {} -> {}, 错误: {}", username, destination, ex.getMessage());
                    
                    // 可以根据异常类型进行不同的处理
                    if (ex instanceof org.springframework.messaging.MessageDeliveryException) {
                        log.warn("消息投递异常，可能由于接收方未连接: {} -> {}", username, destination);
                    }
                    
                    // 记录发送失败的统计信息，用于监控系统健康状态
                    if (destination != null && destination.startsWith("/queue/")) {
                        log.error("私聊消息发送最终失败，需要人工干预: {} -> {}", username, destination);
                    }
                } else if (sent) {
                    // 消息发送成功后的性能监控
                    long messageSize = message.getPayload().toString().length();
                    log.debug("消息发送完成统计: {} -> {}, 消息大小: {} 字节", 
                            username, destination, messageSize);
                    
                    // 可以在这里添加消息发送成功的性能指标记录
                    if (messageSize > 1024) {
                        log.debug("大消息发送成功: {} -> {}, 大小: {} 字节", 
                                username, destination, messageSize);
                    }
                } else {
                    // 消息发送失败但无异常的情况
                    log.warn("消息发送未完成但无异常: {} -> {}", username, destination);
                }
            }
        }
    }
}