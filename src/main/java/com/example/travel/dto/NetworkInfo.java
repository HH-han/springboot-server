// NetworkInfo.java
package com.example.travel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NetworkInfo {
    private double speed; // KB/s
    private long sent;    // bytes
    private long received; // bytes
}