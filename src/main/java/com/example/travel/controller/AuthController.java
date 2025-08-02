package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.User;
import com.example.travel.service.EmailService;
import com.example.travel.service.UserService;
import com.example.travel.utils.EmailUtils;
import com.example.travel.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
        String email = params.get("email");
        if (email == null || email.isEmpty()) {
            return Result.error("邮箱参数不能为空");
        }

        // 生成验证码
        String code = codeUtil.generateCode();

        // 存储验证码到Redis，5分钟过期
        redisUtils.set("verification_code:" + email, code, 5, TimeUnit.MINUTES);

        // 通过接口调用发送邮件
        emailService.sendVerificationCode(email, code);

        return Result.success("验证码已发送");
    }

    /**
     * 邮箱验证码登录
     * @param params 包含email和code参数的Map
     * @return 登录结果
     */
    @PostMapping("/Emaillogin")
    public Result loginWithCode(@RequestBody Map<String, String> params) {
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
            return Result.error("验证码已过期");
        }

        if (!storedCode.equals(code)) {
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
        return Result.success(data);
    }
}