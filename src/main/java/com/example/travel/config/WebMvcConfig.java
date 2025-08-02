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

        // Windows 系统配置
        if (os.contains("win")) {
            // 检查D盘是否存在
            File dDrive = new File("D:/");
            if (dDrive.exists()) {
                Path imagePath = Paths.get("D:/Image/");
                if (!Files.exists(imagePath)) {
                    try {
                        Files.createDirectories(imagePath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                registry.addResourceHandler("/upload/**")
                        .addResourceLocations("file:D:/Image/");
                log.info("使用目录: {}", imagePath);
            } else {
                // 如果D盘不存在，使用用户主目录下的images文件夹
                String userHome = System.getProperty("user.home");
                Path userImagePath = Paths.get(userHome + "/images/");
                if (!Files.exists(userImagePath)) {
                    try {
                        Files.createDirectories(userImagePath);
                        log.info("创建的目录: {}", userImagePath);
                    } catch (IOException e) {
                        log.error("创建目录失败: {}", userImagePath, e);
                        throw new RuntimeException(e);
                    }
                }
                registry.addResourceHandler("/upload/**")
                        .addResourceLocations("file:" + userHome + "/images/");
            }
        }
        // Linux/Mac 系统配置
        else {
            // 检查常见Linux图片存储目录
            String[] linuxPaths = {
                    "/mnt/images/",
                    "/data/images/",
                    "/var/www/images/",
                    System.getProperty("user.home") + "/images/"
            };

            for (String path : linuxPaths) {
                File dir = new File(path);
                if (dir.exists() && dir.isDirectory()) {
                    registry.addResourceHandler("/upload/**")
                            .addResourceLocations("file:" + path);
                    log.info("使用目录: {}", path);
                    break;
                } else {
                    try {
                        Files.createDirectories(dir.toPath());
                        log.info("创建的目录: {}", dir.getAbsolutePath());
                    } catch (IOException e) {
                        log.error("创建目录失败: {}", dir.getAbsolutePath(), e);
                        throw new RuntimeException(e);
                    }
                    registry.addResourceHandler("/upload/**")
                            .addResourceLocations("file:" + path);
                    break;
                }
            }
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
}
