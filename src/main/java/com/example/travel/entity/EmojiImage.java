package com.example.travel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 表情图片实体类
 */
@Data
@Entity
@Table(name = "emoji_image")
public class EmojiImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    
    @Column(name = "url", nullable = false, length = 255)
    private String url;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    /**
     * 便捷方法：获取图片文件名
     */
    public String getFileName() {
        if (url != null && url.contains("/")) {
            return url.substring(url.lastIndexOf("/") + 1);
        }
        return name;
    }

    /**
     * 便捷方法：判断是否为有效URL
     */
    public boolean isValidUrl() {
        return url != null && (url.startsWith("http") || url.startsWith("/"));
    }

    /**
     * 便捷方法：获取图片扩展名
     */
    public String getFileExtension() {
        if (url != null && url.contains(".")) {
            return url.substring(url.lastIndexOf(".") + 1).toLowerCase();
        }
        return "";
    }

    /**
     * 便捷方法：判断是否为图片文件
     */
    public boolean isImageFile() {
        String extension = getFileExtension();
        return extension.equals("jpg") || extension.equals("jpeg") || 
               extension.equals("png") || extension.equals("gif") || 
               extension.equals("webp") || extension.equals("bmp");
    }
}