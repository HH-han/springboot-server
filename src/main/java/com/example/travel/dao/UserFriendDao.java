package com.example.travel.dao;

import com.example.travel.entity.User;
import com.example.travel.entity.UserFriend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户好友关系DAO接口
 */
@Mapper
public interface UserFriendDao {
    
    /**
     * 根据ID查询好友关系
     */
    UserFriend findById(@Param("id") Long id);
    
    /**
     * 根据用户ID和好友ID查询关系
     */
    UserFriend findByUserIdAndFriendId(@Param("userId") Long userId, @Param("friendId") Long friendId);
    
    /**
     * 根据用户ID查询好友列表
     */
    List<User> findFriendsByUserId(@Param("userId") Long userId);
    
    /**
     * 根据用户ID查询好友关系
     */
    List<UserFriend> findByUserId(@Param("userId") Long userId);
    
    /**
     * 根据关系状态查询
     */
    List<UserFriend> findByRelationStatus(@Param("relationStatus") Integer relationStatus);
    
    /**
     * 新增好友关系
     */
    int insert(UserFriend userFriend);
    
    /**
     * 更新好友关系状态
     */
    int updateRelationStatus(@Param("id") Long id, @Param("relationStatus") Integer relationStatus);
    
    /**
     * 更新好友备注
     */
    int updateRemark(@Param("id") Long id, @Param("remark") String remark);
    
    /**
     * 删除好友关系
     */
    int delete(@Param("id") Long id);
    
    /**
     * 根据用户ID和好友ID删除关系
     */
    int deleteByUserIdAndFriendId(@Param("userId") Long userId, @Param("friendId") Long friendId);
    
    /**
     * 统计用户好友数量
     */
    int countByUserId(@Param("userId") Long userId);
}