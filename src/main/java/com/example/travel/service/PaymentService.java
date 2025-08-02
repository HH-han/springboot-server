package com.example.travel.service;

import com.example.travel.common.Result;
import com.example.travel.dto.CartRequestDTO;
import com.example.travel.dto.PaymentRequestDTO;
import com.example.travel.entity.Payment;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface PaymentService {
    Result processPayment(PaymentRequestDTO request);
    //修改
    boolean isValidStatusTransition(Payment.PaymentStatus current, Payment.PaymentStatus newStatus);

    Result updatePayment(String orderId, Payment.PaymentStatus status);

    @Transactional
    Result updatePayment(String orderId, Payment.PaymentStatus status, String paymentMethod);

    Result deletePayment(Long id);

    Map<String, Object> getPayments(int page, int pageSize, String keyword, Payment.PaymentStatus status, String username);

    Result addToCart(CartRequestDTO request);

    Map<String, Object> getAllPayments(int page, int pageSize, String keyword);
}