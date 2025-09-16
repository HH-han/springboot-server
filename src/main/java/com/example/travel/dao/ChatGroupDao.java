package com.example.travel.dao;

import com.example.travel.entity.ChatGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 聊天群组DAO接口
 */
@Mapper
public interface ChatGroupDao {
    
    /**
     * 根据ID查询群组
     */
    ChatGroup findById(@Param("id") Long id);
    
    /**
     * 根据创建者ID查询群组
     */
    List<ChatGroup> findByCreatorId(@Param("creatorId") Long creatorId);
    
    /**
     * 根据群组名称模糊查询
     */
    List<ChatGroup> findByNameLike(@Param("name") String name);
    
    /**
     * 查询所有群组
     */
    List<ChatGroup> findAll();
    
    /**
     * 新增群组
     */
    int insert(ChatGroup chatGroup);
    
    /**
     * 更新群组信息
     */
    int update(ChatGroup chatGroup);
    
    /**
     * 更新群组状态
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 删除群组
     */
    int delete(@Param("id") Long id);
    
    /**
     * 统计群组数量
     */
    int count();
}