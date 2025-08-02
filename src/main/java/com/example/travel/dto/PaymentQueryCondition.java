package com.example.travel.dto;

import com.example.travel.entity.Payment.PaymentStatus;
import lombok.Data;

@Data
public class PaymentQueryCondition {
    private Integer page;
    private Integer pageSize;
    private String keyword;
    private PaymentStatus status;
    
    public int getOffset() {
        return (page - 1) * pageSize;
    }
}