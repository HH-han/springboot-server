package com.example.travel.controller;


import com.example.travel.common.Result;
import com.example.travel.entity.Province;
import com.example.travel.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/public/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public Result findAll() {
        List<Province> provinces = provinceService.findAll();
        return Result.success(provinces);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Province province = provinceService.findById(id);
        return Result.success(province);
    }
}