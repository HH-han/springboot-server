package com.example.travel.dao;

import com.example.travel.entity.TravelCarousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelCarouselDao {
    //查询所有
    List<TravelCarousel> findAllCarousel(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("keyword") String keyword);
    //查询总数
    int countCarousel(@Param("keyword") String keyword);
    //新增
    int insertCarousel(TravelCarousel travelCarousel);
    //修改
    int updateCarousel(TravelCarousel travelCarousel);
    //删除
    boolean exists(@Param("id") Long id);

    int deleteById(@Param("id") Long id);
    //根据id查询
    TravelCarousel findById(@Param("id") Long id);

}
