package com.example.travel.service;

import com.example.travel.entity.TravelRecommend;

import java.util.List;

public interface TravelRecommendService {
    //查询酒店总数
    List<TravelRecommend> getRecommend(int page, int pageSize, String keyword);
    //查询酒店总数
    int countRecommend(String keyword);
    //新增
    int insertRecommend(TravelRecommend travelRecommend);
    //修改
    int updateRecommend(TravelRecommend travelRecommend);
    //删除
    boolean existById(Long id);
    void deleteRecommend(Long id);
    //根据id查询
    TravelRecommend getById(Long id);
}
