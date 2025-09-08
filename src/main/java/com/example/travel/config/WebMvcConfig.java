package com.example.travel.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 自动检测操作系统
        String os = System.getProperty("os.name").toLowerCase();

        Path imagePath = null;

        // Windows 系统配置
        if (os.contains("win")) {
            // 检查D盘是否存在
            File dDrive = new File("D:/");
            if (dDrive.exists()) {
                imagePath = Paths.get("D:/Image/");
                createDirectoryIfNotExists(imagePath);
                log.info("使用目录: {}", imagePath);
            } else {
                // 如果D盘不存在，使用用户主目录下的images文件夹
                String userHome = System.getProperty("user.home");
                imagePath = Paths.get(userHome, "images");
                createDirectoryIfNotExists(imagePath);
                log.info("创建的目录: {}", imagePath);
            }
        }
        // Linux/Mac 系统配置
        else {
            // Linux系统直接使用/tmp/images目录
            imagePath = Paths.get("/tmp/images/");
            try {
                createDirectoryIfNotExists(imagePath);
                log.info("使用目录: {}", imagePath);
            } catch (Exception e) {
                log.error("无法创建目录: {}", imagePath, e);
                throw new RuntimeException("无法创建图片存储目录: " + imagePath, e);
            }
        }

        if (imagePath != null) {
            registry.addResourceHandler("/upload/**")
                    .addResourceLocations("file:" + imagePath.toString());
        }

        // 添加类路径下的静态资源映射（可选）
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        // 允许访问 .well-known 目录下的文件
        registry.addResourceHandler("/.well-known/**")
                .addResourceLocations("classpath:static/.well-known/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 视图控制器配置
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    private void createDirectoryIfNotExists(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                log.error("创建目录失败: {}", path, e);
                throw new RuntimeException("创建目录失败: " + path, e);
            }
        }
    }
}