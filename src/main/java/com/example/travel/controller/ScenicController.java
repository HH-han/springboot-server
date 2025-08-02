package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.Scenic;
import com.example.travel.service.ScenicService;
import com.example.travel.utils.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/public/scenic")
public class ScenicController {
    @Autowired
    private ScenicService scenicService;
    @GetMapping
    public Result findAllScenic(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword){
        List<Scenic> scenic=scenicService.getScenic(page,pageSize,keyword);
        int total=scenicService.countScenic(keyword);
        Map<String,Object> result=new HashMap<>();
        result.put("list",scenic);
        result.put("total",total);
        return Result.success(result);
    }
    //新增
    @PostMapping
    @Transactional
    public Result addScenic(@RequestBody Scenic scenic) {
        if (scenic.getTitle() == null || scenic.getTitle().trim().isEmpty()) {
            return Result.error("景点名称不能为空");
        }
        if (scenic.getPrice() <= 0) {
            return Result.error("价格必须大于0");
        }
        // 处理图片
        if (scenic.getImage() != null && scenic.getImage().startsWith("data:image")) {
            try {
                String imageUrl = ImageUtils.processBase64Image(scenic.getImage());
                scenic.setImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }
        scenic.setCreated_at(LocalDateTime.now());
        scenicService.addScenic(scenic);
        return Result.success(scenic);
    }
    //修改
    @PutMapping("/{id}")
    @Transactional
    public Result updateScenic(@PathVariable Long id, @RequestBody Scenic scenic) {
        if (!scenicService.existById(id)) {
            return Result.error("景点不存在");
        }
        if (scenic.getTitle() != null && scenic.getTitle().trim().isEmpty()) {
            return Result.error("景点名称不能为空");
        }
        // 获取原有景点信息
        Scenic existingScenic = scenicService.getById(id);
        
        // 处理图片
        if (scenic.getImage() != null && scenic.getImage().startsWith("data:image")) {
            try {
                String imageUrl = ImageUtils.processBase64Image(scenic.getImage());
                scenic.setImage(imageUrl);

                // 删除旧图片
                if (existingScenic != null && existingScenic.getImage() != null) {
                    ImageUtils.deleteImage(existingScenic.getImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (scenic.getImage() == null && existingScenic != null && existingScenic.getImage() != null) {
            // 删除原有图片
            try {
                ImageUtils.deleteImage(existingScenic.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        
        scenic.setId(id);
        scenic.setUpdated_at(LocalDateTime.now());
        scenicService.updateScenic(scenic);
        return Result.success(scenic);
    }
    //删除
    @DeleteMapping("/{id}")
    @Transactional
    public Result deleteScenic(@PathVariable Long id) {
        if (!scenicService.existById(id)) {
            return Result.error("景点不存在");
        }
        // 获取景点信息，包含图片路径
        Scenic scenic = scenicService.getById(id);
        if (scenic != null && scenic.getImage() != null) {
            try {
                // 删除图片文件
                ImageUtils.deleteImage(scenic.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        scenicService.deleteScenic(id);
        return Result.success();
    }
}
