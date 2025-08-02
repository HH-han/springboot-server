package com.example.travel.dao;

import com.example.travel.entity.SafetyTips;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SafetyTipsDao {
    /**
     * 分页查询
     */
    List<SafetyTips> findAllSafetyTips(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize,
            String keyword);
    int countSafety(
            @Param("keyword") String keyword
    );
    /**
     * 带条件的分页查询
     */
    List<SafetyTips> selectByConditionWithPage(Map<String, Object> params);
    /**
     * 根据ID查询安全提示
     */
    SafetyTips selectById(Long id);

    /**
     * 新增安全提示
     */
    int insert(SafetyTips safetyTips);


    /**
     * 更新安全提示
     */
    int update(SafetyTips safetyTips);

    /**
     * 批量更新安全提示
     */
    int batchUpdate(List<SafetyTips> list);

    /**
     * 删除安全提示
     */
    int deleteById(Long id);

    /**
     * 批量删除安全提示
     */
    int batchDelete(List<Long> ids);

    /**
     * 条件查询安全提示
     */
    List<SafetyTips> selectByCondition(Map<String, Object> params);

    /**
     * 统计安全提示数量
     */
    long count(SafetyTips safetyTips);

    /**
     * 条件统计安全提示数量
     */
    long countByCondition(Map<String, Object> params);

    int batchInsert(List<SafetyTips> safetyTipsList);


}