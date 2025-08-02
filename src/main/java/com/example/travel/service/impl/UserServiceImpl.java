package com.example.travel.service.impl;

import com.example.travel.dao.UserDao;
import com.example.travel.entity.User;
import com.example.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> list() {
        return userDao.findAll();
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
        // 生成18位用户ID
        long userId = Long.parseLong(String.format("%05d%013d",
                Math.abs(System.currentTimeMillis() % 100000),
                Math.abs(new Random().nextInt(999999999))));
        user.setUserId(userId);
        return userDao.insertUser(user);
    }
    //修改
    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int updatePermissions(Long id, int permissions) {
        return userDao.updatePermissions(id, permissions);
    }

    @Override
    public int updateStatus(Long id, int status) {
        return userDao.updateStatus(id, status);
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

    //搜索
    @Override
    public List<User> searchUsers(Long id, String username, String nickname, String email, String phone, Long userID) {
        return userDao.searchUsers(id, username, nickname, email, phone, userID);
    }

    @Override
    public void saveBatch(List<User> users) {
        for (User user : users) {
            userDao.insertUser(user);
        }
    }


}