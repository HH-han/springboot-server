package com.example.travel.dao;

import com.example.travel.entity.TravelBlog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TravelBlogDao {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertTravelBlog(TravelBlog blog);

    int updateTravelBlog(TravelBlog blog);

    int deleteTravelBlog(@Param("id") Long id);

    boolean exists(@Param("id") Long id);

    void incrementLikes(@Param("id") Long id);

    void incrementFavorites(@Param("id") Long id);    //查询所有
    List<TravelBlog> findAllTravel(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize,
            @Param("keyword") String keyword);
    //查询
    int countTravel(@Param("keyword") String keyword);
    //List<TravelBlog> findAllTravel();
    TravelBlog findById(Long id);
}