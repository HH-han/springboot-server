package com.example.travel.dto;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class SystemInfo {
    private String hostname;
    private String os;
    private String osName;
    private String kernel;
    private long uptime;
    private int osVersion;
    private int kernelVersion;
    private List<String> loadAvg;
}