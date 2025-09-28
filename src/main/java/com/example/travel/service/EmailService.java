package com.example.travel.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    /**
     * 发送验证码邮件
     * @param toEmail 收件人邮箱
     * @param code 验证码
     */
    void sendVerificationCode(String toEmail, String code);
}