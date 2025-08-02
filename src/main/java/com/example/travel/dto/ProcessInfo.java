// ProcessInfo.java
package com.example.travel.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProcessInfo {
    private int pid;
    private String name;
    private double cpu;
    private long memory;
    private String state;
    private int total;
    private int running;
    private List<ProcessInfo> processes;
    private long cpuUsage;
    private long memoryUsage;

}