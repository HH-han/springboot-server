package com.example.travel.dao;

import com.example.travel.entity.SingleChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单聊消息DAO接口
 */
@Mapper
public interface SingleChatMessageDao {
    
    /**
     * 根据ID查询消息
     */
    SingleChatMessage findById(@Param("id") Long id);
    
    /**
     * 根据发送者和接收者查询消息
     */
    List<SingleChatMessage> findByUsers(@Param("userId1") Long userId1, @Param("userId2") Long userId2, @Param("limit") int limit);
    
    /**
     * 根据发送者ID查询消息
     */
    List<SingleChatMessage> findBySenderId(@Param("senderId") Long senderId);
    
    /**
     * 根据接收者ID查询消息
     */
    List<SingleChatMessage> findByReceiverId(@Param("receiverId") Long receiverId);
    
    /**
     * 新增消息
     */
    int insert(SingleChatMessage message);
    
    /**
     * 更新消息状态
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 更新消息阅读时间
     */
    int updateReadTime(@Param("id") Long id, @Param("readTime") java.time.LocalDateTime readTime);
    
    /**
     * 删除消息
     */
    int delete(@Param("id") Long id);
    
    /**
     * 统计用户未读私聊消息数量
     */
    int countUnreadMessages(@Param("userId") Long userId);
}