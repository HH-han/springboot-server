package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.Food;
import com.example.travel.service.FoodService;
import com.example.travel.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/public/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    // 所有美食
    @GetMapping
    public Result findAllfood(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword){
        List<Food> foods=foodService.getfood(page, pageSize, keyword);
        int total=foodService.countfood(keyword);
        Map<String,Object> result=new HashMap<>();
        result.put("list", foods);
        result.put("total", total);
        return Result.success(result);
    }


    // 根据名称或描述搜索美食
    @GetMapping("/search")
    public Result searchByNameOrDescription(@RequestParam String keyword) {
        List<Food> foods = foodService.searchByNameOrDescription(keyword);
        return Result.success(foods);
    }

    // 根据ID返回美食详情
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Food food = foodService.findById(id);
        return Result.success(food);
    }
    //新增
    @PostMapping
    @Transactional
    public Result createFood(@Validated @RequestBody Food food, BindingResult result) {
        //图片处理
        if (food.getImage() != null && food.getImage().startsWith("data:image")) {
            try {
                String imageUrl = ImageUtils.processBase64Image(food.getImage());
                food.setImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }
        foodService.addFood(food);
        return Result.success(food);
    }
    //修改
    @PutMapping("/{id}")
    @Transactional
    public Result updateFood(@PathVariable Integer id, @Validated @RequestBody Food food, BindingResult result) {
        // 获取原有美食信息
        Food existingFood = foodService.findById(id);
        
        // 图片处理
        if (food.getImage() != null && food.getImage().startsWith("data:image")) {
            try {
                String imageUrl = ImageUtils.processBase64Image(food.getImage());
                food.setImage(imageUrl);

                // 删除旧图片
                if (existingFood != null && existingFood.getImage() != null) {
                    ImageUtils.deleteImage(existingFood.getImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (food.getImage() == null && existingFood != null && existingFood.getImage() != null) {
            // 删除原有图片
            try {
                ImageUtils.deleteImage(existingFood.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }
        
        foodService.updateFood(food);
        return Result.success(food);
    }
    //删除
    @DeleteMapping("/{id}")
    @Transactional
    public Result deleteFood(@PathVariable Long id) {
        // 删除图片
        if (foodService.exists(id)) {
            Food food = foodService.findById(Math.toIntExact(id));
            if (food != null && food.getImage() != null) {
                try {
                    // 删除图片文件
                    ImageUtils.deleteImage(food.getImage());
                } catch (Exception e) {
                    // 文件删除失败不影响主流程
                    System.err.println("删除图片文件失败: " + e.getMessage());
                }
            }
        }
        if (!foodService.exists(id)) {
            return Result.error("错误删除失败");
        }
        foodService.deleteFood(id);
        return Result.success("美食删除成功");
    }
}