package com.example.travel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CartRequestDTO {
    @NotBlank(message = "商品名称不能为空")
    private String itemName;
    
    @NotNull(message = "商品ID不能为空")
    @Positive(message = "商品ID必须为正数")
    @NotNull
    private Long itemId;

    @Min(value = 1, message = "商品数量必须大于0")
    private String quantity;

    @NotNull(message = "商品金额不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DecimalMin(value = "0.0", inclusive = false, message = "商品金额必须大于0")
    private BigDecimal amount;
    @NotNull(message = "用户名不能为空")
    private String username;

}