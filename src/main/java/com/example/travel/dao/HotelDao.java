package com.example.travel.dao;

import com.example.travel.entity.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotelDao {
    //查询所有酒店
    List<Hotel> findAllHotel(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("keyword") String keyword);
    //查询酒店总数
    int countHotels(@Param("keyword") String keyword);
    //新增
    int insertHotel(Hotel hotel);
    //修改
    int updateHotel(Hotel hotel);
    //删除
    boolean exists(@Param("id") Long id);

    int deleteById(@Param("id") Long id);

    Hotel getById(@Param("id") Long id);
}
