package com.example.travel.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * WebSocket通道拦截器
 * 用于处理连接、断开等事件
 */
@Component
@Slf4j
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    /**
     * 发送消息前拦截
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        
        if (accessor != null) {
            StompCommand command = accessor.getCommand();
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