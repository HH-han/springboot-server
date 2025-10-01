package com.example.travel.service;

import com.example.travel.entity.TravelRecommendation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TravelRecommendationService {
    
    /**
     * 根据ID查询旅游推荐
     */
    TravelRecommendation getById(Integer id);
    
    /**
     * 查询所有旅游推荐
     */
    List<TravelRecommendation> getAll();
    
    /**
     * 根据月份ID查询旅游推荐
     */
    List<TravelRecommendation> getByMonthId(Integer monthId);
    
    /**
     * 根据月份名称查询旅游推荐
     */
    List<TravelRecommendation> getByMonthName(String monthName);
    
    /**
     * 分页查询旅游推荐
     */
    List<TravelRecommendation> getRecommendations(int page, int pageSize, String keyword);
    
    /**
     * 新增旅游推荐
     */
    int add(TravelRecommendation travelRecommendation);
    
    /**
     * 更新旅游推荐
     */
    int update(TravelRecommendation travelRecommendation);
    
    /**
     * 删除旅游推荐
     */
    int delete(Integer id);
    
    /**
     * 统计旅游推荐总数
     */
    int count();
    
    /**
     * 根据关键词统计旅游推荐数量
     */
    int countByKeyword(String keyword);
    
    /**
     * 根据月份名称统计旅游推荐数量
     */
    int countByMonthName(String monthName);
    
    /**
     * 检查旅游推荐是否存在
     */
    boolean exists(Integer id);
    
    /**
     * 获取所有月份的推荐数据（包含月份信息）
     */
    List<TravelRecommendation> getMonthlyRecommendations();
    
    /**
     * 根据特定月份获取推荐数据
     */
    List<TravelRecommendation> getRecommendationsByMonth(String monthName);
}