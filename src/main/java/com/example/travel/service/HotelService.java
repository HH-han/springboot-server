package com.example.travel.service;

import com.example.travel.entity.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    //查询酒店总数
    List<Hotel> getHotels(int page, int pageSize, String keyword);
    //查询酒店总数
    int countHotels(String keyword);
    //新增
    int addHotel(Hotel hotel);
    //修改
    int updateHotel(Hotel hotel);
    //删除
    boolean existById(Long id);
    void deleteHotel(Long id);

    Hotel getById(Long id);
}
