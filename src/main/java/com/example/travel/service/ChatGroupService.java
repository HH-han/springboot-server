package com.example.travel.service;

import com.example.travel.entity.ChatGroup;
import java.util.List;

/**
 * 聊天群组服务接口
 */
public interface ChatGroupService {
    
    /**
     * 根据ID查询群组
     */
    ChatGroup findById(Long id);
    
    /**
     * 根据创建者ID查询群组
     */
    List<ChatGroup> findByCreatorId(Long creatorId);
    
    /**
     * 根据名称模糊查询群组
     */
    List<ChatGroup> findByNameLike(String name);
    
    /**
     * 查询所有群组
     */
    List<ChatGroup> findAll();
    
    /**
     * 创建群组
     */
    int createGroup(ChatGroup chatGroup);
    
    /**
     * 更新群组信息
     */
    int updateGroup(ChatGroup chatGroup);
    
    /**
     * 更新群组状态
     */
    int updateStatus(Long id, Integer status);
    
    /**
     * 删除群组
     */
    int deleteGroup(Long id);
    
    /**
     * 统计群组数量
     */
    int count();
}