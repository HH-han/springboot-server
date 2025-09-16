package com.example.travel.dao;

import com.example.travel.entity.ChatGroup;
import com.example.travel.entity.GroupMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 群组成员DAO接口
 */
@Mapper
public interface GroupMemberDao {
    
    /**
     * 根据ID查询群组成员
     */
    GroupMember findById(@Param("id") Long id);
    
    /**
     * 根据群组ID查询成员
     */
    List<GroupMember> findByGroupId(@Param("groupId") Long groupId);
    
    /**
     * 根据用户ID查询加入的群组
     */
    List<GroupMember> findByUserId(@Param("userId") Long userId);
    
    /**
     * 查询用户是否在群组中
     */
    GroupMember findByGroupIdAndUserId(@Param("groupId") Long groupId, @Param("userId") Long userId);
    
    /**
     * 新增群组成员
     */
    int insert(GroupMember groupMember);
    
    /**
     * 更新成员角色
     */
    int updateRole(@Param("id") Long id, @Param("role") Integer role);
    
    /**
     * 更新最后读取消息ID
     */
    int updateLastReadMessageId(@Param("id") Long id, @Param("lastReadMessageId") Long lastReadMessageId);
    
    /**
     * 更新成员状态
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 删除群组成员
     */
    int delete(@Param("id") Long id);
    
    /**
     * 统计群组成员数量
     */
    int countByGroupId(@Param("groupId") Long groupId);
    
    /**
     * 根据群组ID和用户ID删除成员
     */
    int deleteByGroupIdAndUserId(@Param("groupId") Long groupId, @Param("userId") Long userId);

    List<ChatGroup> findGroupsByUserId(Long userId);
}