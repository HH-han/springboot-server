package com.example.travel.service;

import com.example.travel.entity.ChatEmoji;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
/**
 * 聊天表情服务接口
 */
public interface ChatEmojiService {
    
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
    ChatEmoji findById(Integer id);
    
    /**
     * 根据表情代码查询表情
     * @param emojiCode 表情代码
     * @return 表情对象
     */
    ChatEmoji findByEmojiCode(String emojiCode);
    
    /**
     * 根据类型查询表情
     * @param type 表情类型
     * @return 表情列表
     */
    List<ChatEmoji> findByType(String type);
    
    /**
     * 分页查询表情
     * @param page 页码
     * @param pageSize 每页大小
     * @param keyword 关键字
     * @return 表情列表
     */
    List<ChatEmoji> findAllEmojis(int page, int pageSize, String keyword);
    
    /**
     * 查询表情总数
     * @param keyword 关键字
     * @return 表情总数
     */
    int countEmojis(String keyword);
    
    /**
     * 新增表情
     * @param chatEmoji 表情对象
     * @return 是否成功
     */
    int insert(ChatEmoji chatEmoji);
    
    /**
     * 更新表情
     * @param chatEmoji 表情对象
     * @return 是否成功
     */
    int update(ChatEmoji chatEmoji);
    
    /**
     * 删除表情
     * @param id 表情ID
     * @return 是否成功
     */
    int delete(Integer id);
    
    /**
     * 检查表情是否存在
     * @param id 表情ID
     * @return 是否存在
     */
    boolean exists(Integer id);
}