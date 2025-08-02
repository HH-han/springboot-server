package com.example.travel.service.impl;

import com.example.travel.dao.TravelRecommendDao;
import com.example.travel.entity.TravelRecommend;
import com.example.travel.service.TravelRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TravelRecommendServiceImpl implements TravelRecommendService {
    @Autowired
    private TravelRecommendDao travelRecommendDao;

    @Override
    public List<TravelRecommend> getRecommend(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return travelRecommendDao.findAllRecommend(offset, pageSize, keyword);
    }

    @Override
    public int countRecommend(String keyword) {
        return travelRecommendDao.countRecommend(keyword);
    }

    @Override
    public int insertRecommend(TravelRecommend travelRecommend) {
        return travelRecommendDao.insertRecommend(travelRecommend);
    }

    @Override
    public int updateRecommend(TravelRecommend travelRecommend) {
        return travelRecommendDao.updateRecommend(travelRecommend);
    }

    @Override
    public boolean existById(Long id) {
        return travelRecommendDao.exists(id);
    }

    @Override
    public void deleteRecommend(Long id) {
        travelRecommendDao.deleteById(id);
    }

    @Override
    public TravelRecommend getById(Long id) {
        return travelRecommendDao.findById(id);
    }
}
