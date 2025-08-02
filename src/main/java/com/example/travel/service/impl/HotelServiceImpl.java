package com.example.travel.service.impl;

import com.example.travel.dao.HotelDao;
import com.example.travel.entity.Hotel;
import com.example.travel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelDao hotelDao;
    //查询
    @Override
    public List<Hotel> getHotels(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return hotelDao.findAllHotel(offset, pageSize, keyword);
    }

    @Override
    public int countHotels(String keyword) {
        return hotelDao.countHotels(keyword);
    }
    //新增
    @Override
    public int addHotel(Hotel hotel) {
        hotel.setCreated_at(LocalDateTime.now());
        return hotelDao.insertHotel(hotel);
    }
    //修改
    @Override
    public int updateHotel(Hotel hotel) {
        hotel.setUpdated_at(LocalDateTime.now());
        return hotelDao.updateHotel(hotel);
    }

    //删除
    @Override
    public boolean existById(Long id) {

        return hotelDao.exists(id);
    }

    @Override
    public void deleteHotel(Long id) {

        hotelDao.deleteById(id);
    }

    @Override
    public Hotel getById(Long id) {
        return hotelDao.getById(id);
    }
}
