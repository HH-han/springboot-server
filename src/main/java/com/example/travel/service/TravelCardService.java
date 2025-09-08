package com.example.travel.service;

import com.example.travel.entity.TravelCard;

import java.util.List;

public interface TravelCardService {
    //查询酒店总数
    List<TravelCard> getTravelCard(int page, int pageSize, String keyword);
    //查询酒店总数
    int countTravelCard(String keyword);
    //新增
    int addTravelCard(TravelCard travelCard);
    //修改
    int updateTravelCard(TravelCard travelCard);
    //删除
    boolean existById(Long id);

    void deleteTravelCard(Long id);

    TravelCard getCardById(Long id);

    Object searchByName(String name);

    Object searchByKeyword(String keyword);

    /**
     * 根据分类搜索旅游卡
     * @param category 分类名称
     * @return 匹配的旅游卡列表
     */
    List<TravelCard> searchByCategory(String category);

    /**
     * 根据徽章类型搜索旅游卡
     * @param badgeType 徽章类型
     * @return 匹配的旅游卡列表
     */
    List<TravelCard> searchByBadgeType(String badgeType);
}
