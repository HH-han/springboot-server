package com.example.travel.service;

import com.example.travel.common.Result;
import com.example.travel.entity.Destination;
import org.springframework.stereotype.Service;

@Service
public interface DestinationService {
    Result listDestinations(
            String searchQuery,
            String category,
            int currentPage,
            int pageSize
    );
    //新增
    int addDestination(Destination destination);
    //修改
    int updateDestination(Destination destination);
    //删除
    int deleteDestination(Long id);
    boolean exists(Long id);
    //根据id查询
    Destination getById(Long id);
}