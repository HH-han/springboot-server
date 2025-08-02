package com.example.travel.service.impl;


import com.example.travel.dao.FoodDao;
import com.example.travel.entity.Food;
import com.example.travel.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodDao foodDao;

    @Override
    public List<Food> searchByNameOrDescription(String keyword) {
        return foodDao.searchByNameOrDescription(keyword);
    }

    @Override
    public Food findById(Integer id) {
        return foodDao.findById(id);
    }

    @Override
    public List<Food> getfood(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return foodDao.findAllfood(offset,pageSize,keyword);
    }

    @Override
    public int countfood(String keyword) {
        return foodDao.countfood(keyword);
    }
    //新增
    @Override
    public int addFood(Food food) {

        return foodDao.insertFood(food);
    }
    //修改
    @Override
    public int updateFood(Food food) {
        return foodDao.updateFood(food);
    }

    @Override
    public void deleteFood(Long id) {
        foodDao.deleteFood(id);
    }

    @Override
    public boolean exists(Long id) {
        return foodDao.exists(id);
    }
    //删除


}

