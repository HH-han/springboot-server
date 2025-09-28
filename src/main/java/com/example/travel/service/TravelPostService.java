package com.example.travel.service;

import com.example.travel.entity.TravelPost;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TravelPostService {

    
    // 分页获取用户帖子
    List<TravelPost> getfindAllPosts(int page, int pageSize, String keyword,String username);
    // 分页获取所以
    List<TravelPost> getAllPost(int page, int pageSize, String keyword);
    // 统计帖子数量
    int countPosts(String keyword, String username);
    int countAllPosts(String keyword);
    // 根据ID获取帖子
    TravelPost getPostById(Long id);

    // 创建新帖子
    TravelPost createPost(TravelPost post);

    // 更新帖子
    TravelPost updatePost(Long id, TravelPost post);

    // 删除帖子
    void deletePost(Long id);

    // 根据位置搜索
    List<TravelPost> searchByLocation(String location);

    // 根据标签搜索
    List<TravelPost> searchByTag(String tag);

    // 根据关键字搜索
    List<TravelPost> searchByKeyword(String keyword);


}