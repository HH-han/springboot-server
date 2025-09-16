package com.example.travel.dao;

import com.example.travel.entity.MessageReadStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消息已读状态DAO接口
 */
@Mapper
public interface MessageReadStatusDao {
    
    /**
     * 根据ID查询已读状态
     */
    MessageReadStatus findById(@Param("id") Long id);
    
    /**
     * 根据消息ID查询已读用户
     */
    List<MessageReadStatus> findByMessageId(@Param("messageId") Long messageId);
    
    /**
     * 根据用户ID查询已读消息
     */
    List<MessageReadStatus> findByUserId(@Param("userId") Long userId);
    
    /**
     * 根据群组ID查询已读状态
     */
    List<MessageReadStatus> findByGroupId(@Param("groupId") Long groupId);
    
    /**
     * 查询消息的已读用户数量
     */
    int countByMessageId(@Param("messageId") Long messageId);
    
    /**
     * 查询用户是否已读某条消息
     */
    MessageReadStatus findByMessageIdAndUserId(@Param("messageId") Long messageId, @Param("userId") Long userId);
    
    /**
     * 新增已读状态
     */
    int insert(MessageReadStatus messageReadStatus);
    
    /**
     * 删除已读状态
     */
    int delete(@Param("id") Long id);
    
    /**
     * 根据消息ID删除已读状态
     */
    int deleteByMessageId(@Param("messageId") Long messageId);
    
    /**
     * 根据用户ID删除已读状态
     */
    int deleteByUserId(@Param("userId") Long userId);
}