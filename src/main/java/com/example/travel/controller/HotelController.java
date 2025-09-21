package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.Hotel;
import com.example.travel.service.HotelService;
import com.example.travel.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/public/hotel")
public class HotelController {
    
    private final HotelService hotelService;
    private final ImageUtils imageUtils;
    
    @Autowired
    public HotelController(HotelService hotelService, ImageUtils imageUtils) {
        this.hotelService = hotelService;
        this.imageUtils = imageUtils;
    }
    
    //查询
    @GetMapping
    public Result findAllHotel(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword){
        List<Hotel> hotels=hotelService.getHotels(page, pageSize, keyword);
        int total=hotelService.countHotels(keyword);
        Map<String ,Object> result=new HashMap<>();
        result.put("list", hotels);
        result.put("total", total);
        return Result.success(result);
    }

    //新增
    @PostMapping
    @Transactional
    public Result addHotel(@RequestBody Hotel hotel) {
        if (hotel == null || hotel.getHotelName() == null || hotel.getHotelName().trim().isEmpty()) {
            return Result.error("酒店名称不能为空");
        }
        if (hotel.getHotelPrice() == null || hotel.getHotelPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return Result.error("价格必须大于0");
        }
        
        // 处理图片
        if (hotel.getHotelImage() != null && hotel.getHotelImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(hotel.getHotelImage());
                hotel.setHotelImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }
        
        hotel.setCreated_at(LocalDateTime.now());
        hotelService.addHotel(hotel);
        return Result.success(hotel);
    }
    
    //修改
    @PutMapping("/{id}")
    @Transactional
    public Result updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        if (!hotelService.existById(id)) {
            return Result.error("酒店不存在");
        }
        if (hotel.getHotelName() != null && hotel.getHotelName().trim().isEmpty()) {
            return Result.error("酒店名称不能为空");
        }
        if (hotel.getHotelPrice() != null && hotel.getHotelPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return Result.error("价格必须大于0");
        }
        
        // 获取原有酒店信息
        Hotel existingHotel = hotelService.getById(id);
        
        // 处理图片
        if (hotel.getHotelImage() != null && hotel.getHotelImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(hotel.getHotelImage());
                hotel.setHotelImage(imageUrl);
                
                // 删除旧图片
                if (existingHotel != null && existingHotel.getHotelImage() != null) {
                    imageUtils.deleteImage(existingHotel.getHotelImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (hotel.getHotelImage() == null && existingHotel != null && existingHotel.getHotelImage() != null) {
            // 删除原有图片
            try {
                imageUtils.deleteImage(existingHotel.getHotelImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        
        hotel.setId(id);
        hotel.setUpdated_at(LocalDateTime.now());
        hotelService.updateHotel(hotel);
        return Result.success(hotel);
    }

    //删除
    @DeleteMapping("/{id}")
    @Transactional
    public Result deleteHotel(@PathVariable Long id) {
        if (!hotelService.existById(id)) {
            return Result.error("酒店不存在");
        }
        
        // 获取酒店信息，包含图片路径
        Hotel hotel = hotelService.getById(id);
        if (hotel != null && hotel.getHotelImage() != null) {
            try {
                // 删除图片文件
                imageUtils.deleteImage(hotel.getHotelImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }

        hotelService.deleteHotel(id);
        return Result.success();
    }
}
