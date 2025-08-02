package com.example.travel.service;


import com.example.travel.entity.Province;

import java.util.List;

public interface ProvinceService {
    List<Province> findAll();
    Province findById(Integer id);
}