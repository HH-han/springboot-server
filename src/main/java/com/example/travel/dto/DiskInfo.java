// DiskInfo.java
package com.example.travel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiskInfo {
    private long used;
    private long total;
    private long readBytes;
    private long writeBytes;

}