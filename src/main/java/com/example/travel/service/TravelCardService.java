package com.example.travel.service;

import com.example.travel.entity.Hotel;
import com.example.travel.entity.TravelCard;

import java.util.List;

public interface TravelCardService {
    //查询酒店总数
    List<TravelCard> getTravelCard(int page, int pageSize, String keyword);
    //查询酒店总数
    int countTravelCard(String keyword);
    //新增
    int addTravelCard(TravelCard travelCard);
    //修改
    int updateTravelCard(TravelCard travelCard);
    //删除
    boolean existById(Long id);
    void deleteTravelCard(Long id);
}
