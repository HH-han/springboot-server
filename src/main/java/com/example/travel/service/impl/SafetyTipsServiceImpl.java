package com.example.travel.service.impl;

import com.example.travel.dao.SafetyTipsDao;
import com.example.travel.entity.SafetyTips;
import com.example.travel.service.SafetyTipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SafetyTipsServiceImpl implements SafetyTipsService {

    @Autowired
    private SafetyTipsDao safetyTipsDao;


    @Override
    public List<SafetyTips> findAllSafetyTips(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return safetyTipsDao.findAllSafetyTips(offset, pageSize, keyword);
    }

    @Override
    public int countSafety(String keyword) {
        return safetyTipsDao.countSafety(keyword);
    }

    @Override
    public List<SafetyTips> getSafetyTipsPageByCondition(Integer pageNum, Integer pageSize, Map<String, Object> condition) {
        // 设置分页参数
        condition.put("pageNum", pageNum);
        condition.put("pageSize", pageSize);
        condition.put("offset", (pageNum - 1) * pageSize);
        return safetyTipsDao.selectByConditionWithPage(condition);
    }

    @Override
    public SafetyTips getSafetyTipById(Long id) {
        return safetyTipsDao.selectById(id);
    }

    @Override
    @Transactional
    public boolean createSafetyTip(SafetyTips safetyTips) {
        return safetyTipsDao.insert(safetyTips) > 0;
    }

    @Override
    @Transactional
    public boolean batchCreateSafetyTips(List<SafetyTips> safetyTipsList) {
        return safetyTipsDao.batchInsert(safetyTipsList) == safetyTipsList.size();
    }

    @Override
    @Transactional
    public boolean updateSafetyTip(SafetyTips safetyTips) {
        return safetyTipsDao.update(safetyTips) > 0;
    }

    @Override
    @Transactional
    public boolean batchUpdateSafetyTips(List<SafetyTips> safetyTipsList) {
        return safetyTipsDao.batchUpdate(safetyTipsList) == safetyTipsList.size();
    }

    @Override
    @Transactional
    public boolean deleteSafetyTip(Long id) {
        return safetyTipsDao.deleteById(id) > 0;
    }

    @Override
    @Transactional
    public boolean batchDeleteSafetyTips(List<Long> ids) {
        return safetyTipsDao.batchDelete(ids) == ids.size();
    }

    @Override
    public List<SafetyTips> getSafetyTipsByCondition(Map<String, Object> condition) {
        return safetyTipsDao.selectByCondition(condition);
    }

    @Override
    public long countSafetyTips() {
        return safetyTipsDao.count(new SafetyTips());
    }

    @Override
    public long countSafetyTipsByCondition(Map<String, Object> condition) {
        return safetyTipsDao.countByCondition(condition);
    }


}