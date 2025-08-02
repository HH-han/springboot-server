package com.example.travel.service.impl;


import com.example.travel.dao.ProvinceDao;
import com.example.travel.entity.Province;
import com.example.travel.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;

    @Override
    public List<Province> findAll() {
        return provinceDao.findAll();
    }

    @Override
    public Province findById(Integer id) {
        return provinceDao.findById(id);
    }
}