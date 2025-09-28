package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.dto.FriendRequestWithPhoneDTO;
import com.example.travel.entity.*;
import com.example.travel.service.ChatEmojiService;
import com.example.travel.service.EmojiImageService;
import com.example.travel.service.InstantMessagingService;
import com.example.travel.service.UserService;
import com.example.travel.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final ChatEmojiService chatEmojiService;
    private final ImageUtils imageUtils;
    private final SimpMessagingTemplate messagingTemplate;
    private final EmojiImageService emojiImageService;
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
    public Result getFriendList(@RequestParam(required = false) Long userId) {
        try {
            if (userId == null) {
                return Result.error("用户ID不能为空");
            }
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
    public Result deleteFriend(@RequestBody Map<String, Object> params) {
        try {
            Long userId = params.containsKey("userId") ? Long.parseLong(params.get("userId").toString()) : null;
            Long friendId = params.containsKey("friendId") ? Long.parseLong(params.get("friendId").toString()) : null;
            
            if (userId == null) {
                return Result.error("用户ID不能为空");
            }
            if (friendId == null) {
                return Result.error("好友ID不能为空");
            }
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
    public Result getPendingFriendRequests(@RequestParam(required = false) Long userId) {
        try {
            if (userId == null) {
                return Result.error("用户ID不能为空");
            }
            List<FriendRequest> requests = instantMessagingService.getPendingFriendRequests(userId);
            return Result.success(requests);
        } catch (Exception e) {
            logger.error("获取待处理好友申请失败: {}", e.getMessage(), e);
            return Result.error("获取待处理好友申请失败");
        }
    }

    /**
     * 获取未读消息数量
     */
    @GetMapping("/message/unread/count")
    public Result getUnreadMessageCount(@RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long groupId) {
        try {
            if (userId == null) {
                return Result.error("用户ID不能为空");
            }
            int count = instantMessagingService.getUnreadMessageCount(userId, groupId);
            return Result.success(count);
        } catch (Exception e) {
            logger.error("获取未读消息数量失败: {}", e.getMessage(), e);
            return Result.error("获取未读消息数量失败");
        }
    }

    /**
     * 处理单聊图片消息（Base64格式）
     * 接收Base64图片数据，保存到服务器并返回图片URL，然后通过WebSocket发送
     */
    @PostMapping("/single/process/image")
    public Result processSingleImage(@RequestBody Map<String, Object> params) {
        try {
            Long senderId = Long.parseLong(params.get("senderId").toString());
            Long receiverId = Long.parseLong(params.get("receiverId").toString());
            String imageData = params.get("image").toString();
            
            if (senderId == null) {
                return Result.error("发送者ID不能为空");
            }
            
            if (receiverId == null) {
                return Result.error("接收者ID不能为空");
            }
            
            if (imageData == null || imageData.trim().isEmpty()) {
                return Result.error("图片数据不能为空");
            }

            // 使用ImageUtils处理Base64图片数据
            String imageUrl = imageUtils.processBase64Image(imageData);
            
            // 创建图片消息并保存到数据库
            SingleChatMessage message = new SingleChatMessage();
            message.setSenderId(senderId);
            message.setReceiverId(receiverId);
            message.setContent("[图片]");
            message.setMessageType("IMAGE");
            message.setImage(imageUrl);
            
            instantMessagingService.sendSingleMessage(message);
            
            // 通过WebSocket发送图片消息给接收方 - 使用用户名而不是用户ID进行路由
            com.example.travel.entity.User receiver = userService.getById(receiverId);
            if (receiver != null) {
                messagingTemplate.convertAndSendToUser(
                    receiver.getUsername(), 
                    "/queue/messages", 
                    message
                );
                
                // 同时也发送给发送者，确保发送者能看到自己的消息
                com.example.travel.entity.User sender = userService.getById(senderId);
                if (sender != null) {
                    messagingTemplate.convertAndSendToUser(
                        sender.getUsername(), 
                        "/queue/messages", 
                        message
                    );
                }
            }
            
            logger.info("单聊图片处理成功: {} -> {}, 图片URL: {}", senderId, receiverId, imageUrl);
            return Result.success("图片处理成功", imageUrl);
            
        } catch (Exception e) {
            logger.error("单聊图片处理失败: {}", e.getMessage(), e);
            return Result.error("图片处理失败: " + e.getMessage());
        }
    }

    /**
     * 处理群聊图片消息（Base64格式）
     * 接收Base64图片数据，保存到服务器并返回图片URL，然后通过WebSocket发送
     */
    @PostMapping("/group/process/image")
    public Result processGroupImage(@RequestBody Map<String, Object> params) {
        try {
            Long senderId = Long.parseLong(params.get("senderId").toString());
            Long groupId = Long.parseLong(params.get("groupId").toString());
            String imageData = params.get("image").toString();
            
            if (senderId == null) {
                return Result.error("发送者ID不能为空");
            }
            
            if (groupId == null) {
                return Result.error("群组ID不能为空");
            }
            
            if (imageData == null || imageData.trim().isEmpty()) {
                return Result.error("图片数据不能为空");
            }

            // 使用ImageUtils处理Base64图片数据
            String imageUrl = imageUtils.processBase64Image(imageData);
            
            // 创建群聊图片消息并保存到数据库
            GroupChatMessage message = instantMessagingService.sendGroupMessage(senderId, groupId, "[图片]", "IMAGE", imageUrl);
            
            // 通过WebSocket发送图片消息给群组所有成员
            messagingTemplate.convertAndSend(
                "/topic/group/" + groupId, 
                message
            );
            
            logger.info("群聊图片处理成功: 用户{} -> 群组{}, 图片URL: {}", senderId, groupId, imageUrl);
            return Result.success("图片处理成功", imageUrl);
            
        } catch (Exception e) {
            logger.error("群聊图片处理失败: {}", e.getMessage(), e);
            return Result.error("图片处理失败: " + e.getMessage());
        }
    }

    /**
     * 获取所有emoji表情
     */
    @GetMapping("/emoji/all")
    public Result getAllEmojis() {
        try {
            List<ChatEmoji> emojis = chatEmojiService.findAll();
            logger.info("获取所有emoji表情成功，共{}个", emojis.size());
            return Result.success(emojis);
        } catch (Exception e) {
            logger.error("获取emoji表情失败: {}", e.getMessage(), e);
            return Result.error("获取表情失败");
        }
    }
    /**
     * 获取所有emoji_image表情
     */
    @GetMapping("/emoji_image/all")
    public Result getAllEmojis_image() {
        try {
            List<EmojiImage> emojiImages = emojiImageService.findAll();
            logger.info("获取所有emoji_image表情成功，共{}个", emojiImages.size());
            return Result.success(emojiImages);
        }catch (Exception e) {
            logger.error("获取emoji_image表情失败: {}", e.getMessage(), e);
            return Result.error("获取表情失败");
        }
    }
    /**
     * 新增emoji_image表情
     */
    @PostMapping("/emoji_image/add")
    public Result addEmojiImage(@RequestBody EmojiImage emojiImage) {
        try {
            int result = emojiImageService.insert(emojiImage);
            if (result > 0) {
                logger.info("新增emoji_image表情成功: {}", emojiImage);
                return Result.success("新增emoji_image表情成功");
                }else {
                    logger.info("新增emoji_image表情失败: {}", emojiImage);
                    return Result.error("新增emoji_image表情失败");
            }
        }catch (Exception e) {
            logger.error("新增emoji_image表情失败: {}", e.getMessage(), e);
            return Result.error("新增emoji_image表情失败");
        }
    }


}