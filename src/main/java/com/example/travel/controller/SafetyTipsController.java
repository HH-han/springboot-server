package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.SafetyTips;
import com.example.travel.service.SafetyTipsService;
import com.example.travel.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/public/safety-tips")
public class SafetyTipsController {

    private final SafetyTipsService safetyTipsService;

    @Autowired
    public SafetyTipsController(SafetyTipsService safetyTipsService) {
        this.safetyTipsService = safetyTipsService;
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
            
            // 图片处理
            if (safetyTips.getImageUrl() != null && safetyTips.getImageUrl().startsWith("data:image")) {
                try {
                    // 解码base64图片
                    String[] parts = safetyTips.getImageUrl().split(",");
                    byte[] imageBytes = Base64.getDecoder().decode(parts[1]);

                    // 生成唯一文件名
                    String fileName = UUID.randomUUID().toString() + ".png";
                    Path uploadPath = Paths.get("D:/Image/");

                    // 确保目录存在
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    // 保存文件
                    Path filePath = uploadPath.resolve(fileName);
                    Files.write(filePath, imageBytes);

                    // 更新数据库中的图片路径
                    safetyTips.setImageUrl("http://localhost:2025/upload/" + fileName);
                } catch (Exception e) {
                    return Result.error("图片保存失败: " + e.getMessage());
                }
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
    @PutMapping
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
            
            // 图片处理
            if (safetyTips.getImageUrl() != null && safetyTips.getImageUrl().startsWith("data:image")) {
                try {
                    String imageUrl = ImageUtils.processBase64Image(safetyTips.getImageUrl());
                    safetyTips.setImageUrl(imageUrl);

                    // 删除旧图片
                    if (existingTip != null && safetyTips.getImageUrl() != null) {
                        ImageUtils.deleteImage(safetyTips.getImageUrl());
                    }
                } catch (Exception e) {
                    return Result.error("图片保存失败: " + e.getMessage());
                }
            } else if (safetyTips.getImageUrl() == null && existingTip != null && existingTip.getImageUrl() != null) {
                // 删除原有图片
                try {
                    ImageUtils.deleteImage(existingTip.getImageUrl());
                } catch (Exception e) {
                    // 文件删除失败不影响主流程
                    System.err.println("删除图片文件失败: " + e.getMessage());
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
            if (safetyTip != null && safetyTip.getImageUrl() != null && safetyTip.getImageUrl().startsWith("http://localhost:2025/upload/")) {
                try {
                    // 删除图片文件
                    ImageUtils.deleteImage(safetyTip.getImageUrl());
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

    /**
     * 批量删除安全提示
     * @param ids 安全提示ID列表
     * @return 操作结果
     */
    @DeleteMapping("/batch")
    public Result batchDeleteSafetyTips(@RequestBody List<Long> ids) {
        boolean result = safetyTipsService.batchDeleteSafetyTips(ids);
        return result ? Result.success("批量删除成功", true) :
                Result.error("批量删除失败");
    }

    /**
     * 条件查询安全提示
     * @param condition 查询条件
     * @return 安全提示列表
     */
    @PostMapping("/condition")
    public Result getSafetyTipsByCondition(@RequestBody Map<String, Object> condition) {
        List<SafetyTips> list = safetyTipsService.getSafetyTipsByCondition(condition);
        return Result.success("查询成功", list);
    }

    /**
     * 统计安全提示总数
     * @return 总数
     */
    @GetMapping("/count")
    public Result countSafetyTips() {
        long count = safetyTipsService.countSafetyTips();
        return Result.success("统计成功", count);
    }

    /**
     * 条件统计安全提示数量
     * @param condition 统计条件
     * @return 数量
     */
    @PostMapping("/count/condition")
    public Result countSafetyTipsByCondition(@RequestBody Map<String, Object> condition) {
        long count = safetyTipsService.countSafetyTipsByCondition(condition);
        return Result.success("条件统计成功", count);
    }
}