package com.example.travel.controller;


import com.example.travel.common.Result;
import com.example.travel.entity.TravelWorldcharacteristics;
import com.example.travel.service.TravelWorldcharacteristicsService;
import com.example.travel.utils.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("/api/public/travelworld")
public class TravelWorldcharacteristicsController {

    private final TravelWorldcharacteristicsService travelWorldcharacteristicsService;
    private final ImageUtils imageUtils;

    @Autowired
    public TravelWorldcharacteristicsController(TravelWorldcharacteristicsService travelWorldcharacteristicsService, ImageUtils imageUtils) {
        this.travelWorldcharacteristicsService = travelWorldcharacteristicsService;
        this.imageUtils = imageUtils;
    }

    @GetMapping
    public Result TravelWorldcharacteristics(
            @RequestParam(required = false) String searchQuery,
            @RequestParam(defaultValue = "全部") String activeCategory,
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize) {


        return travelWorldcharacteristicsService.listTravelWorld( searchQuery, activeCategory, currentPage, pageSize);
    }
    //新增
    @PostMapping
    @Transactional
    public Result addTravelWorld(@RequestBody TravelWorldcharacteristics travelWorldcharacteristics) {
        // 图片处理
        if (travelWorldcharacteristics.getImage() != null && travelWorldcharacteristics.getImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(travelWorldcharacteristics.getImage());
                travelWorldcharacteristics.setImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }

        travelWorldcharacteristicsService.addTravelWorld(travelWorldcharacteristics);
        return Result.success();
    }
    //修改
    @PutMapping("/{id}")
    @Transactional
    public Result updateTravelWorld(@PathVariable long id, @RequestBody TravelWorldcharacteristics travelWorldcharacteristics) {
        // 获取原有目的地信息
        TravelWorldcharacteristics existingTravelWorld = travelWorldcharacteristicsService.getById(id);

        // 图片处理
        if (travelWorldcharacteristics.getImage() != null && travelWorldcharacteristics.getImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(travelWorldcharacteristics.getImage());
                travelWorldcharacteristics.setImage(imageUrl);

                // 删除旧图片
                if (existingTravelWorld != null && existingTravelWorld.getImage() != null) {
                    imageUtils.deleteImage(existingTravelWorld.getImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (travelWorldcharacteristics.getImage() == null && existingTravelWorld != null && existingTravelWorld.getImage() != null) {
            // 删除原有图片
            try {
                String oldFileName = existingTravelWorld.getImage().substring("http://localhost:2025/upload/destination/".length());
                Path oldImagePath = Paths.get("D:/Image/destination/", oldFileName);
                if (Files.exists(oldImagePath)) {
                    Files.delete(oldImagePath);
                }
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }

        travelWorldcharacteristicsService.updateTravelWorld(travelWorldcharacteristics);
        return Result.success();
    }
    //删除
    @DeleteMapping("/{id}")
    @Transactional
    public Result deleteDestination(@PathVariable Long id) {
        if (!travelWorldcharacteristicsService.exists(id)) {
            return Result.error("错误删除失败");
        }

        // 获取目的地信息，包含图片路径
        TravelWorldcharacteristics travelWorldcharacteristics = travelWorldcharacteristicsService.getById(id);
        if (travelWorldcharacteristics != null && travelWorldcharacteristics.getImage() != null) {
            try {
                imageUtils.deleteImage(travelWorldcharacteristics.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }

        travelWorldcharacteristicsService.deleteTravelWorld(id);
        return Result.success("删除成功");
    }
}
