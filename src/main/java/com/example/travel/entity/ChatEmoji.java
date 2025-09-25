package com.example.travel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 聊天表情实体类
 */
@Data
@Entity
@Table(name = "chat_meoji")
public class ChatEmoji {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "emoji_code", nullable = false, length = 50)
    private String emojiCode;
    
    @Column(name = "emoji_char", nullable = false, length = 10)
    private String emojiChar;
    
    @Column(name = "type", nullable = false, length = 20)
    private String type;
    
    @Column(name = "description", length = 100)
    private String description;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    // 便捷方法：获取表情显示文本
    public String getDisplayText() {
        return emojiChar + " " + (description != null ? description : emojiCode);
    }

    // 便捷方法：判断是否为系统表情
    public boolean isSystemEmoji() {
        return "SYSTEM".equals(type);
    }

    // 便捷方法：判断是否为自定义表情
    public boolean isCustomEmoji() {
        return "CUSTOM".equals(type);
    }
}