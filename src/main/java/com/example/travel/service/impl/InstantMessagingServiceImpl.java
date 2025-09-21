package com.example.travel.service.impl;

import com.example.travel.dao.*;
import com.example.travel.dto.ChatMessage;
import com.example.travel.entity.*;
import com.example.travel.service.InstantMessagingService;
import com.example.travel.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 即时通信服务实现类
 */
@Service
@RequiredArgsConstructor
public class InstantMessagingServiceImpl implements InstantMessagingService {
    
    private final SimpMessagingTemplate messagingTemplate;
    private final UserDao userDao;
    private final ChatGroupDao chatGroupDao;
    private final GroupMemberDao groupMemberDao;
    private final GroupChatMessageDao groupChatMessageDao;
    private final SingleChatMessageDao singleChatMessageDao;
    private final UserFriendDao userFriendDao;
    private final FriendRequestDao friendRequestDao;
    private final MessageReadStatusDao messageReadStatusDao;
    private final ImageUtils imageUtils;
    
    @Override
    public void handleChatMessage(ChatMessage chatMessage) {
        switch (chatMessage.getType()) {
            case CHAT:
                // 这里需要根据消息内容判断是私聊还是群聊
                // 由于ChatMessage类没有提供足够的信息，这里需要重新设计
                // 或者使用其他方式来区分私聊和群聊消息
                throw new UnsupportedOperationException("Chat message type handling not implemented");
            case JOIN:
            case LEAVE:
            case NOTIFICATION:
            case ORDER_UPDATE:
                // 处理其他类型的消息
                throw new UnsupportedOperationException("Message type " + chatMessage.getType() + " handling not implemented");
            default:
                throw new IllegalArgumentException("Unknown message type: " + chatMessage.getType());
        }
    }
    
    @Override
    @Transactional
    public void sendPrivateMessage(Long senderId, Long receiverId, String content, String messageType ,String image) {
        // 验证接收者是否存在
        User receiver = userDao.findById(receiverId);
        if (receiver == null) {
            throw new IllegalArgumentException("接收者不存在，ID: " + receiverId);
        }
        
        String finalContent = content;
        String finalImage = null; // 默认设为null，避免存储原始base64数据
        
        // 处理图片消息 - 无论通过content还是image参数传递，都只存储处理后的URL
        if ("IMAGE".equals(messageType)) {
            String imageData = null;
            
            // 检查是否通过image参数传递图片
            if (image != null && image.startsWith("data:image")) {
                imageData = image;
            }
            // 检查是否通过content参数传递图片
            else if (content != null && content.startsWith("data:image")) {
                imageData = content;
            }
            
            if (imageData != null) {
                try {
                    String imageUrl = imageUtils.processBase64Image(imageData);
                    finalContent = imageUrl; // 将content设置为图片URL
                    finalImage = imageUrl; // 将image也设置为图片URL
                } catch (Exception e) {
                    throw new RuntimeException("图片处理失败: " + e.getMessage());
                }
            }
        } else {
            // 对于非图片消息，如果image参数包含base64数据，则忽略它
            if (image != null && image.startsWith("data:image")) {
                finalImage = null; // 不存储base64数据到数据库
            } else {
                finalImage = image; // 对于普通文本或其他类型的image参数，正常存储
            }
        }
        
        // 保存消息到数据库
        SingleChatMessage message = new SingleChatMessage();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setContent(finalContent);
        message.setMessageType(messageType);
        message.setSendTime(LocalDateTime.now());
        message.setImage(finalImage);
        message.setStatus(1); // 已发送
        
        singleChatMessageDao.insert(message);
        
        // 通过WebSocket发送消息
        messagingTemplate.convertAndSendToUser(
            receiverId.toString(),
            "/queue/private",
            message
        );
    }
    
