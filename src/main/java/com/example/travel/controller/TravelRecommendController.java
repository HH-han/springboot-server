package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.TravelRecommend;
import com.example.travel.service.TravelRecommendService;
import com.example.travel.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/public/travelrecommend")
public class TravelRecommendController {
    
    private final TravelRecommendService travelrecommendService;
    private final ImageUtils imageUtils;
    
    @Autowired
    public TravelRecommendController(TravelRecommendService travelrecommendService, ImageUtils imageUtils) {
        this.travelrecommendService = travelrecommendService;
        this.imageUtils = imageUtils;
    }
    
    //查询
    @GetMapping
    public Result findAllTravelRecommend(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword){
        List<TravelRecommend> travelrecommend=travelrecommendService.getRecommend(page, pageSize, keyword);
        int total=travelrecommendService.countRecommend(keyword);
        Map<String ,Object> result=new HashMap<>();
        result.put("list", travelrecommend);
        result.put("total", total);
        return Result.success(result);
    }

    //新增
    @PostMapping
    @Transactional
    public Result insertTravelRecommend(@RequestBody TravelRecommend travelrecommend) {
        if (travelrecommend == null || travelrecommend.getName() == null || travelrecommend.getName().trim().isEmpty()) {
            return Result.error("推荐名称不能为空");
        }
        
        // 图片处理
        if (travelrecommend.getImage() != null && travelrecommend.getImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(travelrecommend.getImage());
                travelrecommend.setImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }
        
        int result = travelrecommendService.insertRecommend(travelrecommend);
        return result > 0 ? Result.success(travelrecommend) : Result.error("新增失败");
    }
    
    //修改
    @PutMapping("/{id}")
    @Transactional
    public Result updateTravelRecommend(@PathVariable Long id, @RequestBody TravelRecommend travelrecommend) {
        if (!travelrecommendService.existById(id)) {
            return Result.error("推荐不存在");
        }
        if (travelrecommend.getName() != null && travelrecommend.getName().trim().isEmpty()) {
            return Result.error("推荐名称不能为空");
        }
        
        // 获取原有推荐信息
        TravelRecommend existingRecommend = travelrecommendService.getById(id);
        
        // 图片处理
        if (travelrecommend.getImage() != null && travelrecommend.getImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(travelrecommend.getImage());
                travelrecommend.setImage(imageUrl);

                // 删除旧图片
                if (existingRecommend != null && existingRecommend.getImage() != null) {
                    imageUtils.deleteImage(existingRecommend.getImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (travelrecommend.getImage() == null && existingRecommend != null && existingRecommend.getImage() != null) {
            // 删除原有图片
            try {
                imageUtils.deleteImage(existingRecommend.getImage());
                travelrecommend.setImage(null);
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        
        travelrecommend.setId(Math.toIntExact(id));
        int result = travelrecommendService.updateRecommend(travelrecommend);
        return result > 0 ? Result.success(travelrecommend) : Result.error("更新失败");
    }

    //删除
    @DeleteMapping("/{id}")
    @Transactional
    public Result deleteTravelRecommend(@PathVariable Long id) {
        if (!travelrecommendService.existById(id)) {
            return Result.error("推荐不存在");
        }
        
        // 获取推荐信息，包含图片路径
        TravelRecommend travelrecommend = travelrecommendService.getById(id);
        if (travelrecommend != null && travelrecommend.getImage() != null && travelrecommend.getImage().startsWith("http://localhost:2025/upload/")) {
            try {
                // 删除图片文件
                imageUtils.deleteImage(travelrecommend.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        
        travelrecommendService.deleteRecommend(id);
        return Result.success();
    }
}