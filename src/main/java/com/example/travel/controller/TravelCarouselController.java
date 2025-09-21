package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.TravelCarousel;
import com.example.travel.service.TravelCarouselService;
import com.example.travel.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/public/travelcarousel")
public class TravelCarouselController {

    @Autowired
    private TravelCarouselService travelcarouselService;

    @Autowired
    private ImageUtils imageUtils;

    //查询
    @GetMapping
    public Result findAllTravelRecommend(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword){
        List<TravelCarousel> travelcarousel=travelcarouselService.getCarousel(page, pageSize, keyword);
        int total=travelcarouselService.countCarousel(keyword);
        Map<String ,Object> result=new HashMap<>();
        result.put("list", travelcarousel);
        result.put("total", total);
        return Result.success(result);
    }

    //新增
    @PostMapping
    @Transactional
    public Result insertTravelCarousel(@RequestBody TravelCarousel travelcarousel) {
        if (travelcarousel == null || travelcarousel.getTitle() == null || travelcarousel.getTitle().trim().isEmpty()) {
            return Result.error("名称不能为空");
        }

        // 图片处理
        if (travelcarousel.getImage() != null && travelcarousel.getImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(travelcarousel.getImage());
                travelcarousel.setImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }

        int result = travelcarouselService.insertCarousel(travelcarousel);
        return result > 0 ? Result.success(travelcarousel) : Result.error("新增失败");
    }

    //修改
    @PutMapping("/{id}")
    @Transactional
    public Result updateTravelCarousel(@PathVariable Long id, @RequestBody TravelCarousel travelcarousel) {
        if (!travelcarouselService.existById(id)) {
            return Result.error("推荐不存在");
        }
        if (travelcarousel.getTitle() != null && travelcarousel.getTitle().trim().isEmpty()) {
            return Result.error("名称不能为空");
        }

        // 获取原有信息
        TravelCarousel existingCarousel = travelcarouselService.getById(id);

        // 图片处理
        if (travelcarousel.getImage() != null && travelcarousel.getImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(travelcarousel.getImage());
                travelcarousel.setImage(imageUrl);

                // 删除旧图片
                if (existingCarousel != null && existingCarousel.getImage() != null) {
                    imageUtils.deleteImage(existingCarousel.getImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (travelcarousel.getImage() == null && existingCarousel != null && existingCarousel.getImage() != null) {
            // 删除原有图片
            try {
                imageUtils.deleteImage(existingCarousel.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }

        travelcarousel.setId(Math.toIntExact(id));
        int result = travelcarouselService.updateCarousel(travelcarousel);
        return result > 0 ? Result.success(travelcarousel) : Result.error("更新失败");
    }

    //删除
    @DeleteMapping("/{id}")
    @Transactional
    public Result deleteTravelCarousel(@PathVariable Long id) {
        if (!travelcarouselService.existById(id)) {
            return Result.error("推荐不存在");
        }

        // 获取推荐信息，包含图片路径
        TravelCarousel travelcarousel = travelcarouselService.getById(id);
        if (travelcarousel != null && travelcarousel.getImage() != null && travelcarousel.getImage().startsWith("http://localhost:2025/upload/")) {
            try {
                // 删除图片文件
                imageUtils.deleteImage(travelcarousel.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }

        travelcarouselService.deleteCarousel(id);
        return Result.success();
    }
}