    @Override
    @Transactional
    public void sendGroupMessage(Long senderId, Long groupId, String content, String messageType, String image) {
        // 检查用户是否在群组中
        GroupMember member = groupMemberDao.findByGroupIdAndUserId(groupId, senderId);
        if (member == null || member.getStatus() != 1) {
            throw new IllegalArgumentException("User is not a member of this group");
        }
        
        String finalContent = content;
        
        // 处理图片消息 - 无论通过content还是image参数传递，都只存储处理后的URL
        if ("IMAGE".equals(messageType)) {
            String imageData = null;
            
            // 检查是否通过image参数传递图片
            if (image != null && image.startsWith("data:image")) {
                imageData = image;
            }
            // 检查是否通过content参数传递图片
            else if (content != null && content.startsWith("data:image")) {
                imageData = content;
            }
            
            if (imageData != null) {
                try {
                    String imageUrl = imageUtils.processBase64Image(imageData);
                    finalContent = imageUrl; // 将content设置为图片URL
                } catch (Exception e) {
                    throw new RuntimeException("图片处理失败: " + e.getMessage());
                }
            }
        }
        
        // 保存消息到数据库
        GroupChatMessage message = new GroupChatMessage();
        message.setGroupId(groupId);
        message.setSenderId(senderId);
        message.setContent(finalContent);
        message.setMessageType(messageType);
        message.setSendTime(LocalDateTime.now());
        message.setStatus(1); // 正常
        
        groupChatMessageDao.insert(message);
        
        // 通过WebSocket发送消息到群组
        messagingTemplate.convertAndSend("/topic/group/" + groupId, message);
    }
    
    @Override
    @Transactional
    public ChatGroup createGroup(String name, String description, Long creatorId, Integer maxMembers) {
        ChatGroup group = new ChatGroup();
        group.setName(name);
        group.setDescription(description);
        group.setCreatorId(creatorId);
        group.setMaxMembers(maxMembers);
        group.setStatus(1);
        group.setCreatedAt(LocalDateTime.now());
        group.setUpdatedAt(LocalDateTime.now());
        
        chatGroupDao.insert(group);
        
        // 添加创建者为群主
        addGroupMember(group.getId(), creatorId, 2); // 2表示群主
        
        return group;
    }
    
    @Override
    @Transactional
    public void addGroupMember(Long groupId, Long userId, Integer role) {
        GroupMember member = new GroupMember();
        member.setGroupId(groupId);
        member.setUserId(userId);
        member.setRole(role);
        member.setJoinTime(LocalDateTime.now());
        member.setStatus(1);
        
        groupMemberDao.insert(member);
    }
    
    @Override
    @Transactional
    public void removeGroupMember(Long groupId, Long userId) {
        groupMemberDao.deleteByGroupIdAndUserId(groupId, userId);
    }
    
    @Override
    @Transactional
    public void sendFriendRequest(Long senderId, Long receiverId, String message) {
        // 验证接收者是否存在
        User receiver = userDao.findById(receiverId);
        if (receiver == null) {
            throw new IllegalArgumentException("接收者不存在，ID: " + receiverId);
        }
        
        FriendRequest request = new FriendRequest();
        request.setSenderId(senderId);
        request.setReceiverId(receiverId);
        request.setMessage(message);
        request.setStatus(0); // 待处理
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        
        friendRequestDao.insert(request);
        
        // 通知接收方
        messagingTemplate.convertAndSendToUser(
            receiverId.toString(),
            "/queue/friend-request",
            request
        );
    }
    
    @Override
    @Transactional
    public void handleFriendRequest(Long requestId, Integer status) {
        FriendRequest request = friendRequestDao.findById(requestId);
        if (request != null) {
            request.setStatus(status);
            request.setUpdatedAt(LocalDateTime.now());
            friendRequestDao.update(request);
            
            if (status == 1) { // 同意好友申请
                // 检查是否已存在好友关系，避免重复插入
                UserFriend existingFriendship1 = userFriendDao.findByUserIdAndFriendId(
                    request.getSenderId(), request.getReceiverId());
                UserFriend existingFriendship2 = userFriendDao.findByUserIdAndFriendId(
                    request.getReceiverId(), request.getSenderId());
                
                // 建立双向好友关系（如果不存在）
                if (existingFriendship1 == null) {
                    UserFriend friendship1 = new UserFriend();
                    friendship1.setUserId(request.getSenderId());
                    friendship1.setFriendId(request.getReceiverId());
                    friendship1.setRelationStatus(1);
                    friendship1.setCreatedAt(LocalDateTime.now());
                    friendship1.setUpdatedAt(LocalDateTime.now());
                    userFriendDao.insert(friendship1);
                }
                
                if (existingFriendship2 == null) {
                    UserFriend friendship2 = new UserFriend();
                    friendship2.setUserId(request.getReceiverId());
                    friendship2.setFriendId(request.getSenderId());
                    friendship2.setRelationStatus(1);
                    friendship2.setCreatedAt(LocalDateTime.now());
                    friendship2.setUpdatedAt(LocalDateTime.now());
                    userFriendDao.insert(friendship2);
                }
            }
            
            // 通知申请方处理结果
            messagingTemplate.convertAndSendToUser(
                request.getSenderId().toString(),
                "/queue/friend-request-result",
                request
            );
        }
    }
    
