package com.example.travel.dao;

import com.example.travel.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    User findByUsername(
            @Param("username") String username);
    User userimage(
            @Param("username") String username);
    //查询
    List<User> findAllUsers(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize,
            String keyword);
    //查询总数
    int countUser(
            @Param("keyword") String keyword);
    //查询所有
    List<User> findAll();
    //新增
    int insertUser(User user);
    //修改
    int updateUser(User user);
    //权限修改
    int updatePermissions(
                          @Param("id") Long id,
                          @Param("permissions") int permissions
    );
    //修改状态
    int updateStatus(
            @Param("id") Long id,
            @Param("status") int status
    );
    //删除
    int deleteUser(Long id);;

    User findByPhone(String phone);

    User findByEmail(String email);

    User findById(Long id);

    //搜索
    List<User> searchUsers(
            @Param("id") Long id,
            @Param("username") String username,
            @Param("nickname")String nickname,
            @Param("email")String email,
            @Param("phone")String phone,
            @Param("userID")Long userID);
}