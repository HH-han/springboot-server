package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.Strategygroup;
import com.example.travel.service.StrategygroupService;
import com.example.travel.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public/strategy-groups")
public class StrategygroupController {

    private final StrategygroupService strategygroupService;
    private final ImageUtils imageUtils;

    @Autowired
    public StrategygroupController(StrategygroupService strategygroupService, ImageUtils imageUtils) {
        this.strategygroupService = strategygroupService;
        this.imageUtils = imageUtils;
    }

    @GetMapping
    public Result findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        int offset = (page - 1) * pageSize;
        List<Strategygroup> groups = strategygroupService.getAllStrategygroups(offset, pageSize);
        long total = strategygroupService.countStrategygroups();
        Map<String, Object> result = new HashMap<>();
        result.put("list", groups);
        result.put("total", total);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Strategygroup> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(strategygroupService.getStrategygroupById(id));
    }

    @PostMapping
    @Transactional
    public Result create(@RequestBody Strategygroup group) {
        if (group.getTitle() == null || group.getTitle().trim().isEmpty()) {
            return Result.error("标题不能为空");
        }
        
        // 处理图片
        if (group.getImage() != null && group.getImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(group.getImage());
                group.setImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }
        
        return Result.success(strategygroupService.createStrategygroup(group));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result update(
            @PathVariable Integer id,
            @RequestBody Strategygroup groupDetails) {
        
        // 处理图片
        if (groupDetails.getImage() != null && groupDetails.getImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(groupDetails.getImage());
                groupDetails.setImage(imageUrl);

                // 删除旧图片
                Strategygroup existingGroup = strategygroupService.getStrategygroupById(id);
                if (existingGroup != null && existingGroup.getImage() != null) {
                    imageUtils.deleteImage(existingGroup.getImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (groupDetails.getImage() == null) {
            // 删除原有图片
            Strategygroup existingGroup = strategygroupService.getStrategygroupById(id);
            if (existingGroup != null && existingGroup.getImage() != null) {
                try {
                    imageUtils.deleteImage(existingGroup.getImage());
                } catch (Exception e) {
                    System.err.println("删除图片文件失败: " + e.getMessage());
                }
            }
        }
        
        return Result.success(strategygroupService.updateStrategygroup(groupDetails));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Strategygroup group = strategygroupService.getStrategygroupById(id);
        if (group != null && group.getImage() != null) {
            try {
                imageUtils.deleteImage(group.getImage());
            } catch (Exception e) {
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        
        strategygroupService.deleteStrategygroup(id);
        return Result.success("删除成功");
    }

    @GetMapping("/search/title")
    public Result searchByTitle(@RequestParam String title) {
        List<Strategygroup> groups = strategygroupService.searchStrategygroupsByTitle(title);
        return Result.success(groups);
    }
}
