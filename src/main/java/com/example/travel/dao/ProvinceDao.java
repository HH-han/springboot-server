package com.example.travel.dao;


import com.example.travel.entity.Province;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProvinceDao {
    List<Province> findAll();
    Province findById(Integer id);
}