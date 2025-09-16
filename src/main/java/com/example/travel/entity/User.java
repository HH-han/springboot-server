package com.example.travel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
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

    // 即时通信相关字段
    @Column(name = "last_login_time")
    @ExcelProperty("最后登录时间")
    @ColumnWidth(20)
    private LocalDateTime lastLoginTime;

    @Column(name = "online_status")
    @ExcelProperty("在线状态")
    @ColumnWidth(20)
    private Integer onlineStatus;

    @Column(name = "websocket_session_id")
    @ExcelProperty("WebSocket会话ID")
    @ColumnWidth(20)
    private String websocketSessionId;

    @Column(name = "device_info")
    @ExcelProperty("设备信息")
    @ColumnWidth(20)
    private String deviceInfo;

    @Column(name = "ip_address")
    @ExcelProperty("IP地址")
    @ColumnWidth(20)
    private String ipAddress;

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

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getWebsocketSessionId() {
        return websocketSessionId;
    }

    public void setWebsocketSessionId(String websocketSessionId) {
        this.websocketSessionId = websocketSessionId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}


