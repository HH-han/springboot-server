package com.example.travel.utils;

import com.example.travel.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import javax.xml.bind.DatatypeConverter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

public class ImageUtils {
    //记录器
    static Logger logger = LoggerFactory.getLogger(UserController.class);

    private static String UPLOAD_DIR = System.getProperty("os.name").toLowerCase().contains("win") ? "D:/Image/" : "/tmp/images/";
    private static final String BASE_URL = System.getProperty("os.name").toLowerCase().contains("win") ? "http://localhost:2025/upload/" : "http://localhost:2025/tmp/images/";

    public static void deleteImage(String imageUrl) throws Exception {
        if (imageUrl != null && imageUrl.startsWith(BASE_URL)) {
            String fileName = imageUrl.substring(BASE_URL.length());
            java.nio.file.Path imagePath = java.nio.file.Paths.get(UPLOAD_DIR, fileName);

            if (java.nio.file.Files.exists(imagePath)) {
                java.nio.file.Files.delete(imagePath);
            }
        }
    }
    
    public static String processBase64Image(String base64Image) throws Exception {
        if (base64Image == null || !base64Image.startsWith("data:image")) {
            return null;
        }
        
        // 解码base64图片
        String[] parts = base64Image.split(",");
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(parts[1]);

        // 生成唯一文件名
        String fileName = UUID.randomUUID().toString() + ".png";
        Path uploadPath = Paths.get(UPLOAD_DIR);

        // 确保目录存在
        if (!java.nio.file.Files.exists(uploadPath)) {
            try {
                java.nio.file.Files.createDirectories(uploadPath);
            } catch (Exception e) {
                // 如果创建目录失败，尝试使用/tmp目录
                if (!UPLOAD_DIR.equals("/tmp/images/")) {
                    UPLOAD_DIR = "/tmp/images/";
                    uploadPath = Paths.get(UPLOAD_DIR);
                    java.nio.file.Files.createDirectories(uploadPath);
                } else {
                    logger.error("文件保存失败: {}", fileName, e);
                    throw e;
                }
            }
        }

        // 保存文件
        Path filePath = uploadPath.resolve(fileName);
        java.nio.file.Files.write(filePath, imageBytes);
        logger.info("文件保存成功: {}",fileName+BASE_URL);

        // 返回图片URL
        return BASE_URL + fileName;
    }
    
    public static String processMultipartFile(MultipartFile file) throws Exception {
        // 验证文件类型
        String[] allowedTypes = {"image/jpeg", "image/png", "image/gif", "image/webp"};
        if (!Arrays.asList(allowedTypes).contains(file.getContentType())) {
            logger.error("仅支持JPEG、PNG、GIF或WEBP格式的图片");
            throw new Exception("仅支持JPEG、PNG、GIF或WEBP格式的图片");
        }

        // 验证文件大小 (限制2MB)
        long maxSize = 2 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            logger.error("图片大小不能超过2MB");
            throw new Exception("图片大小不能超过2MB");
        }

        // 生成唯一文件名
        String fileName = UUID.randomUUID().toString() + "." + file.getContentType().split("/")[1];
        Path uploadPath = Paths.get(UPLOAD_DIR);

        // 确保目录存在
        if (!java.nio.file.Files.exists(uploadPath)) {
            java.nio.file.Files.createDirectories(uploadPath);
        }

        // 保存文件
        Path filePath = uploadPath.resolve(fileName);
        file.transferTo(filePath.toFile());

        // 返回图片URL
        return BASE_URL + fileName;
    }
}