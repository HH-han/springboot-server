package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.TravelCard;
import com.example.travel.service.TravelCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/public/travelcard")
public class TravelCardController {
    @Autowired
    private TravelCardService travelCardService;
    //查询
    @GetMapping
    public Result findAllTravelCard(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword){
        List<TravelCard> travelCard=travelCardService.getTravelCard(page, pageSize, keyword);
        int total=travelCardService.countTravelCard(keyword);
        Map<String ,Object> result=new HashMap<>();
        result.put("list", travelCard);
        result.put("total", total);
        return Result.success(result);
    }
}
