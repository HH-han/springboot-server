// MemoryInfo.java
package com.example.travel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoryInfo {
    private long used;
    private long total;
    private long available;
    private long swapTotal;
    private long swapUsed;

}