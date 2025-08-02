package com.example.travel.service.impl;

import com.example.travel.common.Result;
import com.example.travel.dao.DestinationDao;
import com.example.travel.entity.Destination;
import com.example.travel.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DestinationServiceImpl implements DestinationService {
    
    private final DestinationDao destinationDao;

    @Autowired
    public DestinationServiceImpl(DestinationDao destinationDao) {
        this.destinationDao = destinationDao;
    }

    @Override
    public Result listDestinations(String searchQuery, String category, int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        List<Destination> destinations = destinationDao.findBySearchAndCategory(searchQuery, category, offset, pageSize);
        int total = destinationDao.countBySearchAndCategory(searchQuery, category);
        int totalPages = (int) Math.ceil((double) total / pageSize);

        Map<String, Object> data = new HashMap<>();
        data.put("destinations", destinations);
        data.put("currentPage", currentPage);
        data.put("totalPages", totalPages);
        data.put("total", total);
        return Result.success(data);
    }
    //新增
    @Override
    public int addDestination(Destination destination) {
        return destinationDao.insertDestination(destination);
    }
    //修改
    @Override
    public int updateDestination(Destination destination) {
        return destinationDao.updateDestination(destination);
    }
    //删除
    @Override
    public int deleteDestination(Long id) {
        return destinationDao.deleteDestination(id);
    }

    @Override
    public boolean exists(Long id) {
        return destinationDao.exists(id);
    }

    @Override
    public Destination getById(Long id) {
        return destinationDao.findById(id);
    }
}