package com.example.travel.controller;

import com.example.travel.dto.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * WebSocket消息控制器
 * 处理实时消息通信
 */
@Controller
@Slf4j
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 处理公共聊天消息
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        log.info("收到公共消息: {}", chatMessage.getContent());
        return chatMessage;
    }

    /**
     * 处理用户加入聊天室
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // 在WebSocket会话中添加用户名
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        log.info("用户 {} 加入聊天室", chatMessage.getSender());
        return chatMessage;
    }

    /**
     * 处理私人消息
     */
    @MessageMapping("/private.message")
    public void sendPrivateMessage(ChatMessage chatMessage, Principal principal) {
        String sender = principal.getName();
        chatMessage.setSender(sender);
        
        log.info("发送私人消息从 {} 到 {}: {}", sender, chatMessage.getReceiver(), chatMessage.getContent());
        
        // 发送给特定用户
        messagingTemplate.convertAndSendToUser(
            chatMessage.getReceiver(),
            "/queue/private",
            chatMessage
        );
    }

    /**
     * 处理系统通知
     */
    @MessageMapping("/system.notification")
    @SendTo("/topic/notifications")
    public ChatMessage sendNotification(ChatMessage chatMessage) {
        log.info("发送系统通知: {}", chatMessage.getContent());
        return chatMessage;
    }

    /**
     * 处理实时订单状态更新
     */
    @MessageMapping("/order.update")
    @SendTo("/topic/orders")
    public ChatMessage updateOrderStatus(ChatMessage chatMessage) {
        log.info("订单状态更新: {}", chatMessage.getContent());
        return chatMessage;
    }
}