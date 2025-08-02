package com.example.travel.service.impl;

import com.example.travel.dao.UserDao;
import com.example.travel.entity.User;
import com.example.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User userimage(String username) {
        return userDao.userimage(username);
    }

    @Override
    public List<User> getAllUsers(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return userDao.findAllUsers(offset,pageSize,keyword);
    }

    @Override
    public int countUser(String keyword) {
        return userDao.countUser(keyword);
    }
    //新增
    @Override
    public int addUser(User user) {
        return userDao.insertUser(user);
    }
    //修改
    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
    //删除
    @Override
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }
    // UserServiceImpl.java
    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) {
        return userDao.findByPhone(phone);
    }

    @Override
    public int registerUser(User user) {
        // 密码加密
        user.setPassword(encodePassword(user.getPassword()));
        return userDao.insertUser(user);
    }

    @Override
    public String encodePassword(String rawPassword) {
        // 使用BCrypt加密
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    @Override
    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        // 使用BCrypt验证
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    @Override
    public User getById(Long id) {
        return userDao.findById(id);
    }


}