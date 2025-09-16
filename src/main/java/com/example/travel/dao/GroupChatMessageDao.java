package com.example.travel.dao;

import com.example.travel.entity.GroupChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 群聊消息DAO接口
 */
@Mapper
public interface GroupChatMessageDao {
    
    /**
     * 根据ID查询消息
     */
    GroupChatMessage findById(@Param("id") Long id);
    
    /**
     * 根据群组ID查询消息
     */
    List<GroupChatMessage> findByGroupId(@Param("groupId") Long groupId);
    
    /**
     * 根据群组ID查询消息（分页）
     */
    List<GroupChatMessage> findByGroupIdWithLimit(@Param("groupId") Long groupId, @Param("limit") int limit);
    
    /**
     * 根据发送者ID查询消息
     */
    List<GroupChatMessage> findBySenderId(@Param("senderId") Long senderId);
    
    /**
     * 新增消息
     */
    int insert(GroupChatMessage message);
    
    /**
     * 更新消息状态
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 删除消息
     */
    int delete(@Param("id") Long id);
    
    /**
     * 统计群组消息数量
     */
    int countByGroupId(@Param("groupId") Long groupId);
    
    /**
     * 统计用户未读群消息数量
     */
    int countUnreadMessages(@Param("groupId") Long groupId, @Param("userId") Long userId);
}