package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.City;
import com.example.travel.entity.Destination;
import com.example.travel.entity.RegionTab;
import com.example.travel.entity.TravelDestination;
import com.example.travel.entity.TravelRecommendation;
import com.example.travel.service.DestinationService;
import com.example.travel.service.PopulardestinationsService;
import com.example.travel.service.TravelRecommendationService;
import com.example.travel.utils.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/public/destination")
public class DestinationController {

    private final DestinationService destinationService;
    private final PopulardestinationsService populardestinationsService;
    private final TravelRecommendationService travelRecommendationService;
    private final ImageUtils imageUtils;

    @Autowired
    public DestinationController(DestinationService destinationService, 
                                PopulardestinationsService populardestinationsService, 
                                TravelRecommendationService travelRecommendationService,
                                ImageUtils imageUtils) {
        this.destinationService = destinationService;
        this.populardestinationsService = populardestinationsService;
        this.travelRecommendationService = travelRecommendationService;
        this.imageUtils = imageUtils;
    }

    @GetMapping("/list")
    public Result listDestinations(
            @RequestParam(required = false) String searchQuery,
            @RequestParam(defaultValue = "全部") String activeCategory,
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize) {
        

        return destinationService.listDestinations(searchQuery, activeCategory, currentPage, pageSize);
    }
    //新增
    @PostMapping
    @Transactional
    public Result addDestination(@RequestBody Destination destination) {
        // 图片处理
        if (destination.getImage() != null && destination.getImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(destination.getImage());
                destination.setImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }

        destinationService.addDestination(destination);
        return Result.success();
    }
    //修改
    @PutMapping("/{id}")
    @Transactional
    public Result updateDestination(@PathVariable long id, @RequestBody Destination destination) {
        // 获取原有目的地信息
        Destination existingDestination = destinationService.getById(id);
        
        // 图片处理
        if (destination.getImage() != null && destination.getImage().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(destination.getImage());
                destination.setImage(imageUrl);

                // 删除旧图片
                if (existingDestination != null && existingDestination.getImage() != null) {
                    imageUtils.deleteImage(existingDestination.getImage());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (destination.getImage() == null && existingDestination != null && existingDestination.getImage() != null) {
            // 删除原有图片
            try {
                imageUtils.deleteImage(existingDestination.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }

        destinationService.updateDestination(destination);
        return Result.success();
    }
    //删除
    @DeleteMapping("/{id}")
    @Transactional
    public Result deleteDestination(@PathVariable Long id) {
        if (!destinationService.exists(id)) {
            return Result.error("错误删除失败");
        }
        
        // 获取目的地信息，包含图片路径
        Destination destination = destinationService.getById(id);
        if (destination != null && destination.getImage() != null) {
            try {
                imageUtils.deleteImage(destination.getImage());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }

        destinationService.deleteDestination(id);
        return Result.success("删除成功");
    }

    // ========== PopulardestinationsService接口实现 ==========

    // RegionTab相关API
    @GetMapping("/region-tab/{id}")
    public Result getRegionTabById(@PathVariable Integer id) {
        RegionTab regionTab = populardestinationsService.getRegionTabById(id);
        return Result.success(regionTab);
    }

    @GetMapping("/region-tabs")
    public Result getAllRegionTabs() {
        List<RegionTab> regionTabs = populardestinationsService.getAllRegionTabs();
        return Result.success(regionTabs);
    }

    @GetMapping("/region-tab/name/{name}")
    public Result getRegionTabByName(@PathVariable String name) {
        RegionTab regionTab = populardestinationsService.getRegionTabByName(name);
        return Result.success(regionTab);
    }

    @PostMapping("/region-tab")
    @Transactional
    public Result addRegionTab(@RequestBody RegionTab regionTab) {
        int result = populardestinationsService.addRegionTab(regionTab);
        return result > 0 ? Result.success("添加成功") : Result.error("添加失败");
    }

    @PutMapping("/region-tab")
    @Transactional
    public Result updateRegionTab(@RequestBody RegionTab regionTab) {
        int result = populardestinationsService.updateRegionTab(regionTab);
        return result > 0 ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/region-tab/{id}")
    @Transactional
    public Result deleteRegionTab(@PathVariable Integer id) {
        int result = populardestinationsService.deleteRegionTab(id);
        return result > 0 ? Result.success("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/region-tabs/count")
    public Result countRegionTabs() {
        int count = populardestinationsService.countRegionTabs();
        return Result.success(count);
    }

    // TravelDestination相关API
    @GetMapping("/travel-destination/{id}")
    public Result getTravelDestinationById(@PathVariable Integer id) {
        TravelDestination travelDestination = populardestinationsService.getTravelDestinationById(id);
        return Result.success(travelDestination);
    }

    @GetMapping("/travel-destinations")
    public Result getAllTravelDestinations() {
        List<TravelDestination> travelDestinations = populardestinationsService.getAllTravelDestinations();
        return Result.success(travelDestinations);
    }

    @GetMapping("/travel-destinations/region/{regionId}")
    public Result getTravelDestinationsByRegionId(@PathVariable Integer regionId) {
        List<TravelDestination> travelDestinations = populardestinationsService.getTravelDestinationsByRegionId(regionId);
        return Result.success(travelDestinations);
    }

    @GetMapping("/travel-destinations/region-name/{regionName}")
    public Result getTravelDestinationsByRegionName(@PathVariable String regionName) {
        List<TravelDestination> travelDestinations = populardestinationsService.getTravelDestinationsByRegionName(regionName);
        return Result.success(travelDestinations);
    }

    @PostMapping("/travel-destination")
    @Transactional
    public Result addTravelDestination(@RequestBody TravelDestination travelDestination) {
        int result = populardestinationsService.addTravelDestination(travelDestination);
        return result > 0 ? Result.success("添加成功") : Result.error("添加失败");
    }

    @PutMapping("/travel-destination")
    @Transactional
    public Result updateTravelDestination(@RequestBody TravelDestination travelDestination) {
        int result = populardestinationsService.updateTravelDestination(travelDestination);
        return result > 0 ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/travel-destination/{id}")
    @Transactional
    public Result deleteTravelDestination(@PathVariable Integer id) {
        int result = populardestinationsService.deleteTravelDestination(id);
        return result > 0 ? Result.success("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/travel-destinations/count")
    public Result countTravelDestinations() {
        int count = populardestinationsService.countTravelDestinations();
        return Result.success(count);
    }

    @GetMapping("/travel-destinations/count/region-name/{regionName}")
    public Result countTravelDestinationsByRegionName(@PathVariable String regionName) {
        int count = populardestinationsService.countTravelDestinationsByRegionName(regionName);
        return Result.success(count);
    }

    // City相关API
    @GetMapping("/city/{id}")
    public Result getCityById(@PathVariable Integer id) {
        City city = populardestinationsService.getCityById(id);
        return Result.success(city);
    }

    @GetMapping("/cities")
    public Result getAllCities() {
        List<City> cities = populardestinationsService.getAllCities();
        return Result.success(cities);
    }

    @GetMapping("/cities/destination/{destinationId}")
    public Result getCitiesByDestinationId(@PathVariable Integer destinationId) {
        List<City> cities = populardestinationsService.getCitiesByDestinationId(destinationId);
        return Result.success(cities);
    }

    @GetMapping("/cities/region-name/{regionName}")
    public Result getCitiesByRegionName(@PathVariable String regionName) {
        List<City> cities = populardestinationsService.getCitiesByRegionName(regionName);
        return Result.success(cities);
    }

    @PostMapping("/city")
    @Transactional
    public Result addCity(@RequestBody City city) {
        int result = populardestinationsService.addCity(city);
        return result > 0 ? Result.success("添加成功") : Result.error("添加失败");
    }

    @PutMapping("/city")
    @Transactional
    public Result updateCity(@RequestBody City city) {
        int result = populardestinationsService.updateCity(city);
        return result > 0 ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/city/{id}")
    @Transactional
    public Result deleteCity(@PathVariable Integer id) {
        int result = populardestinationsService.deleteCity(id);
        return result > 0 ? Result.success("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/cities/count")
    public Result countCities() {
        int count = populardestinationsService.countCities();
        return Result.success(count);
    }

    @GetMapping("/cities/count/destination/{destinationId}")
    public Result countCitiesByDestinationId(@PathVariable Integer destinationId) {
        int count = populardestinationsService.countCitiesByDestinationId(destinationId);
        return Result.success(count);
    }

    @PostMapping("/cities/batch")
    @Transactional
    public Result batchAddCities(@RequestBody List<City> cities) {
        int result = populardestinationsService.batchAddCities(cities);
        return result > 0 ? Result.success("批量添加成功") : Result.error("批量添加失败");
    }

    // 综合查询API
    @GetMapping("/region-tabs-with-destinations")
    public Result getRegionTabsWithDestinations() {
        List<RegionTab> regionTabs = populardestinationsService.getRegionTabsWithDestinations();
        return Result.success(regionTabs);
    }

    @GetMapping("/travel-destinations-with-cities")
    public Result getTravelDestinationsWithCities() {
        List<TravelDestination> travelDestinations = populardestinationsService.getTravelDestinationsWithCities();
        return Result.success(travelDestinations);
    }

    @GetMapping("/cities-with-destination-and-region")
    public Result getCitiesWithDestinationAndRegion() {
        List<City> cities = populardestinationsService.getCitiesWithDestinationAndRegion();
        return Result.success(cities);
    }

    // ========== TravelRecommendationService接口实现 ==========

    // TravelRecommendation相关API
    @GetMapping("/travel-recommendation/{id}")
    public Result getTravelRecommendationById(@PathVariable Integer id) {
        TravelRecommendation travelRecommendation = travelRecommendationService.getById(id);
        return Result.success(travelRecommendation);
    }

    @GetMapping("/travel-recommendations")
    public Result getAllTravelRecommendations() {
        List<TravelRecommendation> travelRecommendations = travelRecommendationService.getAll();
        return Result.success(travelRecommendations);
    }

    @GetMapping("/travel-recommendations/month/{monthId}")
    public Result getTravelRecommendationsByMonthId(@PathVariable Integer monthId) {
        List<TravelRecommendation> travelRecommendations = travelRecommendationService.getByMonthId(monthId);
        return Result.success(travelRecommendations);
    }

    @GetMapping("/travel-recommendations/month-name/{monthName}")
    public Result getTravelRecommendationsByMonthName(@PathVariable String monthName) {
        List<TravelRecommendation> travelRecommendations = travelRecommendationService.getByMonthName(monthName);
        return Result.success(travelRecommendations);
    }

    @GetMapping("/travel-recommendations/list")
    public Result listTravelRecommendations(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        List<TravelRecommendation> travelRecommendations = travelRecommendationService.getRecommendations(page, pageSize, keyword);
        return Result.success(travelRecommendations);
    }

    @PostMapping("/travel-recommendation")
    @Transactional
    public Result addTravelRecommendation(@RequestBody TravelRecommendation travelRecommendation) {
        // 图片处理
        if (travelRecommendation.getImageUrl() != null && travelRecommendation.getImageUrl().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(travelRecommendation.getImageUrl());
                travelRecommendation.setImageUrl(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }

        int result = travelRecommendationService.add(travelRecommendation);
        return result > 0 ? Result.success("添加成功") : Result.error("添加失败");
    }

    @PutMapping("/travel-recommendation/{id}")
    @Transactional
    public Result updateTravelRecommendation(@PathVariable Integer id, @RequestBody TravelRecommendation travelRecommendation) {
        // 获取原有推荐信息
        TravelRecommendation existingRecommendation = travelRecommendationService.getById(id);
        
        // 图片处理
        if (travelRecommendation.getImageUrl() != null && travelRecommendation.getImageUrl().startsWith("data:image")) {
            try {
                String imageUrl = imageUtils.processBase64Image(travelRecommendation.getImageUrl());
                travelRecommendation.setImageUrl(imageUrl);

                // 删除旧图片
                if (existingRecommendation != null && existingRecommendation.getImageUrl() != null) {
                    imageUtils.deleteImage(existingRecommendation.getImageUrl());
                }
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        } else if (travelRecommendation.getImageUrl() == null && existingRecommendation != null && existingRecommendation.getImageUrl() != null) {
            // 删除原有图片
            try {
                imageUtils.deleteImage(existingRecommendation.getImageUrl());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }

        travelRecommendation.setId(id);
        int result = travelRecommendationService.update(travelRecommendation);
        return result > 0 ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/travel-recommendation/{id}")
    @Transactional
    public Result deleteTravelRecommendation(@PathVariable Integer id) {
        if (!travelRecommendationService.exists(id)) {
            return Result.error("旅游推荐不存在");
        }
        
        // 获取推荐信息，包含图片路径
        TravelRecommendation travelRecommendation = travelRecommendationService.getById(id);
        if (travelRecommendation != null && travelRecommendation.getImageUrl() != null) {
            try {
                imageUtils.deleteImage(travelRecommendation.getImageUrl());
            } catch (Exception e) {
                // 文件删除失败不影响主流程
                System.err.println("删除图片文件失败: " + e.getMessage());
            }
        }

        int result = travelRecommendationService.delete(id);
        return result > 0 ? Result.success("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/travel-recommendations/count")
    public Result countTravelRecommendations() {
        int count = travelRecommendationService.count();
        return Result.success(count);
    }

    @GetMapping("/travel-recommendations/count/keyword")
    public Result countTravelRecommendationsByKeyword(@RequestParam(required = false) String keyword) {
        int count = travelRecommendationService.countByKeyword(keyword);
        return Result.success(count);
    }

    @GetMapping("/travel-recommendations/count/month-name/{monthName}")
    public Result countTravelRecommendationsByMonthName(@PathVariable String monthName) {
        int count = travelRecommendationService.countByMonthName(monthName);
        return Result.success(count);
    }

    @GetMapping("/travel-recommendations/monthly")
    public Result getMonthlyRecommendations() {
        List<TravelRecommendation> travelRecommendations = travelRecommendationService.getMonthlyRecommendations();
        return Result.success(travelRecommendations);
    }

    @GetMapping("/travel-recommendations/by-month/{monthName}")
    public Result getRecommendationsByMonth(@PathVariable String monthName) {
        List<TravelRecommendation> travelRecommendations = travelRecommendationService.getRecommendationsByMonth(monthName);
        return Result.success(travelRecommendations);
    }

    @PostMapping("/travel-recommendations/batch")
    @Transactional
    public Result batchAddTravelRecommendations(@RequestBody List<TravelRecommendation> travelRecommendations) {
        int result = 0;
        for (TravelRecommendation recommendation : travelRecommendations) {
            try {
                result += travelRecommendationService.add(recommendation);
            } catch (Exception e) {
                // 记录错误日志，继续处理其他数据
                System.err.println("添加旅游推荐失败: " + e.getMessage());
            }
        }
        return result > 0 ? Result.success("批量添加成功") : Result.error("批量添加失败");
    }

    @GetMapping("/travel-recommendations/tag/{tag}")
    public Result getTravelRecommendationsByTag(@PathVariable String tag) {
        // 这里需要扩展Service接口来支持标签搜索
        List<TravelRecommendation> travelRecommendations = travelRecommendationService.getAll().stream()
                .filter(recommendation -> 
                        recommendation.getTags() != null && 
                        recommendation.getTags().contains(tag))
                .toList();
        return Result.success(travelRecommendations);
    }

    @GetMapping("/travel-recommendations/hot")
    public Result getHotTravelRecommendations(@RequestParam(defaultValue = "5") int limit) {
        // 这里需要扩展Service接口来支持热门推荐
        List<TravelRecommendation> travelRecommendations = travelRecommendationService.getAll().stream()
                .sorted((r1, r2) -> r2.getCreatedAt().compareTo(r1.getCreatedAt()))
                .limit(limit)
                .toList();
        return Result.success(travelRecommendations);
    }

    // ========== DestinationCarouselService接口实现 ==========
}