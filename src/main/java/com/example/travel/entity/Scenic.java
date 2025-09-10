package com.example.travel.entity;

import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "travel_scenic")
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
    private String location;//地址
    private String type;//类型
    private String facility;//设施
    private String traffic;//交通
    private String status;//状态
    private String feature;//特色
    private String phone;//电话
    private String star;//星级
    private String service;//服务;



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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
