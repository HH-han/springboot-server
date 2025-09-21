package com.example.travel.dto;

import java.util.Date;

/**
 * Token状态信息类
 */
public class TokenStatus {
    private boolean valid;
    private boolean expired;
    private String username;
    private Date issuedAt;
    private Date expiration;
    private String message;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TokenStatus{" +
                "valid=" + valid +
                ", expired=" + expired +
                ", username='" + username + '\'' +
                ", issuedAt=" + issuedAt +
                ", expiration=" + expiration +
                ", message='" + message + '\'' +
                '}';
    }
}
