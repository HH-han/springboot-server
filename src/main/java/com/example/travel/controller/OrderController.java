package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.Order;
import com.example.travel.service.OrderService;
import com.example.travel.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/public/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // 分页查询订单
    @GetMapping
    public Result findAllOrder(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        List<Order> orders = orderService.getOrders(page, pageSize, keyword);
        int total = orderService.countOrders(keyword);
        Map<String, Object> result = new HashMap<>();
        result.put("list", orders);
        result.put("total", total);
        return Result.success(result);
    }

    // 根据 ID 查询订单
    @GetMapping("/{id}")
    public Result getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return Result.success(order);
        } else {
            return Result.error("订单不存在");
        }
    }

    // 新增订单
    @PostMapping
    public Result addOrder(@RequestBody Order order) {
        try {
            // 图片处理
            if (order.getImage() != null && order.getImage().startsWith("data:image")) {
                try {
                    String imageUrl = ImageUtils.processBase64Image(order.getImage());
                    order.setImage(imageUrl);
                } catch (Exception e) {
                    return Result.error("图片保存失败: " + e.getMessage());
                }
            }

            int result = orderService.addOrder(order);
            if (result > 0) {
                return Result.success(result);
            } else {
                return Result.error("新增订单失败");
            }
        } catch (Exception e) {
            return Result.error("新增订单时发生错误: " + e.getMessage());
        }
    }

    // 更新订单
    @PutMapping("/{id}")
    public Result updateOrder(@PathVariable Long id, @RequestBody Order order) {
        try {
            // 获取原有订单信息
            Order existingOrder = orderService.getOrderById(id);
            
            // 图片处理
            if (order.getImage() != null && order.getImage().startsWith("data:image")) {
                try {
                    String imageUrl = ImageUtils.processBase64Image(order.getImage());
                    order.setImage(imageUrl);

                    // 删除旧图片
                    if (existingOrder != null && existingOrder.getImage() != null) {
                        ImageUtils.deleteImage(existingOrder.getImage());
                    }
                } catch (Exception e) {
                    return Result.error("图片保存失败: " + e.getMessage());
                }
            } else if (order.getImage() == null && existingOrder != null && existingOrder.getImage() != null) {
                // 删除原有图片
                try {
                    ImageUtils.deleteImage(existingOrder.getImage());
                } catch (Exception e) {
                    // 文件删除失败不影响主流程
                    System.err.println("删除图片文件失败: " + e.getMessage());
                }
            }

            order.setId(Math.toIntExact(id));
            int result = orderService.updateOrder(order);
            if (result > 0) {
                return Result.success(result);
            } else {
                return Result.error("更新订单失败");
            }
        } catch (Exception e) {
            return Result.error("更新订单时发生错误: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteOrder(@PathVariable Long id) {
        try {
            // 获取订单信息，包含图片路径
            Order order = orderService.getOrderById(id);
            if (order != null && order.getImage() != null && order.getImage().startsWith("http://localhost:2025/upload/")) {
                try {
                    // 删除图片文件
                    ImageUtils.deleteImage(order.getImage());
                } catch (Exception e) {
                    // 文件删除失败不影响主流程
                    System.err.println("删除图片文件失败: " + e.getMessage());
                }
            }

            int result = orderService.deleteOrder(id);
            if (result > 0) {
                return Result.success(result);
            } else {
                return Result.error("删除订单失败");
            }
        } catch (Exception e) {
            return Result.error("删除订单时发生错误: " + e.getMessage());
        }
    }
}