package com.example.travel.dao;

import com.example.travel.entity.LoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 登录信息数据访问接口
 */
@Mapper
public interface LoginInfoDao {
    
    /**
     * 根据用户ID查询登录信息
     * @param userId 用户ID
     * @return 登录信息列表
     */
    List<LoginInfo> findByUserId(@Param("userId") Long userId);
    
    /**
     * 根据用户名查询登录信息
     * @param username 用户名
     * @return 登录信息列表
     */
    List<LoginInfo> findByUsername(@Param("username") String username);
    
    /**
     * 根据状态查询登录信息
     * @param status 登录状态
     * @return 登录信息列表
     */
    List<LoginInfo> findByStatus(@Param("status") String status);
    
    /**
     * 根据时间范围查询登录信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 登录信息列表
     */
    List<LoginInfo> findByTimeRange(@Param("startTime") LocalDateTime startTime, 
                                   @Param("endTime") LocalDateTime endTime);
    
    /**
     * 删除指定用户的登录信息
     * @param userId 用户ID
     * @return 删除记录数
     */
    int deleteByUserId(@Param("userId") Long userId);
    
    /**
     * 删除指定时间之前的登录信息
     * @param beforeTime 时间点
     * @return 删除记录数
     */
    int deleteBeforeTime(@Param("beforeTime") LocalDateTime beforeTime);

    /**
     * 插入登录信息
     * @param loginInfo 登录信息对象
     * @return 插入记录数
     */
    int insert(LoginInfo loginInfo);

    /**
     * 分页查询所有登录信息
     * @param offset 偏移量
     * @param pageSize 每页大小
     * @param keyword 搜索关键词
     * @return 登录信息列表
     */
    List<LoginInfo> findAllLoginInfo(@Param("offset") int offset, 
                                   @Param("pageSize") int pageSize, 
                                   @Param("keyword") String keyword);

    /**
     * 统计登录信息总数
     * @param keyword 搜索关键词
     * @return 总数
     */
    int countLoginInfo(@Param("keyword") String keyword);
}