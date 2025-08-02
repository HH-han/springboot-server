package com.example.travel.dao;

import com.example.travel.entity.TravelRecommend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TravelRecommendDao {
    //查询所有
    List<TravelRecommend> findAllRecommend(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("keyword") String keyword);
    //查询总数
    int countRecommend(@Param("keyword") String keyword);
    //新增
    int insertRecommend(TravelRecommend travelRecommend);
    //修改
    int updateRecommend(TravelRecommend travelRecommend);
    //删除
    boolean exists(@Param("id") Long id);

    int deleteById(@Param("id") Long id);
    //根据id查询
    TravelRecommend findById(@Param("id") Long id);
}
