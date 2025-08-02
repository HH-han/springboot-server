// CpuInfo.java
package com.example.travel.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class CpuInfo {
    private double usage;
    private int cores;
    private String model;
    private double temperature;

}