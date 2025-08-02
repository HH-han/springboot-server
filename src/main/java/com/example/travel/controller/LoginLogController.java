package com.example.travel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/system/log/operation")
public class LoginLogController {

    @GetMapping("/list")
    public Object list() {
        return "功能开发中";
    }
}