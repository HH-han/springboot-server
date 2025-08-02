package com.example.travel.dao;

import com.example.travel.entity.TravelCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelCardDao {
    //查询所有酒店
    List<TravelCard> findAllTravelCard(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("keyword") String keyword);
    //查询酒店总数
    int countTravelCard(@Param("keyword") String keyword);
    //新增
    int insertTravelCard(TravelCard travelCard);
    //修改
    int updateTravelCard(TravelCard travelCard);
    //删除
    boolean exists(@Param("id") Long id);

    int deleteById(@Param("id") Long id);
}
