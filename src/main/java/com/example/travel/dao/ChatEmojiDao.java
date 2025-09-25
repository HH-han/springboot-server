package com.example.travel.dao;

import com.example.travel.entity.ChatEmoji;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 聊天表情DAO接口
 */
@Mapper
public interface ChatEmojiDao {
    
    /**
     * 查询所有表情
     * @return 表情列表
     */
    List<ChatEmoji> findAll();
    
    /**
     * 根据ID查询表情
     * @param id 表情ID
     * @return 表情对象
     */
    ChatEmoji findById(@Param("id") Integer id);
    
    /**
     * 根据表情代码查询表情
     * @param emojiCode 表情代码
     * @return 表情对象
     */
    ChatEmoji findByEmojiCode(@Param("emojiCode") String emojiCode);
    
    /**
     * 根据类型查询表情
     * @param type 表情类型
     * @return 表情列表
     */
    List<ChatEmoji> findByType(@Param("type") String type);
    
    /**
     * 分页查询表情
     * @param offset 偏移量
     * @param pageSize 页面大小
     * @param keyword 关键字
     * @return 表情列表
     */
    List<ChatEmoji> findAllEmojis(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize,
            @Param("keyword") String keyword);
    
    /**
     * 查询表情总数
     * @param keyword 关键字
     * @return 表情总数
     */
    int countEmojis(@Param("keyword") String keyword);
    
    /**
     * 新增表情
     * @param chatEmoji 表情对象
     * @return 影响行数
     */
    int insert(ChatEmoji chatEmoji);
    
    /**
     * 更新表情
     * @param chatEmoji 表情对象
     * @return 影响行数
     */
    int update(ChatEmoji chatEmoji);
    
    /**
     * 删除表情
     * @param id 表情ID
     * @return 影响行数
     */
    int delete(@Param("id") Integer id);
    
    /**
     * 检查表情是否存在
     * @param id 表情ID
     * @return 是否存在
     */
    boolean exists(@Param("id") Integer id);
}