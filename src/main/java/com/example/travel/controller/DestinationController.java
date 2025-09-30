package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.City;
import com.example.travel.entity.Destination;
import com.example.travel.entity.RegionTab;
import com.example.travel.entity.TravelDestination;
import com.example.travel.service.DestinationService;
import com.example.travel.service.PopulardestinationsService;
import com.example.travel.utils.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/public/destination")
public class DestinationController {

    private final DestinationService destinationService;
    private final PopulardestinationsService populardestinationsService;
    private final ImageUtils imageUtils;

    @Autowired
    public DestinationController(DestinationService destinationService, 
                                PopulardestinationsService populardestinationsService, 
                                ImageUtils imageUtils) {
        this.destinationService = destinationService;
        this.populardestinationsService = populardestinationsService;
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
                String oldFileName = existingDestination.getImage().substring("http://localhost:2025/upload/destination/".length());
                Path oldImagePath = Paths.get("D:/Image/destination/", oldFileName);
                if (Files.exists(oldImagePath)) {
                    Files.delete(oldImagePath);
                }
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
}