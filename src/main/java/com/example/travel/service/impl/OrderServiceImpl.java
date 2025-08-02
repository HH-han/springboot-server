package com.example.travel.service.impl;

import com.example.travel.dao.OrderDao;
import com.example.travel.entity.Order;
import com.example.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getOrders(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        return orderDao.findAllOrder(offset, pageSize, keyword);
    }

    @Override
    public int countOrders(String keyword) {
        return orderDao.countOrders(keyword);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public int addOrder(Order order) {
        return orderDao.insert(order);
    }

    @Override
    @Transactional
    public int updateOrder(Order order) {
        try {
            return orderDao.update(order);
        } catch (Exception e) {
            logger.error("更新订单异常：{}", e.getMessage());
            throw new RuntimeException("更新订单失败", e);
        }
    }

    @Override
    public int deleteOrder(Long id) {
        return orderDao.deleteById(id);
    }
}