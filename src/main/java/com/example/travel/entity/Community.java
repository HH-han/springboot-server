package com.example.travel.entity;

import java.util.Date;

public class Community {
    // 定义社区字段
    private Integer id; // 社区ID，主键
    private String name; // 社区名称
    private String description; // 社区描述或简介
    private String imageUrl; // 社区封面图片的URL（可选）
    private Integer memberCount; // 社区成员数量
    private Date createdAt; // 社区创建时间
    private Date updatedAt; // 社区最后更新时间
    private String creatorId; // 创建者的用户ID（可选，关联用户表）

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    // toString 方法，方便调试
    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", memberCount=" + memberCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", creatorId='" + creatorId + '\'' +
                '}';
    }
}