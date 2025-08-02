package com.example.travel.dto;
import lombok.Data;
import java.util.List;
@Data
public class ServerMonitorData {
    private CpuInfo cpu;
    private MemoryInfo memory;
    private DiskInfo disk;
    private NetworkInfo network;
    private int processCount;
    private List<ProcessInfo> processes;
    private SystemInfo system;
}