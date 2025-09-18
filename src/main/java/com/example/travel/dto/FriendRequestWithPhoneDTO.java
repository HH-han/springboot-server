package com.example.travel.dto;

import lombok.Data;

/**
 * 支持手机号查找的好友申请DTO
 * 用于前端通过手机号发送好友申请
 */
@Data
public class FriendRequestWithPhoneDTO {
    
    /**
     * 发送者ID
     */
    private Long senderId;
    
    /**
     * 接收者ID（可选，与receiverPhone二选一）
     */
    private Long receiverId;
    
    /**
     * 接收者手机号（可选，与receiverId二选一）
     */
    private String receiverPhone;
    
    /**
     * 申请消息
     */
    private String message;
}