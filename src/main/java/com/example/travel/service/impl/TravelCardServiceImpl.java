package com.example.travel.service.impl;

import com.example.travel.dao.TravelCardDao;
import com.example.travel.entity.TravelCard;
import com.example.travel.service.TravelCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TravelCardServiceImpl implements TravelCardService {
    @Autowired
    private TravelCardDao TravelCardDao;
    @Override
    public List<TravelCard> getTravelCard(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return TravelCardDao.findAllTravelCard(offset, pageSize, keyword);
    }

    @Override
    public int countTravelCard(String keyword) {
        return TravelCardDao.countTravelCard(keyword);
    }

    @Override
    public int addTravelCard(TravelCard travelCard) {
        return 0;
    }

    @Override
    public int updateTravelCard(TravelCard travelCard) {
        return 0;
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public void deleteTravelCard(Long id) {

    }
}
