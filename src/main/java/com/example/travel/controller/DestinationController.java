package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.Destination;
import com.example.travel.service.DestinationService;
import com.example.travel.utils.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("/api/public/destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping("/list")
    public Result listDestinations(
            @RequestParam(required = false) String searchQuery,
            @RequestParam(defaultValue = "全部") String activeCategory,
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize) {
        

        return destinationService.listDestinations(searchQuery, activeCategory, currentPage, pageSize);
    }
    //新增
    @PostMapping
    @Transactional
    public Result addDestination(@RequestBody Destination destination) {
        // 图片处理
        if (destination.getImage() != null && destination.getImage().startsWith("data:image")) {
            try {
                String imageUrl = ImageUtils.processBase64Image(destination.getImage());
                destination.setImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }

        destinationService.addDestination(destination);
        return Result.success();
    }
    //修改
    @PutMapping("/{id}")
    @Transactional
    public Result updateDestination(@PathVariable long id, @RequestBody Destination destination) {
        // 获取原有目的地信息
        Destination existingDestination = destinationService.getById(id);
        
        // 图片处理
        if (destination.getImage() != null && destination.getImage().startsWith("data:image")) {
            try {
                String imageUrl = ImageUtils.processBase64Image(destination.getImage());
                destination.setImage(imageUrl);

                // 删除旧图片
                if (existingDestination != null && existingDestination.getImage() != null) {
                    ImageUtils.deleteImage(existingDestination.getImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (destination.getImage() == null && existingDestination != null && existingDestination.getImage() != null) {
            // 删除原有图片
            try {
                String oldFileName = existingDestination.getImage().substring("http://localhost:2025/upload/destination/".length());
                Path oldImagePath = Paths.get("D:/Image/destination/", oldFileName);
                if (Files.exists(oldImagePath)) {
                    Files.delete(oldImagePath);
                }
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }

        destinationService.updateDestination(destination);
        return Result.success();
    }
    //删除
    @DeleteMapping("/{id}")
    @Transactional
    public Result deleteDestination(@PathVariable Long id) {
        if (!destinationService.exists(id)) {
            return Result.error("错误删除失败");
        }
        
        // 获取目的地信息，包含图片路径
        Destination destination = destinationService.getById(id);
        if (destination != null && destination.getImage() != null) {
            try {
                ImageUtils.deleteImage(destination.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }

        destinationService.deleteDestination(id);
        return Result.success("删除成功");
    }
}