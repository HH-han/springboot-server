// MonitorService.java
package com.example.travel.service;

import com.example.travel.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface SystemMonitorService {

    /**
     * 获取完整的服务器监控数据
     */
    ServerMonitorData getServerMonitorData();

    /**
     * 获取CPU监控数据
     */
    CpuInfo getCpuInfo();

    /**
     * 获取内存监控数据
     */
    MemoryInfo getMemoryInfo();

    /**
     * 获取磁盘监控数据
     */
    DiskInfo getDiskInfo();

    /**
     * 获取网络监控数据
     */
    NetworkInfo getNetworkInfo();

    /**
     * 获取进程信息
     */
    ProcessInfo getProcessInfo();

    /**
     * 获取系统信息
     */
    SystemInfo getSystemInfo();
}