package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * WebSocket聊天消息DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    
    /**
     * 消息类型
     */
    private MessageType type;
    
    /**
     * 发送者
     */
    private String sender;
    
    /**
     * 接收者（用于私人消息）
     */
    private String receiver;
    
    /**
     * 消息内容
     */
    private String content;
    
    /**
     * 消息时间
     */
    private LocalDateTime timestamp;
    
    /**
     * 消息类型枚举
     */
    public enum MessageType {
        CHAT,       // 聊天消息
        JOIN,       // 用户加入
        LEAVE,      // 用户离开
        NOTIFICATION, // 系统通知
        ORDER_UPDATE // 订单更新
    }
    
    /**
     * 便捷构造方法
     */
    public ChatMessage(MessageType type, String sender, String content) {
        this.type = type;
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     * 便捷构造方法（包含接收者）
     */
    public ChatMessage(MessageType type, String sender, String receiver, String content) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }
}