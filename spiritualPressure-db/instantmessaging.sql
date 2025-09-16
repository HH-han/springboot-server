/*
Navicat Premium Data Transfer

Source Server         : localhost2025
Source Server Type    : MySQL
Source Server Version : 80028 (8.0.28)
Source Host           : localhost:3306
Source Schema         : db_travel

Target Server Type    : MySQL
Target Server Version : 80028 (8.0.28)
File Encoding         : 65001

Date: 16/09/2025 10:33:31
*/

SET NAMES utf8mb4;

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
-- 用户表
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户头像',
    `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
    `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '邮箱',
    `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
    `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '联系方式',
    `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '昵称',
    `travelmage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '旅行图片',
    `experience` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '经验 ',
    `signature` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '签名 ',
    `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `iv` blob NULL,
    `permissions` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '权限',
    `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户Id',
    `status` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '1' COMMENT '状态',
    `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
    `online_status` tinyint NULL DEFAULT 1 COMMENT '在线状态: 0-离线, 1-在线, 2-忙碌, 3-隐身',
    `websocket_session_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'WebSocket会话ID',
    `device_info` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '设备信息',
    `ip_address` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '最后登录IP地址',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `username` (`username` ASC) USING BTREE,
    UNIQUE INDEX `account` (`email` ASC) USING BTREE,
    INDEX `idx_online_status` (`online_status` ASC) USING BTREE,
    INDEX `idx_last_login_time` (`last_login_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- 群组表（使用chat_groups避免关键字冲突）
CREATE TABLE chat_groups (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    avatar VARCHAR(255),
    creator_id BIGINT NOT NULL,
    max_members INT DEFAULT 500,
    status TINYINT DEFAULT 1 COMMENT '0:解散, 1:正常',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_creator_id (creator_id),
    INDEX idx_status (status),
    FOREIGN KEY (creator_id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 群组成员表
CREATE TABLE group_members (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    group_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    role TINYINT DEFAULT 0 COMMENT '0:普通成员, 1:管理员, 2:群主',
    join_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_read_message_id BIGINT DEFAULT 0 COMMENT '最后读取的消息ID',
    status TINYINT DEFAULT 1 COMMENT '0:已退出, 1:正常',
    UNIQUE KEY uk_group_user (group_id, user_id),
    INDEX idx_group_id (group_id),
    INDEX idx_user_id (user_id),
    INDEX idx_role (role),
    FOREIGN KEY (group_id) REFERENCES chat_groups (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 群聊消息表
CREATE TABLE group_chat_messages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    group_id BIGINT NOT NULL,
    sender_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    message_type VARCHAR(20) DEFAULT 'TEXT' COMMENT 'TEXT, IMAGE, FILE, VOICE, VIDEO',
    file_url VARCHAR(255) COMMENT '文件消息的URL',
    file_size BIGINT DEFAULT 0 COMMENT '文件大小(字节)',
    duration INT DEFAULT 0 COMMENT '音频/视频时长(秒)',
    send_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    status TINYINT DEFAULT 1 COMMENT '0:撤回, 1:正常',
    INDEX idx_group_id (group_id),
    INDEX idx_sender_id (sender_id),
    INDEX idx_send_time (send_time),
    FOREIGN KEY (group_id) REFERENCES chat_groups (id) ON DELETE CASCADE,
    FOREIGN KEY (sender_id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 消息已读状态表 (用于群消息的已读回执)
CREATE TABLE message_read_status (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    message_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    group_id BIGINT NOT NULL,
    read_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_message_user (message_id, user_id),
    INDEX idx_user_id (user_id),
    INDEX idx_group_id (group_id),
    FOREIGN KEY (message_id) REFERENCES group_chat_messages (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES chat_groups (id) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 好友关系表
CREATE TABLE user_friends (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    friend_id BIGINT NOT NULL,
    relation_status TINYINT NOT NULL DEFAULT 0 COMMENT '0:待确认, 1:已好友, 2:已拒绝, 3:已拉黑',
    remark VARCHAR(50) COMMENT '好友备注',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_user_friend (user_id, friend_id),
    INDEX idx_user_id (user_id),
    INDEX idx_friend_id (friend_id),
    INDEX idx_relation_status (relation_status),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (friend_id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 单聊消息表
CREATE TABLE single_chat_messages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    message_type VARCHAR(20) DEFAULT 'TEXT' COMMENT 'TEXT, IMAGE, FILE, VOICE, VIDEO',
    file_url VARCHAR(255) COMMENT '文件消息的URL',
    file_size BIGINT DEFAULT 0 COMMENT '文件大小(字节)',
    duration INT DEFAULT 0 COMMENT '音频/视频时长(秒)',
    send_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    read_time DATETIME COMMENT '消息被阅读的时间',
    status TINYINT DEFAULT 0 COMMENT '0:发送中, 1:已发送, 2:已送达, 3:已读',
    INDEX idx_sender_receiver (sender_id, receiver_id),
    INDEX idx_receiver_sender (receiver_id, sender_id),
    INDEX idx_send_time (send_time),
    INDEX idx_status (status),
    FOREIGN KEY (sender_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 好友申请记录表（可选）
CREATE TABLE friend_requests (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    message VARCHAR(255) COMMENT '申请附言',
    status TINYINT DEFAULT 0 COMMENT '0:待处理, 1:已同意, 2:已拒绝',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_sender_id (sender_id),
    INDEX idx_receiver_id (receiver_id),
    INDEX idx_status (status),
    FOREIGN KEY (sender_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;