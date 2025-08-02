package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResult {
    private Boolean success;
    private String orderId;
    private String message;
}