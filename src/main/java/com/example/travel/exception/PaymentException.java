package com.example.travel.exception;

public class PaymentException extends RuntimeException {


    private final int code;

    public PaymentException(String message, int code) {
        super(message);
        this.code = code;
    }

    public PaymentException(String message, int code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }



    public PaymentException(String 支付金额必须大于零) {
        this(支付金额必须大于零, 400);
    }

    public int getCode() {
        return code;
    }
}