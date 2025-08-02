package com.example.travel.service;

import com.example.travel.entity.Order;
import java.util.List;

public interface OrderService {
    // 分页查询订单
    List<Order> getOrders(int page, int pageSize, String keyword);

    // 查询订单总数
    int countOrders(String keyword);

    // 根据 ID 查询订单
    Order getOrderById(Long id);

    // 新增订单
    int addOrder(Order order);

    // 更新订单
    int updateOrder(Order order);

    // 删除订单
    int deleteOrder(Long id);
}