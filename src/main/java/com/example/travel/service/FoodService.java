package com.example.travel.service;

import com.example.travel.entity.Food;

import java.util.List;

public interface FoodService {
    List<Food> searchByNameOrDescription(String keyword);
    Food findById(Integer id);
    //查询没美食总数
    List<Food> getfood(int page, int pageSize, String keyword);
    //查询酒店总数
    int countfood(String keyword);
    //新增
    int addFood(Food food);
    //修改
    int updateFood(Food food);
    //删除
    void deleteFood(Long id);
    boolean exists(Long id);
}