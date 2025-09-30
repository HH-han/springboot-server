package com.example.travel.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id", nullable = false)
    private TravelDestination destination;

    @Column(name = "city_name", nullable = false, length = 100)
    private String cityName;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public City() {
        this.createdAt = LocalDateTime.now();
    }

    // getter和setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public TravelDestination getDestination() { return destination; }
    public void setDestination(TravelDestination destination) { this.destination = destination; }

    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}