    @Override
    public List<User> getFriends(Long userId) {
        return userFriendDao.findFriendsByUserId(userId);
    }
    
    @Override
    public List<ChatGroup> getUserGroups(Long userId) {
        return groupMemberDao.findGroupsByUserId(userId);
    }
    
    @Override
    public List<GroupChatMessage> getGroupMessageHistory(Long groupId, int limit) {
        return groupChatMessageDao.findByGroupIdWithLimit(groupId, limit);
    }
    
    @Override
    public List<SingleChatMessage> getPrivateMessageHistory(Long userId, Long friendId, int limit) {
        return singleChatMessageDao.findByUsers(userId, friendId, limit);
    }
    
    @Override
    @Transactional
    public void markMessageAsRead(Long messageId, Long userId, Long groupId) {
        MessageReadStatus readStatus = new MessageReadStatus();
        readStatus.setMessageId(messageId);
        readStatus.setUserId(userId);
        readStatus.setGroupId(groupId);
        readStatus.setReadTime(LocalDateTime.now());
        
        messageReadStatusDao.insert(readStatus);
        
        // 更新群成员的最后读取消息ID
        groupMemberDao.updateLastReadMessageId(
            groupMemberDao.findByGroupIdAndUserId(groupId, userId).getId(),
            messageId
        );
    }
    
    @Override
    @Transactional
    public void updateUserOnlineStatus(Long userId, Integer onlineStatus, String websocketSessionId, String deviceInfo, String ipAddress) {
        User user = userDao.findById(userId);
        if (user != null) {
            user.setOnlineStatus(onlineStatus);
            user.setWebsocketSessionId(websocketSessionId);
            user.setDeviceInfo(deviceInfo);
            user.setIpAddress(ipAddress);
            user.setLastLoginTime(LocalDateTime.now());
            
            userDao.updateUser(user);
            
            // 广播用户状态变化
            messagingTemplate.convertAndSend("/topic/user-status", user);
        }
    }
    @Override
    @Transactional
    public void sendSingleMessage(SingleChatMessage message) {
        if (message.getSenderId() == null || message.getReceiverId() == null || 
            message.getContent() == null || message.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("消息参数不完整");
        }
        
        // 验证接收者是否存在
        User receiver = userDao.findById(message.getReceiverId());
        if (receiver == null) {
            throw new IllegalArgumentException("接收者不存在，ID: " + message.getReceiverId());
        }
        
        message.setSendTime(LocalDateTime.now());
        message.setStatus(1); // 已发送
        
        // 处理图片消息
        if ("IMAGE".equals(message.getMessageType()) && message.getImage() != null && message.getImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(message.getImage());
                message.setContent(imageUrl);
                message.setImage(imageUrl); // 存储处理后的图片URL
            } catch (Exception e) {
                throw new RuntimeException("图片处理失败: " + e.getMessage());
            }
        } else if (message.getImage() != null && message.getImage().startsWith("data:image")) {
            // 非图片消息但image参数包含base64数据，清空image字段
            message.setImage(null);
        }
        
        singleChatMessageDao.insert(message);
        
        // 发送WebSocket通知
        messagingTemplate.convertAndSendToUser(
            message.getReceiverId().toString(), 
            "/queue/private", 
            message
        );
    }





    @Override
    public List<User> getFriendList(Long userId) {
        return userFriendDao.findFriendsByUserId(userId);
    }

    @Override
    public List<GroupChatMessage> getGroupChatHistory(Long groupId, int limit) {
        return groupChatMessageDao.findByGroupIdWithLimit(groupId, limit);
    }

    @Override
    public List<SingleChatMessage> getSingleChatHistory(Long userId1, Long userId2, int limit) {
        return singleChatMessageDao.findByUsers(userId1, userId2, limit);
    }



    @Override
    public List<FriendRequest> getPendingFriendRequests(Long userId) {
        return friendRequestDao.findByReceiverIdAndStatus(userId, 0);
    }

    @Override
    @Transactional
    public void deleteFriend(Long userId, Long friendId) {
        // 删除双向好友关系
        userFriendDao.deleteByUserIdAndFriendId(userId, friendId);
        userFriendDao.deleteByUserIdAndFriendId(friendId, userId);
    }

    @Override
    public int getUnreadMessageCount(Long userId, Long groupId) {
        if (groupId != null) {
            return groupChatMessageDao.countUnreadMessages(groupId, userId);
        } else {
            return singleChatMessageDao.countUnreadMessages(userId);
        }
    }
}