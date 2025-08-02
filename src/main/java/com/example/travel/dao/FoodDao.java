package com.example.travel.dao;

import com.example.travel.entity.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FoodDao {
    List<Food> searchByNameOrDescription(String keyword);
    Food findById(Integer id);
    //查询所有酒店
    List<Food> findAllfood(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("keyword") String keyword);
    //查询酒店总数
    int countfood(@Param("keyword") String keyword);
    //新增
    int insertFood(Food food);
    //修改
    int updateFood(Food food);
    //删除
    int deleteFood(@Param("id") Long id);
    boolean exists(@Param("id") Long id);

}
