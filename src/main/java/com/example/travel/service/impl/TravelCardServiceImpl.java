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
    private TravelCardDao travelCardDao;
    @Override
    public List<TravelCard> getTravelCard(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return travelCardDao.findAllTravelCard(offset, pageSize, keyword);
    }

    @Override
    public int countTravelCard(String keyword) {
        return travelCardDao.countTravelCard(keyword);
    }

    @Override
    public int addTravelCard(TravelCard travelCard) {
        return travelCardDao.insertTravelCard(travelCard);
    }

    @Override
    public int updateTravelCard(TravelCard travelCard) {
        return travelCardDao.updateTravelCard(travelCard);
    }

    @Override
    public boolean existById(Long id) {
        return travelCardDao.exists(id);
    }

    @Override
    public void deleteTravelCard(Long id) {
        travelCardDao.deleteById(id);
    }

    @Override
    public TravelCard getCardById(Long id) {
        return travelCardDao.getCardById(id);
    }

    @Override
    public Object searchByName(String name) {
        return travelCardDao.findByName(name);
    }

    @Override
    public Object searchByKeyword(String keyword) {
        return travelCardDao.findByKeyword(keyword);
    }

    @Override
    public List<TravelCard> searchByCategory(String category) {
        return travelCardDao.findByCategory(category);
    }

    @Override
    public List<TravelCard> searchByBadgeType(String badgeType) {
        return travelCardDao.findByBadgeType(badgeType);
    }
}
