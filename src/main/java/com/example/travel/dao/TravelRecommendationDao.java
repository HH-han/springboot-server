package com.example.travel.dao;

import com.example.travel.entity.TravelRecommendation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelRecommendationDao {
    
    /**
     * 根据ID查询旅游推荐
     */
    TravelRecommendation findById(@Param("id") Integer id);
    
    /**
     * 查询所有旅游推荐
     */
    List<TravelRecommendation> findAll();
    
    /**
     * 根据月份ID查询旅游推荐
     */
    List<TravelRecommendation> findByMonthId(@Param("monthId") Integer monthId);
    
    /**
     * 根据月份名称查询旅游推荐
     */
    List<TravelRecommendation> findByMonthName(@Param("monthName") String monthName);
    
    /**
     * 新增旅游推荐
     */
    int insert(TravelRecommendation travelRecommendation);
    
    /**
     * 更新旅游推荐
     */
    int update(TravelRecommendation travelRecommendation);
    
    /**
     * 删除旅游推荐
     */
    int delete(@Param("id") Integer id);
    
    /**
     * 统计旅游推荐总数
     */
    int count();
    
    /**
     * 根据月份名称统计旅游推荐数量
     */
    int countByMonthName(@Param("monthName") String monthName);
}