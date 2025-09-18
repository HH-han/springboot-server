package com.example.travel.service;

import com.example.travel.dto.ChatMessage;
import com.example.travel.entity.*;

import java.util.List;

/**
 * 即时通信服务接口
 */
public interface InstantMessagingService {
    
    /**
     * 处理收到的聊天消息
     */
    void handleChatMessage(ChatMessage chatMessage);
    
    /**
     * 发送单聊消息
     */
    void sendPrivateMessage(Long senderId, Long receiverId, String content, String messageType, String image);
    
    /**
     * 发送单聊消息（实体方式）
     */
    void sendSingleMessage(SingleChatMessage message);
    
    /**
     * 发送群聊消息
     */
    void sendGroupMessage(Long senderId, Long groupId, String content, String messageType, String image);
    

    
    /**
     * 创建群组
     */
    ChatGroup createGroup(String name, String description, Long creatorId, Integer maxMembers);
    
    /**
     * 添加群组成员
     */
    void addGroupMember(Long groupId, Long userId, Integer role);
    
    /**
     * 移除群组成员
     */
    void removeGroupMember(Long groupId, Long userId);
    
    /**
     * 发送好友申请
     */
    void sendFriendRequest(Long senderId, Long receiverId, String message);
    

    
    /**
     * 处理好友申请
     */
    void handleFriendRequest(Long requestId, Integer status);
    
    /**
     * 获取用户的好友列表
     */
    List<User> getFriends(Long userId);
    
    /**
     * 获取好友列表
     */
    List<User> getFriendList(Long userId);
    
    /**
     * 获取用户加入的群组列表
     */
    List<ChatGroup> getUserGroups(Long userId);
    
    /**
     * 获取群组消息历史
     */
    List<GroupChatMessage> getGroupMessageHistory(Long groupId, int limit);
    
    /**
     * 获取群聊历史消息
     */
    List<GroupChatMessage> getGroupChatHistory(Long groupId, int limit);
    
    /**
     * 获取单聊消息历史
     */
    List<SingleChatMessage> getPrivateMessageHistory(Long userId, Long friendId, int limit);
    
    /**
     * 获取单聊历史消息
     */
    List<SingleChatMessage> getSingleChatHistory(Long userId1, Long userId2, int limit);
    
    /**
     * 标记消息为已读
     */
    void markMessageAsRead(Long messageId, Long userId, Long groupId);
    
    /**
     * 获取待处理的好友申请
     */
    List<FriendRequest> getPendingFriendRequests(Long userId);
    
    /**
     * 删除好友
     * @param userId 用户ID
     * @param friendId 好友ID
     */
    void deleteFriend(Long userId, Long friendId);
    
    /**
     * 获取未读消息数量
     */
    int getUnreadMessageCount(Long userId, Long groupId);
    
    /**
     * 更新用户在线状态
     */
    void updateUserOnlineStatus(Long userId, Integer onlineStatus, String websocketSessionId, String deviceInfo, String ipAddress);
}