package com.example.travel.dao;

import com.example.travel.entity.TravelPost;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface TravelPostDao {
    // 插入帖子基本信息
    int insertPost(TravelPost post);
    // 根据ID查询完整帖子
    TravelPost findById(Long id);
    // 分页查询所有帖子
    List<TravelPost> findAll(
            @Param("offset") int offset,
            @Param("pageSize")
            int pageSize, String username);
    List<TravelPost> findAllPost(
            @Param("offset") int offset,
            @Param("pageSize")
            int pageSize
    );
    // 根据关键字分页查询帖子
    List<TravelPost> findByTitleContaining(@Param("keyword") String keyword, 
                                            @Param("offset") int offset,
                                            @Param("username") String username,
                                            @Param("pageSize") int pageSize);

    List<TravelPost> findAllByTitleContaining(@Param("keyword") String keyword,
                                           @Param("offset") int offset,
                                           @Param("pageSize") int pageSize);
    // 统计所有帖子数量
    int countAll(@Param("keyword") String keyword);
    
    // 根据关键字统计帖子数量
    int countByTitleContaining(@Param("keyword") String keyword);

    // 更新帖子基本信息
    int updatePost(TravelPost post);

    // 删除帖子
    int deleteById(Long id);

    // 根据位置搜索
    List<TravelPost> findByLocation(String location);

    // 根据标签搜索
    List<TravelPost> findByTag(String tag);

    // 根据标题关键字搜索
    List<TravelPost> findByTitleContaining(String keyword);
}