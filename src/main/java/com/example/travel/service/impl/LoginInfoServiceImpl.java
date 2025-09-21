package com.example.travel.service.impl;

import com.example.travel.dao.LoginInfoDao;
import com.example.travel.entity.LoginInfo;
import com.example.travel.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录信息服务实现类
 */
@Service
public class LoginInfoServiceImpl implements LoginInfoService {
    
    @Autowired
    private LoginInfoDao loginInfoDao;
    
    @Override
    public boolean recordLoginInfo(LoginInfo loginInfo) {
        return loginInfoDao.insert(loginInfo) > 0;
    }
    
    @Override
    public List<LoginInfo> getLoginInfoByUserId(Long userId) {
        return loginInfoDao.findByUserId(userId);
    }
    
    @Override
    public List<LoginInfo> getLoginInfoByUsername(String username) {
        return loginInfoDao.findByUsername(username);
    }
    
    @Override
    public List<LoginInfo> getLoginInfoByStatus(String status) {
        return loginInfoDao.findByStatus(status);
    }
    
    @Override
    public List<LoginInfo> getLoginInfoByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        // 使用自定义SQL查询时间范围
        return loginInfoDao.findByTimeRange(startTime, endTime);
    }
    
    @Override
    public int deleteLoginInfoByUserId(Long userId) {
        return loginInfoDao.deleteByUserId(userId);
    }
    
    @Override
    public int deleteLoginInfoBeforeTime(LocalDateTime beforeTime) {
        // 使用自定义SQL删除指定时间之前的记录
        return loginInfoDao.deleteBeforeTime(beforeTime);
    }
    
    @Override
    public LoginInfo getLastLoginInfo(Long userId) {
        // 使用自定义SQL查询最后一条登录信息
        List<LoginInfo> loginInfos = loginInfoDao.findByUserId(userId);
        if (loginInfos != null && !loginInfos.isEmpty()) {
            return loginInfos.get(0); // 按时间倒序排列，第一条就是最后登录的
        }
        return null;
    }

    @Override
    public Map<String, Object> getAllLoginInfo(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        List<LoginInfo> loginInfos = loginInfoDao.findAllLoginInfo(offset, pageSize, keyword);
        int total = loginInfoDao.countLoginInfo(keyword);
        
        Map<String, Object> result = new HashMap<>();
        result.put("list", loginInfos);
        result.put("total", total);
        return result;
    }
}