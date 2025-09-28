package com.example.travel.service;

import com.example.travel.entity.TravelCollection;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TravelCollectionService {
    /**
     * 查询所有收藏
     * @return 收藏列表
     */
    List<TravelCollection> findAll();

    /**
     * 根据id查询收藏
     * @param id 收藏ID
     * @return 收藏对象
     */
    TravelCollection findById(Integer id);

    /**
     * 根据用户名查询收藏
     * @param username 用户名
     * @return 收藏列表
     */
    List<TravelCollection> findByUsername(String username);

    /**
     * 分页查询收藏
     * @param page 偏移量
     * @param pageSize 页面大小
     * @param keyword 关键字
     * @return 收藏列表
     */
    List<TravelCollection> findAllCollections(int page, int pageSize, String keyword);

    /**
     * 查询收藏总数
     * @param keyword 关键字
     * @return 收藏总数
     */
    int countCollection(String keyword);

    /**
     * 新增收藏
     * @param travelCollection 收藏对象
     * @return 影响行数
     */
    int insertCollection(TravelCollection travelCollection);

    /**
     * 修改收藏
     * @param travelCollection 收藏对象
     * @return 影响行数
     */
    int updateCollection(TravelCollection travelCollection);

    /**
     * 删除收藏
     * @param id 收藏ID
     * @return 影响行数
     */
    int deleteCollection(Integer id);

    /**
     * 多条件搜索收藏
     * @param id 收藏ID
     * @param username 用户名
     * @param collectionname 收藏名
     * @param location 地点
     * @param classification 分类
     * @return 收藏列表
     */
    List<TravelCollection> searchCollections(Integer id, String username, String collectionname, String location, String classification);
}