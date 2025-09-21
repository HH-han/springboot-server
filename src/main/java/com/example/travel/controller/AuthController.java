package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.User;
import com.example.travel.service.EmailService;
import com.example.travel.service.UserService;
import com.example.travel.utils.EmailUtils;
import com.example.travel.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.travel.utils.RedisUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final EmailService emailService;
    private final EmailUtils codeUtil;
    private final RedisUtils redisUtils;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    // 使用构造器注入替代字段注入
    @Autowired
    public AuthController(EmailService emailService,
                          EmailUtils codeUtil,
                          RedisUtils redisUtils,
                          UserService userService,
                          JwtUtils jwtUtils) {
        this.emailService = emailService;
        this.codeUtil = codeUtil;
        this.redisUtils = redisUtils;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    /**
     * 发送验证码
     * @param params 包含email参数的Map
     * @return 操作结果
     */
    @PostMapping("/send-code")
    public Result sendVerificationCode(@RequestBody Map<String, String> params) {
        Logger logger = LoggerFactory.getLogger(AuthController.class);
        String email = params.get("email");
        if (email == null || email.isEmpty()) {
            logger.warn("邮箱参数不能为空");
            return Result.error("邮箱参数不能为空");
        }

        // 生成验证码
        String code = codeUtil.generateCode();

        // 存储验证码到Redis，5分钟过期
        redisUtils.set("verification_code:" + email, code, 5, TimeUnit.MINUTES);

        // 通过接口调用发送邮件
        emailService.sendVerificationCode(email, code);
        logger.info("验证码已发送至邮箱: {}", email);
        return Result.success("验证码已发送");
    }

    /**
     * 邮箱验证码登录
     * @param params 包含email和code参数的Map
     * @return 登录结果
     */
    @PostMapping("/Emaillogin")
    public Result loginWithCode(@RequestBody Map<String, String> params) {
        Logger logger = LoggerFactory.getLogger(AuthController.class);
        String email = params.get("email");
        String code = params.get("code");

        // 参数校验
        if (email == null || email.isEmpty()) {
            return Result.error("邮箱参数不能为空");
        }
        if (code == null || code.isEmpty()) {
            return Result.error("验证码参数不能为空");
        }
        
        // 检查邮箱是否已注册
        User user = userService.findByEmail(email);
        if (user == null) {
            return Result.error("该邮箱未注册");
        }
        // 从Redis获取验证码
        String storedCode = redisUtils.getString("verification_code:" + email);

        if (storedCode == null) {
            logger.warn("验证码已过期: {}", email);
            return Result.error("验证码已过期");
        }

        if (!storedCode.equals(code)) {
            logger.warn("验证码错误: {}", email);
            return Result.error("验证码错误");
        }

        // 验证成功，删除验证码
        redisUtils.delete("verification_code:" + email);

        // 生成JWT token
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24小时有效期
                .signWith(SignatureAlgorithm.HS512, jwtUtils.getJwtSecret())
                .compact();

        // 移除敏感信息再返回
        //user.setPassword(null);

        // 创建返回数据
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("token", token);
        // 返回完整数据
        logger.info("登录成功: {}", email);
        return Result.success(data);
    }

    /**
     * 获取Token状态信息
     * @param token JWT令牌
     * @return Token状态信息
     */
    @GetMapping("/token-status")
    public Result getTokenStatus(@RequestHeader("Authorization") String token) {
        Logger logger = LoggerFactory.getLogger(AuthController.class);
        
        // 移除Bearer前缀
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        try {
            // 使用JwtUtils获取Token状态
            Object tokenStatus = jwtUtils.getTokenStatus(token);
            logger.info("Token状态查询成功");
            return Result.success(tokenStatus);
        } catch (Exception e) {
            logger.warn("Token状态查询失败: {}", e.getMessage());
            return Result.error("Token状态查询失败: " + e.getMessage());
        }
    }

    /**
     * 刷新Token
     * @param token JWT令牌
     * @return 新的Token信息
     */
    @PostMapping("/refresh-token")
    public Result refreshToken(@RequestHeader("Authorization") String token) {
        Logger logger = LoggerFactory.getLogger(AuthController.class);
        
        // 移除Bearer前缀
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        try {
            String newToken = jwtUtils.refreshToken(token);
            logger.info("Token刷新成功");
            
            Map<String, Object> data = new HashMap<>();
            data.put("token", newToken);
            return Result.success(data);
        } catch (Exception e) {
            logger.warn("Token刷新失败: {}", e.getMessage());
            return Result.error("Token刷新失败: " + e.getMessage());
        }
    }

    /**
     * 获取Token详细信息
     * @param token JWT令牌
     * @return Token详细信息
     */
    @GetMapping("/token-details")
    public Result getTokenDetails(@RequestHeader("Authorization") String token) {
        Logger logger = LoggerFactory.getLogger(AuthController.class);
        
        // 移除Bearer前缀
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        try {
            Map<String, Object> tokenDetails = jwtUtils.getTokenDetails(token);
            logger.info("Token详细信息查询成功");
            return Result.success(tokenDetails);
        } catch (Exception e) {
            logger.warn("Token详细信息查询失败: {}", e.getMessage());
            return Result.error("Token详细信息查询失败: " + e.getMessage());
        }
    }

    /**
     * 验证Token有效性
     * @param params 包含token参数的Map
     * @return 验证结果
     */
    @PostMapping("/validate-token")
    public Result validateToken(@RequestBody Map<String, String> params) {
        Logger logger = LoggerFactory.getLogger(AuthController.class);
        
        String token = params.get("token");
        if (token == null || token.isEmpty()) {
            return Result.error("Token参数不能为空");
        }
        
        try {
            Map<String, Object> validationResult = jwtUtils.validateTokenWithResult(token);
            logger.info("Token验证成功");
            return Result.success(validationResult);
        } catch (Exception e) {
            logger.warn("Token验证失败: {}", e.getMessage());
            return Result.error("Token验证失败: " + e.getMessage());
        }
    }

    /**
     * 获取用户权限信息
     * @param token JWT令牌
     * @return 用户权限信息
     */
    @GetMapping("/user-permissions")
    public Result getUserPermissions(@RequestHeader("Authorization") String token) {
        Logger logger = LoggerFactory.getLogger(AuthController.class);
        
        // 移除Bearer前缀
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        try {
            Claims claims = jwtUtils.validateToken(token);
            String username = claims.getSubject();
            
            // 根据用户名获取用户信息
            User user = userService.findByUsername(username);
            if (user == null) {
                return Result.error("用户不存在");
            }
            
            // 构建权限信息（这里需要根据实际业务逻辑实现）
            Map<String, Object> permissions = new HashMap<>();
            permissions.put("username", username);
            permissions.put("userId", user.getId());
            permissions.put("roles", new String[]{"USER"}); // 默认角色
            permissions.put("permissions", new String[]{"read", "write"}); // 默认权限
            
            logger.info("用户权限信息查询成功: {}", username);
            return Result.success(permissions);
        } catch (Exception e) {
            logger.warn("用户权限信息查询失败: {}", e.getMessage());
            return Result.error("用户权限信息查询失败: " + e.getMessage());
        }
    }
}