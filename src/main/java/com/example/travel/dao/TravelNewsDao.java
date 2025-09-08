package com.example.travel.dao;

import com.example.travel.entity.TravelNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TravelNewsDao {

    /**
     * 查询所有旅游新闻（分页）
     * @param offset 偏移量
     * @param pageSize 每页数量
     * @param keyword 搜索关键词
     * @return 旅游新闻列表
     */
    List<TravelNews> findAllTravelNews(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("keyword") String keyword);

    /**
     * 查询旅游新闻总数
     * @param keyword 搜索关键词
     * @return 总数
     */
    int countTravelNews(@Param("keyword") String keyword);

    /**
     * 查询所有旅游新闻
     * @return 旅游新闻列表
     */
    List<TravelNews> SelectAllTravelNews();

    /**
     * 根据ID查询单个旅游新闻
     * @param id 新闻ID
     * @return 旅游新闻详情
     */
    TravelNews findById(@Param("id") Long id);

    /**
     * 增加点赞数
     * @param id 新闻ID
     * @return 影响行数
     */
    int incrementLikes(@Param("id") Long id);

    /**
     * 增加收藏数
     * @param id 新闻ID
     * @return 影响行数
     */
    int incrementFavorites(@Param("id") Long id);

    /**
     * 更新旅游新闻
     * @param travelNews 旅游新闻对象
     * @return 影响行数
     */
    int updateTravelNews(TravelNews travelNews);

    /**
     * 新增旅游新闻
     * @param travelNews 旅游新闻对象
     * @return 影响行数
     */
    int insertTravelNews(TravelNews travelNews);

    /**
     * 删除旅游新闻
     * @param id 新闻ID
     * @return 影响行数
     */
    int deleteTravelNews(@Param("id") Long id);

    /**
     * 检查旅游新闻是否存在
     * @param id 新闻ID
     * @return 是否存在
     */
    boolean exists(@Param("id") Long id);
}
