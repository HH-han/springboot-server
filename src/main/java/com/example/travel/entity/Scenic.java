package com.example.travel.entity;

import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "scenic")
public class Scenic {
    private Long id; // 卡片ID
    private Double sales;//评价
    private Double evaluation;//销量
    private String title; // 卡片标题
    private String subtitle; // 卡片副标题
    private String image; // 卡片图片（Base64或URL）
    private LocalDateTime created_at; // 创建时间
    private LocalDateTime updated_at; // 更新时
    private double price;//价格


    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Double evaluation) {
        this.evaluation = evaluation;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getSubtitle() {

        return subtitle;
    }

    public void setSubtitle(String subtitle) {

        this.subtitle = subtitle;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public LocalDateTime getCreated_at() {

        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {

        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {

        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {

        this.updated_at = updated_at;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }
}
