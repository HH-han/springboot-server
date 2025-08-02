package com.example.travel.service;

import com.example.travel.entity.User;
import java.util.List;

public interface UserService {
    /**
     * 批量保存用户
     * @param users 用户列表
     */
    void saveBatch(List<User> users);
    
    /**
     * 获取所有用户列表
     * @return 用户列表
     */
    List<User> list();
    
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户对象
     */
    User findByUsername(String username);
    User userimage(String username);
    //分页查询
    /**
     * 分页查询用户列表
     * @param keyword 搜索关键字
     * @param page 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    List<User> getAllUsers(int page,int pageSize,String keyword);
    //查询总数
    int countUser(String keyword);
    //新增
    /**
     * 新增用户
     * @param user 用户对象
     * @return 是否新增成功
     */
    int addUser(User user);
    //修改
    /**
     * 更新用户信息
     * @param user 用户对象
     * @return 是否更新成功
     */
    int updateUser(User user);
    //权限修改
    /**
     * 修改用户权限
     *
     * @param id
     * @param permissions 权限
     * @return 是否修改成功
     */
    int updatePermissions(Long id, int permissions);
    //修改状态
    /**
     * 修改用户状态
     * @param id 用户ID
     * @param status 状态
     * @return 是否修改成功
     */
    int updateStatus(Long id, int status);
    //删除
    /**
     * 根据ID删除用户
     * @param id 用户ID
     * @return 是否删除成功
     */
    int deleteUser(Long id);

    User findByEmail(String email);
    User findByPhone(String phone);
    
    /**
     * 用户注册
     * @param user 用户对象
     * @return 是否注册成功
     */
    int registerUser(User user);
    
    /**
     * 加密密码
     * @param rawPassword 原始密码
     * @return 加密后的密码
     */
    String encodePassword(String rawPassword);
    
    /**
     * 验证密码
     * @param rawPassword 原始密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    boolean matchesPassword(String rawPassword, String encodedPassword);

    User getById(Long id);

    List<User> searchUsers(Long id, String username, String nickname, String email, String phone, Long userID);
}