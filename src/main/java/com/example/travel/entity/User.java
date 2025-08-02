package com.example.travel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
public class User {
    // 成员变量
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcelProperty("ID")
    @ColumnWidth(20)
    private Integer id;
    @Column(name = "avatar_url")
    @ExcelProperty("头像")
    @ColumnWidth(20)
    private String image;
    @Column(unique = true, nullable = false, length = 50)
    @ExcelProperty("用户名")
    @ColumnWidth(20)
    private String username;
    @Column(unique = true, nullable = false)
    @ExcelProperty("邮箱")
    @ColumnWidth(20)
    private String email;
    @Column(nullable = false)
    @ExcelProperty("密码")
    @ColumnWidth(20)
    private String password;
    @Column(name = "mobile", unique = true)
    @ExcelProperty("手机号")
    @ColumnWidth(20)
    private String phone;
    @Column(name = "display_name")
    @ExcelProperty("昵称")
    @ColumnWidth(20)
    private String nickname;
    @Column(name = "create_time", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    private LocalDateTime createTime;
    @Column(name = "updata_time", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @ExcelProperty("更新时间")
    @ColumnWidth(20)
    private LocalDateTime updateTime;
    @Column(name = "signature")
    @ExcelProperty("个性签名")
    @ColumnWidth(20)
    private String signature;
    @Column(name = "travelmage")
    @ExcelProperty("旅行经历")
    @ColumnWidth(20)
    private String travelmage;
    @Column(name = "experience")
    @ExcelProperty("个人经历")
    @ColumnWidth(20)
    private String experience;
    @Column(name = "userId")
    @ExcelProperty("用户ID")
    @ColumnWidth(20)
    private long userId;
    @Column(name = "permissions")
    @ExcelProperty("权限")
    @ColumnWidth(20)
    private int permissions;
    @Column(name = "status")
    @ExcelProperty("状态")
    @ColumnWidth(20)
    private int status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTravelmage() {
        return travelmage;
    }

    public void setTravelmage(String travelmage) {
        this.travelmage = travelmage;
    }

    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}


