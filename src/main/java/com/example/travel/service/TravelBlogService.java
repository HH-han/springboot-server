package com.example.travel.service;

import com.example.travel.entity.TravelBlog;
import java.util.List;
import java.util.Optional;

public interface TravelBlogService {
    //分页查询
    List<TravelBlog> getTravel(int page, int pageSize, String keyword);
    //查询总数
    int countTravel(String keyword);
    Optional<TravelBlog> getBlogById(Long id);
    void likeBlog(Long id);
    void favoriteBlog(Long id);
    
    void saveBlog(TravelBlog blog);
    void updateBlog(TravelBlog blog);
    void deleteBlog(Long id);
    boolean existById(Long id);

    TravelBlog getById(Long id);
}