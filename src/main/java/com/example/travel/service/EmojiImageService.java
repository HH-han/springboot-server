package com.example.travel.service;

import com.example.travel.entity.EmojiImage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 表情图片服务接口
 */
@Service
public interface EmojiImageService {
    
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
    EmojiImage findById(Integer id);
    
    /**
     * 根据名称查询表情图片
     * @param name 表情图片名称
     * @return 表情图片列表
     */
    List<EmojiImage> findByName(String name);
    
    /**
     * 根据URL查询表情图片
     * @param url 表情图片URL
     * @return 表情图片对象
     */
    EmojiImage findByUrl(String url);
    
    /**
     * 分页查询表情图片
     * @param page 页码
     * @param pageSize 每页大小
     * @param keyword 关键字
     * @return 表情图片列表
     */
    List<EmojiImage> findAllEmojiImages(int page, int pageSize, String keyword);
    
    /**
     * 查询表情图片总数
     * @param keyword 关键字
     * @return 表情图片总数
     */
    int countEmojiImages(String keyword);
    
    /**
     * 新增表情图片
     * @param emojiImage 表情图片对象
     * @return 是否成功
     */
    int insert(EmojiImage emojiImage);
    
    /**
     * 更新表情图片
     * @param emojiImage 表情图片对象
     * @return 是否成功
     */
    int update(EmojiImage emojiImage);
    
    /**
     * 删除表情图片
     * @param id 表情图片ID
     * @return 是否成功
     */
    int delete(Integer id);
    
    /**
     * 检查表情图片是否存在
     * @param id 表情图片ID
     * @return 是否存在
     */
    boolean exists(Integer id);
    
    /**
     * 检查名称是否存在
     * @param name 表情图片名称
     * @return 是否存在
     */
    boolean existsByName(String name);
    
    /**
     * 检查URL是否存在
     * @param url 表情图片URL
     * @return 是否存在
     */
    boolean existsByUrl(String url);
    
    /**
     * 批量插入表情图片
     * @param emojiImages 表情图片列表
     * @return 影响行数
     */
    int batchInsert(List<EmojiImage> emojiImages);
    
    /**
     * 批量删除表情图片
     * @param ids ID列表
     * @return 影响行数
     */
    int batchDelete(List<Integer> ids);
    
    /**
     * 验证表情图片数据
     */
    boolean validateEmojiImage(EmojiImage emojiImage);
    
    /**
     * 获取有效的表情图片列表
     */
    List<EmojiImage> findValidEmojiImages();
    
    /**
     * 根据文件扩展名查找表情图片
     */
    List<EmojiImage> findByFileExtension(String extension);
    
    /**
     * 获取最新的表情图片
     */
    List<EmojiImage> findLatest(int limit);
    
    /**
     * 搜索表情图片（支持名称和URL）
     */
    List<EmojiImage> search(String keyword);
}