package com.example.travel.dao;

import com.example.travel.entity.TravelCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelCardDao {
    //查询所有酒店
    List<TravelCard> findAllTravelCard(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("keyword") String keyword);
    //查询酒店总数
    int countTravelCard(@Param("keyword") String keyword);
    //新增
    int insertTravelCard(TravelCard travelCard);
    //修改
    int updateTravelCard(TravelCard travelCard);
    //删除
    boolean exists(@Param("id") Long id);
    //删除id查询
    int deleteById(@Param("id") Long id);
    //id查询
    TravelCard getCardById(Long id);

    /**
     * 根据分类搜索旅游卡
     * @param category 分类名称
     * @return 匹配的旅游卡列表
     */
    List<TravelCard> findByCategory(@Param("category") String category);

    /**
     * 根据徽章类型搜索旅游卡
     * @param badgeType 徽章类型
     * @return 匹配的旅游卡列表
     */
    List<TravelCard> findByBadgeType(@Param("badgeType") String badgeType);

    /**
     * 根据名称搜索旅游卡
     * @param name 名称
     * @return 匹配的旅游卡列表
     */
    List<TravelCard> findByName(@Param("name") String name);

    /**
     * 根据关键词搜索旅游卡
     * @param keyword 关键词
     * @return 匹配的旅游卡列表
     */
    List<TravelCard> findByKeyword(@Param("keyword") String keyword);
}
