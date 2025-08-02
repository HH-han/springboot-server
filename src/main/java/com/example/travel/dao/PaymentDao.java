package com.example.travel.dao;


import com.example.travel.dto.PaymentQueryCondition;
import com.example.travel.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface PaymentDao {
    int insertPayment(Payment payment); // 插入支付记录
    void updatePayment(Payment payment); // 更新支付状态

    Payment selectByOrderId(String orderId);

    List<Payment> findAllWithCondition(
            @Param("condition") PaymentQueryCondition condition);
    int countByCondition(
            @Param("condition") PaymentQueryCondition condition);

    int updateStatusWithLock(
            @Param("id") Long id,
            @Param("status") Payment.PaymentStatus status,
            @Param("version") int version);

    List<Payment> findAll(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize,
            @Param("keyword") String keyword,
            @Param("status") Payment.PaymentStatus status,
            @Param("username") String username);
    // 查询订单总数
    List<Payment> findAllOrders(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize,
            @Param("keyword") String keyword);
    int count(
            @Param("keyword") String keyword,
            @Param("status") Payment.PaymentStatus status,
            @Param("username") String username);
    int  countallOrders(
            @Param("keyword") String keyword);

    int deleteById(
            @Param("id") Long id);

    Payment findByOrderId(String orderId);

    long insertCartItem(Payment payment);
    // 查询所有订单



}