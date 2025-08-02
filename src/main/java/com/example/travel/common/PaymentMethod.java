package com.example.travel.common;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum PaymentMethod {
    ALIPAY("支付宝"),
    UNIONPAY("银联"),
    WECHAT("微信");


    private final String chineseName;

    PaymentMethod(String chineseName) {
        this.chineseName = chineseName;
    }

    @JsonValue
    public String getChineseName() {
        return chineseName;
    }

    public static List<String> getChineseNames() {
        return Arrays.stream(values())
                .map(paymentMethod -> paymentMethod.chineseName)
                .collect(Collectors.toList());
    }
}