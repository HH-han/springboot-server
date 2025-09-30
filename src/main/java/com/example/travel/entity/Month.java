package com.example.travel.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "months")
public class Month {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "month_name", nullable = false, unique = true, length = 20)
    private String monthName;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // 默认构造函数
    public Month() {
    }
    
    // 带参数的构造函数
    public Month(String monthName) {
        this.monthName = monthName;
    }
    
    // Getter和Setter方法
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getMonthName() {
        return monthName;
    }
    
    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return "Month{" +
                "id=" + id +
                ", monthName='" + monthName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}