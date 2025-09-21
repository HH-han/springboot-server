package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.TravelBlog;
import com.example.travel.service.TravelBlogService;
import com.example.travel.utils.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/public/blogs")
public class TravelBlogController {

    private final TravelBlogService travelBlogService;
    private final ImageUtils imageUtils;

    @Autowired
    public TravelBlogController(TravelBlogService travelBlogService, ImageUtils imageUtils) {
        this.travelBlogService = travelBlogService;
        this.imageUtils = imageUtils;
    }
    
    /**
     * 获取所有博客
     */
    @GetMapping
    public Result findAllTravel(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword){
        List<TravelBlog> travelBlogs=travelBlogService.getTravel(page,pageSize,keyword);
        int total=travelBlogService.countTravel(keyword);
        Map<String, Object> result = new HashMap<>();
        result.put("total",total);
        result.put("list",travelBlogs);
        return Result.success(result);
    }

    /**
     * 根据ID获取博客
     */
    @GetMapping("/{id}")
    public Result getBlogById(@PathVariable Long id) {
        Optional<TravelBlog> blog = travelBlogService.getBlogById(id);
        return blog.map(Result::success)
                .orElseGet(() -> Result.error("Blog not found"));
    }

    /**
     * 点赞博客
     */
    @PostMapping("/{id}/like")
    public Result likeBlog(@PathVariable Long id) {
        travelBlogService.likeBlog(id);
        return Result.success();
    }

    /**
     * 收藏博客
     */
    @PostMapping("/{id}/favorite")
    public Result favoriteBlog(@PathVariable Long id) {
        travelBlogService.favoriteBlog(id);
        return Result.success();
    }

    @PostMapping
    @Transactional
    public Result addTravelBlog(@RequestBody TravelBlog blog) {
        if (blog.getTitle() == null || blog.getTitle().trim().isEmpty()) {
            return Result.error("博客标题不能为空");
        }
        if (blog.getContent().length() > 10000) {
            return Result.error("内容长度不能超过10000字符");
        }
        // 处理图片
        if (blog.getCoverImage() != null && blog.getCoverImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(blog.getCoverImage());
                blog.setCoverImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }
        blog.setCreated_at(LocalDateTime.now());
        travelBlogService.saveBlog(blog);
        return Result.success(blog);
    }
    // 更新博客
    @PutMapping("/{id}")
    @Transactional
    public Result updateBlog(@PathVariable Long id, @RequestBody TravelBlog blog) {
        if (!travelBlogService.existById(id)) {
            return Result.error("博客不存在");
        }
        if (blog.getTitle() != null && blog.getTitle().trim().isEmpty()) {
            return Result.error("标题不能为空");
        }
        
        // 获取原有博客信息
        TravelBlog existingBlog = travelBlogService.getById(id);
        
        // 处理图片
        if (blog.getCoverImage() != null && blog.getCoverImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(blog.getCoverImage());
                blog.setCoverImage(imageUrl);

                // 删除旧图片
                if (existingBlog != null && existingBlog.getCoverImage() != null) {
                    imageUtils.deleteImage(existingBlog.getCoverImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (blog.getCoverImage() == null && existingBlog != null && existingBlog.getCoverImage() != null) {
            // 删除原有图片
            try {
                imageUtils.deleteImage(existingBlog.getCoverImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        
        blog.setId(id);
        blog.setUpdatedAt(LocalDateTime.now());
        travelBlogService.updateBlog(blog);
        return Result.success(blog);
    }
    // 删除博客
    @DeleteMapping("/{id}")
    @Transactional
    public Result deleteBlog(@PathVariable Long id) {
        if (!travelBlogService.existById(id)) {
            return Result.error("博客不存在");
        }
        // 获取博客信息，包含图片路径
        TravelBlog blog = travelBlogService.getById(id);
        if (blog != null && blog.getCoverImage() != null) {
            try {
                // 删除图片文件
                imageUtils.deleteImage(blog.getCoverImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        travelBlogService.deleteBlog(id);
        return Result.success();
    }
}