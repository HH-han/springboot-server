package com.example.travel.service;

import com.example.travel.common.Result;
import com.example.travel.entity.TravelWorldcharacteristics;
import org.springframework.stereotype.Service;

@Service
public interface TravelWorldcharacteristicsService {
    //分页查询
    Result listTravelWorld(
            String searchQuery,
            String category,
            int currentPage,
            int pageSize
    );
    //新增
    int addTravelWorld(TravelWorldcharacteristics travelWorldcharacteristics);
    //修改
    int updateTravelWorld(TravelWorldcharacteristics travelWorldcharacteristics);
    //删除
    int deleteTravelWorld(Long id);
    boolean exists(Long id);
    //根据id查询
    TravelWorldcharacteristics getById(Long id);
}