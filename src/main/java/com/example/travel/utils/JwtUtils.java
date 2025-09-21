package com.example.travel.utils;
import com.example.travel.dto.TokenStatus;
import io.jsonwebtoken.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    @Getter
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token 已过期");
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            throw new RuntimeException("Token 无效");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Token 为空");
        }
    }

    public String getUsernameFromToken(String token) {
        return validateToken(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        try {
            Claims claims = validateToken(token);
            return claims.getExpiration().before(new Date());
        } catch (RuntimeException e) {
            return true;
        }
    }

    /**
     * 获取Token状态信息
     * @param token JWT token
     * @return Token状态信息对象
     */
    public TokenStatus getTokenStatus(String token) {
        TokenStatus status = new TokenStatus();
        
        if (token == null || token.trim().isEmpty()) {
            status.setValid(false);
            status.setMessage("Token为空");
            return status;
        }
        
        try {
            Claims claims = validateToken(token);
            Date expiration = claims.getExpiration();
            Date now = new Date();
            
            status.setValid(true);
            status.setUsername(claims.getSubject());
            status.setIssuedAt(claims.getIssuedAt());
            status.setExpiration(expiration);
            status.setExpired(expiration.before(now));
            
            if (status.isExpired()) {
                status.setMessage("Token已过期");
            } else {
                long remainingMs = expiration.getTime() - now.getTime();
                long remainingMinutes = remainingMs / (60 * 1000);
                status.setMessage("Token有效，剩余时间: " + remainingMinutes + "分钟");
            }
            
        } catch (RuntimeException e) {
            status.setValid(false);
            status.setMessage(e.getMessage());
        }
        
        return status;
    }



    /**
     * 刷新Token
     * @param token 原始Token
     * @return 新的Token
     */
    public String refreshToken(String token) {
        try {
            Claims claims = validateToken(token);
            String username = claims.getSubject();
            return generateToken(username);
        } catch (RuntimeException e) {
            throw new RuntimeException("Token刷新失败: " + e.getMessage());
        }
    }

    /**
     * 获取Token详细信息
     * @param token JWT token
     * @return Token详细信息Map
     */
    public Map<String, Object> getTokenDetails(String token) {
        Map<String, Object> details = new HashMap<>();
        
        try {
            Claims claims = validateToken(token);
            
            details.put("username", claims.getSubject());
            details.put("issuedAt", claims.getIssuedAt());
            details.put("expiration", claims.getExpiration());
            details.put("valid", true);
            details.put("expired", claims.getExpiration().before(new Date()));
            
            // 计算剩余时间
            long remainingMs = claims.getExpiration().getTime() - new Date().getTime();
            details.put("remainingMinutes", remainingMs / (60 * 1000));
            details.put("remainingSeconds", remainingMs / 1000);
            
        } catch (RuntimeException e) {
            details.put("valid", false);
            details.put("error", e.getMessage());
        }
        
        return details;
    }

    /**
     * 验证Token有效性
     * @param token JWT token
     * @return 验证结果Map
     */
    public Map<String, Object> validateTokenWithResult(String token) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            Claims claims = validateToken(token);
            result.put("valid", true);
            result.put("username", claims.getSubject());
            result.put("expired", claims.getExpiration().before(new Date()));
        } catch (RuntimeException e) {
            result.put("valid", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
}