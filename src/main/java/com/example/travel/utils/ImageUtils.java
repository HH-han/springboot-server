package com.example.travel.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

public class ImageUtils {
    
    private static final String UPLOAD_DIR = "D:/Image/";
    private static final String BASE_URL = "http://localhost:2025/upload/";
    
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
            java.nio.file.Files.createDirectories(uploadPath);
        }

        // 保存文件
        Path filePath = uploadPath.resolve(fileName);
        java.nio.file.Files.write(filePath, imageBytes);

        // 返回图片URL
        return BASE_URL + fileName;
    }
    
    public static String processMultipartFile(MultipartFile file) throws Exception {
        // 验证文件类型
        String[] allowedTypes = {"image/jpeg", "image/png", "image/gif", "image/webp"};
        if (!Arrays.asList(allowedTypes).contains(file.getContentType())) {
            throw new Exception("仅支持JPEG、PNG、GIF或WEBP格式的图片");
        }

        // 验证文件大小 (限制2MB)
        long maxSize = 2 * 1024 * 1024;
        if (file.getSize() > maxSize) {
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