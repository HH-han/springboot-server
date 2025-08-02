package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.dto.CartRequestDTO;
import com.example.travel.entity.Payment;
import com.example.travel.service.PaymentService;
import com.example.travel.dto.PaymentRequestDTO;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/public/payment")
@RequiredArgsConstructor
public class PaymentController {
    //分页查询
    @Operation(summary = "支付列表查询")
    @GetMapping
    public Result listPayments(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") @Min(1) int page,
            @Parameter(description = "每页数量") @RequestParam(defaultValue = "10") @Range(min = 1, max = 100) int pageSize,
            @Parameter(description = "搜索关键字") @RequestParam(required = false) String keyword,
            @Parameter(description = "支付状态") @RequestParam(required = false) Payment.PaymentStatus status,
            @Parameter(description = "用户名") @RequestParam(required = false) String username) {
        Map<String, Object> result = paymentService.getPayments(page, pageSize, keyword, status, username);
        return Result.success(result);
    }
    
    //查询所有支付数据
    @Operation(summary = "查询所有支付数据")
    @GetMapping("/all")
    public Result listAllPayments(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") @Min(1) int page,
            @Parameter(description = "每页数量") @RequestParam(defaultValue = "10") @Range(min = 1, max = 100) int pageSize,
            @Parameter(description = "搜索关键字") @RequestParam(required = false) String keyword) {
        Map<String, Object> result = paymentService.getAllPayments(page, pageSize,keyword);
        return Result.success(result);
    }
    private final PaymentService paymentService;
    //新增订单
    @PostMapping("/shoping")
    @Operation(summary = "创建支付订单")
    public Result createPayment(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "支付请求参数")
            @Validated @RequestBody PaymentRequestDTO request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getFieldError().getDefaultMessage());
        }
        // 处理数组格式的请求参数
        if (request instanceof List) {
            return Result.error("请求参数格式错误，请使用对象格式");
        }
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return Result.error("支付金额必须大于0");
        }
        
        try {
        } catch (NumberFormatException e) {
            return Result.error("商品ID格式不正确");
        }
        
        // 补充支付方式枚举校验
        if (request.getPaymentMethod() == null) {
            return Result.error("请选择支付方式");
        }
        return paymentService.processPayment(request);
    }
    //更新订单
    @PutMapping("/{orderId}")
    @Operation(summary = "更新支付状态")
    public Result updatePaymentStatus(
            @Parameter(description = "订单号") @PathVariable String orderId,
            @Parameter(description = "新支付状态") @RequestParam(required = true) @NotNull String status,
            @Parameter(description = "支付方式") @RequestParam(required = false) String paymentMethod) {
        if (StringUtils.isBlank(orderId)) {
            return Result.error("订单号不能为空");
        }
        if (StringUtils.isBlank(status)) {
            return Result.error("状态参数不能为空");
        }
        try {
            Payment.PaymentStatus paymentStatus = Payment.PaymentStatus.valueOf(status.toUpperCase());
            return paymentService.updatePayment(orderId, paymentStatus, paymentMethod);
        } catch (IllegalArgumentException e) {
            return Result.error("无效的支付状态，有效值为：PENDING, SUCCESS, FAILED, COMPLETED");
        }
    }

    @DeleteMapping("/{id}")
    public Result deletePayment(@PathVariable Long id) {
        return paymentService.deletePayment(id);
    }
    //购物车
    @PostMapping("/cart")
    @Operation(summary = "加入购物车")
    public Result addToCart(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "购物车请求参数")
            @Validated @RequestBody CartRequestDTO request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getFieldError().getDefaultMessage());
        }
        // 处理数组格式的请求参数
        if (request instanceof List) {
            return Result.error("请求参数格式错误，请使用对象格式");
        }
        return paymentService.addToCart(request);
    }
}