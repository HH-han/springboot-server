package com.example.travel.service;

import com.example.travel.entity.TravelBlog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface TravelBlogService {
    // 分页查询旅行博客
    List<TravelBlog> getTravel(int page, int pageSize, String keyword);
    // 查询旅行博客总数
    int countTravel(String keyword);
    // 根据ID查询博客详情
    Optional<TravelBlog> getBlogById(Long id);
    // 点赞博客
    void likeBlog(Long id);
    // 收藏博客
    void favoriteBlog(Long id);
    // 保存博客
    void saveBlog(TravelBlog blog);
    // 更新博客
    void updateBlog(TravelBlog blog);
    // 删除博客
    void deleteBlog(Long id);
    // 检查博客是否存在
    boolean existById(Long id);
    // 根据ID获取博客
    TravelBlog getById(Long id);
}