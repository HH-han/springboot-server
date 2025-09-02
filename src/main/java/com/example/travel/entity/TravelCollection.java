package com.example.travel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Table(name = "travel_collection")
@EqualsAndHashCode(callSuper = false)
public class TravelCollection {
    // 成员变量
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcelProperty("ID")
    @ColumnWidth(20)
    private Integer id;

    @Column(nullable = false, length = 10)
    @ExcelProperty("用户名")
    @ColumnWidth(20)
    private String username;

    @Column(nullable = false, length = 255)
    @ExcelProperty("收藏名")
    @ColumnWidth(20)
    private String collectionname;

    @Column(nullable = false, precision = 10, scale = 2)
    @ExcelProperty("价格")
    @ColumnWidth(20)
    private Double price;

    @Column(nullable = false, length = 100)
    @ExcelProperty("简介")
    @ColumnWidth(20)
    private String profile;

    @Column(nullable = false, length = 20)
    @ExcelProperty("地点")
    @ColumnWidth(20)
    private String location;

    @Column(nullable = false, length = 20)
    @ExcelProperty("特色")
    @ColumnWidth(20)
    private String characteristics;

    @Column(nullable = false, length = 5)
    @ExcelProperty("评分")
    @ColumnWidth(20)
    private String score;

    @Column(nullable = false, length = 255)
    @ExcelProperty("收藏")
    @ColumnWidth(20)
    private String collection;

    @Column(nullable = false, length = 255)
    @ExcelProperty("销量")
    @ColumnWidth(20)
    private String sales;

    @Column(nullable = false, length = 255)
    @ExcelProperty("图片")
    @ColumnWidth(20)
    private String image;

    @Column(nullable = false, length = 10)
    @ExcelProperty("分类")
    @ColumnWidth(20)
    private String classification;

    @Column(name = "create_time", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    private LocalDateTime createTime;

    @Column(name = "update_time", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @ExcelProperty("更新时间")
    @ColumnWidth(20)
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCollectionname() {
        return collectionname;
    }

    public void setCollectionname(String collectionname) {
        this.collectionname = collectionname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}