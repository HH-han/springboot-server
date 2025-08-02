package com.example.travel.service;

import com.example.travel.entity.Scenic;

import java.util.List;

public interface ScenicService {
    //获取所有卡片
    //List<Scenic> getAllCards();
    //查询所有景点
    List<Scenic> getScenic(int page, int pageSize, String keyword);
    //查询景点总数
    int countScenic(String keyword);
    //新增
    void addScenic(Scenic scenic);

    boolean existById(Long id);
    //修改
    void updateScenic(Scenic scenic);
    //删除
    void deleteScenic(Long id);
    //根据id查询
    Scenic getById(Long id);
}
