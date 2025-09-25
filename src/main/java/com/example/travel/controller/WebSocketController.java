package com.example.travel.controller;

import com.example.travel.dto.ChatMessage;
import com.example.travel.entity.SingleChatMessage;
import com.example.travel.entity.User;
import com.example.travel.service.InstantMessagingService;
import com.example.travel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final InstantMessagingService instantMessagingService;
    private final UserService userService;

    public WebSocketController(SimpMessagingTemplate messagingTemplate, 
                              InstantMessagingService instantMessagingService,
                              UserService userService) {
        this.messagingTemplate = messagingTemplate;
        this.instantMessagingService = instantMessagingService;
        this.userService = userService;
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
    @SendTo("/user/queue/messages")
    public ChatMessage sendPrivateMessage(ChatMessage chatMessage, Principal principal) {
        String sender = principal.getName();
        chatMessage.setSender(sender);

        log.info("发送私人消息从 {} 到 {}: {}", sender, chatMessage.getReceiver(), chatMessage.getContent());

        log.info("私人消息已转发到通用队列 /user/queue/messages");
        
        // 直接返回消息，由@SendTo注解处理消息转发
        return chatMessage;
    }

    /**
     * 处理单聊消息（前端通过WebSocket发送）
     * 只发送给特定的接收者，而不是广播到公共主题
     */
    @MessageMapping("/chat/single")
    public void handleSingleChatMessage(@Payload(required = false) SingleChatMessage message, Principal principal) {
        // 处理消息为空的情况
        if (message == null) {
            log.warn("收到空消息体，创建默认消息对象");
            message = new SingleChatMessage();
        }
        
        log.info("收到单聊消息: {}", message);

        try {
            // 直接使用前端发送的senderId，确保消息正确保存到数据库
            if (message.getSenderId() == null) {
                // 如果前端没有发送senderId，则从principal获取
                String sender = principal.getName();
                Long senderId = Long.parseLong(sender);
                message.setSenderId(senderId);
            }

            // 确保消息内容不为空
            if (message.getContent() == null || message.getContent().trim().isEmpty()) {
                log.warn("消息内容为空，设置默认内容");
                message.setContent("[空消息]");
            }

            // 使用服务层发送消息（包含保存到数据库和WebSocket发送）
            instantMessagingService.sendSingleMessage(message);

        } catch (Exception e) {
            log.error("处理单聊消息时发生错误: {}", e.getMessage());
            
            // 发生错误时，仍然尝试发送给接收者（不保存到数据库）
            if (message.getReceiverId() != null) {
                try {
                    // 通过用户ID获取接收者的用户名
                    User receiver = userService.getById(message.getReceiverId());
                    if (receiver != null) {
                        String receiverUsername = receiver.getUsername();
                        messagingTemplate.convertAndSendToUser(
                            receiverUsername,
                            "/queue/messages",
                            message
                        );
                        log.info("错误消息已发送给接收者: {} (用户名: {})", message.getReceiverId(), receiverUsername);
                    } else {
                        log.error("未找到接收者用户，用户ID: {}", message.getReceiverId());
                    }
                    
                    // 同时也发送给发送者
                    User sender = userService.getById(message.getSenderId());
                    if (sender != null) {
                        String senderUsername = sender.getUsername();
                        messagingTemplate.convertAndSendToUser(
                            senderUsername,
                            "/queue/messages",
                            message
                        );
                        log.info("错误消息同时也发送给发送者: {} (用户名: {})", message.getSenderId(), senderUsername);
                    } else {
                        log.error("未找到发送者用户，用户ID: {}", message.getSenderId());
                    }
                } catch (Exception ex) {
                    log.error("发送错误消息失败: {}", ex.getMessage());
                }
            }
        }
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

    /**
     * websocket发送图片,采用api上传,然后在socket中发送文件地址即可
     * 用于处理即时通信中的图片消息传输
     */
    @MessageMapping("/chat/image")
    public void handleImageMessage(@Payload(required = false) SingleChatMessage message, Principal principal) {
        // 处理消息为空的情况
        if (message == null) {
            log.warn("收到空图片消息体，创建默认消息对象");
            message = new SingleChatMessage();
        }
        
        log.info("收到图片单聊消息: {}", message);

        try {
            // 直接使用前端发送的senderId，确保消息正确保存到数据库
            if (message.getSenderId() == null) {
                // 如果前端没有发送senderId，则从principal获取
                String sender = principal.getName();
                Long senderId = Long.parseLong(sender);
                message.setSenderId(senderId);
            }

            // 确保消息内容不为空（图片消息内容为图片URL或base64数据）
            if (message.getContent() == null || message.getContent().trim().isEmpty()) {
                log.warn("图片消息内容为空，设置默认内容");
                message.setContent("[图片消息]");
            }

            // 设置消息类型为图片
            if (message.getMessageType() == null) {
                message.setMessageType("IMAGE");
            }

            // 使用服务层发送消息（包含保存到数据库和WebSocket发送）
            instantMessagingService.sendSingleMessage(message);
            
            log.info("图片消息已处理并发送给接收者和发送者，消息内容为: {}", message.getContent());

        } catch (Exception e) {
            log.error("处理图片单聊消息时发生错误: {}", e.getMessage());
            
            // 发生错误时，仍然尝试发送给接收者和发送者（不保存到数据库）
            try {
                // 通过用户ID获取接收者的用户名
                com.example.travel.entity.User receiver = userService.getById(message.getReceiverId());
                if (receiver != null) {
                    String receiverUsername = receiver.getUsername();
                    messagingTemplate.convertAndSendToUser(
                        receiverUsername,
                        "/queue/messages",
                        message
                    );
                    log.info("错误图片消息已发送给接收者: {} (用户名: {})", message.getReceiverId(), receiverUsername);
                }
                
                // 同时也发送给发送者
                com.example.travel.entity.User sender = userService.getById(message.getSenderId());
                if (sender != null) {
                    String senderUsername = sender.getUsername();
                    messagingTemplate.convertAndSendToUser(
                        senderUsername,
                        "/queue/messages",
                        message
                    );
                    log.info("错误图片消息同时也发送给发送者: {} (用户名: {})", message.getSenderId(), senderUsername);
                }
            } catch (Exception ex) {
                log.error("发送错误图片消息失败: {}", ex.getMessage());
            }
        }
    }

    /**
     * 简单的心跳测试端点
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) {
        log.info("收到心跳测试消息: {}", message);
        return "Hello, " + message + "!";
    }
    }