package com.example.travel.service;


import com.example.travel.entity.Province;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProvinceService {
    List<Province> findAll();
    Province findById(Integer id);
}