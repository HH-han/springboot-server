package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.User;
import com.example.travel.listener.UserDataListener;
import com.example.travel.service.UserService;
import com.example.travel.utils.ImageUtils;
import com.example.travel.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/public/user")
public class UserController {
    //记录器
    Logger logger = LoggerFactory.getLogger(UserController.class);
     //用户服务
    private final UserService userService;
     //JWT工具类
    private final JwtUtils jwtUtils;
     //获取用户信息
    @GetMapping("/info")
    public Result getUserInfo(HttpServletRequest request) {

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
        //查询用户状态
        if(user.getStatus() == 1){
            logger.warn("登录失败: 用户被禁用 - {}", username);
            return Result.error("用户被禁用");
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
        logger.info("登录成功 - {}",username);
        return Result.success("登录成功",data);
    }

    /**
     * admin用户登录
     * @param loginUser 登录用户信息（包含用户名和密码）
     * @return 登录结果
     */
    @PostMapping("/adminlogin")
    public Result adminlogin(@RequestBody User loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();


        // 参数校验
        if (username == null || username.trim().isEmpty()) {
            logger.warn("登录失败: 用户名为空");
            return Result.error("用户名不能为空！");
        }
        if (password == null || password.trim().isEmpty()) {
            logger.warn("登录失败: 密码为空");
            return Result.error("密码不能为空！");
        }
        // 查询用户
        User user = userService.findByUsername(username);
        if (user == null) {
            logger.warn("登录失败: 用户不存在 - {}", username);
            return Result.error("用户名或密码错误！");
        }
        //admin登录
        if (user.getPermissions() == 0){
            logger.info("权限不足，请联系管理员 - {}",username);
            return Result.error("权限不足，请联系管理员。");
        }
        //查询用户状态
        if(user.getStatus() == 1){
            logger.warn("登录失败: 用户被禁用 - {}", username);
            return Result.error("登录失败: 用户被禁用，请联系客服。");
        }
        // 密码比对
        if (!password.equals(user.getPassword())) {
            logger.warn("登录失败: 密码错误 - {}", username);
            return Result.error("用户名或密码错误！");
        }
        // 移除敏感信息再返回
        //user.setPassword(null);

        // 创建返回数据
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        data.put("token", jwtUtils.generateToken(username));
        // 返回完整数据
        logger.info("登录成功 - {}",username);
        return Result.success("登录成功",data);
    }
    /**
     * 用户头像
     * @param username 用户名
     * @return 统一响应结果
     */
    @PutMapping("/avatar/{username}")
    public Result uploadAvatar(@PathVariable String username, @RequestParam("file") MultipartFile file) {
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
            logger.info("图片上传失败: {}", username+ e.getMessage());
            return Result.error("图片上传失败: " + e.getMessage());
        }
    }
    /**
     * 更新用户信息
     */
    @PutMapping("/update")
    public Result updateUser(@RequestBody User user) {
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
            if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                throw new IllegalArgumentException("用户名不能为空");
            }
            currentUser.setUsername(user.getUsername());
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

    /**
     *修改权限
     */
    @PutMapping("/updateRole")
    public Result updateRole(@RequestBody Map<String, Object> params) {
        try {
            Long id = Long.parseLong(params.get("id").toString());
            int permissions = Integer.parseInt(params.get("permissions").toString());
            String username = params.get("username").toString();
            
            int result = userService.updatePermissions(id, permissions);
            if (result > 0) {
                logger.info("用户权限修改成功，用户名: {}", username);
                return Result.success("更新成功");
            } else {
                logger.error("用户权限修改失败，用户名: {}", username);
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            logger.error("修改权限时发生异常: {}", e.getMessage(), e);
            return Result.error("服务器内部错误");
        }
    }
    /**
     *修改状态
     */
    @PutMapping("/updateStatus")
    public Result updateStatus(@RequestBody Map<String, Object> params) {
        try {
            Long id = Long.parseLong(params.get("id").toString());
            int status = Integer.parseInt(params.get("status").toString());
            String username = params.get("username").toString();

            int result = userService.updateStatus(id, status);
            if (result > 0) {
                logger.info("用户状态修改成功，用户名: {}", username);
                return Result.success("更新成功");
            } else {
                logger.error("用户状态修改失败，用户名: {}", username);
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            logger.error("修改状态时发生异常: {}", e.getMessage(), e);
            return Result.error("服务器内部错误");
        }
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
            logger.error("更新用户时发生异常，用户ID: {}", id, e);
            return Result.error("服务器内部错误");
        }
    }
    //删除
    @DeleteMapping(("/{id}"))
    public Result deleteUser(@PathVariable Long id) {
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

    //查询
    /**
     * @param id ID
     * @param username 用户名
     * @param nickname 昵称
     * @param email 邮箱
     * @param phone 手机号
     * @param userID 用户ID
     */
    @GetMapping("/search")
    public Result searchUsers(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Long userID) {
        if (id != null) {
            User user = userService.getById(id);
            if (user != null) {
                logger.info("用户查询成功，ID: {}", id);
                return Result.success(user);
            } else {
                logger.warn("用户不存在，ID: {}", id);
                return Result.error("用户不存在");
            }
        }
        List<User> users = userService.searchUsers(id, username, nickname, email, phone, userID);
        return Result.success(users);
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
            logger.warn("密码不一致");
            return Result.error("两次密码不一致");
        }
        // 检查用户唯一性
        if (userService.findByUsername(username) != null) {
            logger.warn("用户名已存在");
            return Result.error("用户名已存在");
        }
        if (userService.findByEmail(email) != null) {
            logger.warn("邮箱已注册");
           return Result.error("邮箱已注册");
        }
        if (userService.findByPhone(phone) != null) {
            logger.warn("手机号已注册");
            return Result.error("手机号已注册");
        }

        // 创建用户对象
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setCreateTime(LocalDateTime.now());

        // 处理图片
        if (image != null && image.startsWith("data:image")) {
            try {
                String imageUrl = ImageUtils.processBase64Image(image);
                user.setImage(imageUrl);
            } catch (Exception e) {
                return Result.error("图片保存失败: " + e.getMessage());
            }
        }

        // 保存用户
        int result = userService.addUser(user);
        if (result > 0) {
            logger.info("用户注册成功，用户名: {}", username);
            return Result.success("注册成功");
        } else {
            logger.error("用户注册失败，用户名: {}", username);
            return Result.error("注册失败");
        }
    }
    // 用户列表导出
    @GetMapping("/export")
    @ApiOperation("导出用户列表")
    public void exportUserList(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("用户列表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");

        List<User> list = userService.list();
        logger.info("用户列表导出成功，共导出 {} 条数据", list.size());
        EasyExcel.write(response.getOutputStream(), User.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("用户数据")
                .doWrite(list);
    }

    @PostMapping("/import")
    @ApiOperation("导入用户数据")
    public Result importUserList(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), User.class, new UserDataListener(userService))
                .sheet()
                .doRead();
        logger.info("用户数据导入成功");
        return Result.success();
    }
}