package com.example.travel.dao;

import com.example.travel.entity.FriendRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 好友申请记录DAO接口
 */
@Mapper
public interface FriendRequestDao {
    
    /**
     * 根据ID查询申请记录
     */
    FriendRequest findById(@Param("id") Long id);
    
    /**
     * 根据发送者ID查询申请记录
     */
    List<FriendRequest> findBySenderId(@Param("senderId") Long senderId);
    
    /**
     * 根据接收者ID查询申请记录
     */
    List<FriendRequest> findByReceiverId(@Param("receiverId") Long receiverId);
    
    /**
     * 根据状态查询申请记录
     */
    List<FriendRequest> findByStatus(@Param("status") Integer status);
    
    /**
     * 根据发送者和接收者查询申请记录
     */
    FriendRequest findBySenderIdAndReceiverId(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
    
    /**
     * 新增申请记录
     */
    int insert(FriendRequest friendRequest);
    
    /**
     * 更新申请状态
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 更新申请记录
     */
    int update(FriendRequest friendRequest);
    
    /**
     * 删除申请记录
     */
    int delete(@Param("id") Long id);
    
    /**
     * 统计待处理申请数量
     */
    int countPendingByReceiverId(@Param("receiverId") Long receiverId);
    
    /**
     * 根据接收者ID和状态查询申请记录
     */
    List<FriendRequest> findByReceiverIdAndStatus(@Param("receiverId") Long receiverId, @Param("status") Integer status);
}