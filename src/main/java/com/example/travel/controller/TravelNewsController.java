package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.TravelNews;
import com.example.travel.service.TravelNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.example.travel.utils.ImageUtils;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 旅游新闻控制器，处理旅游新闻的相关请求
 */
@RestController
@RequestMapping("/api/public/news")
public class TravelNewsController {

    private final TravelNewsService travelNewsService;
    private final ImageUtils imageUtils;

    @Autowired
    public TravelNewsController(TravelNewsService travelNewsService, ImageUtils imageUtils) {
        this.travelNewsService = travelNewsService;
        this.imageUtils = imageUtils;
    }

    /**
     * 获取所有旅游新闻（分页）
     * @param page 当前页码，默认为1
     * @param pageSize 每页显示数量，默认为10
     * @param keyword 搜索关键词（可选）
     * @return 包含新闻列表和总数的结果
     */
    @GetMapping
    public Result findAllTravelNews(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        List<TravelNews> travelNews = travelNewsService.findAllTravelNews(page, pageSize, keyword);
        int total = travelNewsService.countTravelNews(keyword);
        Map<String, Object> result = new HashMap<>();
        result.put("list", travelNews);
        result.put("total", total);
        return Result.success(result);
    }

    /**
     * 获取所有旅游新闻（不分页）
     * @return 旅游新闻列表
     */
    @GetMapping("/all")
    public Result getAllNews() {
        List<TravelNews> travelNews = travelNewsService.selectAllTravelNews();
        return Result.success(travelNews);
    }

    /**
     * 根据ID获取单个旅游新闻
     * @param id 新闻ID
     * @return 新闻详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<TravelNews> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(travelNewsService.findById(id));
    }

    /**
     * 新增旅游新闻
     * @param news 新闻对象
     * @return 创建后的新闻
     */
    @PostMapping
    @Transactional
    public Result createNews(@RequestBody TravelNews news) {
        // 验证必填字段
        if (news.getTitle() == null || news.getTitle().trim().isEmpty()) {
            return Result.error("标题不能为空");
        }
        if (news.getContent() == null || news.getContent().trim().isEmpty()) {
            return Result.error("内容不能为空");
        }
        
        // 处理图片
        if (news.getCoverImage() != null && news.getCoverImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(news.getCoverImage());
                news.setCoverImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }
        
        return Result.success(travelNewsService.insertTravelNews(news));
    }

    /**
     * 更新旅游新闻
     * @param id 新闻ID
     * @param newsDetails 更新的新闻内容
     * @return 更新后的新闻
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result updateNews(
            @PathVariable Long id,
            @RequestBody TravelNews newsDetails) {
        // 处理图片
        if (newsDetails.getCoverImage() != null && newsDetails.getCoverImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(newsDetails.getCoverImage());
                newsDetails.setCoverImage(imageUrl);

                // 删除旧图片
                TravelNews existingNews = travelNewsService.findById(id);
                if (existingNews != null && existingNews.getCoverImage() != null) {
                    imageUtils.deleteImage(existingNews.getCoverImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (newsDetails.getCoverImage() == null) {
            // 删除原有图片
            TravelNews existingNews = travelNewsService.findById(id);
            if (existingNews != null && existingNews.getCoverImage() != null) {
                try {
                    imageUtils.deleteImage(existingNews.getCoverImage());
                } catch (Exception e) {
                    // 文件删除失败不影响主流程
                    System.err.println("删除图片文件失败: " + e.getMessage());
                }
            }
        }
        return Result.success(travelNewsService.updateTravelNews(newsDetails));
    }

    /**
     * 删除旅游新闻
     * @param id 新闻ID
     * @return 空响应
     */
    @DeleteMapping("/{id}")
    public Result deleteNews(@PathVariable Long id) {
        // 获取新闻信息，包含图片路径
        TravelNews news = travelNewsService.findById(id);
        if (news != null && news.getCoverImage() != null) {
            try {
                // 删除图片文件
                imageUtils.deleteImage(news.getCoverImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        
        travelNewsService.deleteTravelNews(id);
        return Result.success("删除成功");
    }

    /**
     * 增加点赞数
     * @param id 新闻ID
     * @return 操作结果
     */
    @PostMapping("/{id}/like")
    public Result likeNews(@PathVariable Long id) {
        int result = travelNewsService.incrementLikes(id);
        if (result > 0) {
            return Result.success("点赞成功");
        } else {
            return Result.error("点赞失败");
        }
    }

    /**
     * 增加收藏数
     * @param id 新闻ID
     * @return 操作结果
     */
    @PostMapping("/{id}/favorite")
    public Result favoriteNews(@PathVariable Long id) {
        int result = travelNewsService.incrementFavorites(id);
        if (result > 0) {
            return Result.success("收藏成功");
        } else {
            return Result.error("收藏失败");
        }
    }

    /**
     * 检查新闻是否存在
     * @param id 新闻ID
     * @return 是否存在
     */
    @GetMapping("/{id}/exists")
    public Result checkNewsExists(@PathVariable Long id) {
        boolean exists = travelNewsService.exists(id);
        return Result.success(exists);
    }
}
