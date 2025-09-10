package com.example.travel.exception;

import com.example.travel.common.Result;
import com.example.travel.common.PaymentMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;
import org.springframework.validation.FieldError;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        FieldError fieldError = (FieldError) error;
                        return fieldError.getDefaultMessage();
                    }
                    return error.getDefaultMessage();
                })
                .collect(Collectors.joining(", "));
        return Result.fail(400, errorMessage);
    }

    @ExceptionHandler(PaymentException.class)
    public Result handlePaymentException(PaymentException ex) {
        log.error("支付业务异常: {}", ex.getMessage(), ex);
        return Result.fail(400, ex.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, IllegalArgumentException.class})
    public Result handleDeserializationException(Exception ex) {
        if (ex.getCause() instanceof IllegalArgumentException || ex.getMessage().contains("PaymentMethod")) {
            log.warn("无效支付方式参数: {}", ex.getMessage());
            return Result.fail(400, "请使用以下支付方式: " + String.join("/", PaymentMethod.getChineseNames()));
        }
        log.warn("请求参数解析失败: {}", ex.getMessage());
        return Result.fail(400, "请求参数格式错误，请检查JSON格式");
    }

    @ExceptionHandler(Exception.class)
    public Result handleGenericException(Exception ex) {
        log.error("系统异常: {}", ex.getMessage());
        return Result.fail(500, "系统异常，请联系管理员");
    }
}