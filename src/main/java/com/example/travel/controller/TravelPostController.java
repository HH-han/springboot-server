package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.TravelPost;
import com.example.travel.service.TravelPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.travel.utils.ImageUtils;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 旅游帖子控制器，处理旅游帖子的相关请求
 */
@RestController
@RequestMapping("/api/public/posts")
public class TravelPostController {

    private final TravelPostService travelPostService;
    private final ImageUtils imageUtils;

    @Autowired
    public TravelPostController(TravelPostService travelPostService, ImageUtils imageUtils) {
        this.travelPostService = travelPostService;
        this.imageUtils = imageUtils;
    }

    /**
     * 根据用户名获取帖子列表（分页）
     * @param page 当前页码，默认为1
     * @param pageSize 每页显示数量，默认为10
     * @param keyword 搜索关键词（可选）
     * @param username 用户名
     * @return 包含帖子列表和总数的结果
     */
    @GetMapping
    public Result findAllHotel(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam String username) {
        List<TravelPost> travelpost = travelPostService.getfindAllPosts(page, pageSize, keyword, username);
        int total = travelPostService.countPosts(keyword, username);
        Map<String, Object> result = new HashMap<>();
        result.put("list", travelpost);
        result.put("total", total);
        return Result.success(result);
    }

    /**
     * 获取所有帖子列表（分页）
     * @param page 当前页码，默认为1
     * @param pageSize 每页显示数量，默认为10
     * @param keyword 搜索关键词（可选）
     * @return 包含帖子列表和总数的结果
     */
    @GetMapping("/all")
    public Result getAllPost(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        List<TravelPost> travelpost = travelPostService.getAllPost(page, pageSize, keyword);
        int total = travelPostService.countAllPosts(keyword);
        Map<String, Object> result = new HashMap<>();
        result.put("list", travelpost);
        result.put("total", total);
        return Result.success(result);
    }

    /**
     * 根据ID获取单个帖子
     * @param id 帖子ID
     * @return 帖子详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<TravelPost> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(travelPostService.getPostById(id));
    }

    /**
     * @param post 帖子对象
     * @return 创建后的帖子
     */
    //新增
    @PostMapping
    @Transactional
    public Result createPost(@RequestBody TravelPost post) {
        // 验证必填字段
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            return Result.error("标题不能为空");
        }
        
        // 处理tags字段
        if (post.getTags() != null && post.getTags().startsWith("[") && post.getTags().endsWith("]")) {
            try {
                String tagsJson = post.getTags();
                ObjectMapper mapper = new ObjectMapper();
                String[] tagsArray = mapper.readValue(tagsJson, String[].class);
                post.setTags(String.join(",", tagsArray));
            } catch (Exception e) {
                return Result.error("标签格式解析失败: " + e.getMessage());
            }
        }
        
        // 处理图片
        if (post.getImages() != null && post.getImages().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(post.getImages());
                post.setImages(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }
        return Result.success(travelPostService.createPost(post));
    }

    /**
     * 更新帖子
     * @param id 帖子ID
     * @param postDetails 更新的帖子内容
     * @return 更新后的帖子
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result updatePost(
            @PathVariable Long id,
            @RequestBody TravelPost postDetails) {
        // 处理图片
        if (postDetails.getImages() != null && postDetails.getImages().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(postDetails.getImages());
                postDetails.setImages(imageUrl);

                // 删除旧图片
                TravelPost existingPost = travelPostService.getPostById(id);
                if (existingPost != null && existingPost.getImages() != null) {
                    imageUtils.deleteImage(existingPost.getImages());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (postDetails.getImages() == null) {
            // 删除原有图片
            TravelPost existingPost = travelPostService.getPostById(id);
            if (existingPost != null && existingPost.getImages() != null) {
                try {
                    imageUtils.deleteImage(existingPost.getImages());
                } catch (Exception e) {
                    // 文件删除失败不影响主流程
                    System.err.println("删除图片文件失败: " + e.getMessage());
                }
            }
        }
        return Result.success(travelPostService.updatePost(id, postDetails));
    }

    /**
     * 删除帖子
     * @param id 帖子ID
     * @return 空响应
     */
    @DeleteMapping("/{id}")
    public Result deletePost(@PathVariable Long id) {
        travelPostService.deletePost(id);
        //获取帖子信息，包含图片路径
        TravelPost post = travelPostService.getPostById(id);
        if (post != null && post.getImages() != null) {
            try {
                // 删除图片文件
                imageUtils.deleteImage(post.getImages());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        return Result.success("删除成功");
    }

    /**
     * 根据地点搜索帖子
     * @param location 地点名称
     * @return 匹配的帖子列表
     */
    @GetMapping("/search/location")
    public Result searchByLocation(
            @RequestParam String location) {
        return Result.success(travelPostService.searchByLocation(location));
    }

    /**
     * 根据标签搜索帖子
     * @param tag 标签名称
     * @return 匹配的帖子列表
     */
    @GetMapping("/search/tag")
    public Result searchByTag(
            @RequestParam String tag) {
        return Result.success(travelPostService.searchByTag(tag));
    }

    /**
     * 根据关键词搜索帖子
     * @param keyword 搜索关键词
     * @return 匹配的帖子列表
     */
    @GetMapping("/search/keyword")
    public Result searchByKeyword(
            @RequestParam String keyword) {
        return Result.success(travelPostService.searchByKeyword(keyword));
    }
}
