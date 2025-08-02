package com.example.travel.service.impl;
import com.example.travel.dto.*;
import com.example.travel.service.SystemMonitorService;
import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SystemMonitorServiceImpl implements SystemMonitorService {

    private static final SystemInfo SYSTEM_INFO = new SystemInfo();
    private static final HardwareAbstractionLayer HARDWARE = SYSTEM_INFO.getHardware();
    private static final OperatingSystem OS = SYSTEM_INFO.getOperatingSystem();

    // CPU tick缓存
    private long[] prevCpuTicks = new long[CentralProcessor.TickType.values().length];

    // 网络数据缓存
    private long lastNetworkCheckTime = 0;
    private long lastBytesSent = 0;
    private long lastBytesRecv = 0;

    @Override
    @Cacheable(value = "serverMonitorData", key = "'fullData'", unless = "#result == null")
    public ServerMonitorData getServerMonitorData() {
        ServerMonitorData data = new ServerMonitorData();
        data.setCpu(getCpuInfo());
        data.setMemory(getMemoryInfo());
        data.setDisk(getDiskInfo());
        data.setNetwork(getNetworkInfo());
        ProcessInfo processInfo = getProcessInfo();
        data.setProcesses(processInfo != null ? processInfo.getProcesses() : new ArrayList<>());
        data.setProcessCount(processInfo != null ? processInfo.getProcesses().size() : 0);
        data.setSystem(getSystemInfo());
        return data;
    }

    @Override
    @Cacheable(value = "cpuInfo", key = "'current'", unless = "#result == null")
    public CpuInfo getCpuInfo() {
        CentralProcessor processor = HARDWARE.getProcessor();

        // 等待1秒获取准确的CPU使用率
        Util.sleep(1000);

        double usage = processor.getSystemCpuLoadBetweenTicks(prevCpuTicks) * 100;
        prevCpuTicks = processor.getSystemCpuLoadTicks();

        return CpuInfo.builder()
                .usage(Math.round(usage * 10) / 10.0)
                .cores(processor.getLogicalProcessorCount())
                .model(processor.getProcessorIdentifier().getName())
                .temperature(getCpuTemperature())
                .build();
    }

    @Override
    @Cacheable(value = "memoryInfo", key = "'current'", unless = "#result == null")
    public MemoryInfo getMemoryInfo() {
        GlobalMemory memory = HARDWARE.getMemory();
        long total = memory.getTotal();
        long available = memory.getAvailable();

        return MemoryInfo.builder()
                .total(total)
                .used(total - available)
                .available(available)
                .swapTotal(memory.getVirtualMemory().getSwapTotal())
                .swapUsed(memory.getVirtualMemory().getSwapUsed())
                .build();
    }

    @Override
    @Cacheable(value = "diskInfo", key = "'current'", unless = "#result == null")
    public DiskInfo getDiskInfo() {
        List<HWDiskStore> disks = HARDWARE.getDiskStores();
        long total = 0;
        long used = 0;
        long readBytes = 0;
        long writeBytes = 0;

        for (HWDiskStore disk : disks) {
            total += disk.getSize();
            used += disk.getSize() - disk.getWriteBytes();
            readBytes += disk.getReadBytes();
            writeBytes += disk.getWriteBytes();
        }

        return DiskInfo.builder()
                .total(total)
                .used(used)
                .readBytes(readBytes)
                .writeBytes(writeBytes)
                .build();
    }

    @Override
    @Cacheable(value = "networkInfo", key = "'current'", unless = "#result == null")
    public NetworkInfo getNetworkInfo() {
        List<NetworkIF> networks = HARDWARE.getNetworkIFs();
        long bytesSent = 0;
        long bytesRecv = 0;

        for (NetworkIF net : networks) {
            bytesSent += net.getBytesSent();
            bytesRecv += net.getBytesRecv();
            net.updateAttributes();
        }

        // 计算网络速度
        long currentTime = System.currentTimeMillis();
        double speed = 0;

        if (lastNetworkCheckTime > 0) {
            long timeDiff = currentTime - lastNetworkCheckTime;
            if (timeDiff > 0) {
                long sentDiff = bytesSent - lastBytesSent;
                long recvDiff = bytesRecv - lastBytesRecv;
                speed = (sentDiff + recvDiff) / (timeDiff / 1000.0) / 1024; // KB/s
            }
        }

        lastNetworkCheckTime = currentTime;
        lastBytesSent = bytesSent;
        lastBytesRecv = bytesRecv;

        return NetworkInfo.builder()
                .sent(bytesSent)
                .received(bytesRecv)
                .speed(Math.round(speed * 10) / 10.0)
                .build();
    }

    @Override
    @Cacheable(value = "processInfo", key = "'top10'", unless = "#result == null")
    public ProcessInfo getProcessInfo() {
        List<OSProcess> topProcesses = OS.getProcesses();
        List<ProcessInfo> processes = new ArrayList<>();

        for (OSProcess p : topProcesses) {
            processes.add(ProcessInfo.builder()
                    .pid(p.getProcessID())
                    .name(p.getName())
                    .cpuUsage((long) (Math.round(p.getProcessCpuLoadBetweenTicks(p) * 10000) / 100.0))
                    .memoryUsage(p.getResidentSetSize())
                    .state(p.getState().name())
                    .build());
        }

        return ProcessInfo.builder()
                .total(OS.getProcessCount())
                .running(OS.getThreadCount())
                .processes(processes)
                .build();
    }

    @Override
    @Cacheable(value = "systemInfo", key = "'current'", unless = "#result == null")
    public com.example.travel.dto.SystemInfo getSystemInfo() {
        return com.example.travel.dto.SystemInfo.builder()
                .hostname(OS.getNetworkParams().getHostName())
                .osName(OS.getFamily())
                .osVersion(Integer.parseInt(OS.getVersionInfo().getVersion()))
                .kernelVersion(Integer.parseInt(OS.getVersionInfo().getBuildNumber()))
                .uptime(OS.getSystemUptime())
                .loadAvg(getSystemLoadAverage())
                .build();
    }

    // ========== 私有方法 ==========

    private double getCpuTemperature() {
        try {
            Sensors sensors = HARDWARE.getSensors();
            double temp = sensors.getCpuTemperature();
            // 如果温度无效或查询失败，尝试备用方法
            if (temp <= 0) {
                // 备用方法：使用操作系统命令获取温度
                temp = getCpuTemperatureFallback();
            }
            return temp;
        } catch (Exception e) {
            log.warn("获取CPU温度失败，将尝试备用方法", e);
            return getCpuTemperatureFallback();
        }
    }
    
    private double getCpuTemperatureFallback() {
        try {
            // Windows系统下使用WMI命令获取CPU温度
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                try {
                    Process process = Runtime.getRuntime().exec(
                        "wmic /namespace:\\\\root\\wmi PATH MSAcpi_ThermalZoneTemperature get CurrentTemperature");
                    process.waitFor();
                    
                    try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {
                        
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.trim().matches("\\d+")) {
                                // WMI返回的温度是开尔文温度*10
                                double tempKelvin = Double.parseDouble(line.trim()) / 10.0;
                                return tempKelvin - 273.15; // 转换为摄氏度
                            }
                        }
                    }
                } catch (Exception e) {
                    log.warn("WMI命令获取CPU温度失败，将尝试备用方法", e);
                    log.warn("备用方法获取CPU温度失败，将返回0表示未知温度", e);
                    // 尝试其他WMI查询方法
                    return getCpuTemperatureFromOtherWmiQuery();
                }
            }
            
            return 0; // 默认返回0表示未知温度
        } catch (Exception e) {
            log.warn("备用方法获取CPU温度失败", e);
            return 0;
        }
    }
    
    private double getCpuTemperatureFromOtherWmiQuery() {
        try {
            // 尝试使用其他WMI查询路径获取温度
            Process process = Runtime.getRuntime().exec(
                "wmic /namespace:\\\\root\\cimv2 PATH Win32_PerfFormattedData_Counters_ThermalZoneInformation get Temperature");
            process.waitFor();
            
            try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
                
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().matches("\\d+")) {
                        return Double.parseDouble(line.trim());
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            log.warn("备用WMI查询方法获取CPU温度失败", e);
            return 0;
        }
    }

    private List<String> getSystemLoadAverage() {
        double[] loadAvg = HARDWARE.getProcessor().getSystemLoadAverage(3);
        List<String> result = new ArrayList<>();
        for (double load : loadAvg) {
            result.add(String.valueOf(Math.round(load * 100) / 100.0));
        }
        return result;
    }
}