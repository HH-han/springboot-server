package com.example.travel.service;

import com.example.travel.entity.LoginInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
/**
 * 登录信息服务接口
 */
public interface LoginInfoService {
    
    /**
     * 记录登录信息
     * @param loginInfo 登录信息实体
     * @return 是否成功
     */
    boolean recordLoginInfo(LoginInfo loginInfo);
    
    /**
     * 根据用户ID查询登录信息
     * @param userId 用户ID
     * @return 登录信息列表
     */
    List<LoginInfo> getLoginInfoByUserId(Long userId);
    
    /**
     * 根据用户名查询登录信息
     * @param username 用户名
     * @return 登录信息列表
     */
    List<LoginInfo> getLoginInfoByUsername(String username);
    
    /**
     * 根据状态查询登录信息
     * @param status 登录状态
     * @return 登录信息列表
     */
    List<LoginInfo> getLoginInfoByStatus(String status);
    
    /**
     * 根据时间范围查询登录信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 登录信息列表
     */
    List<LoginInfo> getLoginInfoByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 删除指定用户的登录信息
     * @param userId 用户ID
     * @return 删除记录数
     */
    int deleteLoginInfoByUserId(Long userId);
    
    /**
     * 删除指定时间之前的登录信息
     * @param beforeTime 时间点
     * @return 删除记录数
     */
    int deleteLoginInfoBeforeTime(LocalDateTime beforeTime);
    
    /**
     * 获取用户最后一次登录信息
     * @param userId 用户ID
     * @return 登录信息
     */
    LoginInfo getLastLoginInfo(Long userId);

    /**
     * 分页查询所有登录信息
     * @param page 页码
     * @param pageSize 每页大小
     * @param keyword 搜索关键词
     * @return 包含登录信息列表和总数的结果
     */
    Map<String, Object> getAllLoginInfo(int page, int pageSize, String keyword);
}