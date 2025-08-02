package com.example.travel.controller;

import com.example.travel.dto.CartRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @PostMapping("/cart/add")
    public String addToCart(@RequestBody CartRequestDTO cartRequest) {
        // 实现添加购物车逻辑
        return "商品添加成功";
    }
}