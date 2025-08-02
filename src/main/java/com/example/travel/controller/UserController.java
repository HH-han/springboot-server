package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.User;
import com.example.travel.service.UserService;
import com.example.travel.utils.ImageUtils;
import com.example.travel.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/public/user")
public class UserController {
    /**
     * 用户服务
     */
    private final UserService userService;
    /**
     * JWT工具类
     */
    private final JwtUtils jwtUtils;

    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    public Result getUserInfo(HttpServletRequest request) {
        Logger logger = LoggerFactory.getLogger(UserController.class);

        try {
            // 认证检查
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()
                    || "anonymousUser".equals(authentication.getPrincipal())) {
                logger.warn("未授权访问尝试");
                return Result.error("401");
            }

            String username = authentication.getName();
            User user = userService.findByUsername(username);

            if (user == null) {
                logger.error("用户不存在: {}", username);
                return Result.error("404");
            }

            //user.setPassword(null);
            return Result.success(user);

        } catch (Exception e) {
            logger.error("获取用户信息异常: {}", e.getMessage());
            return Result.error("500");
        }
    }
    /**
     * 用户登录
     * @param loginUser 登录用户信息（包含用户名和密码）
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        Logger logger = LoggerFactory.getLogger(UserController.class);
        
        // 参数校验
        if (username == null || username.trim().isEmpty()) {
            logger.warn("登录失败: 用户名为空");
            return Result.error("用户名不能为空");
        }
        if (password == null || password.trim().isEmpty()) {
            logger.warn("登录失败: 密码为空");
            return Result.error("密码不能为空");
        }

        // 查询用户
        User user = userService.findByUsername(username);
        if (user == null) {
            logger.warn("登录失败: 用户不存在 - {}", username);
            return Result.error("用户名或密码错误");
        }

        // 密码比对
        if (!password.equals(user.getPassword())) {
            logger.warn("登录失败: 密码错误 - {}", username);
            return Result.error("用户名或密码错误");
        }
        // 移除敏感信息再返回
        //user.setPassword(null);

        // 创建返回数据
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("token", jwtUtils.generateToken(username));
        // 返回完整数据
        return Result.success("登录成功",data);
    }
    /**
     * 获取用户头像
     * @param username 用户名
     * @return 统一响应结果
     */
    @PutMapping("/avatar/{username}")
    public Result uploadAvatar(@PathVariable String username, @RequestParam("file") MultipartFile file) {
        Logger logger = LoggerFactory.getLogger(UserController.class);
        try {
            // 使用ImageUtils处理图片上传
            String image = ImageUtils.processMultipartFile(file);
            // 查询用户获取ID
            User existingUser = userService.findByUsername(username);
            if (existingUser == null) {
                return Result.error("用户不存在");
            }
            // 保存旧图片路径
            String oldImagePath = existingUser.getImage();
            // 更新用户对象
            existingUser.setImage(image);
            userService.updateUser(existingUser);

            // 删除原有图片
            if (oldImagePath != null) {
                try {
                    ImageUtils.deleteImage(oldImagePath);
                } catch (Exception e) {
                    logger.warn("Failed to delete old avatar file: " + oldImagePath, e);
                }
            }
            logger.info("头像更新成功，用户名: {}", username);
            return Result.success("0", image);
        } catch (Exception e) {
            return Result.error("图片上传失败: " + e.getMessage());
        }
    }
    /**
     * 更新用户信息
     */
    @PutMapping("/update")
    public Result updateUser(@RequestBody User user) {
        Logger logger = LoggerFactory.getLogger(UserController.class);
        try {
            // 参数校验
            if (user.getNickname() == null || user.getNickname().trim().isEmpty()) {
                return Result.error("请为自己设置一个响亮的昵称");
            }
            if (user.getEmail() != null && !user.getEmail().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
                return Result.error("邮箱格式不正确");
            }
            if (user.getPhone() != null && !user.getPhone().matches("^1[3-9]\\d{9}$")) {
                return Result.error("手机号格式不正确");
            }

            // 获取当前用户名
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User currentUser = userService.findByUsername(username);
            
            // 检查邮箱是否已被其他用户使用
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                User emailUser = userService.findByEmail(user.getEmail());
                if (emailUser != null && !emailUser.getId().equals(currentUser.getId())) {
                    return Result.error("邮箱已被其他用户使用");
                }
            }
            
            // 检查手机号是否已被其他用户使用
            if (user.getPhone() != null && !user.getPhone().isEmpty()) {
                User phoneUser = userService.findByPhone(user.getPhone());
                if (phoneUser != null && !phoneUser.getId().equals(currentUser.getId())) {
                    return Result.error("手机号已被其他用户使用");
                }
            }

            // 更新用户信息
            currentUser.setNickname(user.getNickname());
            currentUser.setEmail(user.getEmail());
            currentUser.setPhone(user.getPhone());
            currentUser.setPassword(user.getPassword());
            currentUser.setTravelmage(user.getTravelmage());
            currentUser.setSignature(user.getSignature());
            currentUser.setExperience(user.getExperience());

            int result = userService.updateUser(currentUser);
            if (result > 0) {
                logger.info("用户信息更新成功，用户名: {}", username);
                return Result.success("更新成功");
            } else {
                logger.error("用户信息更新失败，用户名: {}", username);
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            logger.error("更新用户信息时发生异常: {}", e.getMessage(), e);
            return Result.error("服务器内部错误");
        }
    }
    @Autowired
    public UserController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }
    //分页查询
    @GetMapping
    public Result findAllUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword){
        List<User> users=userService.getAllUsers(page,pageSize,keyword);
        int total =userService.countUser(keyword);
        Map<String, Object> result = new HashMap<>();
        result.put("list",users);
        result.put("total",total);
        return Result.success(result);
    }
    //新增
    @PostMapping
    public Result addUser(@RequestBody User user) {
        Logger logger = LoggerFactory.getLogger(UserController.class);
        try {
            // 参数校验
            if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                return Result.error("用户名不能为空");
            }
            if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                return Result.error("密码不能为空");
            }
            if (user.getEmail() != null && !user.getEmail().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
                return Result.error("邮箱格式不正确");
            }
            if (user.getPhone() != null && !user.getPhone().matches("^1[3-9]\\d{9}$")) {
                return Result.error("手机号格式不正确");
            }
            //图片处理
            if (user.getImage() != null && user.getImage().startsWith("data:image")) {
                try {
                    String imageUrl = ImageUtils.processBase64Image(user.getImage());
                    user.setImage(imageUrl);
                } catch (Exception e) {
                    return Result.error("图片保存失败: " + e.getMessage());
                }
            }
            // 密码加密
            //user.setPassword(userService.encodePassword(user.getPassword()));

            int result = userService.addUser(user);
            if (result > 0) {
                logger.info("用户创建成功：{}", user.getUsername());
                return Result.success("用户创建成功");
            } else {
                logger.error("用户创建失败：{}", user);
                return Result.error("用户创建失败");
            }
        } catch (Exception e) {
            logger.error("用户创建时发生异常：{}", e.getMessage(), e);
            return Result.error("服务器内部错误");
        }
    }
    //修改
    @PutMapping("/{id}")
    public Result updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            // 获取原有用户信息
            User existingUser = userService.getById(id);
            
            // 检查邮箱是否已被其他用户使用
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                User emailUser = userService.findByEmail(user.getEmail());
                if (emailUser != null && !emailUser.getId().equals(id.intValue())) {
                    return Result.error("邮箱已被其他用户使用");
                }
            }
            
            // 检查手机号是否已被其他用户使用
            if (user.getPhone() != null && !user.getPhone().isEmpty()) {
                User phoneUser = userService.findByPhone(user.getPhone());
                if (phoneUser != null && !phoneUser.getId().equals(id.intValue())) {
                    return Result.error("手机号已被其他用户使用");
                }
            }
            // 处理图片
            if (user.getImage() != null && user.getImage().startsWith("data:image")) {
                try {
                    String imageUrl = ImageUtils.processBase64Image(user.getImage());
                    user.setImage(imageUrl);

                    // 删除旧图片
                    if (existingUser != null && existingUser.getImage() != null) {
                        ImageUtils.deleteImage(existingUser.getImage());
                    }
                } catch (Exception e) {
                    return Result.error("图片保存失败: " + e.getMessage());
                }
            } else if (user.getImage() == null && existingUser != null && existingUser.getImage() != null) {
                // 删除原有图片
                try {
                    ImageUtils.deleteImage(existingUser.getImage());
                } catch (Exception e) {
                    // 文件删除失败不影响主流程
                    System.err.println("删除图片文件失败: " + e.getMessage());
                }
            }
            
            user.setId(id.intValue());
            int result = userService.updateUser(user);
            return result > 0 ? Result.success("更新成功") : Result.error("更新失败");
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(UserController.class);
            logger.error("更新用户时发生异常，用户ID: {}", id, e);
            return Result.error("服务器内部错误");
        }
    }
    //删除
    @DeleteMapping(("/{id}"))
    public Result deleteUser(@PathVariable Long id) {
        Logger logger = LoggerFactory.getLogger(UserController.class);
        try {
            // 获取用户信息，包含图片路径
            User user = userService.getById(id);
            if (user != null && user.getImage() != null) {
                try {
                    // 删除图片文件
                    ImageUtils.deleteImage(user.getImage());
                } catch (Exception e) {
                    // 文件删除失败不影响主流程
                    System.err.println("删除图片文件失败: " + e.getMessage());
                }
            }

            int result = userService.deleteUser(id);
            if (result > 0) {
                return Result.success(result);
            } else {
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            logger.error("删除用户时出现异常，用户ID: {}", id, e);
            return Result.error("删除用户时出现异常，请稍后重试");
        }
    }


    /**
     * 新用户注册
     * **/
    @PostMapping("/register")
    public Result registerUser(@RequestBody Map<String, String> registerData) {
        // 获取参数
        String username = registerData.get("username");
        String nickname = registerData.get("nickname");
        String email = registerData.get("email");
        String phone = registerData.get("phone");
        String password = registerData.get("password");
        String confirmPassword = registerData.get("confirmPassword");
        String image = registerData.get("image");

        // 密码校验
        if (!password.equals(confirmPassword)) {
            return Result.error("两次密码不一致");
        }
        // 检查用户唯一性
        if (userService.findByUsername(username) != null) {
            return Result.error("用户名已存在");
        }
        if (userService.findByEmail(email) != null) {
           return Result.error("邮箱已注册");
        }
        if (userService.findByPhone(phone) != null) {
            return Result.error("手机号已注册");
        }

        // 处理头像
        String imagePath = null;
        if (image != null && image.startsWith("data:image")) {
            try {
                // 解码base64图片
                String[] parts = image.split(",");
                byte[] imageBytes = Base64.getDecoder().decode(parts[1]);

                // 生成唯一文件名
                String fileName = UUID.randomUUID().toString() + ".png";
                Path uploadPath = Paths.get("D:/Image/");
                
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                
                Path filePath = uploadPath.resolve(fileName);
                Files.write(filePath, imageBytes);
                imagePath = "http://localhost:2025/upload/" + fileName;
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }

        // 创建用户对象
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setImage(imagePath);
        user.setCreateTime(LocalDateTime.now());

        // 保存用户
        int result = userService.addUser(user);
        return result > 0 ? Result.success("注册成功") : Result.error("注册失败");
    }

}