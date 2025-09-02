package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.TravelCollection;
import com.example.travel.service.TravelCollectionService;
import com.example.travel.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public/travel-collections")
public class TravelCollectionController {
    //记录器
    Logger logger = LoggerFactory.getLogger(TravelCollectionController.class);

    @Autowired
    private TravelCollectionService travelCollectionService;

    // 查询所有收藏
    @GetMapping
    public Result findAll() {
        List<TravelCollection> collections = travelCollectionService.findAll();
        logger.info("查询成功");
        return Result.success(collections);
    }

    // 根据id查询收藏
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        TravelCollection collection = travelCollectionService.findById(id);
        if (collection != null) {
            return Result.success(collection);
        } else {
            logger.warn("未找到收藏");
            return Result.error("未找到收藏");
        }
    }

    // 根据用户名查询收藏
    @GetMapping("/username/{username}")
    public Result findByUsername(@PathVariable String username) {
        List<TravelCollection> collections = travelCollectionService.findByUsername(username);
        return Result.success(collections);
    }

    // 分页查询收藏
    @GetMapping("/search")
    public Result findAllCollections(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        List<TravelCollection> travelCollection = travelCollectionService.findAllCollections(page, pageSize, keyword);
        int total = travelCollectionService.countCollection(keyword);
        Map<String, Object> result = new HashMap<>();
        result.put("list", travelCollection);
        result.put("total", total);
        return Result.success(result);
    }

    // 查询收藏总数
    @GetMapping("/count")
    public Result countCollection(@RequestParam(required = false) String keyword) {
        int count = travelCollectionService.countCollection(keyword);
        return Result.success(count);
    }

    // 新增收藏
    @PostMapping
    public Result insertCollection(@RequestBody TravelCollection travelCollection) {
        if (travelCollection.getUsername() == null || travelCollection.getUsername().trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (travelCollection.getCollectionname() == null || travelCollection.getCollectionname().trim().isEmpty()) {
            return Result.error("收藏名不能为空");
        }
        // 设置自动生成的时间字段
        travelCollection.setCreateTime(LocalDateTime.now());
        // 处理图片
        if (travelCollection.getImage() != null && travelCollection.getImage().startsWith("data:image")) {
            try {
                String imageUrl = ImageUtils.processBase64Image(travelCollection.getImage());
                travelCollection.setImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }
        
        int result = travelCollectionService.insertCollection(travelCollection);
        if (result > 0) {
            return Result.success(travelCollection);
        } else {
            return Result.error("新增失败");
        }
    }

    // 修改收藏
    @PutMapping("/{id}")
    public Result updateCollection(@PathVariable Integer id, @RequestBody TravelCollection travelCollection) {
        travelCollection.setId(id);
        travelCollection.setUpdateTime(LocalDateTime.now());
        // 处理图片
        if (travelCollection.getImage() != null && travelCollection.getImage().startsWith("data:image")) {
            try {
                String imageUrl = ImageUtils.processBase64Image(travelCollection.getImage());
                travelCollection.setImage(imageUrl);
                
                // 删除旧图片
                TravelCollection existingCollection = travelCollectionService.findById(id);
                if (existingCollection != null && existingCollection.getImage() != null) {
                    ImageUtils.deleteImage(existingCollection.getImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (travelCollection.getImage() == null) {
            // 删除原有图片
            TravelCollection existingCollection = travelCollectionService.findById(id);
            if (existingCollection != null && existingCollection.getImage() != null) {
                try {
                    ImageUtils.deleteImage(existingCollection.getImage());
                } catch (Exception e) {
                    // 文件删除失败不影响主流程
                    System.err.println("删除图片文件失败: " + e.getMessage());
                }
            }
        }
        
        int result = travelCollectionService.updateCollection(travelCollection);
        if (result > 0) {
            return Result.success(travelCollection);
        } else {
            logger.warn("更新失败");
            return Result.error("更新失败");
        }
    }

    // 删除收藏
    @DeleteMapping("/{id}")
    public Result deleteCollection(@PathVariable Integer id) {
        //获取收藏信息，包含图片路径
        TravelCollection collection = travelCollectionService.findById(id);
        if (collection != null && collection.getImage() != null) {
            try {
                // 删除图片文件
                ImageUtils.deleteImage(collection.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        
        int result = travelCollectionService.deleteCollection(id);
        if (result > 0) {
            return Result.success("删除成功");
        } else {
            logger.warn("删除失败");
            return Result.error("删除失败");
        }
    }

    // 多条件搜索收藏
    @GetMapping("/search-advanced")
    public Result searchCollections(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String collectionname,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String classification) {
        List<TravelCollection> collections = travelCollectionService.searchCollections(id, username, collectionname, location, classification);
        logger.info("搜索成功");
        return Result.success(collections);
    }
}