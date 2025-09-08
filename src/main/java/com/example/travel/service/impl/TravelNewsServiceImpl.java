package com.example.travel.service.impl;

import com.example.travel.dao.TravelNewsDao;
import com.example.travel.entity.TravelNews;
import com.example.travel.service.TravelNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TravelNewsServiceImpl implements TravelNewsService {

    @Autowired
    private TravelNewsDao travelNewsDao;

    @Override
    public List<TravelNews> findAllTravelNews(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return travelNewsDao.findAllTravelNews(offset, pageSize, keyword);
    }

    @Override
    public int countTravelNews(String keyword) {
        return travelNewsDao.countTravelNews(keyword);
    }

    @Override
    public List<TravelNews> selectAllTravelNews() {
        return travelNewsDao.SelectAllTravelNews();
    }

    @Override
    public TravelNews findById(Long id) {
        return travelNewsDao.findById(id);
    }

    @Override
    public int incrementLikes(Long id) {
        return travelNewsDao.incrementLikes(id);
    }

    @Override
    public int incrementFavorites(Long id) {
        return travelNewsDao.incrementFavorites(id);
    }

    @Override
    public int updateTravelNews(TravelNews travelNews) {
        return travelNewsDao.updateTravelNews(travelNews);
    }

    @Override
    public int insertTravelNews(TravelNews travelNews) {
        return travelNewsDao.insertTravelNews(travelNews);
    }

    @Override
    public int deleteTravelNews(Long id) {
        return travelNewsDao.deleteTravelNews(id);
    }

    @Override
    public boolean exists(Long id) {
        return travelNewsDao.exists(id);
    }
}
