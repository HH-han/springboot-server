package com.example.travel.service.impl;// EmailServiceImpl.java
import com.example.travel.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendVerificationCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2560177364@qq.com");
        message.setTo(toEmail);
        message.setSubject("您的验证码");
        message.setText("您的验证码是: " + code + "，有效期为5分钟。");
        mailSender.send(message);
    }
}