package com.example.travel.service.impl;
import com.example.travel.dao.PaymentDao;
import com.example.travel.dto.CartRequestDTO;
import com.example.travel.entity.Payment;
import com.example.travel.service.PaymentService;
import com.example.travel.common.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import com.example.travel.dto.PaymentRequestDTO;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    
    private final PaymentDao paymentDao;
    private static final Random random = new Random();

    /**
     * 处理支付请求，生成订单号并模拟支付网关调用。
     * 根据支付结果更新支付状态并保存到数据库。
     *
     * @param request 支付请求DTO，包含支付相关信息
     * @return 支付结果，包含订单号和支付状态
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public Result processPayment(PaymentRequestDTO request) {
        try {
            String orderId = generateOrderId();

            // 模拟支付网关调用，80%的成功率
            boolean paymentSuccess = simulatePaymentGateway();

            Payment payment = new Payment();
            payment.setOrderId(orderId);
            payment.setUsername(request.getUsername());
            payment.setQuantity(request.getQuantity());
            payment.setItemId(request.getItemId());
            payment.setItemName(request.getItemName());
            payment.setAmount(request.getAmount());
            payment.setPaymentMethod(request.getPaymentMethod());
            payment.setStatus(paymentSuccess ? Payment.PaymentStatus.SUCCESS : Payment.PaymentStatus.FAILED);
            payment.setCreatedAt(LocalDateTime.now());

            paymentDao.insertPayment(payment);

            return paymentSuccess ?
                Result.success("支付成功", new PaymentResult(orderId, payment.getStatus())) :
                Result.error("支付失败");
        } catch (Exception e) {
            log.error("支付失败，订单号：{}，金额：{}元，支付方式：{}", request.getOrderId(), request.getAmount(), request.getPaymentMethod(), e);
            return Result.error(e.getMessage() != null ? e.getMessage() : "支付网关处理失败");
        }
    }

    /**
     * 验证支付状态转换是否合法。
     *
     * @param current 当前支付状态
     * @param newStatus 新的支付状态
     * @return 如果状态转换合法返回true，否则返回false
     */
    @Override
    public boolean isValidStatusTransition(Payment.PaymentStatus current, Payment.PaymentStatus newStatus) {
        if (current == null) return newStatus == Payment.PaymentStatus.PENDING;

        return switch (current) {
            case PENDING -> newStatus == Payment.PaymentStatus.SUCCESS || newStatus == Payment.PaymentStatus.FAILED;
            case SUCCESS -> newStatus == Payment.PaymentStatus.COMPLETED;
            case FAILED, COMPLETED -> false;
            default -> false;
        };
    }

    /**
     * 更新支付状态。
     *
     * @param orderId 订单号
     * @param status 新的支付状态
     * @return 更新结果，包含成功或失败信息
     */
    @Override
    @Transactional
    public Result updatePayment(String orderId, Payment.PaymentStatus status) {
        return updatePayment(orderId, status, null);
    }

    @Override
    @Transactional
    public Result updatePayment(String orderId, Payment.PaymentStatus status, String paymentMethod) {
        Payment payment = paymentDao.findByOrderId(orderId);
        if (payment == null) return Result.error("订单不存在");

        if (!isValidStatusTransition(payment.getStatus(), status)) {
            return Result.error("非法状态转换");
        }

        payment.setStatus(status);
        if (paymentMethod != null) {
            try {
                payment.setPaymentMethod(Payment.PaymentMethod.valueOf(paymentMethod.toUpperCase()));
            } catch (IllegalArgumentException e) {
                return Result.error("无效的支付方式，有效值为：ALIPAY, WECHAT, BANK");
            }
        }
        payment.setUpdatedAt(LocalDateTime.now());
        paymentDao.updatePayment(payment);
        return Result.success("状态更新成功");
    }
    /**
     * 根据ID删除支付记录。
     *
     * @param id 支付记录ID
     * @return 删除结果，包含成功或失败信息
     */
    @Override
    @Transactional
    public Result deletePayment(Long id) {
        int result = paymentDao.deleteById(id);
        return result > 0 ? Result.success("删除成功") : Result.error("删除失败");
    }

    /**
     * 分页查询支付记录。
     *
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @param keyword 搜索关键字
     * @param status 支付状态
     * @return 包含支付记录、当前页码、总页数和总记录数的Map
     */
    @Override
    public Map<String, Object> getPayments(int page, int pageSize, String keyword, Payment.PaymentStatus status, String username) {
        int offset = (page - 1) * pageSize;
        List<Payment> payments = paymentDao.findAll(offset, pageSize, keyword, status, username);
        int total = paymentDao.count(keyword, status,username);

        return Map.of(
            "payments", payments,
            "currentPage", page,
            "totalPages", (int) Math.ceil((double)total / pageSize),
            "total", total
        );
    }

    /**
     * 将商品加入购物车。
     *
     * @param request 购物车请求DTO，包含商品信息
     * @return 加入购物车结果，包含成功或失败信息
     */
    @Override
    @Transactional
    public Result addToCart(CartRequestDTO request) {
        try {
//            log.info("购物车请求参数 itemName={} itemId={} quantity={} amount={}",
//                    request.getItemName(), request.getItemId(), request.getQuantity(), request.getAmount()
//            );
            //购物车验证逻辑

            // 生成购物车记录
            Payment payment = new Payment();
            payment.setOrderId(generateOrderId());
            payment.setUsername(request.getUsername());
            payment.setItemName(request.getItemName());
            payment.setItemId(request.getItemId());
            payment.setQuantity(request.getQuantity());
            payment.setAmount(request.getAmount());
            payment.setCreatedAt(LocalDateTime.now());
            payment.setQuantity(request.getQuantity());

            // 调用DAO插入数据库
            long result = paymentDao.insertCartItem(payment);

            if (result > 0) {
                return Result.success("商品已成功加入购物车");
            } else {
                return Result.error("加入购物车失败");
            }
        } catch (Exception e) {
            log.error("加入购物车失败，商品名:{}，商品ID:{}，数量:{}，金额:{}",
                request.getItemName(), request.getItemId(), request.getQuantity(), request.getAmount(), e);
            return Result.error(e.getMessage() != null ? e.getMessage() : "加入购物车时发生错误");
        }
    }
    @Override
    public Map<String, Object> getAllPayments(int page, int pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        List<Payment> payments = paymentDao.findAllOrders(offset, pageSize, keyword);
        int total = paymentDao.countallOrders(keyword);
        Map<String, Object> result = new HashMap<>();
        result.put("list", payments);
        result.put("total", total);
        return result;
    }
    /**
     * 生成唯一的订单号。
     *
     * @return 生成的订单号
     */
    private String generateOrderId() {
        return "PAY" + System.currentTimeMillis() + random.nextInt(9999);
    }

    /**
     * 模拟支付网关调用，返回支付是否成功。
     *
     * @return 支付成功返回true，否则返回false
     */
    private boolean simulatePaymentGateway() {
        return random.nextDouble() < 0.8; // 80%成功率
    }

    @Data
    @AllArgsConstructor
    public static class PaymentResult {
        private String orderId;
        private Payment.PaymentStatus status;
    }
}
