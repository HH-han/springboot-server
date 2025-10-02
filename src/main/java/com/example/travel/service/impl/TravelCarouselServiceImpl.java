package com.example.travel.service.impl;

import com.example.travel.dao.TravelCarouselDao;
import com.example.travel.entity.TravelCarousel;
import com.example.travel.service.TravelCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TravelCarouselServiceImpl implements TravelCarouselService {

    @Autowired
    private TravelCarouselDao travelCarouselDao;

    @Override
    public List<TravelCarousel> getCarousel(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return travelCarouselDao.findAllCarousel(offset, pageSize, keyword);
    }

    @Override
    public int countCarousel(String keyword) {
        return travelCarouselDao.countCarousel(keyword);
    }

    @Override
    public int insertCarousel(TravelCarousel travelRecommend) {
        return travelCarouselDao.insertCarousel(travelRecommend);
    }

    @Override
    public int updateCarousel(TravelCarousel travelRecommend) {
        return travelCarouselDao.updateCarousel(travelRecommend);
    }

    @Override
    public boolean existById(Long id) {
        return travelCarouselDao.exists(id);
    }

    @Override
    public void deleteCarousel(Long id) {
        travelCarouselDao.deleteById(id);

    }

    @Override
    public TravelCarousel getById(Long id) {
        return travelCarouselDao.findById(id);
    }

}
