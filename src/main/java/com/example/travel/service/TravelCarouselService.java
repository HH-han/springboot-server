package com.example.travel.service;

import com.example.travel.entity.TravelCarousel;

import java.util.List;

public interface TravelCarouselService {
    //查询酒店总数
    List<TravelCarousel> getCarousel(int page, int pageSize, String keyword);
    //查询酒店总数
    int countCarousel(String keyword);
    //新增
    int insertCarousel(TravelCarousel travelRecommend);
    //修改
    int updateCarousel(TravelCarousel travelRecommend);
    //删除
    boolean existById(Long id);
    void deleteCarousel(Long id);
    //根据id查询
    TravelCarousel getById(Long id);
}
