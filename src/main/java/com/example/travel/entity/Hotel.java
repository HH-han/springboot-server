package com.example.travel.entity;

import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "hotel")
public class Hotel {
    private Long id; // 卡片ID
    private Double sales;//评价
    private Double evaluation;//销量
    private String hotelName; // 卡片标题
    private String hotelDescription; // 卡片副标题
    private String hotelImage; // 卡片图片（Base64或URL）
    private BigDecimal hotelPrice;//价格
    private LocalDateTime created_at; // 创建时间
    private LocalDateTime updated_at; // 更新时


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

    public String getHotelName() {

        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDescription() {

        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {

        this.hotelDescription = hotelDescription;
    }

    public String getHotelImage() {

        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {

        this.hotelImage = hotelImage;
    }

    public BigDecimal getHotelPrice() {

        return hotelPrice;
    }

    public void setHotelPrice(BigDecimal hotelPrice) {

        this.hotelPrice = hotelPrice;
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
}
