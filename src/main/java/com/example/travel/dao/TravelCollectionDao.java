package com.example.travel.dao;

import com.example.travel.entity.TravelCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelCollectionDao {

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
    TravelCollection findById(@Param("id") Integer id);

    /**
     * 根据用户名查询收藏
     * @param username 用户名
     * @return 收藏列表
     */
    List<TravelCollection> findByUsername(@Param("username") String username);

    /**
     * 分页查询收藏
     * @param offset 偏移量
     * @param pageSize 页面大小
     * @param keyword 关键字
     * @return 收藏列表
     */
    List<TravelCollection> findAllCollections(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize,
            @Param("keyword") String keyword);

    /**
     * 查询收藏总数
     * @param keyword 关键字
     * @return 收藏总数
     */
    int countCollection(@Param("keyword") String keyword);

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
    int deleteCollection(@Param("id") Integer id);

    /**
     * 多条件搜索收藏
     * @param id 收藏ID
     * @param username 用户名
     * @param collectionname 收藏名
     * @param location 地点
     * @param classification 分类
     * @return 收藏列表
     */
    List<TravelCollection> searchCollections(
            @Param("id") Integer id,
            @Param("username") String username,
            @Param("collectionname") String collectionname,
            @Param("location") String location,
            @Param("classification") String classification);
}