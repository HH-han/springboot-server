package com.example.travel.service.impl;

import com.example.travel.dao.TravelRecommendationDao;
import com.example.travel.entity.TravelRecommendation;
import com.example.travel.service.TravelRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TravelRecommendationServiceImpl implements TravelRecommendationService {

    @Autowired
    private TravelRecommendationDao travelRecommendationDao;

    /**
     * 根据ID查询旅游推荐
     */
    @Override
    public TravelRecommendation getById(Integer id) {
        return travelRecommendationDao.findById(id);
    }

    /**
     * 查询所有旅游推荐
     */
    @Override
    public List<TravelRecommendation> getAll() {
        return travelRecommendationDao.findAll();
    }

    /**
     * 根据月份ID查询旅游推荐
     */
    @Override
    public List<TravelRecommendation> getByMonthId(Integer monthId) {
        return travelRecommendationDao.findByMonthId(monthId);
    }

    /**
     * 根据月份名称查询旅游推荐
     */
    @Override
    public List<TravelRecommendation> getByMonthName(String monthName) {
        return travelRecommendationDao.findByMonthName(monthName);
    }

    /**
     * 分页查询旅游推荐
     */
    @Override
    public List<TravelRecommendation> getRecommendations(int page, int pageSize, String keyword) {
        // 计算偏移量
        int offset = (page - 1) * pageSize;
        
        // 这里需要扩展DAO接口来支持分页和关键词查询
        // 暂时返回所有数据，后续可以扩展DAO接口
        if (keyword != null && !keyword.trim().isEmpty()) {
            // 根据关键词过滤（这里需要扩展DAO接口）
            return travelRecommendationDao.findAll().stream()
                    .filter(recommendation -> 
                            recommendation.getName().contains(keyword) || 
                            (recommendation.getDescription() != null && recommendation.getDescription().contains(keyword)))
                    .skip(offset)
                    .limit(pageSize)
                    .toList();
        } else {
            // 无关键词，直接分页
            return travelRecommendationDao.findAll().stream()
                    .skip(offset)
                    .limit(pageSize)
                    .toList();
        }
    }

    /**
     * 新增旅游推荐
     */
    @Override
    public int add(TravelRecommendation travelRecommendation) {
        // 设置创建时间和更新时间
        if (travelRecommendation.getCreatedAt() == null) {
            travelRecommendation.setCreatedAt(java.time.LocalDateTime.now());
        }
        travelRecommendation.setUpdatedAt(java.time.LocalDateTime.now());
        
        return travelRecommendationDao.insert(travelRecommendation);
    }

    /**
     * 更新旅游推荐
     */
    @Override
    public int update(TravelRecommendation travelRecommendation) {
        // 设置更新时间
        travelRecommendation.setUpdatedAt(java.time.LocalDateTime.now());
        
        return travelRecommendationDao.update(travelRecommendation);
    }

    /**
     * 删除旅游推荐
     */
    @Override
    public int delete(Integer id) {
        return travelRecommendationDao.delete(id);
    }

    /**
     * 统计旅游推荐总数
     */
    @Override
    public int count() {
        return travelRecommendationDao.count();
    }

    /**
     * 根据关键词统计旅游推荐数量
     */
    @Override
    public int countByKeyword(String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return (int) travelRecommendationDao.findAll().stream()
                    .filter(recommendation -> 
                            recommendation.getName().contains(keyword) || 
                            (recommendation.getDescription() != null && recommendation.getDescription().contains(keyword)))
                    .count();
        } else {
            return travelRecommendationDao.count();
        }
    }

    /**
     * 根据月份名称统计旅游推荐数量
     */
    @Override
    public int countByMonthName(String monthName) {
        return travelRecommendationDao.countByMonthName(monthName);
    }

    /**
     * 检查旅游推荐是否存在
     */
    @Override
    public boolean exists(Integer id) {
        return travelRecommendationDao.findById(id) != null;
    }

    /**
     * 获取所有月份的推荐数据（包含月份信息）
     */
    @Override
    public List<TravelRecommendation> getMonthlyRecommendations() {
        return travelRecommendationDao.findAll();
    }

    /**
     * 根据特定月份获取推荐数据
     */
    @Override
    public List<TravelRecommendation> getRecommendationsByMonth(String monthName) {
        return travelRecommendationDao.findByMonthName(monthName);
    }

    /**
     * 批量添加旅游推荐
     */
    public int batchAdd(List<TravelRecommendation> recommendations) {
        int successCount = 0;
        for (TravelRecommendation recommendation : recommendations) {
            try {
                successCount += add(recommendation);
            } catch (Exception e) {
                // 记录错误日志，继续处理其他数据
                System.err.println("添加旅游推荐失败: " + e.getMessage());
            }
        }
        return successCount;
    }

    /**
     * 根据标签搜索旅游推荐
     */
    public List<TravelRecommendation> searchByTag(String tag) {
        return travelRecommendationDao.findAll().stream()
                .filter(recommendation -> 
                        recommendation.getTags() != null && 
                        recommendation.getTags().contains(tag))
                .toList();
    }

    /**
     * 获取热门旅游推荐（按创建时间排序）
     */
    public List<TravelRecommendation> getHotRecommendations(int limit) {
        return travelRecommendationDao.findAll().stream()
                .sorted((r1, r2) -> r2.getCreatedAt().compareTo(r1.getCreatedAt()))
                .limit(limit)
                .toList();
    }
}