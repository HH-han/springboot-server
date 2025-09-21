package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.SafetyTips;
import com.example.travel.service.SafetyTipsService;
import com.example.travel.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/public/safety-tips")
public class SafetyTipsController {

    private final SafetyTipsService safetyTipsService;
    private final ImageUtils imageUtils;

    @Autowired
    public SafetyTipsController(SafetyTipsService safetyTipsService, ImageUtils imageUtils) {
        this.safetyTipsService = safetyTipsService;
        this.imageUtils = imageUtils;
    }

    /**
     * 分页查询安全提示列表
     * @param page 当前页码
     * @param pageSize 每页数量
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result getSafetyTipsPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword){
        List<SafetyTips> safetytips=safetyTipsService.findAllSafetyTips(page,pageSize,keyword);
        int total =safetyTipsService.countSafety(keyword);
        Map<String, Object> result = new HashMap<>();
        result.put("list",safetytips);
        result.put("total",total);
        return Result.success(result);
    }

    /**
     * 条件分页查询安全提示
     * @param pageNum 当前页码
     * @param pageSize 每页数量
     * @param condition 查询条件
     * @return 分页结果
     */
    @PostMapping("/page/condition")
    public Result getSafetyTipsPageByCondition(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestBody Map<String, Object> condition) {
        List<SafetyTips> pageInfo = safetyTipsService.getSafetyTipsPageByCondition(pageNum, pageSize, condition);
        return Result.success("条件查询成功", pageInfo);
    }
    /**
     * 根据ID获取安全提示详情
     * @param id 安全提示ID
     * @return 安全提示详情
     */
    @GetMapping("/{id}")
    public Result getSafetyTipById(@PathVariable Long id) {
        SafetyTips safetyTip = safetyTipsService.getSafetyTipById(id);
        return Result.success("查询成功", safetyTip);
    }

    /**
     * 新增安全提示
     * @param safetyTips 安全提示数据
     * @return 操作结果
     */
    @PostMapping
    public Result insert(@Validated @RequestBody SafetyTips safetyTips) {
        try {
            if (safetyTips.getTitle() == null || safetyTips.getTitle().trim().isEmpty()) {
                return Result.error("标题不能为空");
            }
            if (safetyTips.getDescription() == null || safetyTips.getDescription().trim().isEmpty()) {
                return Result.error("内容不能为空");
            }
            
            // 处理图片
            if (safetyTips.getImageUrl() != null && safetyTips.getImageUrl().startsWith("data:image")) {
                try {
                    String imageUrl = imageUtils.processBase64Image(safetyTips.getImageUrl());
                    safetyTips.setImageUrl(imageUrl);
                } catch (Exception e) {
                    return Result.error("图片保存失败: " + e.getMessage());
                }
            } else if (safetyTips.getImageUrl() != null) {
                // 如果imageUrl不是base64格式，保持原样（已经是有效的URL）
                // 不需要额外处理
            }

            boolean result = safetyTipsService.createSafetyTip(safetyTips);
            return result ? Result.success("安全提示创建成功", safetyTips) :
                    Result.error("安全提示创建失败");
        } catch (Exception e) {
            return Result.error("创建安全提示时发生错误: " + e.getMessage());
        }
    }

    /**
     * 更新安全提示
     * @param safetyTips 安全提示数据
     * @return 操作结果
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result update(@Validated @RequestBody SafetyTips safetyTips) {
        try {
            if (safetyTips.getId() == null) {
                return Result.error("ID不能为空");
            }
            if (safetyTips.getTitle() == null || safetyTips.getTitle().trim().isEmpty()) {
                return Result.error("标题不能为空");
            }
            if (safetyTips.getDescription() == null || safetyTips.getDescription().trim().isEmpty()) {
                return Result.error("内容不能为空");
            }

            // 获取原有安全提示信息
            SafetyTips existingTip = safetyTipsService.getSafetyTipById(safetyTips.getId());
            
            // 处理图片
            if (safetyTips.getImageUrl() != null && safetyTips.getImageUrl().startsWith("data:image")) {
                try {
                    String imageUrl = imageUtils.processBase64Image(safetyTips.getImageUrl());
                    safetyTips.setImageUrl(imageUrl);

                    // 删除旧图片
                    if (existingTip != null && existingTip.getImageUrl() != null) {
                        imageUtils.deleteImage(existingTip.getImageUrl());
                    }
                } catch (Exception e) {
                    return Result.error("图片保存失败: " + e.getMessage());
                }
            } else if (safetyTips.getImageUrl() == null) {
                // 删除原有图片
                if (existingTip != null && existingTip.getImageUrl() != null) {
                    try {
                        imageUtils.deleteImage(existingTip.getImageUrl());
                    } catch (Exception e) {
                        // 文件删除失败不影响主流程
                        System.err.println("删除图片文件失败: " + e.getMessage());
                    }
                }
            } else {
                // 如果imageUrl不是base64格式，保持原样（已经是有效的URL）
                // 删除旧图片（如果URL发生变化）
                if (existingTip != null && existingTip.getImageUrl() != null && 
                    !existingTip.getImageUrl().equals(safetyTips.getImageUrl())) {
                    try {
                        imageUtils.deleteImage(existingTip.getImageUrl());
                    } catch (Exception e) {
                        // 文件删除失败不影响主流程
                        System.err.println("删除图片文件失败: " + e.getMessage());
                    }
                }
            }

            boolean result = safetyTipsService.updateSafetyTip(safetyTips);
            return result ? Result.success("更新成功", safetyTips) :
                    Result.error("更新失败");
        } catch (Exception e) {
            return Result.error("更新安全提示时发生错误: " + e.getMessage());
        }
    }
    /**
     * 批量新增安全提示
     * @param safetyTipsList 安全提示列表
     * @return 操作结果
     */
    @PostMapping("/batch")
    public Result batchCreateSafetyTips(@Validated @RequestBody List<SafetyTips> safetyTipsList) {
        boolean result = safetyTipsService.batchCreateSafetyTips(safetyTipsList);
        return result ? Result.success("批量创建成功", true) :
                    Result.error("批量创建失败");
    }
    /**
     * 批量更新安全提示
     * @param safetyTipsList 安全提示列表
     * @return 操作结果
     */
    @PutMapping("/batch")
    public Result batchUpdateSafetyTips(@Validated @RequestBody List<SafetyTips> safetyTipsList) {
        boolean result = safetyTipsService.batchUpdateSafetyTips(safetyTipsList);
        return result ? Result.success("批量更新成功", true) :
                    Result.error("批量更新失败");
    }

    /**
     * 删除安全提示
     * @param id 安全提示ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}")
    public Result deleteSafetyTip(@PathVariable Long id) {
        try {
            // 获取安全提示信息，包含图片路径
            SafetyTips safetyTip = safetyTipsService.getSafetyTipById(id);
            if (safetyTip != null && safetyTip.getImageUrl() != null) {
                try {
                    // 删除图片文件
                    imageUtils.deleteImage(safetyTip.getImageUrl());
                } catch (Exception e) {
                    // 文件删除失败不影响主流程
                    System.err.println("删除图片文件失败: " + e.getMessage());
                }
            }

            boolean result = safetyTipsService.deleteSafetyTip(id);
            return result ? Result.success("删除成功", true) :
                    Result.error("删除失败");
        } catch (Exception e) {
            return Result.error("删除安全提示时发生错误: " + e.getMessage());
        }
    }
}