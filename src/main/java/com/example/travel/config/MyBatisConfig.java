package com.example.travel.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.travel.dao")
public class MyBatisConfig {
}