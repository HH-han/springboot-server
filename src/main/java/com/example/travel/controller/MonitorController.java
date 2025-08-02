package com.example.travel.controller;
import com.example.travel.dto.ServerMonitorData;
import com.example.travel.service.SystemMonitorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // 允许所有跨域请求
public class MonitorController {

    private final SystemMonitorService monitorService;

    public MonitorController(SystemMonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @GetMapping("/server-monitor")
    public ServerMonitorData getServerMonitorData() {
        return monitorService.getServerMonitorData();
    }
}