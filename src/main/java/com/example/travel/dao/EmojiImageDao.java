package com.example.travel.dao;

import com.example.travel.entity.EmojiImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表情图片DAO接口
 */
@Mapper
public interface EmojiImageDao {
    
    /**
     * 查询所有表情图片
     * @return 表情图片列表
     */
    List<EmojiImage> findAll();
    
    /**
     * 根据ID查询表情图片
     * @param id 表情图片ID
     * @return 表情图片对象
     */
    EmojiImage findById(@Param("id") Integer id);
    
    /**
     * 根据名称查询表情图片
     * @param name 表情图片名称
     * @return 表情图片列表
     */
    List<EmojiImage> findByName(@Param("name") String name);
    
    /**
     * 根据URL查询表情图片
     * @param url 表情图片URL
     * @return 表情图片对象
     */
    EmojiImage findByUrl(@Param("url") String url);
    
    /**
     * 分页查询表情图片
     * @param offset 偏移量
     * @param pageSize 页面大小
     * @param keyword 关键字
     * @return 表情图片列表
     */
    List<EmojiImage> findAllEmojiImages(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize,
            @Param("keyword") String keyword);
    
    /**
     * 查询表情图片总数
     * @param keyword 关键字
     * @return 表情图片总数
     */
    int countEmojiImages(@Param("keyword") String keyword);
    
    /**
     * 新增表情图片
     * @param emojiImage 表情图片对象
     * @return 影响行数
     */
    int insert(EmojiImage emojiImage);
    
    /**
     * 更新表情图片
     * @param emojiImage 表情图片对象
     * @return 影响行数
     */
    int update(EmojiImage emojiImage);
    
    /**
     * 删除表情图片
     * @param id 表情图片ID
     * @return 影响行数
     */
    int delete(@Param("id") Integer id);
    
    /**
     * 检查表情图片是否存在
     * @param id 表情图片ID
     * @return 是否存在
     */
    boolean exists(@Param("id") Integer id);
    
    /**
     * 检查名称是否存在
     * @param name 表情图片名称
     * @return 是否存在
     */
    boolean existsByName(@Param("name") String name);
    
    /**
     * 检查URL是否存在
     * @param url 表情图片URL
     * @return 是否存在
     */
    boolean existsByUrl(@Param("url") String url);
    
    /**
     * 批量插入表情图片
     * @param emojiImages 表情图片列表
     * @return 影响行数
     */
    int batchInsert(@Param("list") List<EmojiImage> emojiImages);
    
    /**
     * 批量删除表情图片
     * @param ids ID列表
     * @return 影响行数
     */
    int batchDelete(@Param("ids") List<Integer> ids);
}