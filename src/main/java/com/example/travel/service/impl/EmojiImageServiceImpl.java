package com.example.travel.service.impl;

import com.example.travel.dao.EmojiImageDao;
import com.example.travel.entity.EmojiImage;
import com.example.travel.service.EmojiImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 表情图片服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EmojiImageServiceImpl implements EmojiImageService {

    private final EmojiImageDao emojiImageDao;

    @Override
    @Transactional(readOnly = true)
    public List<EmojiImage> findAll() {
        log.info("查询所有表情图片");
        return emojiImageDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EmojiImage findById(Integer id) {
        log.info("根据ID查询表情图片: {}", id);
        return emojiImageDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmojiImage> findByName(String name) {
        log.info("根据名称查询表情图片: {}", name);
        return emojiImageDao.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public EmojiImage findByUrl(String url) {
        log.info("根据URL查询表情图片: {}", url);
        return emojiImageDao.findByUrl(url);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmojiImage> findAllEmojiImages(int page, int pageSize, String keyword) {
        log.info("分页查询表情图片, 页码: {}, 大小: {}, 关键字: {}", page, pageSize, keyword);
        int offset = (page - 1) * pageSize;
        return emojiImageDao.findAllEmojiImages(offset, pageSize, keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public int countEmojiImages(String keyword) {
        log.info("查询表情图片总数, 关键字: {}", keyword);
        return emojiImageDao.countEmojiImages(keyword);
    }

    @Override
    public int insert(EmojiImage emojiImage) {
        log.info("新增表情图片: {}", emojiImage.getName());
        
        // 验证数据
        if (!validateEmojiImage(emojiImage)) {
            throw new IllegalArgumentException("表情图片数据验证失败");
        }
        
        // 设置创建时间和更新时间
        emojiImage.setCreatedAt(LocalDateTime.now());
        emojiImage.setUpdatedAt(LocalDateTime.now());
        
        return emojiImageDao.insert(emojiImage);
    }

    @Override
    public int update(EmojiImage emojiImage) {
        log.info("更新表情图片: {}", emojiImage.getId());
        
        // 检查是否存在
        if (!emojiImageDao.exists(emojiImage.getId())) {
            throw new IllegalArgumentException("表情图片不存在: " + emojiImage.getId());
        }
        
        // 验证数据
        if (!validateEmojiImage(emojiImage)) {
            throw new IllegalArgumentException("表情图片数据验证失败");
        }
        
        // 设置更新时间
        emojiImage.setUpdatedAt(LocalDateTime.now());
        
        return emojiImageDao.update(emojiImage);
    }

    @Override
    public int delete(Integer id) {
        log.info("删除表情图片: {}", id);
        
        if (!emojiImageDao.exists(id)) {
            throw new IllegalArgumentException("表情图片不存在: " + id);
        }
        
        return emojiImageDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Integer id) {
        return emojiImageDao.exists(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return emojiImageDao.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUrl(String url) {
        return emojiImageDao.existsByUrl(url);
    }

    @Override
    public int batchInsert(List<EmojiImage> emojiImages) {
        log.info("批量插入表情图片, 数量: {}", emojiImages.size());
        
        // 验证每个表情图片
        for (EmojiImage emojiImage : emojiImages) {
            if (!validateEmojiImage(emojiImage)) {
                throw new IllegalArgumentException("表情图片数据验证失败: " + emojiImage.getName());
            }
            
            // 设置时间
            emojiImage.setCreatedAt(LocalDateTime.now());
            emojiImage.setUpdatedAt(LocalDateTime.now());
        }
        
        return emojiImageDao.batchInsert(emojiImages);
    }

    @Override
    public int batchDelete(List<Integer> ids) {
        log.info("批量删除表情图片, 数量: {}", ids.size());
        return emojiImageDao.batchDelete(ids);
    }

    @Override
    public boolean validateEmojiImage(EmojiImage emojiImage) {
        if (emojiImage == null) {
            return false;
        }
        
        // 验证名称
        if (!StringUtils.hasText(emojiImage.getName()) || emojiImage.getName().length() > 50) {
            return false;
        }
        
        // 验证URL
        if (!StringUtils.hasText(emojiImage.getUrl()) || emojiImage.getUrl().length() > 255) {
            return false;
        }
        
        // 验证URL格式
        if (!emojiImage.isValidUrl()) {
            return false;
        }
        
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmojiImage> findValidEmojiImages() {
        log.info("查询有效的表情图片");
        
        // 获取所有表情图片，然后过滤有效的
        List<EmojiImage> all = emojiImageDao.findAll();
        List<EmojiImage> validList = new ArrayList<>();
        
        for (EmojiImage emojiImage : all) {
            if (validateEmojiImage(emojiImage)) {
                validList.add(emojiImage);
            }
        }
        
        return validList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmojiImage> findByFileExtension(String extension) {
        log.info("根据文件扩展名查询表情图片: {}", extension);
        
        if (!StringUtils.hasText(extension)) {
            return new ArrayList<>();
        }
        
        // 获取所有表情图片，然后过滤指定扩展名的
        List<EmojiImage> all = emojiImageDao.findAll();
        List<EmojiImage> filteredList = new ArrayList<>();
        
        for (EmojiImage emojiImage : all) {
            if (emojiImage.getFileExtension().equalsIgnoreCase(extension)) {
                filteredList.add(emojiImage);
            }
        }
        
        return filteredList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmojiImage> findLatest(int limit) {
        log.info("查询最新的表情图片, 数量: {}", limit);
        
        if (limit <= 0) {
            return new ArrayList<>();
        }
        
        // 获取所有表情图片，然后按创建时间排序并限制数量
        List<EmojiImage> all = emojiImageDao.findAll();
        all.sort((e1, e2) -> e2.getCreatedAt().compareTo(e1.getCreatedAt()));
        
        return all.stream().limit(limit).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmojiImage> search(String keyword) {
        log.info("搜索表情图片: {}", keyword);
        
        if (!StringUtils.hasText(keyword)) {
            return findAll();
        }
        
        // 获取所有表情图片，然后过滤包含关键字的
        List<EmojiImage> all = emojiImageDao.findAll();
        List<EmojiImage> searchResults = new ArrayList<>();
        
        for (EmojiImage emojiImage : all) {
            if (emojiImage.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                emojiImage.getUrl().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(emojiImage);
            }
        }
        
        return searchResults;
    }
}