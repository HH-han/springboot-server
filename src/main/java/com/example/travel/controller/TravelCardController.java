package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.TravelCard;
import com.example.travel.service.TravelCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.example.travel.utils.ImageUtils;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 旅游卡控制器，处理旅游卡的相关请求
 */
@CrossOrigin
@RestController
@RequestMapping("/api/public/travelcard")
public class TravelCardController {

    private final TravelCardService travelCardService;

    @Autowired
    public TravelCardController(TravelCardService travelCardService) {
        this.travelCardService = travelCardService;
    }
    /**
     * 获取所有旅游卡列表（分页）
     * @param page 当前页码，默认为1
     * @param pageSize 每页显示数量，默认为10
     * @param keyword 搜索关键词（可选）
     * @return 包含旅游卡列表和总数的结果
     */
    @GetMapping
    public Result findAllTravelCard(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        List<TravelCard> travelCard = travelCardService.getTravelCard(page, pageSize, keyword);
        int total = travelCardService.countTravelCard(keyword);
        Map<String, Object> result = new HashMap<>();
        result.put("list", travelCard);
        result.put("total", total);
        return Result.success(result);
    }

    /**
     * 根据ID获取单个旅游卡
     * @param id 旅游卡ID
     * @return 旅游卡详情
     */
    @GetMapping("/{id}")
    public Result getCardById(@PathVariable Long id) {
        TravelCard card = travelCardService.getCardById(id);
        if (card == null) {
            return Result.error("旅游卡不存在");
        }
        return Result.success(card);
    }

    /**
     * 新增旅游卡
     * @param card 旅游卡对象
     * @return 创建后的旅游卡
     */
    @PostMapping
    @Transactional
    public Result createCard(@RequestBody TravelCard card) {
        // 验证必填字段
        if (card.getTitle() == null || card.getTitle().trim().isEmpty()) {
            return Result.error("标题不能为空");
        }
        
        // 处理图片
        if (card.getImage() != null && card.getImage().startsWith("data:image")) {
            try {
                String imageUrl = ImageUtils.processBase64Image(card.getImage());
                card.setImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }
        return Result.success(travelCardService.addTravelCard(card));
    }

    /**
     * 更新旅游卡
     * @param id 旅游卡ID
     * @param cardDetails 更新的旅游卡内容
     * @return 更新后的旅游卡
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result updateCard(
            @PathVariable Long id,
            @RequestBody TravelCard cardDetails) {
        // 处理图片
        if (cardDetails.getImage() != null && cardDetails.getImage().startsWith("data:image")) {
            try {
                String imageUrl = ImageUtils.processBase64Image(cardDetails.getImage());
                cardDetails.setImage(imageUrl);

                // 删除旧图片
                TravelCard existingCard = travelCardService.getCardById(id);
                if (existingCard != null && existingCard.getImage() != null) {
                    ImageUtils.deleteImage(existingCard.getImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (cardDetails.getImage() == null) {
            // 删除原有图片
            TravelCard existingCard = travelCardService.getCardById(id);
            if (existingCard != null && existingCard.getImage() != null) {
                try {
                    ImageUtils.deleteImage(existingCard.getImage());
                } catch (Exception e) {
                    // 文件删除失败不影响主流程
                    System.err.println("删除图片文件失败: " + e.getMessage());
                }
            }
        }
        return Result.success(travelCardService.updateTravelCard(cardDetails));
    }

    /**
     * 删除旅游卡
     * @param id 旅游卡ID
     * @return 空响应
     */
    @DeleteMapping("/{id}")
    public Result deleteCard(@PathVariable Long id) {
        //获取旅游卡信息，包含图片路径
        TravelCard card = travelCardService.getCardById(id);
        if (card != null && card.getImage() != null) {
            try {
                // 删除图片文件
                ImageUtils.deleteImage(card.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        travelCardService.deleteTravelCard(id);
        return Result.success("删除成功");
    }

    /**
     * 根据名称搜索旅游卡
     * @param name 名称
     * @return 匹配的旅游卡列表
     */
    @GetMapping("/search/name")
    public Result searchByName(
            @RequestParam String name) {
        return Result.success(travelCardService.searchByName(name));
    }

    /**
     * 根据关键词搜索旅游卡
     * @param keyword 搜索关键词
     * @return 匹配的旅游卡列表
     */
    @GetMapping("/search/keyword")
    public Result searchByKeyword(
            @RequestParam String keyword) {
        return Result.success(travelCardService.searchByKeyword(keyword));
    }

    /**
     * 根据分类搜索旅游卡
     * @param category 分类名称
     * @return 匹配的旅游卡列表
     */
    @GetMapping("/search/category")
    public Result searchByCategory(
            @RequestParam String category) {
        return Result.success(travelCardService.searchByCategory(category));
    }

    /**
     * 根据徽章类型搜索旅游卡
     * @param badgeType 徽章类型
     * @return 匹配的旅游卡列表
     */
    @GetMapping("/search/badge")
    public Result searchByBadgeType(
            @RequestParam String badgeType) {
        return Result.success(travelCardService.searchByBadgeType(badgeType));
    }
}
