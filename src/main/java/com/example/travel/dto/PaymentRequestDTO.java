package com.example.travel.dto;

import com.example.travel.entity.Payment.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {
    public String quantity;
    private Long itemId;
    private String itemName;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String orderId;
    private String username;

}