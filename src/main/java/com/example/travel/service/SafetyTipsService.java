package com.example.travel.service;

import com.example.travel.entity.SafetyTips;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface SafetyTipsService {
    /**
     * 分页查询安全提示
     */
    List<SafetyTips> findAllSafetyTips(int page,int pageSize,String keyword);
    int countSafety(String keyword);
    /**
     * 条件分页查询
     */
    List<SafetyTips> getSafetyTipsPageByCondition(Integer pageNum, Integer pageSize, Map<String, Object> condition);

    /**
     * 根据ID获取安全提示
     */
    SafetyTips getSafetyTipById(Long id);

    /**
     * 新增安全提示
     */
    boolean createSafetyTip(SafetyTips safetyTips);

    /**
     * 批量新增安全提示
     */
    boolean batchCreateSafetyTips(List<SafetyTips> safetyTipsList);

    /**
     * 更新安全提示
     */
    boolean updateSafetyTip(SafetyTips safetyTips);

    /**
     * 批量更新安全提示
     */
    boolean batchUpdateSafetyTips(List<SafetyTips> safetyTipsList);

    /**
     * 删除安全提示
     */
    boolean deleteSafetyTip(Long id);

    /**
     * 批量删除安全提示
     */
    boolean batchDeleteSafetyTips(List<Long> ids);

    /**
     * 条件查询
     */
    List<SafetyTips> getSafetyTipsByCondition(Map<String, Object> condition);

    /**
     * 统计总数
     */
    long countSafetyTips();

    /**
     * 条件统计
     */
    long countSafetyTipsByCondition(Map<String, Object> condition);


}