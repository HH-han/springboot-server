package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.dto.FriendRequestWithPhoneDTO;
import com.example.travel.entity.*;
import com.example.travel.service.InstantMessagingService;
import com.example.travel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 即时通信控制器
 * 处理单聊、群聊、好友关系等即时通信功能
 */
@CrossOrigin
@RestController
@RequestMapping("/api/public/im")
@RequiredArgsConstructor
public class InstantMessagingController {

    private final InstantMessagingService instantMessagingService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(InstantMessagingController.class);

    /**
     * 发送单聊消息（参数方式）
     */
    @PostMapping("/single/send/param")
    public Result sendSingleMessageParam(@RequestBody Map<String, Object> params) {
        try {
            Long senderId = Long.parseLong(params.get("senderId").toString());
            Long receiverId = Long.parseLong(params.get("receiverId").toString());
            String content = params.get("content").toString();
            String messageType = params.getOrDefault("messageType", "text").toString();
            String image = params.getOrDefault("image", "").toString();

            if (content == null || content.trim().isEmpty()) {
                return Result.error("消息内容不能为空");
            }

            instantMessagingService.sendPrivateMessage(senderId, receiverId, content, messageType ,image);
            logger.info("单聊消息发送成功: {} -> {}", senderId, receiverId);
            return Result.success("消息发送成功");
        } catch (Exception e) {
            logger.error("发送单聊消息失败: {}", e.getMessage(), e);
            return Result.error("发送消息失败");
        }
    }

    /**
     * 发送单聊消息（实体方式）
     */
    @PostMapping("/single/send")
    public Result sendSingleMessage(@RequestBody SingleChatMessage message) {
        try {
            if (message.getSenderId() == null) {
                return Result.error("发送者ID不能为空");
            }
            if (message.getReceiverId() == null) {
                return Result.error("接收者ID不能为空");
            }
            if (message.getContent() == null || message.getContent().trim().isEmpty()) {
                return Result.error("消息内容不能为空");
            }

            instantMessagingService.sendSingleMessage(message);
            logger.info("单聊消息发送成功: {} -> {}", message.getSenderId(), message.getReceiverId());
            return Result.success("消息发送成功", message);
        } catch (Exception e) {
            logger.error("发送单聊消息失败: {}", e.getMessage(), e);
            return Result.error("发送消息失败");
        }
    }

    /**
     * 发送群聊消息（参数方式）
     */
    @PostMapping("/group/send/param")
    public Result sendGroupMessageParam(@RequestBody Map<String, Object> params) {
        try {
            Long groupId = Long.parseLong(params.get("groupId").toString());
            Long senderId = Long.parseLong(params.get("senderId").toString());
            String content = params.get("content").toString();
            String messageType = params.getOrDefault("messageType", "text").toString();

            if (content == null || content.trim().isEmpty()) {
                return Result.error("消息内容不能为空");
            }

            String image = params.containsKey("image") ? params.get("image").toString() : null;
            instantMessagingService.sendGroupMessage(senderId, groupId, content, messageType, image);
            logger.info("群聊消息发送成功: 群组{} -> 用户{}", groupId, senderId);
            return Result.success("消息发送成功");
        } catch (Exception e) {
            logger.error("发送群聊消息失败: {}", e.getMessage(), e);
            return Result.error("发送消息失败");
        }
    }

    /**
     * 发送群聊消息（实体方式）
     */
    @PostMapping("/group/send")
    public Result sendGroupMessage(@RequestBody GroupChatMessage message) {
        try {
            if (message.getGroupId() == null) {
                return Result.error("群组ID不能为空");
            }
            if (message.getSenderId() == null) {
                return Result.error("发送者ID不能为空");
            }
            if (message.getContent() == null || message.getContent().trim().isEmpty()) {
                return Result.error("消息内容不能为空");
            }

            // GroupChatMessage实体没有image字段，无法通过实体方式接收图片参数
            // 请使用参数方式发送群聊图片消息：/im/group/send/param
            String image = null;
            
            instantMessagingService.sendGroupMessage(message.getSenderId(), message.getGroupId(), message.getContent(),
                    message.getMessageType(), image);
            logger.info("群聊消息发送成功: 群组{} -> 用户{}", message.getGroupId(), message.getSenderId());
            return Result.success("消息发送成功", message);
        } catch (Exception e) {
            logger.error("发送群聊消息失败: {}", e.getMessage(), e);
            return Result.error("发送消息失败");
        }
    }

