package com.example.travel.controller;

import com.example.travel.common.Result;
import com.example.travel.entity.ChatGroup;
import com.example.travel.service.ChatGroupService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 群组管理控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/api/public/chat-group")
@RequiredArgsConstructor
public class ChatGroupController {
    
    private final ChatGroupService chatGroupService;
    private final Logger logger = LoggerFactory.getLogger(ChatGroupController.class);
    
    /**
     * 根据ID查询群组
     */
    @GetMapping("/{id}")
    public Result getGroupById(@PathVariable Long id) {
        try {
            ChatGroup group = chatGroupService.findById(id);
            if (group == null) {
                return Result.error("群组不存在");
            }
            return Result.success(group);
        } catch (Exception e) {
            logger.error("查询群组失败: {}", e.getMessage(), e);
            return Result.error("查询群组失败");
        }
    }
    
    /**
     * 根据创建者ID查询群组
     */
    @GetMapping("/creator/{creatorId}")
    public Result getGroupsByCreator(@PathVariable Long creatorId) {
        try {
            List<ChatGroup> groups = chatGroupService.findByCreatorId(creatorId);
            return Result.success(groups);
        } catch (Exception e) {
            logger.error("查询用户创建的群组失败: {}", e.getMessage(), e);
            return Result.error("查询用户创建的群组失败");
        }
    }
    
    /**
     * 根据名称搜索群组
     */
    @GetMapping("/search")
    public Result searchGroups(@RequestParam String name) {
        try {
            List<ChatGroup> groups = chatGroupService.findByNameLike(name);
            return Result.success(groups);
        } catch (Exception e) {
            logger.error("搜索群组失败: {}", e.getMessage(), e);
            return Result.error("搜索群组失败");
        }
    }
    
    /**
     * 创建群组
     */
    @PostMapping
    public Result createGroup(@RequestBody ChatGroup chatGroup) {
        try {
            if (chatGroup.getName() == null || chatGroup.getName().trim().isEmpty()) {
                return Result.error("群组名称不能为空");
            }
            if (chatGroup.getCreatorId() == null) {
                return Result.error("创建者ID不能为空");
            }
            
            int result = chatGroupService.createGroup(chatGroup);
            if (result > 0) {
                logger.info("群组创建成功: {}", chatGroup.getName());
                return Result.success("群组创建成功", chatGroup);
            } else {
                return Result.error("群组创建失败");
            }
        } catch (Exception e) {
            logger.error("创建群组失败: {}", e.getMessage(), e);
            return Result.error("创建群组失败");
        }
    }
    
    /**
     * 更新群组信息
     */
    @PutMapping
    public Result updateGroup(@RequestBody ChatGroup chatGroup) {
        try {
            if (chatGroup.getId() == null) {
                return Result.error("群组ID不能为空");
            }
            if (chatGroup.getName() == null || chatGroup.getName().trim().isEmpty()) {
                return Result.error("群组名称不能为空");
            }
            
            int result = chatGroupService.updateGroup(chatGroup);
            if (result > 0) {
                logger.info("群组更新成功: {}", chatGroup.getId());
                return Result.success("群组更新成功");
            } else {
                return Result.error("群组更新失败");
            }
        } catch (Exception e) {
            logger.error("更新群组失败: {}", e.getMessage(), e);
            return Result.error("更新群组失败");
        }
    }
    
    /**
     * 删除群组
     */
    @DeleteMapping("/{id}")
    public Result deleteGroup(@PathVariable Long id) {
        try {
            int result = chatGroupService.deleteGroup(id);
            if (result > 0) {
                logger.info("群组删除成功: {}", id);
                return Result.success("群组删除成功");
            } else {
                return Result.error("群组删除失败");
            }
        } catch (Exception e) {
            logger.error("删除群组失败: {}", e.getMessage(), e);
            return Result.error("删除群组失败");
        }
    }
}