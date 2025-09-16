package com.example.travel.dao;

import com.example.travel.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
        /**
         * 根据用户名查询用户
         * 
         * @param username 用户名
         * @return 用户对象
         */
        User findByUsername(
                        @Param("username") String username);

        User userimage(
                        @Param("username") String username);

        // 查询
        List<User> findAllUsers(
                        @Param("offset") int offset,
                        @Param("pageSize") int pageSize,
                        String keyword);

        // 查询总数
        int countUser(
                        @Param("keyword") String keyword);

        // 查询所有
        List<User> findAll();

        // 新增
        int insertUser(User user);

        // 修改
        int updateUser(User user);

        // 权限修改
        int updatePermissions(
                        @Param("id") Long id,
                        @Param("permissions") int permissions);

        // 修改状态
        int updateStatus(
                        @Param("id") Long id,
                        @Param("status") int status);

        // 删除
        int deleteUser(Long id);;

        User findByPhone(String phone);

        User findByEmail(String email);

        User findById(Long id);

        // 搜索
        List<User> searchUsers(
                        @Param("id") Long id,
                        @Param("username") String username,
                        @Param("nickname") String nickname,
                        @Param("email") String email,
                        @Param("phone") String phone,
                        @Param("userID") Long userID);

        // 即时通信相关方法
        /**
         * 更新用户最后登录时间
         * @param id 用户ID
         * @param lastLoginTime 最后登录时间
         * @return 更新结果
         */
        int updateLastLoginTime(
                        @Param("id") Long id,
                        @Param("lastLoginTime") java.time.LocalDateTime lastLoginTime);

        /**
         * 更新用户在线状态
         * @param id 用户ID
         * @param onlineStatus 在线状态
         * @return 更新结果
         */
        int updateOnlineStatus(
                        @Param("id") Long id,
                        @Param("onlineStatus") Integer onlineStatus);

        /**
         * 更新WebSocket会话ID
         * @param id 用户ID
         * @param websocketSessionId WebSocket会话ID
         * @return 更新结果
         */
        int updateWebsocketSessionId(
                        @Param("id") Long id,
                        @Param("websocketSessionId") String websocketSessionId);

        /**
         * 更新设备信息
         * @param id 用户ID
         * @param deviceInfo 设备信息
         * @return 更新结果
         */
        int updateDeviceInfo(
                        @Param("id") Long id,
                        @Param("deviceInfo") String deviceInfo);

        /**
         * 更新IP地址
         * @param id 用户ID
         * @param ipAddress IP地址
         * @return 更新结果
         */
        int updateIpAddress(
                        @Param("id") Long id,
                        @Param("ipAddress") String ipAddress);

        /**
         * 根据在线状态查询用户
         * @param onlineStatus 在线状态
         * @return 用户列表
         */
        List<User> findByOnlineStatus(@Param("onlineStatus") Integer onlineStatus);

        /**
         * 根据WebSocket会话ID查询用户
         * @param websocketSessionId WebSocket会话ID
         * @return 用户对象
         */
        User findByWebsocketSessionId(@Param("websocketSessionId") String websocketSessionId);
}