    /**
     * 获取单聊历史消息
     */
    @GetMapping("/single/history")
    public Result getSingleChatHistory(@RequestParam(required = false) Long userId1,
            @RequestParam(required = false) Long userId2,
            @RequestParam(defaultValue = "50") int limit) {
        try {
            if (userId1 == null) {
                return Result.error("用户ID1不能为空");
            }
            if (userId2 == null) {
                return Result.error("用户ID2不能为空");
            }
            List<SingleChatMessage> messages = instantMessagingService.getSingleChatHistory(userId1, userId2, limit);
            return Result.success(messages);
        } catch (Exception e) {
            logger.error("获取单聊历史消息失败: {}", e.getMessage(), e);
            return Result.error("获取历史消息失败");
        }
    }

    /**
     * 获取群聊历史消息
     */
    @GetMapping("/group/history")
    public Result getGroupChatHistory(@RequestParam(required = false) Long groupId,
            @RequestParam(defaultValue = "50") int limit) {
        try {
            if (groupId == null) {
                return Result.error("群组ID不能为空");
            }
            List<GroupChatMessage> messages = instantMessagingService.getGroupChatHistory(groupId, limit);
            return Result.success(messages);
        } catch (Exception e) {
            logger.error("获取群聊历史消息失败: {}", e.getMessage(), e);
            return Result.error("获取历史消息失败");
        }
    }

    /**
     * 发送好友申请（参数方式）
     * 支持通过手机号查找接收者
     */
    @PostMapping("/friend/request/param")
    public Result sendFriendRequestParam(@RequestBody Map<String, Object> params) {
        try {
            Long senderId = Long.parseLong(params.get("senderId").toString());
            String message = params.getOrDefault("message", "").toString();
            
            // 支持通过receiverId或receiverPhone查找接收者
            Long receiverId = null;
            if (params.containsKey("receiverId")) {
                receiverId = Long.parseLong(params.get("receiverId").toString());
            } else if (params.containsKey("receiverPhone")) {
                String receiverPhone = params.get("receiverPhone").toString();
                User receiver = userService.findByPhone(receiverPhone);
                if (receiver == null) {
                    return Result.error("未找到该手机号对应的用户");
                }
                receiverId = receiver.getId().longValue();
            } else {
                return Result.error("请提供接收者ID或手机号");
            }

            instantMessagingService.sendFriendRequest(senderId, receiverId, message);
            logger.info("好友申请发送成功: {} -> {}", senderId, receiverId);
            return Result.success("好友申请发送成功");
        } catch (Exception e) {
            logger.error("发送好友申请失败: {}", e.getMessage(), e);
            return Result.error("发送好友申请失败");
        }
    }

    /**
     * 发送好友申请（实体方式）
     * 支持通过手机号查找接收者
     */
    @PostMapping("/friend/request")
    public Result sendFriendRequest(@RequestBody FriendRequestWithPhoneDTO friendRequest) {
        try {
            if (friendRequest.getSenderId() == null) {
                return Result.error("发送者ID不能为空");
            }
            
            Long receiverId = null;
            if (friendRequest.getReceiverId() != null) {
                receiverId = friendRequest.getReceiverId();
            } else if (friendRequest.getReceiverPhone() != null && !friendRequest.getReceiverPhone().trim().isEmpty()) {
                // 通过手机号查找接收者
                User receiver = userService.findByPhone(friendRequest.getReceiverPhone());
                if (receiver == null) {
                    return Result.error("未找到该手机号对应的用户");
                }
                receiverId = receiver.getId().longValue();
            } else {
                return Result.error("请提供接收者ID或手机号");
            }

            instantMessagingService.sendFriendRequest(friendRequest.getSenderId(), receiverId,
                    friendRequest.getMessage());
            logger.info("好友申请发送成功: {} -> {}", friendRequest.getSenderId(), receiverId);
            return Result.success("好友申请发送成功", friendRequest);
        } catch (Exception e) {
            logger.error("发送好友申请失败: {}", e.getMessage(), e);
            return Result.error("发送好友申请失败");
        }
    }

