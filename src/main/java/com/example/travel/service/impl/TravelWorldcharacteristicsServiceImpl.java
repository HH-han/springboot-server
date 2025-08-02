package com.example.travel.service.impl;

import com.example.travel.common.Result;
import com.example.travel.dao.TravelWorldcharacteristicsDao;
import com.example.travel.entity.TravelWorldcharacteristics;
import com.example.travel.service.TravelWorldcharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TravelWorldcharacteristicsServiceImpl implements TravelWorldcharacteristicsService {

    @Autowired
    private TravelWorldcharacteristicsDao travelWorldcharacteristicsDao;


    @Override
    public Result listTravelWorld(String searchQuery, String category, int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        List<TravelWorldcharacteristics> travelWorldcharacteristics = travelWorldcharacteristicsDao.findBySearchAndCategory(searchQuery, category, offset, pageSize);
        int total = travelWorldcharacteristicsDao.countBySearchAndCategory(searchQuery, category);
        int totalPages = (int) Math.ceil((double) total / pageSize);

        Map<String, Object> data = new HashMap<>();
        data.put("travelWorldcharacteristics", travelWorldcharacteristics);
        data.put("currentPage", currentPage);
        data.put("totalPages", totalPages);
        data.put("total", total);
        return Result.success(data);
    }

    @Override
    public int addTravelWorld(TravelWorldcharacteristics travelWorldcharacteristics) {
        return travelWorldcharacteristicsDao.insert(travelWorldcharacteristics);
    }

    @Override
    public int updateTravelWorld(TravelWorldcharacteristics travelWorldcharacteristics) {
        return travelWorldcharacteristicsDao.update(travelWorldcharacteristics);
    }

    @Override
    public int deleteTravelWorld(Long id) {
        return travelWorldcharacteristicsDao.delete(id);
    }

    @Override
    public boolean exists(Long id) {
        return travelWorldcharacteristicsDao.exists(id);
    }

    @Override
    public TravelWorldcharacteristics getById(Long id) {
        return travelWorldcharacteristicsDao.findById(id);
    }
}
