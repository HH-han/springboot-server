package com.example.travel.dao;

import com.example.travel.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OrderDao {
    // 查询所有订单（分页）
    List<Order> findAllOrder(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("keyword") String keyword);

    // 查询订单总数（用于分页）
    int countOrders(@Param("keyword") String keyword);

    // 根据 ID 查询订单
    Order findById(Long id);

    // 新增订单
    int insert(Order order);

    // 更新订单
    int update(Order order);

    // 删除订单
    int deleteById(Long id);
}