    /**
     * 处理好友申请
     */
    @PutMapping("/friend/request/handle")
    public Result handleFriendRequest(@RequestBody Map<String, Object> params) {
        try {
            Long requestId = Long.parseLong(params.get("requestId").toString());
            Integer status = Integer.parseInt(params.get("status").toString());

            instantMessagingService.handleFriendRequest(requestId, status);
            logger.info("好友申请处理成功: 申请ID{}", requestId);
            return Result.success("处理成功");
        } catch (Exception e) {
            logger.error("处理好友申请失败: {}", e.getMessage(), e);
            return Result.error("处理好友申请失败");
        }
    }

    /**
     * 接受好友申请
     */
    @PutMapping("/friend/request/accept")
    public Result acceptFriendRequest(@RequestBody Map<String, Object> params) {
        try {
            Long requestId = Long.parseLong(params.get("requestId").toString());

            instantMessagingService.handleFriendRequest(requestId, 1); // 1表示接受
            logger.info("好友申请接受成功: 申请ID{}", requestId);
            return Result.success("好友申请已接受");
        } catch (Exception e) {
            logger.error("接受好友申请失败: {}", e.getMessage(), e);
            return Result.error("接受好友申请失败");
        }
    }

    /**
     * 拒绝好友申请
     */
    @PutMapping("/friend/request/reject")
    public Result rejectFriendRequest(@RequestBody Map<String, Object> params) {
        try {
            Long requestId = Long.parseLong(params.get("requestId").toString());

            instantMessagingService.handleFriendRequest(requestId, 2); // 2表示拒绝
            logger.info("好友申请拒绝成功: 申请ID{}", requestId);
            return Result.success("好友申请已拒绝");
        } catch (Exception e) {
            logger.error("拒绝好友申请失败: {}", e.getMessage(), e);
            return Result.error("拒绝好友申请失败");
        }
    }

    /**
     * 获取用户好友列表
     */
    @GetMapping("/friend/list")
    public Result getFriendList(@RequestParam Long userId) {
        try {
            List<User> friends = instantMessagingService.getFriendList(userId);
            return Result.success(friends);
        } catch (Exception e) {
            logger.error("获取好友列表失败: {}", e.getMessage(), e);
            return Result.error("获取好友列表失败");
        }
    }

    /**
     * 删除好友
     */
    @DeleteMapping("/friend/delete")
    public Result deleteFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        try {
            instantMessagingService.deleteFriend(userId, friendId);
            logger.info("好友删除成功: {} -> {}", userId, friendId);
            return Result.success("好友删除成功");
        } catch (Exception e) {
            logger.error("删除好友失败: {}", e.getMessage(), e);
            return Result.error("删除好友失败");
        }
    }

    /**
     * 获取待处理的好友申请
     */
    @GetMapping("/friend/request/pending")
    public Result getPendingFriendRequests(@RequestParam Long userId) {
        try {
            List<FriendRequest> requests = instantMessagingService.getPendingFriendRequests(userId);
            return Result.success(requests);
        } catch (Exception e) {
            logger.error("获取待处理好友申请失败: {}", e.getMessage(), e);
            return Result.error("获取待处理好友申请失败");
        }
    }

    /**
     * 标记消息为已读
     */
    @PostMapping("/message/read")
    public Result markMessageAsRead(@RequestBody Map<String, Object> params) {
        try {
            Long messageId = Long.parseLong(params.get("messageId").toString());
            Long userId = Long.parseLong(params.get("userId").toString());
            Long groupId = params.get("groupId") != null ? Long.parseLong(params.get("groupId").toString()) : null;

            instantMessagingService.markMessageAsRead(messageId, userId, groupId);
            logger.info("消息标记为已读: 消息ID{}, 用户ID{}", messageId, userId);
            return Result.success("消息已读");
        } catch (Exception e) {
            logger.error("标记消息已读失败: {}", e.getMessage(), e);
            return Result.error("标记消息已读失败");
        }
    }

    /**
     * 获取未读消息数量
     */
    @GetMapping("/message/unread/count")
    public Result getUnreadMessageCount(@RequestParam Long userId,
            @RequestParam(required = false) Long groupId) {
        try {
            int count = instantMessagingService.getUnreadMessageCount(userId, groupId);
            return Result.success(count);
        } catch (Exception e) {
            logger.error("获取未读消息数量失败: {}", e.getMessage(), e);
            return Result.error("获取未读消息数量失败");
        }
    }
}