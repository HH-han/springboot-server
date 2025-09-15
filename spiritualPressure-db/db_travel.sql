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

 Date: 10/09/2025 19:51:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456');

-- ----------------------------
-- Table structure for community
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `imageUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `memberCount` int NOT NULL DEFAULT 0,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  `creatorId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of community
-- ----------------------------
INSERT INTO `community` VALUES (1, '编程爱好者社区', '一个专注于编程技术交流的社区', 'https://example.com/community.jpg', 1200, '2025-03-10 11:20:59', '2025-03-10 11:20:59', 'user123');

-- ----------------------------
-- Table structure for destination
-- ----------------------------
DROP TABLE IF EXISTS `destination`;
CREATE TABLE `destination`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `cities` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of destination
-- ----------------------------
INSERT INTO `destination` VALUES (35, '法国', 55.00, '欧洲', 'http://localhost:2025/upload/63c46eac-b80a-40ea-b27e-9739b5331b59.png', '2025-04-10 21:12:17', '巴黎', '法国的首都，被誉为“光之城”，以其艺术、时尚、美食和浪漫氛围而闻名。巴黎拥有许多世界著名的地标，如埃菲尔铁塔、卢浮宫和巴黎圣母院。');
INSERT INTO `destination` VALUES (36, '日本', 123.00, '亚洲', 'http://localhost:2025/upload/5d967a79-e622-4275-b121-181bb662130d.png', '2025-04-30 21:12:22', '东京', '日本的首都，是世界上人口最多的城市之一。东京以其先进的科技、丰富的文化和历史遗迹而著称。游客可以在这里体验到传统与现代的完美融合，如浅草寺和东京塔。');
INSERT INTO `destination` VALUES (37, '美国', 330.00, '北美洲', 'http://localhost:2025/upload/b90177ea-986b-488a-97fa-916bee5dd4dd.png', '2025-04-23 21:12:26', '纽约', '美国最大的城市，也是全球最重要的商业、金融、媒体和文化中心之一。纽约以其标志性建筑如自由女神像、时代广场和中央公园而闻名。');
INSERT INTO `destination` VALUES (38, '澳大利亚', 12.00, '大洋洲', 'http://localhost:2025/upload/76767ba1-b03f-4a34-b449-3054565410ad.png', NULL, '悉尼', '澳大利亚最大的城市，以其标志性的悉尼歌剧院和悉尼海港大桥而闻名。悉尼以其美丽的海滩、丰富的户外活动和多元文化而受到游客的喜爱。');
INSERT INTO `destination` VALUES (39, '英国', 540.00, '欧洲', 'http://localhost:2025/upload/205fc6f4-9d59-459b-a163-8b5b6154296c.png', NULL, '伦敦', '英国的首都，是一个历史悠久且文化多元的城市。伦敦拥有许多著名的地标，如大本钟、伦敦眼和白金汉宫，同时也是一个重要的金融中心。');
INSERT INTO `destination` VALUES (40, '阿拉伯联合酋长国', 440.00, '亚洲', 'http://localhost:2025/upload/9cd4ae87-cc3d-440a-a003-e2f628b918a8.png', NULL, '迪拜', '阿拉伯联合酋长国的一个城市，以其豪华的购物中心、豪华酒店和创新的摩天大楼而闻名。迪拜塔是世界上最高的建筑，而帆船酒店则是一个标志性的豪华酒店。');
INSERT INTO `destination` VALUES (41, '中国', 650.00, '亚洲', 'http://localhost:2025/upload/6af20cf1-3764-4d27-837d-9553f30acd2d.png', NULL, '北京', '中国的首都，拥有悠久的历史和丰富的文化遗产。北京是故宫、天安门广场、长城等著名历史遗迹的所在地，同时也是中国的政治和文化中心。\r\n');
INSERT INTO `destination` VALUES (42, '巴西', 440.00, '南美洲', 'http://localhost:2025/upload/ff3c03b2-9550-40db-acad-1225dd6dd683.png', NULL, '里约热内卢', '巴西的一个大城市，以其美丽的海滩、狂欢节和基督救世主像而闻名。里约热内卢是一个充满活力的城市，以其热情的居民和丰富的文化活动而受到游客的喜爱。');
INSERT INTO `destination` VALUES (43, '南非', 12.00, '非洲', 'http://localhost:2025/upload/4529b274-6c10-45e9-9a0d-5143ad7e534c.png', NULL, '开普敦', '南非的一个城市，以其壮丽的自然风光和历史遗迹而著名。开普敦是桌山和好望角的所在地，也是探索南非文化和野生动物的理想起点。');
INSERT INTO `destination` VALUES (45, '应该', 0.00, '欧洲', 'http://localhost:2025/upload/cf0f3658-120c-4e72-bda0-622da621c498.png', NULL, '巴黎', NULL);
INSERT INTO `destination` VALUES (46, '美国', 0.00, '北美洲', 'statue_of_liberty.jpg', NULL, '纽约自由女神像', NULL);
INSERT INTO `destination` VALUES (47, '日本', 0.00, '亚洲', 'mount_fuji.jpg', NULL, '东京富士山', NULL);
INSERT INTO `destination` VALUES (48, '大峡谷', 0.00, '北美洲', 'grand_canyon.jpg', NULL, '拉斯维加斯', NULL);
INSERT INTO `destination` VALUES (49, '长城', 0.00, '亚洲', 'great_wall.jpg', NULL, '北京', NULL);
INSERT INTO `destination` VALUES (50, '金字塔', 0.00, '非洲', 'pyramids.jpg', NULL, '开罗', NULL);
INSERT INTO `destination` VALUES (51, '悉尼港', 0.00, '大洋洲', 'sydney_harbor.jpg', NULL, '悉尼', NULL);
INSERT INTO `destination` VALUES (52, '亚马逊雨林', 0.00, '南美洲', 'amazon_rainforest.jpg', NULL, '马瑙斯', NULL);
INSERT INTO `destination` VALUES (53, '尼亚加拉大瀑布', 0.00, '北美洲', 'niagara_falls.jpg', NULL, '多伦多', NULL);
INSERT INTO `destination` VALUES (54, '威尼斯', 0.00, '欧洲', 'venice.jpg', NULL, '威尼斯', NULL);
INSERT INTO `destination` VALUES (55, '布拉格', 0.00, '欧洲', 'prague.jpg', NULL, '布拉格', NULL);
INSERT INTO `destination` VALUES (56, '巴塞罗那', 0.00, '欧洲', 'barcelona.jpg', NULL, '巴塞罗那', NULL);
INSERT INTO `destination` VALUES (57, '阿姆斯特丹', 0.00, '欧洲', 'amsterdam.jpg', NULL, '阿姆斯特丹', NULL);
INSERT INTO `destination` VALUES (58, '京都', 0.00, '亚洲', 'kyoto.jpg', NULL, '京都', NULL);
INSERT INTO `destination` VALUES (59, '悉尼', 0.00, '大洋洲', 'sydney.jpg', NULL, '悉尼', NULL);
INSERT INTO `destination` VALUES (60, '巴黎', 0.00, '欧洲', 'paris.jpg', NULL, '巴黎', NULL);
INSERT INTO `destination` VALUES (61, '东京', 0.00, '亚洲', 'tokyo.jpg', NULL, '东京', NULL);
INSERT INTO `destination` VALUES (62, '纽约', 0.00, '北美洲', 'newyork.jpg', NULL, '纽约', NULL);
INSERT INTO `destination` VALUES (63, '悉尼', 0.00, '大洋洲', 'sydney.jpg', NULL, '悉尼', NULL);
INSERT INTO `destination` VALUES (64, '伦敦', 0.00, '欧洲', 'london.jpg', NULL, '伦敦', NULL);
INSERT INTO `destination` VALUES (65, '迪拜', 0.00, '亚洲', 'dubai.jpg', NULL, '迪拜', NULL);
INSERT INTO `destination` VALUES (66, '北京', 0.00, '亚洲', 'beijing.jpg', NULL, '北京', NULL);
INSERT INTO `destination` VALUES (67, '里约热内卢', 0.00, '南美洲', 'rio.jpg', NULL, '里约热内卢', NULL);
INSERT INTO `destination` VALUES (68, '开普敦', 0.00, '非洲', 'cape_town.jpg', NULL, '开普敦', NULL);
INSERT INTO `destination` VALUES (69, '圣托里尼', 0.00, '欧洲', 'santorini.jpg', NULL, '圣托里尼', NULL);
INSERT INTO `destination` VALUES (70, '摩洛哥', 0.00, '非洲', 'morocco.jpg', NULL, '马拉喀什', NULL);
INSERT INTO `destination` VALUES (71, '布达佩斯', 0.00, '欧洲', 'budapest.jpg', NULL, '布达佩斯', NULL);
INSERT INTO `destination` VALUES (79, '1', 0.00, '1', 'http://localhost:2025/upload/5220b0af-b4c5-4a2d-92b9-186f4417641f.png', NULL, '1', NULL);
INSERT INTO `destination` VALUES (80, '1', 0.00, '1', 'http://localhost:2025/upload/fef4dee0-76d5-45a4-b2e7-c5b44d9b7fe3.png', NULL, '12,12,123', NULL);
INSERT INTO `destination` VALUES (81, '1', 2.00, '1', 'http://localhost:2025/upload/2e242644-1883-44b1-85a2-40ee17c0c0f3.png', NULL, '111', NULL);

-- ----------------------------
-- Table structure for myorders
-- ----------------------------
DROP TABLE IF EXISTS `myorders`;
CREATE TABLE `myorders`  (
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `item_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `amount` decimal(10, 2) NOT NULL,
  `status` enum('PENDING','PAID','COMPLETED') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'PENDING',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of myorders
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `price` int NOT NULL,
  `totalAmount` decimal(10, 2) NOT NULL,
  `status` enum('PENDING','PAID','SHIPPED','COMPLETED') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'PENDING',
  `orderDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paymentDate` datetime NULL DEFAULT NULL,
  `paymentMethod` enum('BANK','PAYPAL','WECHAT') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `sku` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sku`(`sku` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (37, 'http://localhost:2025/upload/4259e526-0a5e-4223-afe5-107f492032da.png', 120, 120.00, 'COMPLETED', '2025-01-15 10:23:45', '2025-01-15 10:25:30', 'WECHAT', 'SKU001', '苏州刺绣', '2025-03-26 20:57:40', '中国传统手工艺品，以精细的针法和丰富的色彩闻名');
INSERT INTO `orders` VALUES (38, 'http://localhost:2025/upload/251949b6-998b-4c36-b6f6-c140c9e2eade.png', 85, 85.00, 'PAID', '2025-01-18 14:12:33', '2025-01-18 14:13:45', 'PAYPAL', 'SKU002', '景德镇瓷器', '2025-03-26 20:57:40', '中国瓷器之都出品，白如玉、明如镜、薄如纸、声如磬');
INSERT INTO `orders` VALUES (39, 'http://localhost:2025/upload/9a179bb4-2a48-4b4a-83bb-80388339e6cc.png', 45, 45.00, 'SHIPPED', '2025-02-05 09:45:21', '2025-02-05 09:46:10', 'BANK', 'SKU003', '云南普洱茶', '2025-03-26 20:57:40', '陈年发酵茶，具有独特的陈香和保健功效');
INSERT INTO `orders` VALUES (40, 'http://localhost:2025/upload/9a937a6f-64dc-472e-a234-0b4f691c6363.png', 150657, 239.00, 'PENDING', '2025-02-12 16:30:15', NULL, 'WECHAT', 'SKU004', '西藏唐卡', '2025-03-26 20:57:40', '藏传佛教绘画艺术，色彩鲜艳，具有宗教和文化价值');
INSERT INTO `orders` VALUES (41, 'http://localhost:2025/upload/85e0a664-e9fe-4e32-88c7-0f17fcdfcde2.png', 65, 65.00, 'COMPLETED', '2025-02-20 11:22:10', '2025-02-20 11:23:45', 'PAYPAL', 'SKU005', '杭州丝绸', '2025-03-26 20:57:40', '质地柔软光滑，中国四大名绣之一');
INSERT INTO `orders` VALUES (42, 'http://localhost:2025/upload/141d683c-421d-4b21-9aaf-5f2fbd398f2c.png', 180, 180.00, 'PAID', '2025-03-01 13:45:33', '2025-03-01 13:46:20', 'BANK', 'SKU006', '西安兵马俑复制品', '2025-03-26 20:57:40', '按比例缩小的秦始皇兵马俑复制品，具有历史纪念意义');
INSERT INTO `orders` VALUES (43, 'http://localhost:2025/upload/c611b539-6755-47e3-b46c-2de8b99297e4.png', 95, 95.00, 'COMPLETED', '2025-03-08 10:15:22', '2025-03-08 10:16:30', 'WECHAT', 'SKU007', '海南椰雕', '2025-03-26 20:57:40', '用海南椰子壳雕刻而成的工艺品，具有热带风情');
INSERT INTO `orders` VALUES (44, 'http://localhost:2025/upload/ea24b498-cbc6-471c-a65c-f892f97c1249.png', 55, 55.00, 'SHIPPED', '2025-03-12 15:33:44', '2025-03-12 15:34:50', 'PAYPAL', 'SKU008', '北京景泰蓝', '2025-03-26 20:57:40', '传统珐琅工艺品，色彩绚丽，工艺精湛');
INSERT INTO `orders` VALUES (45, 'http://localhost:2025/upload/d2dad7c3-1fa3-428b-9e5c-ebd89a4b7eea.png', 120, 120.00, 'PENDING', '2025-03-15 09:12:11', NULL, 'BANK', 'SKU009', '四川蜀绣', '2025-03-26 20:57:40', '中国四大名绣之一，以细腻的针法和丰富的色彩著称');
INSERT INTO `orders` VALUES (46, 'http://localhost:2025/upload/700be096-c583-4dfd-833a-ef9fe64c0867.png', 75, 75.00, 'COMPLETED', '2025-03-18 14:45:33', '2025-03-18 14:46:20', 'WECHAT', 'SKU010', '新疆和田玉', '2025-03-26 20:57:40', '质地温润，中国四大名玉之一');
INSERT INTO `orders` VALUES (47, 'http://localhost:2025/upload/59fee6e5-0f6b-4e4d-8bcb-91cb42faa96f.png', 200, 200.00, 'PAID', '2025-03-20 11:22:44', '2025-03-20 11:23:30', 'PAYPAL', 'SKU011', '广东潮汕木雕', '2025-03-26 20:57:40', '精细的木雕工艺品，具有浓郁的岭南特色');
INSERT INTO `orders` VALUES (48, 'http://localhost:2025/upload/7883c255-5976-4306-8be6-7707887d7f74.png', 90, 90.00, 'COMPLETED', '2025-03-22 16:15:22', '2025-03-22 16:16:10', 'BANK', 'SKU012', '湖南湘绣', '2025-03-26 20:57:40', '中国四大名绣之一，以写实风格和丰富色彩见长');
INSERT INTO `orders` VALUES (49, 'http://localhost:2025/upload/5878faa1-3ac8-49c5-b5e4-ec243ef39cca.png', 110, 110.00, 'SHIPPED', '2025-03-23 10:33:11', '2025-03-23 10:34:05', 'WECHAT', 'SKU013', '福建武夷岩茶', '2025-03-26 20:57:40', '乌龙茶中的极品，具有独特的岩韵');
INSERT INTO `orders` VALUES (50, 'item14.jpg', 60, 60.00, 'PENDING', '2025-03-24 13:45:22', NULL, 'PAYPAL', 'SKU014', '内蒙古马头琴', '2025-03-26 20:57:40', '蒙古族传统乐器，琴头雕刻成马头形状');
INSERT INTO `orders` VALUES (51, 'item15.jpg', 130, 130.00, 'COMPLETED', '2025-03-25 09:12:33', '2025-03-25 09:13:20', 'BANK', 'SKU015', '山西老陈醋', '2025-03-26 20:57:40', '传统发酵工艺酿造，酸香浓郁，回味悠长');
INSERT INTO `orders` VALUES (52, 'item16.jpg', 70, 70.00, 'PAID', '2025-03-26 14:22:44', '2025-03-26 14:23:30', 'WECHAT', 'SKU016', '南京云锦', '2025-03-26 20:57:40', '中国传统提花丝织工艺品，有\"寸锦寸金\"之称');
INSERT INTO `orders` VALUES (53, 'item17.jpg', 95, 95.00, 'COMPLETED', '2025-03-27 11:15:55', '2025-03-27 11:16:40', 'PAYPAL', 'SKU017', '安徽宣纸', '2025-03-26 20:57:40', '中国传统书画用纸，质地绵韧，墨韵清晰');
INSERT INTO `orders` VALUES (54, 'item18.jpg', 180, 180.00, 'SHIPPED', '2025-03-28 15:33:22', '2025-03-28 15:34:10', 'BANK', 'SKU018', '贵州茅台酒', '2025-03-26 20:57:40', '中国酱香型白酒的代表，具有独特的酿造工艺');
INSERT INTO `orders` VALUES (55, 'item19.jpg', 50, 50.00, 'PENDING', '2025-03-29 10:45:11', NULL, 'WECHAT', 'SKU019', '台湾高山茶', '2025-03-26 20:57:40', '生长在海拔1000米以上的高山茶园，香气清雅');

-- ----------------------------
-- Table structure for payments
-- ----------------------------
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `item_id` int NOT NULL DEFAULT 0,
  `item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `amount` decimal(10, 2) NOT NULL,
  `payment_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `status` enum('PENDING','SUCCESS','FAILED','COMPLETED') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'PENDING',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` int NULL DEFAULT 0,
  `quantity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 191 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payments
-- ----------------------------
INSERT INTO `payments` VALUES (120, 'hhj', 'PAY17467587392354052', 0, '重庆火锅', 98.00, 'ALIPAY', 'SUCCESS', '2025-05-09 10:45:39', '2025-05-11 12:23:04', 0, '1');
INSERT INTO `payments` VALUES (121, 'hhj', 'PAY17467590468854939', 0, '景德镇瓷器', 85.00, 'BANK', 'SUCCESS', '2025-05-09 10:50:47', '2025-05-11 12:23:04', 0, '1');
INSERT INTO `payments` VALUES (122, 'hhj', 'PAY17467612120942358', 2, '上海小笼包', 28.00, 'BANK', 'SUCCESS', '2025-05-09 11:26:52', '2025-05-11 12:23:05', 0, '1');
INSERT INTO `payments` VALUES (123, 'hhj', 'PAY17467612430562292', 5, '九寨沟酒店', 600.00, NULL, 'PENDING', '2025-05-09 11:27:23', '2025-05-11 12:23:05', 1, '1');
INSERT INTO `payments` VALUES (125, 'gzy', 'PAY17467670393912636', 0, '黄山酒店', 750.00, 'WECHAT', 'SUCCESS', '2025-05-09 13:03:59', '2025-05-11 12:23:06', 0, '1');
INSERT INTO `payments` VALUES (126, 'gzy', 'PAY17467670491309638', 38, '景德镇瓷器', 85.00, NULL, 'PENDING', '2025-05-09 13:04:09', '2025-05-11 12:23:07', 1, '1');
INSERT INTO `payments` VALUES (138, '345', 'PAY17470605963544332', 2, '绍兴古镇探秘', 369.00, 'ALIPAY', 'SUCCESS', '2025-05-12 22:36:36', '2025-05-12 22:38:42', 0, '3');
INSERT INTO `payments` VALUES (140, '567', 'PAY17471835022299237', 2, '绍兴古镇探秘', 369.00, NULL, 'PENDING', '2025-05-14 08:45:02', '2025-05-14 08:45:02', 1, '3');
INSERT INTO `payments` VALUES (141, '567', 'PAY17471835207665919', 0, '故宫', 240.00, 'WECHAT', 'FAILED', '2025-05-14 08:45:21', '2025-05-14 08:45:20', 0, '2');
INSERT INTO `payments` VALUES (154, '李四', 'PAY17520630147355708', 0, '绍兴古镇探秘', 492.00, 'ALIPAY', 'SUCCESS', '2025-07-09 20:10:15', '2025-07-09 20:10:14', 0, '4');
INSERT INTO `payments` VALUES (155, '李四', 'PAY17520630250171739', 2, '故宫', 240.00, NULL, 'PENDING', '2025-07-09 20:10:25', '2025-07-09 20:10:25', 1, '2');
INSERT INTO `payments` VALUES (158, '李四', 'PAY17520630384382352', 0, '长城酒店', 240.00, 'BANK', 'SUCCESS', '2025-07-09 20:10:38', '2025-07-09 20:10:38', 0, '3');
INSERT INTO `payments` VALUES (164, 'admin', 'PAY17533300029244924', 0, '厦门鼓浪屿酒店', 900.00, 'BANK', 'SUCCESS', '2025-07-24 12:06:43', '2025-07-24 12:06:42', 0, '1');
INSERT INTO `payments` VALUES (165, 'admin', 'PAY17544034816767325', 0, '美国', 213.00, 'ALIPAY', 'SUCCESS', '2025-08-05 22:18:02', '2025-08-05 22:18:01', 0, '1');
INSERT INTO `payments` VALUES (167, 'admin', 'PAY17544036327031462', 0, '南非', 48.00, 'WECHAT', 'SUCCESS', '2025-08-05 22:20:33', '2025-08-05 22:20:32', 0, '1');
INSERT INTO `payments` VALUES (168, 'admin', 'PAY17544037581333522', 0, '法国', 165.00, 'ALIPAY', 'SUCCESS', '2025-08-05 22:22:38', '2025-08-05 22:22:38', 0, '1');
INSERT INTO `payments` VALUES (170, 'admin', 'PAY17544039005444056', 0, '吉林朝鲜冷面', 144.00, 'WECHAT', 'SUCCESS', '2025-08-05 22:25:01', '2025-08-05 22:25:00', 0, '3');
INSERT INTO `payments` VALUES (172, 'admin', 'PAY17544069525792908', 0, '故宫', 259.00, 'ALIPAY', 'SUCCESS', '2025-08-05 23:15:53', '2025-08-05 23:15:52', 0, '1');
INSERT INTO `payments` VALUES (173, 'admin', 'PAY17544089434463509', 0, '重庆火锅', 98.00, 'ALIPAY', 'SUCCESS', '2025-08-05 23:49:03', '2025-08-05 23:49:03', 0, '1');
INSERT INTO `payments` VALUES (174, 'admin', 'PAY17544090210281848', 0, '上海小笼包', 28.00, 'BANK', 'SUCCESS', '2025-08-05 23:50:21', '2025-08-05 23:50:21', 0, '1');
INSERT INTO `payments` VALUES (176, 'admin', 'PAY1754409141392495', 0, '英国', 540.00, 'ALIPAY', 'SUCCESS', '2025-08-05 23:52:21', '2025-08-05 23:52:21', 0, '1');
INSERT INTO `payments` VALUES (177, '李四', 'PAY1754919532000881', 0, '绍兴古镇探秘', 123.00, 'WECHAT', 'SUCCESS', '2025-08-11 21:38:52', '2025-08-11 21:38:52', 0, '1');
INSERT INTO `payments` VALUES (178, '李四', 'PAY17549195759414975', 1, '长城', 80.00, NULL, 'PENDING', '2025-08-11 21:39:35', '2025-08-11 21:39:35', 1, '1');
INSERT INTO `payments` VALUES (180, 'admin', 'PAY17550744682062814', 0, '绍兴古镇探秘', 123.00, 'WECHAT', 'SUCCESS', '2025-08-13 16:41:08', '2025-08-13 16:41:08', 0, '1');
INSERT INTO `payments` VALUES (181, 'admin', 'PAY17550744760502278', 0, '千岛湖自驾', 26614.00, 'BANK', 'SUCCESS', '2025-08-13 16:41:16', '2025-08-13 16:41:16', 0, '1');
INSERT INTO `payments` VALUES (182, 'admin', 'PAY17550744848355319', 0, '绍兴古镇探秘', 123.00, 'ALIPAY', 'FAILED', '2025-08-13 16:41:25', '2025-08-13 16:41:24', 0, '1');
INSERT INTO `payments` VALUES (183, 'admin', 'PAY17550744870601957', 0, '绍兴古镇探秘', 123.00, 'ALIPAY', 'FAILED', '2025-08-13 16:41:27', '2025-08-13 16:41:27', 0, '1');
INSERT INTO `payments` VALUES (184, 'admin', 'PAY17550744890197835', 0, '绍兴古镇探秘', 123.00, 'WECHAT', 'FAILED', '2025-08-13 16:41:29', '2025-08-13 16:41:29', 0, '1');
INSERT INTO `payments` VALUES (185, 'admin', 'PAY17550744914438920', 0, '绍兴古镇探秘', 123.00, 'BANK', 'SUCCESS', '2025-08-13 16:41:31', '2025-08-13 16:41:31', 0, '1');
INSERT INTO `payments` VALUES (186, 'admin', 'PAY17550744995447427', 0, '绍兴古镇探秘', 123.00, 'ALIPAY', 'SUCCESS', '2025-08-13 16:41:40', '2025-08-13 16:41:39', 0, '1');
INSERT INTO `payments` VALUES (188, 'admin', 'PAY17550745292765153', 0, '故宫', 120.00, 'WECHAT', 'SUCCESS', '2025-08-13 16:42:09', '2025-08-13 16:42:09', 0, '1');

-- ----------------------------
-- Table structure for post_images
-- ----------------------------
DROP TABLE IF EXISTS `post_images`;
CREATE TABLE `post_images`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `post_id` bigint NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `post_id`(`post_id` ASC) USING BTREE,
  CONSTRAINT `post_images_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `travel_posts` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_images
-- ----------------------------

-- ----------------------------
-- Table structure for provinces
-- ----------------------------
DROP TABLE IF EXISTS `provinces`;
CREATE TABLE `provinces`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '省份名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of provinces
-- ----------------------------
INSERT INTO `provinces` VALUES (2, '上海');
INSERT INTO `provinces` VALUES (23, '云南');
INSERT INTO `provinces` VALUES (28, '内蒙古');
INSERT INTO `provinces` VALUES (1, '北京');
INSERT INTO `provinces` VALUES (27, '台湾');
INSERT INTO `provinces` VALUES (8, '吉林');
INSERT INTO `provinces` VALUES (21, '四川');
INSERT INTO `provinces` VALUES (3, '天津');
INSERT INTO `provinces` VALUES (31, '宁夏');
INSERT INTO `provinces` VALUES (12, '安徽');
INSERT INTO `provinces` VALUES (15, '山东');
INSERT INTO `provinces` VALUES (6, '山西');
INSERT INTO `provinces` VALUES (19, '广东');
INSERT INTO `provinces` VALUES (29, '广西');
INSERT INTO `provinces` VALUES (32, '新疆');
INSERT INTO `provinces` VALUES (10, '江苏');
INSERT INTO `provinces` VALUES (14, '江西');
INSERT INTO `provinces` VALUES (5, '河北');
INSERT INTO `provinces` VALUES (16, '河南');
INSERT INTO `provinces` VALUES (11, '浙江');
INSERT INTO `provinces` VALUES (20, '海南');
INSERT INTO `provinces` VALUES (17, '湖北');
INSERT INTO `provinces` VALUES (18, '湖南');
INSERT INTO `provinces` VALUES (34, '澳门');
INSERT INTO `provinces` VALUES (25, '甘肃');
INSERT INTO `provinces` VALUES (13, '福建');
INSERT INTO `provinces` VALUES (30, '西藏');
INSERT INTO `provinces` VALUES (22, '贵州');
INSERT INTO `provinces` VALUES (7, '辽宁');
INSERT INTO `provinces` VALUES (4, '重庆');
INSERT INTO `provinces` VALUES (24, '陕西');
INSERT INTO `provinces` VALUES (26, '青海');
INSERT INTO `provinces` VALUES (33, '香港');
INSERT INTO `provinces` VALUES (9, '黑龙江');

-- ----------------------------
-- Table structure for safety_tips
-- ----------------------------
DROP TABLE IF EXISTS `safety_tips`;
CREATE TABLE `safety_tips`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述内容',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片URL',
  `category_id` bigint NULL DEFAULT NULL COMMENT '分类ID',
  `sort_weight` int NULL DEFAULT 0 COMMENT '排序权重(0-999)',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
  `creator_id` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `updater_id` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_category`(`category_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_sort`(`sort_weight` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '安全知识提示表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of safety_tips
-- ----------------------------
INSERT INTO `safety_tips` VALUES (1, '火灾逃生指南', '1.保持冷静，迅速判断安全出口位置\n2.用湿毛巾捂住口鼻', 'http://localhost:2025/upload/95e511b2-dd7e-461c-a65a-5e10df3501e8.png', 1, 0, 1, 1, NULL, '2025-04-07 09:29:56', '2025-09-03 21:09:55');
INSERT INTO `safety_tips` VALUES (2, '电器使用安全', '1.不要用湿手接触电器\n2.定期检查电线是否老化', 'http://localhost:2025/upload/e3fcf169-4b90-47ef-9445-22d06bbe7699.png', 2, 0, 1, 1, NULL, '2025-04-07 09:29:56', '2025-09-03 21:10:00');
INSERT INTO `safety_tips` VALUES (3, '安全过马路', '1.遵守交通信号灯\n2.走人行横道\n', 'http://localhost:2025/upload/230020e3-957e-4c03-a1b9-92f6709a7d15.png', 3, 0, 1, 1, NULL, '2025-04-07 09:29:56', '2025-09-03 21:10:06');

-- ----------------------------
-- Table structure for travel_attractions
-- ----------------------------
DROP TABLE IF EXISTS `travel_attractions`;
CREATE TABLE `travel_attractions`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `rating` decimal(2, 1) NULL DEFAULT NULL,
  `tags` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_attractions
-- ----------------------------
INSERT INTO `travel_attractions` VALUES (1, '九寨沟风景区', '四川', 'https://example.com/attraction1.jpg', 'nature', 220.00, 4.9, '世界遗产,5A景区');
INSERT INTO `travel_attractions` VALUES (2, '故宫博物院', '北京', 'https://example.com/attraction2.jpg', 'history', 60.00, 4.8, '世界遗产,必去景点');
INSERT INTO `travel_attractions` VALUES (3, '上海迪士尼乐园', '上海', 'https://example.com/attraction3.jpg', 'amusement', 399.00, 4.7, '亲子游,主题乐园');
INSERT INTO `travel_attractions` VALUES (4, '大英博物馆', '伦敦', 'https://example.com/attraction4.jpg', 'museum', 0.00, 4.6, '免费,世界著名');
INSERT INTO `travel_attractions` VALUES (5, '银座购物区', '东京', 'https://example.com/attraction5.jpg', 'shopping', 0.00, 4.5, '购物天堂,奢侈品');

-- ----------------------------
-- Table structure for travel_blog
-- ----------------------------
DROP TABLE IF EXISTS `travel_blog`;
CREATE TABLE `travel_blog`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地点',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
  `likes` int NULL DEFAULT 0 COMMENT '喜欢',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `browse` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浏览',
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `favorites` int NULL DEFAULT 0 COMMENT '收藏',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updata_at` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_blog
-- ----------------------------
INSERT INTO `travel_blog` VALUES (2, '绍兴古镇探秘', '小桥流水人家的江南韵味', '绍兴', 'http://localhost:2025/upload/ab978574-c56d-4137-bb71-421820ba1e8e.png', 264, 123.00, '123', '0', 232, '2025-09-03 22:53:27', '2025-09-03 22:53:32');
INSERT INTO `travel_blog` VALUES (3, '千岛湖自驾', '湖中青岛，岛中有湖的奇妙景观', '杭州', 'http://localhost:2025/upload/6e21e088-404d-439d-baa0-97a5953cc20e.png', 399, 12312.00, '12333', '0', 106, '2025-09-03 22:53:29', '2025-09-03 22:53:27');
INSERT INTO `travel_blog` VALUES (4, '乌镇夜色漫游', '桨声灯影里的江南水乡', '嘉兴', 'http://localhost:2025/upload/962aa934-fe6c-4a33-9a38-3c452bf94d77.png', 143, 234.00, '1233', '0', 205, '2025-09-03 22:53:27', '2025-09-03 22:53:27');
INSERT INTO `travel_blog` VALUES (5, '普陀山朝圣之旅', '海天佛国的宗教文化体验', '舟山', 'http://localhost:2025/upload/ffe026f4-532d-4802-8e9c-89cff8766556.png', 398, 2314.00, '343', '0', 82, '2025-09-03 22:53:27', '2025-09-03 22:53:27');
INSERT INTO `travel_blog` VALUES (6, '横店影视城', '穿越古今的影视主题乐园', '金华', 'http://localhost:2025/upload/983725a4-a1ca-4dcb-aa11-102a179ee903.png', 49, 213.00, '533', '0', 107, '2025-09-03 22:53:27', '2025-09-03 22:53:27');
INSERT INTO `travel_blog` VALUES (7, '雁荡山地质奇观', '火山岩地貌的自然博物馆', '温州', 'http://localhost:2025/upload/00b54301-4add-4209-833f-1a8077679b30.png', 498, 213.00, '3434', '0', 43, '2025-09-03 22:53:27', '2025-09-03 22:53:27');
INSERT INTO `travel_blog` VALUES (8, '莫干山民宿体验', '竹林深处的避世天堂', '湖州', 'http://localhost:2025/upload/8042a25d-268c-4f68-8084-e1df00259197.png', 128, 34435.00, '432', '0', 20, '2025-09-03 22:53:27', '2025-09-03 22:53:27');
INSERT INTO `travel_blog` VALUES (9, '南浔古镇美食记', '江南传统小吃的舌尖之旅', '湖州', 'http://localhost:2025/upload/843bca13-ab91-480e-87f8-c7779fb1070f.png', 336, 2323.00, '232', '0', 191, '2025-09-03 22:53:27', '2025-09-03 22:53:27');
INSERT INTO `travel_blog` VALUES (11, '天台山国清寺', '佛教天台宗的发源地', '台州', 'http://localhost:2025/upload/2b1afe72-7307-4d70-ae68-417363d7d77d.png', 416, 76.00, '1234', '0', 73, '2025-09-03 22:53:27', '2025-09-03 22:53:27');
INSERT INTO `travel_blog` VALUES (12, '宁波老外滩夜游', '百年商埠的现代风情', '宁波', 'http://localhost:2025/upload/08462fb2-1c7d-4da6-8201-1bc2e1b8a4ff.png', 459, 213.00, '1233', '0', 120, '2025-09-03 22:53:27', '2025-09-03 22:53:27');
INSERT INTO `travel_blog` VALUES (13, '嵊泗列岛自由行', '东海上的璀璨明珠', '舟山', 'http://localhost:2025/upload/ed32587f-2de9-4e10-b726-1311a846e5ca.png', 21, 234.00, '223', '0', 139, '2025-09-03 22:53:27', '2025-09-03 22:53:27');
INSERT INTO `travel_blog` VALUES (37, '马尔代夫', '以其清澈的海水、丰富的海洋生物和豪华的度假村而闻名于世，被誉为“印度洋的珍珠”', '马尔代夫', 'http://localhost:2025/upload/ecba711f-6ddb-4713-9b47-9d29a3609ffd.png', 2087, 4599.00, NULL, NULL, 520, '2025-09-04 11:22:19', NULL);

-- ----------------------------
-- Table structure for travel_cards
-- ----------------------------
DROP TABLE IF EXISTS `travel_cards`;
CREATE TABLE `travel_cards`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `likes` int NULL DEFAULT 0,
  `saved` tinyint(1) NULL DEFAULT 0,
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `badge_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `badge_text` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `score` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_cards
-- ----------------------------
INSERT INTO `travel_cards` VALUES (1, '马尔代夫7日梦幻之旅', '探索马尔代夫最美丽的海岛，体验水上别墅和浮潜的乐趣...', 'http://localhost:2025/image/travelcard/medf.jpg', 1268, 0, 'island', 'hot', '热门', '4.6');
INSERT INTO `travel_cards` VALUES (2, '日本京都文化深度游', '穿越时光，感受日本传统文化的魅力，从金阁寺到祗园...', 'http://localhost:2025/image/travelcard/rbdj.jpg', 1581, 0, 'culture', NULL, NULL, '4.6');
INSERT INTO `travel_cards` VALUES (3, '巴黎浪漫之旅', '漫步塞纳河畔，欣赏埃菲尔铁塔的浪漫夜景...', 'http://localhost:2025/image/travelcard/bl.jpg', 629, 1, 'romantic', 'new', '新品', '4.6');
INSERT INTO `travel_cards` VALUES (4, '泰国清迈美食之旅', '品尝泰国美食，探索清迈夜市的热闹与繁华...', 'http://localhost:2025/image/travelcard/tgqm.jpg', 1732, 1, 'food', NULL, NULL, '4.6');
INSERT INTO `travel_cards` VALUES (5, '澳大利亚悉尼海滩之旅', '享受悉尼的阳光沙滩，冲浪和海豚共舞...', 'http://localhost:2025/image/travelcard/adlyxn.jpg', 1248, 1, 'beach', 'hot', '热门', '4.6');
INSERT INTO `travel_cards` VALUES (6, '意大利托斯卡纳乡村之旅', '穿梭于托斯卡纳的葡萄园和橄榄树之间，体验意大利乡村的宁静与美好...', 'http://localhost:2025/image/travelcard/medf.jpg', 138, 1, 'countryside', NULL, NULL, NULL);
INSERT INTO `travel_cards` VALUES (7, '南非野生动物探险之旅', '深入非洲草原，与狮子、大象等野生动物近距离接触...', 'http://localhost:2025/image/travelcard/nfysdw.jpg', 1390, 0, 'wildlife', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (8, '希腊圣托里尼岛之旅', '感受圣托里尼岛的蓝白小镇，仿佛置身于童话世界...', 'http://localhost:2025/image/travelcard/xlstln.jpg', 1168, 1, 'island', 'hot', '热门', NULL);
INSERT INTO `travel_cards` VALUES (9, '加拿大落基山脉徒步之旅', '在落基山脉中徒步，欣赏壮丽的山景和清澈的湖泊...', 'http://localhost:2025/image/travelcard/jndljs.jpg', 1681, 0, 'hiking', NULL, NULL, NULL);
INSERT INTO `travel_cards` VALUES (10, '摩洛哥马拉喀什之旅', '穿梭于马拉喀什的古老街道，感受阿拉伯文化的独特魅力...', 'http://localhost:2025/image/travelcard/mlgmlks.jpg', 1437, 1, 'culture', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (11, '新西兰皇后镇冒险之旅', '在皇后镇体验各种极限运动，如蹦极、跳伞等...', '@/assets/北京天坛.jpg', 177, 1, 'adventure', 'hot', '热门', NULL);
INSERT INTO `travel_cards` VALUES (12, '西班牙巴塞罗那建筑之旅', '欣赏高迪的建筑杰作，感受巴塞罗那的艺术氛围...', '@/assets/北京天坛.jpg', 426, 1, 'architecture', NULL, NULL, NULL);
INSERT INTO `travel_cards` VALUES (13, '美国黄石国家公园之旅', '探索黄石国家公园的火山地貌和野生动物...', '@/assets/北京天坛.jpg', 507, 0, 'nature', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (14, '越南下龙湾游船之旅', '乘坐游船游览下龙湾的奇峰异石，仿佛置身于仙境...', '@/assets/北京天坛.jpg', 1808, 1, 'cruise', 'hot', '热门', NULL);
INSERT INTO `travel_cards` VALUES (15, '英国伦敦历史之旅', '参观伦敦的大本钟、伦敦塔等历史建筑，感受英国的皇家气息...', '@/assets/北京天坛.jpg', 1798, 0, 'history', NULL, NULL, NULL);
INSERT INTO `travel_cards` VALUES (16, '巴西里约热内卢狂欢之旅', '加入里约热内卢的狂欢节，感受巴西的热情与活力...', '@/assets/北京天坛.jpg', 1900, 1, 'festival', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (17, '冰岛极光之旅', '在冰岛的极寒之地，追寻神秘的极光...', '@/assets/北京天坛.jpg', 1921, 1, 'aurora', 'hot', '热门', NULL);
INSERT INTO `travel_cards` VALUES (18, '印度泰姬陵之旅', '参观印度的泰姬陵，感受这座爱情建筑的浪漫与庄严...', '@/assets/北京天坛.jpg', 1410, 0, 'culture', NULL, NULL, NULL);
INSERT INTO `travel_cards` VALUES (19, '阿根廷布宜诺斯艾利斯探戈之旅', '在布宜诺斯艾利斯的街头巷尾，感受探戈的热情与奔放...', '@/assets/北京天坛.jpg', 1493, 1, 'dance', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (20, '瑞士阿尔卑斯山滑雪之旅', '在阿尔卑斯山的雪道上畅滑，享受滑雪的乐趣...', '@/assets/北京天坛.jpg', 1682, 1, 'skiing', 'hot', '热门', NULL);
INSERT INTO `travel_cards` VALUES (21, '埃及金字塔之旅', '探索埃及的金字塔，揭开古埃及文明的神秘面纱...', '@/assets/北京天坛.jpg', 535, 0, 'history', NULL, NULL, NULL);
INSERT INTO `travel_cards` VALUES (22, '墨西哥坎昆海滩度假之旅', '在坎昆的海滩上享受阳光沙滩，放松身心...', '@/assets/北京天坛.jpg', 1037, 0, 'beach', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (23, '挪威峡湾游船之旅', '乘坐游船游览挪威的峡湾，欣赏壮观的峡湾景色...', 'http://localhost:2025/upload/286b36aa-8119-4255-9902-beb404fb1a57.png', 886, 1, 'cruise', 'hot', '热门', NULL);
INSERT INTO `travel_cards` VALUES (24, '柬埔寨吴哥窟之旅', '参观柬埔寨的吴哥窟，感受古高棉文化的辉煌...', 'http://localhost:2025/upload/11fc9b1c-4af4-4941-8769-9bcaf6314269.png', 1505, 1, 'culture', NULL, '美食', NULL);
INSERT INTO `travel_cards` VALUES (25, '葡萄牙里斯本之旅', '漫步里斯本的街头，感受葡萄牙的海洋文化和历史韵味...', 'http://localhost:2025/upload/f5282ab1-31a4-40e3-a144-a457c1bcb1cf.png', 624, 1, 'history', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (26, '南非开普敦之旅', '在开普敦欣赏桌山的美景，体验南非的多元文化...', 'http://localhost:2025/upload/d624c7ec-0e12-4ee5-8150-877ca0eabb78.png', 307, 1, 'culture', 'hot', '热门', NULL);
INSERT INTO `travel_cards` VALUES (27, '日本北海道雪景之旅', '在日本北海道欣赏美丽的雪景，体验冬季的浪漫与宁静...', 'http://localhost:2025/upload/7be77a50-bacb-464d-ba00-3de7b850d950.png', 235, 1, 'winter', NULL, '旅行', NULL);
INSERT INTO `travel_cards` VALUES (28, '澳大利亚大堡礁潜水之旅', '在大堡礁潜水，探索海底世界的奇妙与美丽...', 'http://localhost:2025/upload/18fcb0ab-075e-4f94-bc87-30218b4122a0.png', 1695, 0, 'diving', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (29, '意大利威尼斯水城之旅', '乘坐贡多拉游览威尼斯的水道，感受水城的独特魅力...', 'http://localhost:2025/upload/0da2a75e-bf9c-455a-899a-3ff5b5c5ec07.png', 91, 0, 'watercity', 'hot', '热门', NULL);
INSERT INTO `travel_cards` VALUES (30, '美国纽约都市之旅', '感受纽约的繁华与活力，参观自由女神像、时代广场等标志性建筑...', 'http://localhost:2025/upload/6d48a76f-313e-4482-8c93-48f3fe4ac3c6.png', 473, 1, 'city', NULL, '旅行', NULL);
INSERT INTO `travel_cards` VALUES (31, '希腊雅典古迹之旅', '参观雅典卫城等古迹，感受古希腊文明的辉煌...', 'http://localhost:2025/upload/e52bef62-363a-496f-9339-480f48e5159a.png', 177, 0, 'history', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (32, '马来西亚兰卡威岛之旅', '在兰卡威岛享受阳光沙滩，体验水上活动的乐趣...', 'http://localhost:2025/upload/15178695-ea87-4344-83fa-ec9c19dcfa0b.png', 1108, 0, 'beach', 'hot', '热门', NULL);

-- ----------------------------
-- Table structure for travel_carousel
-- ----------------------------
DROP TABLE IF EXISTS `travel_carousel`;
CREATE TABLE `travel_carousel`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_carousel
-- ----------------------------
INSERT INTO `travel_carousel` VALUES (7, 'http://localhost:2025/upload/7ef9c22b-3bf3-4d88-a715-d4644cb403c0.png', '阳光海滩', '夏威夷', '在金色沙滩上享受阳光和海浪。', '2025-08-09 14:16:53', '2025-08-17 23:17:56');
INSERT INTO `travel_carousel` VALUES (8, 'http://localhost:2025/upload/a9aa60c23af2b5b5.jpg_r_1360x1360x95_5dac3624.jpg', '历史遗迹', '罗马', '参观古老的斗兽场和历史遗迹。', '2025-08-09 14:17:01', '2025-08-09 14:17:04');
INSERT INTO `travel_carousel` VALUES (9, 'http://localhost:2025/upload/31838030-0280-45e5-ba29-1a7b06aff47a.png', '自然奇观', '黄石国家公园', '探索壮观的地热喷泉和野生动物。', '2025-08-09 14:17:01', '2025-08-09 14:17:01');
INSERT INTO `travel_carousel` VALUES (10, 'http://localhost:2025/upload/1684144616881709.jpg', '文化之旅', '北京', '游览故宫和长城，感受中国文化。', '2025-08-09 14:17:01', '2025-08-09 14:17:01');
INSERT INTO `travel_carousel` VALUES (11, 'http://localhost:2025/upload/588e90da44b9a2b5.jpg', '现代都市', '东京', '体验日本的现代科技和传统文化。', '2025-08-09 14:17:01', '2025-08-09 14:17:01');
INSERT INTO `travel_carousel` VALUES (12, 'http://localhost:2025/upload/46b991ab-c43e-4512-a677-c60b92d73801.png', '艺术殿堂', '巴黎', '欣赏卢浮宫的艺术珍品和埃菲尔铁塔的美景。', '2025-08-09 14:17:01', '2025-09-03 22:41:05');

-- ----------------------------
-- Table structure for travel_collection
-- ----------------------------
DROP TABLE IF EXISTS `travel_collection`;
CREATE TABLE `travel_collection`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '用户名',
  `collectionname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '收藏名',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '价格',
  `profile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '简介',
  `location` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '地点',
  `characteristics` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '特色',
  `score` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '评分',
  `collection` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '收藏',
  `sales` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '销量',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '图片',
  `classification` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '分类',
  `createTime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_collection
-- ----------------------------
INSERT INTO `travel_collection` VALUES (1, 'user1', '黄山之旅', 1200.50, '领略黄山的奇松怪石', '安徽黄山', '自然风光', '4.5', '100', '500', 'http://localhost:2025/upload/9e526ae3-1af5-42c9-9e4e-4431902c041a.png', '旅行', '2025-08-24 13:01:54', '2025-09-04 09:45:58');
INSERT INTO `travel_collection` VALUES (4, 'user4', '九寨沟四季行', 1500.00, '欣赏九寨沟四季美景', '四川九寨沟', '自然景观', '4.6', '120', '350', 'http://localhost:2025/upload/3773ec09-ecd1-49e7-9c1c-ee57de996010.png', '自然', '2025-08-24 13:01:54', '2025-09-04 09:46:08');

-- ----------------------------
-- Table structure for travel_foods
-- ----------------------------
DROP TABLE IF EXISTS `travel_foods`;
CREATE TABLE `travel_foods`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '美食名称',
  `province_id` int NULL DEFAULT NULL COMMENT '所属省份ID',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `sales` int NULL DEFAULT 0 COMMENT '销量',
  `rating` decimal(3, 1) NULL DEFAULT 0.0 COMMENT '评分',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '美食描述',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '美食分类',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '美食状态',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '美食位置',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '美食图片URL',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `province_id`(`province_id` ASC) USING BTREE,
  CONSTRAINT `travel_foods_ibfk_1` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_foods
-- ----------------------------
INSERT INTO `travel_foods` VALUES (1, '北京烤鸭', 1, 250.00, 3200, 4.9, '北京特色烤鸭，皮脆肉嫩，风味独特', '小吃', '预订', '中国四川某地', '11632983407', 'http://localhost:2025/upload/ed1196b2-9831-4eb6-904e-f3ec2ba06db5.png', '2025-03-17 10:28:45', '2025-09-08 10:32:31');
INSERT INTO `travel_foods` VALUES (2, '上海小笼包', 2, 28.00, 5600, 4.8, '上海特色小吃，小笼包皮薄馅嫩，汤汁丰富', '汤品', '在售', '中国黑龙江某地', '18852299416', 'http://localhost:2025/upload/b2d6a213-d054-41dc-abc4-92ca948aa276.png', '2025-03-17 10:28:45', '2025-09-08 10:32:31');
INSERT INTO `travel_foods` VALUES (4, '重庆火锅', 4, 98.00, 2543, 4.8, '重庆特色火锅，麻辣鲜香，回味无穷', '甜品', '停售', '中国山西某地', '16067115969', 'http://localhost:2025/upload/85bda384-dc1b-493b-b0b5-6ed0e9ae34e4.png', '2025-03-17 10:28:45', '2025-09-08 10:32:31');
INSERT INTO `travel_foods` VALUES (5, '河北板面', 5, 2220.00, 4300, 4.7, '河北特色面食，面条劲道，汤汁浓郁', '小吃', '在售', '中国青海某地', '14984596869', 'http://localhost:2025/upload/8f1df9a2-da79-439e-8421-bbf5682a48c1.png', '2025-03-17 10:28:45', '2025-09-08 10:32:31');
INSERT INTO `travel_foods` VALUES (6, '山西刀削面', 6, 28.00, 5600, 4.8, '山西特色面食，面条劲道，配料丰富', '饮品', '预订', '中国安徽某地', '13280405070', 'http://localhost:2025/upload/c28fee60-b10f-48ec-9479-f463aabf15ca.png', '2025-03-17 10:28:45', '2025-09-08 10:32:31');
INSERT INTO `travel_foods` VALUES (7, '辽宁锅包肉', 7, 48.00, 5600, 4.8, '辽宁传统名菜，锅包肉外酥里嫩，酸甜可口', '小吃', '在售', '中国黑龙江某地', '11426665048', 'http://localhost:2025/upload/ed775d43-14f5-48c3-ba68-71e91d9bda02.png', '2025-03-17 10:28:45', '2025-09-08 10:32:46');
INSERT INTO `travel_foods` VALUES (8, '吉林朝鲜冷面', 8, 22.00, 3400, 4.7, '吉林特色小吃，冷面爽滑，汤汁酸甜', '汤品', '在售', '中国西藏某地', '17633373090', 'http://localhost:2025/upload/77fdd118-b82c-4bc7-991f-a012c22104e1.png', '2025-03-17 10:28:45', '2025-09-08 10:32:49');
INSERT INTO `travel_foods` VALUES (9, '黑龙江红肠', 9, 38.00, 4500, 4.6, '黑龙江特色美食，红肠口感醇厚，风味独特', '饮品', '停售', '中国西藏某地', '19687173358', 'http://localhost:2025/upload/51adad20-ca9f-429d-840d-7a30f0dd7a57.png', '2025-03-17 10:28:45', '2025-09-08 10:32:51');
INSERT INTO `travel_foods` VALUES (11, '浙江东坡肉', 11, 88.00, 1123, 4.7, '浙江名菜，肥而不腻，入口即化', '主食', '在售', '中国湖北某地', '14397313920', 'http://localhost:2025/upload/93d5eaba-254a-4077-9786-02e978fd9d31.png', '2025-03-17 10:28:45', '2025-09-08 10:32:57');
INSERT INTO `travel_foods` VALUES (12, '安徽臭豆腐', 12, 18.00, 7800, 4.7, '安徽特色小吃，外焦里嫩，香气扑鼻', '汤品', '在售', '中国上海某地', '13617704765', 'http://localhost:2025/upload/b0e73ca3-8c81-4edb-86d9-95108ef28572.png', '2025-03-17 10:28:45', '2025-09-08 10:33:00');
INSERT INTO `travel_foods` VALUES (13, '福建佛跳墙', 13, 298.00, 1200, 4.9, '福建名菜，食材丰富，汤汁浓郁', '小吃', '预订', '中国山西某地', '12690379227', 'http://localhost:2025/upload/f4fdd625-54c8-4ef5-8d86-3d4f3844fe1e.png', '2025-03-17 10:28:45', '2025-09-08 10:33:02');
INSERT INTO `travel_foods` VALUES (14, '江西瓦罐汤', 14, 38.00, 4500, 4.6, '江西特色汤品，鲜美可口，营养丰富', '汤品', '在售', '中国台湾某地', '18766061124', 'http://localhost:2025/upload/2e121215-2ef7-488f-b89e-aad5c5bbcc5e.png', '2025-03-17 10:28:45', '2025-09-08 10:33:04');
INSERT INTO `travel_foods` VALUES (15, '山东煎饼', 15, 12.00, 6700, 4.5, '山东传统小吃，薄脆可口，搭配丰富', '饮品', '停售', '中国甘肃某地', '17838320379', 'http://localhost:2025/upload/eae46c08-c83c-4a79-8462-9ab6cc3adb38.png', '2025-03-17 10:28:45', '2025-09-08 10:33:06');
INSERT INTO `travel_foods` VALUES (16, '河南烩面', 16, 28.00, 5600, 4.8, '河南特色面食，面条劲道，汤汁浓郁', '汤品', '停售', '中国内蒙古某地', '17493011449', 'https://source.unsplash.com/random/?huimian', '2025-03-17 10:28:45', '2025-09-08 10:33:08');
INSERT INTO `travel_foods` VALUES (17, '湖北鸭脖', 17, 22.00, 4300, 4.7, '湖北特色小吃，鸭脖香辣可口', '主食', '在售', '中国云南某地', '19199048742', 'https://source.unsplash.com/random/?duck', '2025-03-17 10:28:45', '2025-09-08 10:33:10');
INSERT INTO `travel_foods` VALUES (18, '湖南米粉', 18, 18.00, 7800, 4.7, '湖南经典小吃，米粉爽滑，汤汁鲜美', '汤品', '预订', '中国北京某地', '11339863542', 'https://source.unsplash.com/random/?rice', '2025-03-17 10:28:45', '2025-09-08 10:33:12');
INSERT INTO `travel_foods` VALUES (19, '广东早茶', 19, 58.00, 1890, 4.7, '经典广东早茶，丰富多样的点心', '主食', '停售', '中国西藏某地', '12450319198', 'https://source.unsplash.com/random/?dimsum', '2025-03-17 10:28:45', '2025-09-08 10:33:14');
INSERT INTO `travel_foods` VALUES (20, '海南清补凉', 20, 18.00, 3400, 4.6, '海南特色甜品，食材丰富，冰爽可口', '主食', '在售', '中国内蒙古某地', '12930610272', 'https://source.unsplash.com/random/?qingbuliang', '2025-03-17 10:28:45', '2025-09-08 10:33:17');
INSERT INTO `travel_foods` VALUES (21, '四川麻辣火锅', 21, 98.00, 2543, 4.8, '正宗四川麻辣火锅，体验舌尖上的麻辣盛宴', '汤品', '预订', '中国福建某地', '15723540433', 'https://source.unsplash.com/random/?hotpot', '2025-03-17 10:28:45', '2025-09-08 10:33:19');
INSERT INTO `travel_foods` VALUES (22, '贵州酸汤鱼', 22, 68.00, 2100, 4.5, '贵州特色美食，酸汤开胃，鱼肉鲜嫩', '汤品', '在售', '中国北京某地', '16795008823', 'https://source.unsplash.com/random/?suanthangyu', '2025-03-17 10:28:45', '2025-09-08 10:33:22');
INSERT INTO `travel_foods` VALUES (23, '云南过桥米线', 24, 48.00, 3400, 4.8, '云南特色小吃，汤热味鲜，米线爽滑', '小吃', '在售', '中国宁夏某地', '14081323403', 'https://source.unsplash.com/random/?guoqiaomixian', '2025-03-17 10:28:45', '2025-09-08 10:33:23');
INSERT INTO `travel_foods` VALUES (24, '陕西肉夹馍', 25, 18.00, 5600, 4.7, '陕西传统小吃，馍香肉嫩，肥而不腻', '饮品', '停售', '中国甘肃某地', '13498285075', 'https://source.unsplash.com/random/?roujiamo', '2025-03-17 10:28:45', '2025-09-08 10:33:25');
INSERT INTO `travel_foods` VALUES (25, '甘肃拉面', 26, 22.00, 4300, 4.6, '甘肃特色面食，面条劲道，汤汁浓郁', '主食', '预订', '中国台湾某地', '15324657261', 'https://source.unsplash.com/random/?lanzhou', '2025-03-17 10:28:45', '2025-09-08 10:33:27');
INSERT INTO `travel_foods` VALUES (26, '青海酸奶', 27, 12.00, 1500, 4.5, '青海特色饮品，口感醇厚，营养丰富', '主食', '停售', '中国台湾某地', '13996421857', 'https://source.unsplash.com/random/?yogurt', '2025-03-17 10:28:45', '2025-09-08 10:33:29');
INSERT INTO `travel_foods` VALUES (27, '台湾珍珠奶茶', 28, 18.00, 7800, 4.9, '台湾特色饮品，奶茶醇厚，珍珠Q弹', '小吃', '在售', '中国四川某地', '13475531200', 'https://source.unsplash.com/random/?pearlmilktea', '2025-03-17 10:28:45', '2025-09-08 10:33:31');
INSERT INTO `travel_foods` VALUES (28, '内蒙古烤全羊', 29, 398.00, 800, 4.9, '内蒙古特色美食，烤羊外焦里嫩，香气四溢', '汤品', '预订', '中国河北某地', '14397103450', 'https://source.unsplash.com/random/?kaoquanyang', '2025-03-17 10:28:45', '2025-09-08 10:33:33');

-- ----------------------------
-- Table structure for travel_hotel
-- ----------------------------
DROP TABLE IF EXISTS `travel_hotel`;
CREATE TABLE `travel_hotel`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hotelname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店名',
  `sales` decimal(10, 1) NULL DEFAULT NULL COMMENT '酒店销量',
  `evaluation` varbinary(10) NULL DEFAULT NULL COMMENT '酒店评分',
  `hoteldescription` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '酒店描述',
  `hotelimage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店图片',
  `hotelprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '酒店价格',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `hotelAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店地址',
  `hotelStar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店星级',
  `hotelFacility` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店设施',
  `hotelStatus` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '酒店状态',
  `hotelTraffic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店交通',
  `hotelService` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店服务',
  `hotelPhone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店电话',
  `hotelType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '酒店类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_hotel
-- ----------------------------
INSERT INTO `travel_hotel` VALUES (1, '长城酒店', 4.5, 0x3132352E30, '位于长城脚下，风景优美，交通便利', 'http://localhost:2025/upload/1723f4d1-48b6-44d7-803d-172dfad09c02.png', 80.00, '2025-03-12 16:01:45', '2025-09-08 08:30:21', '北京市海淀区中关村大街10号', '5星', '免费Wi-Fi, 停车场, 健身房', '0', '地铁4号线中关村站', '24小时前台服务, 叫醒服务', '010-12345678', '商务酒店');
INSERT INTO `travel_hotel` VALUES (2, '故宫酒店', 2.0, 0x3131312E30, '临近故宫，方便游览，周边美食众多', 'http://localhost:2025/upload/31dae9ea-d0ef-4a10-aa80-98750a743f62.png', 1200.00, '2025-03-12 16:01:45', '2025-09-08 08:30:22', '北京市东城区景山前街4号', '4星', '免费Wi-Fi, 停车场, 餐厅', '0', '地铁1号线天安门东站', '24小时前台服务, 行李寄存', '010-87654321', '文化主题酒店');
INSERT INTO `travel_hotel` VALUES (4, '黄山酒店', 4.0, 0x3131312E30, '位于黄山脚下，登山游客的首选', 'http://localhost:2025/upload/52878b0e-767b-4f45-9ffe-3336934ac24f.png', 750.00, '2025-03-12 16:01:45', '2025-09-08 08:30:22', '安徽省黄山市黄山区汤口镇', '3星', '免费Wi-Fi, 停车场, 会议室', '0', '黄山风景区南大门', '24小时前台服务, 叫醒服务', '0559-12345678', '度假酒店');
INSERT INTO `travel_hotel` VALUES (5, '九寨沟酒店', 3.0, 0x3333332E30, '靠近九寨沟景区，自然风光迷人', 'http://localhost:2025/upload/b7b94812-59b9-4d05-9a02-c240b49ecd67.png', 600.00, '2025-03-12 16:01:45', '2025-09-08 08:30:22', '四川省阿坝藏族羌族自治州九寨沟县漳扎镇', '4星', '免费Wi-Fi, 停车场, 餐厅', '0', '九寨沟景区入口', '24小时前台服务, 行李寄存', '0837-12345678', '自然景观酒店');
INSERT INTO `travel_hotel` VALUES (6, '桂林山水酒店', 4.0, 0x323232322E30, '依山傍水，喀斯特地貌景观独特', 'http://localhost:2025/upload/a6a1666d-2137-4b44-b2a3-00076b95e343.png', 550.00, '2025-03-12 16:01:45', '2025-09-08 08:30:23', '广西壮族自治区桂林市阳朔县阳朔镇', '3星', '免费Wi-Fi, 停车场, 游泳池', '0', '阳朔西街', '24小时前台服务, 叫醒服务', '0773-12345678', '山水酒店');
INSERT INTO `travel_hotel` VALUES (7, '张家界酒店', 3.0, 0x3434342E30, '临近张家界国家森林公园，适合探险', 'http://localhost:2025/upload/cafb06a2-a2b3-4278-b20f-7b6f90a650eb.png', 700.00, '2025-03-12 16:01:45', '2025-09-08 08:30:23', '湖南省张家界市武陵源区索溪峪镇', '4星', '免费Wi-Fi, 停车场, 健身房', '0', '张家界国家森林公园入口', '24小时前台服务, 行李寄存', '0744-12345678', '探险酒店');
INSERT INTO `travel_hotel` VALUES (8, '布达拉宫酒店', 3.0, 0x3332332E30, '位于拉萨市中心，靠近布达拉宫', 'http://localhost:2025/upload/68198890-1f3c-4731-b0ce-21364c51391c.png', 1000.00, '2025-03-12 16:01:45', '2025-09-08 08:30:23', '西藏自治区拉萨市城关区布达拉宫广场', '5星', '免费Wi-Fi, 停车场, 餐厅', '0', '布达拉宫广场', '24小时前台服务, 行李寄存', '0891-12345678', '文化主题酒店');
INSERT INTO `travel_hotel` VALUES (9, '兵马俑酒店', 1.0, 0x323133322E30, '距离西安兵马俑景区仅几分钟车程', 'http://localhost:2025/upload/f57acc2d-b1ec-4472-b643-611a354d7ac8.png', 850.00, '2025-03-12 16:01:45', '2025-09-08 08:30:24', '陕西省西安市临潼区秦陵北路', '3星', '免费Wi-Fi, 停车场, 会议室', '0', '兵马俑景区入口', '24小时前台服务, 叫醒服务', '029-12345678', '历史文化酒店');
INSERT INTO `travel_hotel` VALUES (12, '青岛海景酒店', 4.0, 0x353535352E30, '临海而建，海景房视野开阔', 'http://localhost:2025/upload/0b4d20f5-ebc0-431a-a6d6-0253381803db.png', 1300.00, '2025-03-12 16:01:45', '2025-09-08 08:30:24', '山东省青岛市南区太平路1号', '5星', '免费Wi-Fi, 停车场, 游泳池', '0', '青岛栈桥', '24小时前台服务, 行李寄存', '0532-12345678', '海景酒店');
INSERT INTO `travel_hotel` VALUES (13, '成都熊猫酒店', 4.0, 0x35353534332E30, '靠近熊猫基地，适合亲子游', 'http://localhost:2025/upload/9a65095f-eed3-4258-be41-eb3157d4db52.png', 700.00, '2025-03-12 16:01:45', '2025-09-08 08:30:25', '四川省成都市成华区熊猫大道1375号', '4星', '免费Wi-Fi, 停车场, 餐厅', '0', '成都大熊猫繁育研究基地', '24小时前台服务, 叫醒服务', '028-12345678', '亲子酒店');
INSERT INTO `travel_hotel` VALUES (14, '厦门鼓浪屿酒店', 1.0, 0x33332E30, '位于鼓浪屿岛上，充满文艺气息', 'http://localhost:2025/upload/b100bd30-3ed5-434a-8598-4221709defdd.png', 900.00, '2025-03-12 16:01:45', '2025-09-08 08:30:28', '福建省厦门市思明区鼓浪屿', '3星', '免费Wi-Fi, 停车场, 会议室', '0', '鼓浪屿码头', '24小时前台服务, 行李寄存', '0592-12345678', '文艺酒店');
INSERT INTO `travel_hotel` VALUES (15, '哈尔滨冰雪酒店', 1.0, 0x323232312E30, '冬季热门，冰雪大世界旁', 'http://localhost:2025/upload/914114ea-7a9c-41fc-8ac7-f3b0487b428a.png', 1200.00, '2025-03-12 16:01:45', '2025-09-08 08:30:29', '黑龙江省哈尔滨市道里区中央大街', '5星', '免费Wi-Fi, 停车场, 健身房', '0', '哈尔滨冰雪大世界', '24小时前台服务, 行李寄存', '0451-12345678', '冰雪主题酒店');
INSERT INTO `travel_hotel` VALUES (16, '西双版纳度假酒店', 1.0, 0x31322E30, '热带雨林风情，适合避寒', 'http://localhost:2025/upload/97f85632-7dc0-4dcc-85e2-183e2082a39f.png', 800.00, '2025-03-12 16:01:45', '2025-09-08 08:30:29', '云南省西双版纳傣族自治州景洪市', '4星', '免费Wi-Fi, 停车场, 游泳池', '0', '西双版纳热带植物园', '24小时前台服务, 叫醒服务', '0691-12345678', '热带雨林酒店');
INSERT INTO `travel_hotel` VALUES (17, '上海外滩酒店', 2.0, 0x31322E30, '位于外滩，城市景观绝佳', 'http://localhost:2025/upload/0b42201c-092f-4f0d-ba14-df2dc9e5e1cb.png', 1800.00, '2025-03-12 16:01:45', '2025-09-08 08:30:30', '上海市黄浦区中山东二路600号', '5星', '免费Wi-Fi, 停车场, 餐厅', '0', '外滩', '24小时前台服务, 行李寄存', '021-12345678', '城市景观酒店');
INSERT INTO `travel_hotel` VALUES (18, '北京王府井酒店', 4.0, 0x35353532332E30, '地处繁华商圈，购物便利', 'http://localhost:2025/upload/64931100-3569-40d4-a1ef-badf8ed36927.png', 1400.00, '2025-03-12 16:01:45', '2025-09-08 08:30:30', '北京市东城区王府井大街111号', '5星', '免费Wi-Fi, 停车场, 健身房', '0', '地铁1号线王府井站', '24小时前台服务, 行李寄存', '010-12345678', '商务酒店');
INSERT INTO `travel_hotel` VALUES (19, '广州塔酒店', NULL, NULL, '靠近广州塔，城市地标旁', 'https://example.com/images/guangzhou_tower_hotel.jpg', 1350.00, '2025-03-12 16:01:45', '2025-09-08 08:30:31', '广东省广州市海珠区新港中路397号', '5星', '免费Wi-Fi, 停车场, 餐厅', '0', '广州塔', '24小时前台服务, 行李寄存', '020-12345678', '地标酒店');
INSERT INTO `travel_hotel` VALUES (20, '南京夫子庙酒店', NULL, NULL, '临近夫子庙，古色古香', 'https://example.com/images/nanjing_fuzimiao_hotel.jpg', 950.00, '2025-03-12 16:01:45', '2025-09-08 08:30:37', '江苏省南京市秦淮区夫子庙秦淮风光带', '4星', '免费Wi-Fi, 停车场, 会议室', '0', '夫子庙', '24小时前台服务, 叫醒服务', '025-12345678', '古色古香酒店');

-- ----------------------------
-- Table structure for travel_news
-- ----------------------------
DROP TABLE IF EXISTS `travel_news`;
CREATE TABLE `travel_news`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `created_at` datetime NOT NULL COMMENT '发生时间',
  `updated_at` datetime NOT NULL COMMENT '更新时间',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '内容',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '地点',
  `coverImage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图片',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_news
-- ----------------------------
INSERT INTO `travel_news` VALUES (1, '2025-09-01 08:00:00', '2025-09-08 11:29:20', '秋日的浪漫之约', '在秋天的阳光下，漫步于枫叶林间，感受大自然的宁静与美好。', '枫林小镇', 'http://localhost:2025/upload/5cffb2ca-c67e-4566-a4b5-56943c48a72b.png', NULL);
INSERT INTO `travel_news` VALUES (2, '2025-09-02 09:00:00', '2025-09-08 11:29:39', '海边的悠闲时光', '躺在沙滩上，享受阳光的沐浴，聆听海浪的声音，让身心彻底放松。', '阳光海滩', 'http://localhost:2025/upload/7d351c7a-9b2a-4781-8995-ac3d969ad431.png', NULL);
INSERT INTO `travel_news` VALUES (3, '2025-09-03 10:00:00', '2025-09-08 11:29:47', '山间徒步的乐趣', '沿着山间小道徒步前行，欣赏沿途的风景，呼吸清新的空气，感受大自然的魅力。', '翠绿山脉', 'http://localhost:2025/upload/7b379d2c-3a36-40dd-afc6-2be69ced676e.png', NULL);
INSERT INTO `travel_news` VALUES (4, '2025-09-04 11:00:00', '2025-09-08 11:30:02', '古城的韵味之旅', '漫步在古城的石板路上，欣赏古老的建筑，感受历史的沉淀。', '古韵小镇', 'http://localhost:2025/upload/322714ba-e17e-46cc-a81b-a32d4475d20f.png', NULL);
INSERT INTO `travel_news` VALUES (5, '2025-09-05 12:00:00', '2025-09-08 11:30:20', '乡村的宁静生活', '在乡村的田野间漫步，欣赏美丽的田园风光，体验宁静的乡村生活。', '田园乡村', 'http://localhost:2025/upload/ccb3b017-0ec4-4fc9-89b3-022ded28c5e7.png', NULL);
INSERT INTO `travel_news` VALUES (6, '2025-09-06 13:00:00', '2025-09-08 11:30:30', '冰雪世界的奇遇', '在冰雪覆盖的世界中，体验滑雪的乐趣，欣赏冰雕的美丽。', '冰雪小镇', 'http://localhost:2025/upload/18114609-2665-4b55-91bc-f80dde65810d.png', NULL);
INSERT INTO `travel_news` VALUES (7, '2025-09-07 14:00:00', '2025-09-08 11:30:58', '沙漠的探险之旅', '在沙漠中探险，感受大自然的壮丽与神秘，体验刺激的沙漠穿越。', '金色沙漠', 'http://localhost:2025/upload/bb054bda-d809-461b-8f32-7ef4e5c37326.png', NULL);
INSERT INTO `travel_news` VALUES (8, '2025-09-08 15:00:00', '2025-09-08 11:31:13', '森林的神秘之旅', '走进森林深处，探索未知的奥秘，感受大自然的宁静与神秘。', '神秘森林', 'http://localhost:2025/upload/467d4b53-ff27-4134-897b-5e849fb295e4.png', NULL);
INSERT INTO `travel_news` VALUES (9, '2025-09-09 16:00:00', '2025-09-08 11:31:23', '湖泊的宁静之美', '在湖边漫步，欣赏湖水的宁静与美丽，感受大自然的宁静与和谐。', '碧波湖', 'http://localhost:2025/upload/d62c3e4f-22c4-4c8d-ad94-6a5438606365.png', NULL);
INSERT INTO `travel_news` VALUES (10, '2025-09-10 17:00:00', '2025-09-08 11:31:29', '草原的自由之歌', '在草原上奔跑，感受自由与奔放，欣赏草原的广阔与美丽。', '广袤草原', 'http://localhost:2025/upload/4f68c279-ec32-43bf-a529-218296f00f36.png', NULL);
INSERT INTO `travel_news` VALUES (11, '2025-09-08 03:28:48', '2025-09-08 11:28:48', '2', '2', '2', 'http://localhost:2025/upload/b650453a-6b41-4fa2-bf09-dde8e917a5b5.png', NULL);

-- ----------------------------
-- Table structure for travel_notes
-- ----------------------------
DROP TABLE IF EXISTS `travel_notes`;
CREATE TABLE `travel_notes`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `color` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_notes
-- ----------------------------
INSERT INTO `travel_notes` VALUES (18, '代码', '.glass-modal-overlay {\n    position: fixed;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    background: rgba(0, 0, 0, 0.5);\n    backdrop-filter: blur(8px);\n    display: flex;\n    justify-content: center;\n    align-items: center;\n    z-index: 1000;\n}\n\n.glass-modal {\n    width: 80%;\n    max-width: 800px;\n    background: rgba(255, 255, 255, 0.2);\n    border-radius: 20px;\n    border: 1px solid rgba(255, 255, 255, 0.3);\n    box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.2);\n    backdrop-filter: blur(12px);\n    overflow: hidden;\n    color: #fff;\n}\n\n.modal-header {\n    padding: 20px;\n    border-bottom: 1px solid rgba(255, 255, 255, 0.1);\n}\n\n.modal-content {\n    padding: 20px;\n    height: 60vh;\n}\n\n.modal-footer {\n    padding: 15px 20px;\n    display: flex;\n    justify-content: flex-end;\n    gap: 10px;\n    border-top: 1px solid rgba(255, 255, 255, 0.1);\n}\n\n.editor-container {\n    display: flex;\n    height: 100%;\n    gap: 20px;\n}\n\n.glass-input {\n    width: 100%;\n    background: rgba(255, 255, 255, 0.1);\n    border: 1px solid rgba(255, 255, 255, 0.2);\n    border-radius: 12px;\n    padding: 12px 15px;\n    color: white;\n    font-size: 16px;\n    transition: all 0.3s ease;\n    outline: none;\n}\n\n.glass-input:focus {\n    border-color: rgba(255, 255, 255, 0.4);\n    box-shadow: 0 0 0 3px rgba(255, 255, 255, 0.1);\n}\n\n.title-input {\n    font-size: 20px;\n    font-weight: bold;\n}\n\n.markdown-editor {\n    flex: 1;\n    height: 100%;\n    resize: none;\n    font-family: \'Courier New\', monospace;\n}\n\n.glass-preview {\n    flex: 1;\n    height: 100%;\n    overflow-y: auto;\n    padding: 15px;\n    background: rgba(255, 255, 255, 0.05);\n    border-radius: 12px;\n}\n\n.glass-button {\n    padding: 8px 20px;\n    border-radius: 10px;\n    border: none;\n    background: rgba(255, 255, 255, 0.1);\n    color: white;\n    cursor: pointer;\n    transition: all 0.3s ease;\n    border: 1px solid rgba(255, 255, 255, 0.2);\n}\n\n.glass-button:hover {\n    background: rgba(255, 255, 255, 0.2);\n}\n\n.glass-button.primary {\n    background: rgba(98, 0, 234, 0.5);\n    border: 1px solid rgba(98, 0, 234, 0.7);\n}\n\n.glass-button.primary:hover {\n    background: rgba(98, 0, 234, 0.7);\n}', '#ffdfba', NULL, '2025-07-09 21:50:08', 'admin');
INSERT INTO `travel_notes` VALUES (22, '回家日记', '好像没啥事情可以做，但是比外出工作要强。但是呢有点不太好，就是不能见到我的乖乖咯。', '#ffffff', NULL, '2025-07-21 00:48:06', 'admin');
INSERT INTO `travel_notes` VALUES (26, '我的乖乖', '可可爱没有脑袋，特别特别关心我。', '#ffffff', NULL, '2025-07-18 19:57:05', 'admin');
INSERT INTO `travel_notes` VALUES (35, '12', '121111', '#ffffff', NULL, '2025-08-21 23:03:10', 'admin');

-- ----------------------------
-- Table structure for travel_posts
-- ----------------------------
DROP TABLE IF EXISTS `travel_posts`;
CREATE TABLE `travel_posts`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `date` datetime(6) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `images` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_posts
-- ----------------------------
INSERT INTO `travel_posts` VALUES (16, 'admin', '新西兰南岛自驾', '新西兰', '2023-11-18 00:00:00.000000', '沿着南岛的海岸线自驾，从皇后镇的冒险到米尔福德峡湾的自然奇观，每一处都美不胜收。', '2025-06-05 20:21:19', '2025-07-19 13:41:10', 'http://localhost:2025/upload/OIP-C.png', '旅行,探险,自驾', NULL);
INSERT INTO `travel_posts` VALUES (17, 'adm', '泰国清迈小城故事', '泰国清迈', '2023-03-08 00:00:00.000000', '清迈的慢生活节奏，古老的寺庙，周末夜市的美食，还有与大象的亲密接触，都是难忘的回忆。', '2025-06-05 20:21:19', '2025-07-20 16:00:26', 'http://localhost:2025/upload/wKgBZ1ogyL6AKTDhAAeUy6tC1xQ97.jpeg', '旅行,探险,自驾', NULL);
INSERT INTO `travel_posts` VALUES (27, 'hhj', '云南昆明', '昆明', '2025-07-05 00:00:00.000000', '去看憨斑鸠', '2025-07-05 21:56:22', '2025-07-20 17:01:53', 'http://localhost:2025/upload/v2-984ffaa2ee8055909890df3cb7213218_r.jpg', '旅行,探险,自驾', NULL);
INSERT INTO `travel_posts` VALUES (60, 'admin', '去哪里玩', '找乖乖', '2025-07-26 08:00:00.000000', '好玩开心', '2025-07-25 11:14:07', '2025-07-25 11:14:31', 'http://localhost:2025/upload/98aecec2-461b-4745-aafe-e1e308270ab4.png', '', NULL);
INSERT INTO `travel_posts` VALUES (67, '水晶', '222', '昆明', '2025-08-01 08:00:00.000000', '12', '2025-08-11 21:00:56', '2025-08-11 21:00:56', 'http://localhost:2025/upload/9facad95-e8cd-4c88-a46b-bb09f6b8d86c.png', '旅行,探险', NULL);
INSERT INTO `travel_posts` VALUES (68, '李四', '12', '12', '2025-08-05 08:00:00.000000', '12', '2025-08-11 21:41:13', '2025-08-11 21:41:13', 'http://localhost:2025/upload/03610dea-7297-450e-b3c4-fe0cce309dd3.png', '吃饭', NULL);
INSERT INTO `travel_posts` VALUES (74, 'admin', '12', '·1', '2025-08-08 08:00:00.000000', '火灾安全', '2025-08-21 23:04:08', '2025-09-03 18:51:09', 'http://localhost:2025/upload/40024d3a-c71c-41b4-9966-70a603d70658.png', '旅行,探险', NULL);

-- ----------------------------
-- Table structure for travel_recommend
-- ----------------------------
DROP TABLE IF EXISTS `travel_recommend`;
CREATE TABLE `travel_recommend`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `details` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_recommend
-- ----------------------------
INSERT INTO `travel_recommend` VALUES (1, 'http://localhost:2025/upload/e016b220-beb3-46cc-ac56-f49452e12044.png', '埃菲尔铁塔', '艾菲尔铁塔（Eiffel Tower）是法国巴黎最著名的地标之一，也是世界上最有名的铁结构建筑之一。', '建造时间：1887年开始建造，1889年完工。用途：最初作为1889年巴黎世博会的入口拱门和展示结构，后来成为通信和旅游用途。设计者：由法国工程师古斯塔夫·埃菲尔（Gustave Eiffel）设计，埃菲尔公司负责建造。结构特点：铁架结构，高约324米（含天线），曾是世界最高的人造建筑长达41年（1889年至1930年）。游客吸引力：每年吸引数百万游客，是全球访问量最高的付费纪念碑。意义：象征巴黎与法国的工业进步与文化魅力，代表浪漫与现代工程技术的结合。');
INSERT INTO `travel_recommend` VALUES (2, 'http://localhost:2025/upload/aa9f54ff-4bf6-4164-8f0a-db051d0ab449.png', '北京天坛', '天坛位于中国北京市东城区，是明清两代皇帝“祭天祈谷”的重要皇家祭祀建筑群，也是中国古代建筑艺术的杰出代表。', '北京天坛是中国北京著名的古代祭祀建筑群，始建于明朝永乐年间（公元1420年），是中国古代皇帝祭天祈谷的重要场所。天坛占地约273公顷，整体布局严格讲究对称和风水理念，代表了中国传统建筑艺术的高度成就。天坛的核心建筑包括祈年殿、圜丘坛和皇穹宇。祈年殿是用于祈求丰收的主殿，建筑宏伟，屋顶采用蓝色琉璃瓦，象征天空；圜丘坛是皇帝冬至祭天的地方，以其独特的回声效果闻名；皇穹宇则是存放祭天神牌的地方。天坛不仅是中国古代祭天文化的象征，也因其独特的建筑美学和悠久的历史，被列为世界文化遗产，是北京的重要旅游景点之一。');
INSERT INTO `travel_recommend` VALUES (3, 'http://localhost:2025/upload/720abcdc-6183-4c44-b116-b2f98cc36730.png', '北京故宫', '北京故宫，又称紫禁城，位于北京市中心，是中国明清两代的皇家宫殿。', '建造时间：始建于明朝永乐四年（公元1406年），建成于1420年。占地面积：约72万平方米，有9000多间房屋。建筑特色：宫殿布局严谨，南北中轴线对称，体现了中国古代皇权的尊严和礼仪制度。主要功能：皇帝处理政务、举行重大典礼和居住的场所。文化价值：现为故宫博物院，收藏丰富，涵盖绘画、文物、古籍、陶瓷等，是研究中国历史文化的重要基地。世界地位：1987年被列入世界文化遗产名录。');
INSERT INTO `travel_recommend` VALUES (4, 'http://localhost:2025/upload/884d4186-c81d-4796-9e5e-5909611860aa.png', '埃及金字塔', '埃及金字塔是古埃及文明的重要象征，也是世界七大奇迹之一。它们主要是法老的陵墓，建造于古王国时期（约公元前2686年至公元前2181年），用以安葬法老和保护他们的灵魂。', '埃及金字塔是古埃及文明的重要象征，也是世界七大奇迹之一。它们主要是法老的陵墓，建造于古王国时期（约公元前2686年至公元前2181年），用以安葬法老和保护他们的灵魂。主要特点结构：金字塔通常是方形底座，上面逐渐收敛成尖顶的巨大石块建筑。材料：主要由石灰石和花岗岩建成。规模：最大的为吉萨金字塔群中的胡夫金字塔，原高约146米，是古代世界最高的建筑之一。功能：不仅是陵墓，还包含复杂的地下通道和墓室，体现了埃及人的宗教信仰和对来世的重视。代表金字塔胡夫金字塔（吉萨大金字塔）：最大最著名。哈夫拉金字塔：胡夫的儿子建造，规模略小。孟卡拉金字塔：最小的吉萨三大金字塔。');

-- ----------------------------
-- Table structure for travel_scenic
-- ----------------------------
DROP TABLE IF EXISTS `travel_scenic`;
CREATE TABLE `travel_scenic`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标题',
  `evaluation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '评价',
  `sales` decimal(10, 1) NULL DEFAULT NULL COMMENT '销售 ',
  `subtitle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '副标题',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '价格',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态',
  `star` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '星级',
  `service` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '服务',
  `facility` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '基础设施',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类型',
  `traffic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '交通',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '位置',
  `feature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '特色',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_scenic
-- ----------------------------
INSERT INTO `travel_scenic` VALUES (1, '马尔代夫7日梦幻之旅', '3333.0', 5.0, '以其清澈的海水、丰富的海洋生物和豪华的度假村而闻名于世，被誉为“印度洋的珍珠”', 'http://localhost:2025/upload/2a83f6ea-def7-4078-a50b-5b5493d8b537.png', 555.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达, 地铁站附近', '北京市海淀区', '历史文化', '010-12345678');
INSERT INTO `travel_scenic` VALUES (2, '日本京都文化深度游', '22312.0', 4.0, '东京是日本的首都和最大的城市，也是全球最重要的经济、文化和政治中心之一', 'http://localhost:2025/upload/55aa9e17-5f94-4260-ab44-628ada98d5b0.png', 444.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '5星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '文化体验', '地铁站附近', '日本京都', '传统建筑', '075-12345678');
INSERT INTO `travel_scenic` VALUES (3, '西湖', '1222.0', 2.0, '杭州著名的风景名胜区', 'http://localhost:2025/upload/c78d0f7b-818d-4c14-9d99-54c7b30d1427.png', 60.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '3星', '导游服务, 停车服务', '停车场, 餐厅', '自然景观', '公交直达', '浙江省杭州市', '湖光山色', '0571-12345678');
INSERT INTO `travel_scenic` VALUES (5, '九寨沟', '323.0', 3.0, '四川省的自然保护区', 'http://localhost:2025/upload/d3417442-b3fd-472e-aaa2-0a8cd5f3b3f2.png', 150.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 行李寄存', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达', '四川省阿坝藏族羌族自治州', '自然风光', '0837-12345678');
INSERT INTO `travel_scenic` VALUES (6, '桂林山水', '23233.0', 3.0, '广西壮族自治区的喀斯特地貌', 'http://localhost:2025/upload/e62dcb52-6cfd-4fa5-8ad1-2ff7e6f99fca.png', 99.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '3星', '导游服务, 停车服务', '停车场, 餐厅', '自然景观', '公交直达', '广西壮族自治区桂林市', '喀斯特地貌', '0773-12345678');
INSERT INTO `travel_scenic` VALUES (7, '张家界', '23222.0', 3.0, '湖南省的国家森林公园', 'http://localhost:2025/upload/2725b347-0b3d-4ee2-89ee-939391eccd77.png', 110.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '自然景观', '公交直达', '湖南省张家界市', '山岳景观', '0744-12345678');
INSERT INTO `travel_scenic` VALUES (8, '布达拉宫', '123.0', 3.0, '西藏的著名宗教建筑', 'http://localhost:2025/upload/75f3c4bf-6870-47d1-bbaf-e33fdf5c697e.png', 130.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '5星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '文化景观', '公交直达', '西藏自治区拉萨市', '宗教建筑', '0891-12345678');
INSERT INTO `travel_scenic` VALUES (9, '兵马俑', '1232.0', 2.0, '西安的秦始皇陵兵马俑', 'http://localhost:2025/upload/338f553a-72c3-48a7-83c5-a32c883e6eeb.png', 70.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '3星', '导游服务, 行李寄存', '停车场, 餐厅', '历史遗迹', '公交直达', '陕西省西安市', '古代遗迹', '029-12345678');
INSERT INTO `travel_scenic` VALUES (13, '华山', '12312.0', 3.0, '陕西省的著名山岳风景区', 'http://localhost:2025/upload/de3439fe-5242-4bc8-8952-e844673181c8.png', 180.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达', '陕西省', '山岳景观', '0913-12345678');
INSERT INTO `travel_scenic` VALUES (14, '嵩山', '1231.0', 2.0, '河南省的著名山岳风景区', 'http://localhost:2025/upload/c9a20b34-78c1-4948-9686-70546c554498.png', 220.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '3星', '导游服务, 行李寄存', '停车场, 餐厅', '自然景观', '公交直达', '河南省', '山岳景观', '0371-12345678');
INSERT INTO `travel_scenic` VALUES (15, '衡山', '12311.0', 4.0, '湖南省的著名山岳风景区', 'http://localhost:2025/upload/8c2cf793-7c91-499a-ae04-c115324b6a05.png', 210.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达', '湖南省', '山岳景观', '0731-12345678');
INSERT INTO `travel_scenic` VALUES (16, '恒山', '345.0', 4.0, '山西省的著名山岳风景区', 'http://localhost:2025/upload/ca3b0c41-23dc-49ed-8195-b6c9623d6ce7.png', 190.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '自然景观', '公交直达', '山西省', '山岳景观', '0351-12345678');
INSERT INTO `travel_scenic` VALUES (17, '庐山', '345.0', 5.0, '江西省的著名山岳风景区', 'http://localhost:2025/upload/ad50c640-7d2d-4f36-8c35-372244ee9f29.png', 230.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '5星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达', '江西省', '山岳景观', '0791-12345678');
INSERT INTO `travel_scenic` VALUES (18, '峨眉山', '3453.0', 4.0, '四川省的著名山岳风景区', 'http://localhost:2025/upload/f25be0ea-b699-4f91-8b5e-b5160d62d339.png', 240.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '自然景观', '公交直达', '四川省', '山岳景观', '0816-12345678');
INSERT INTO `travel_scenic` VALUES (19, '武夷山', '666.0', 4.0, '福建省的著名山岳风景区', 'http://localhost:2025/upload/0e85e422-362e-4fb0-8b83-4885064ae217.png', 250.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达', '福建省', '山岳景观', '0591-12345678');
INSERT INTO `travel_scenic` VALUES (21, '普陀山', '2321.0', 4.0, '浙江省的著名佛教圣地', 'http://localhost:2025/upload/7edec7a8-f06f-4abd-967e-d16d5f552517.png', 270.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 行李寄存', '停车场, 餐厅', '佛教圣地', '公交直达', '浙江省', '佛教文化', '0574-12345678');
INSERT INTO `travel_scenic` VALUES (22, '九华山', NULL, NULL, '安徽省的著名佛教圣地', 'http://localhost:2025/image/scenic/jhs.jpg', 280.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '5星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '佛教圣地', '公交直达', '安徽省', '佛教文化', '0551-12345678');
INSERT INTO `travel_scenic` VALUES (23, '峨眉山', NULL, NULL, '四川省的著名佛教圣地', 'https://example.com/im', 290.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '佛教圣地', '公交直达', '四川省', '佛教文化', '0816-12345678');
INSERT INTO `travel_scenic` VALUES (24, '黄山', NULL, NULL, '安徽省的著名山岳风景区', 'https://example.com/im', 300.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达', '安徽省', '山岳景观', '0551-12345678');
INSERT INTO `travel_scenic` VALUES (25, '泰山', NULL, NULL, '山东省的著名山岳风景区', 'https://example.com/im', 310.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '5星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '自然景观', '公交直达', '山东省', '山岳景观', '0531-12345678');
INSERT INTO `travel_scenic` VALUES (26, '华山', NULL, NULL, '陕西省的著名山岳风景区', 'https://example.com/im', 320.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达', '陕西省', '山岳景观', '0913-12345678');
INSERT INTO `travel_scenic` VALUES (27, '嵩山', NULL, NULL, '河南省的著名山岳风景区', 'https://example.com/im', 330.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '3星', '导游服务, 行李寄存', '停车场, 餐厅', '自然景观', '公交直达', '河南省', '山岳景观', '0371-12345678');
INSERT INTO `travel_scenic` VALUES (28, '衡山', NULL, NULL, '湖南省的著名山岳风景区', 'https://example.com/im', 340.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达', '湖南省', '山岳景观', '0731-12345678');
INSERT INTO `travel_scenic` VALUES (29, '恒山', NULL, NULL, '山西省的著名山岳风景区', 'https://example.com/im', 350.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '自然景观', '公交直达', '山西省', '山岳景观', '0351-12345678');
INSERT INTO `travel_scenic` VALUES (30, '庐山', NULL, NULL, '江西省的著名山岳风景区', 'https://example.com/im', 360.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '5星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达', '江西省', '山岳景观', '0791-12345678');
INSERT INTO `travel_scenic` VALUES (31, '峨眉山', NULL, NULL, '四川省的著名山岳风景区', 'https://example.com/im', 370.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '自然景观', '公交直达', '四川省', '山岳景观', '0816-12345678');
INSERT INTO `travel_scenic` VALUES (32, '武夷山', NULL, NULL, '福建省的著名山岳风景区', 'https://example.com/im', 380.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达', '福建省', '山岳景观', '0591-12345678');
INSERT INTO `travel_scenic` VALUES (33, '五台山', NULL, NULL, '山西省的著名佛教圣地', 'https://example.com/im', 390.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '佛教圣地', '公交直达', '山西省', '佛教文化', '0351-12345678');
INSERT INTO `travel_scenic` VALUES (34, '普陀山', NULL, NULL, '浙江省的著名佛教圣地', 'https://example.com/im', 400.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '5星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '佛教圣地', '公交直达', '浙江省', '佛教文化', '0574-12345678');
INSERT INTO `travel_scenic` VALUES (35, '九华山', NULL, NULL, '安徽省的著名佛教圣地', 'https://example.com/im', 410.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '佛教圣地', '公交直达', '安徽省', '佛教文化', '0551-12345678');
INSERT INTO `travel_scenic` VALUES (36, '峨眉山', NULL, NULL, '四川省的著名佛教圣地', 'https://example.com/im', 420.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '佛教圣地', '公交直达', '四川省', '佛教文化', '0816-12345678');
INSERT INTO `travel_scenic` VALUES (37, '黄山', NULL, NULL, '安徽省的著名山岳风景区', 'https://example.com/im', 430.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '5星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '自然景观', '公交直达', '安徽省', '山岳景观', '0551-12345678');
INSERT INTO `travel_scenic` VALUES (38, '泰山', NULL, NULL, '山东省的著名山岳风景区', 'https://example.com/im', 440.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 停车服务', '停车场, 餐厅, 咖啡厅', '自然景观', '公交直达', '山东省', '山岳景观', '0531-12345678');
INSERT INTO `travel_scenic` VALUES (39, '华山', NULL, NULL, '陕西省的著名山岳风景区', 'https://example.com/im', 450.00, '2025-03-12 16:28:19', '2025-09-08 09:45:01', '营业中', '4星', '导游服务, 行李寄存', '停车场, 餐厅, 健身房', '自然景观', '公交直达', '陕西省', '山岳景观', '0913-12345678');

-- ----------------------------
-- Table structure for travel_strategygroup
-- ----------------------------
DROP TABLE IF EXISTS `travel_strategygroup`;
CREATE TABLE `travel_strategygroup`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `image` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `tag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `tagIcon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `members` int NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `groupName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `groupDescription` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `dates` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `highlights` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_strategygroup
-- ----------------------------
INSERT INTO `travel_strategygroup` VALUES (3, '意大利罗马历史探秘', 'http://localhost:2025/upload/557b6cde-a7cd-417c-b9db-69ae0d8bec18.png', '历史之旅', 'fas fa-history', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 150, '参观罗马斗兽场，漫步梵蒂冈，感受古罗马文明。', 6800.00, 'abroad', '罗马历史迷', '专注于罗马历史文化的深度探索。', '2024年1月15日 - 1月22日', '罗马斗兽场、梵蒂冈博物馆、许愿池');
INSERT INTO `travel_strategygroup` VALUES (4, '法国巴黎艺术之旅', 'http://localhost:2025/upload/557b6cde-a7cd-417c-b9db-69ae0d8bec18.png', '艺术之旅', 'fas fa-paint-brush', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 250, '欣赏卢浮宫艺术，漫步塞纳河畔，体验法式浪漫。', 7500.00, 'abroad', '巴黎艺术团', '分享巴黎的艺术与时尚。', '2024年2月20日 - 2月27日', '卢浮宫、埃菲尔铁塔、蒙马特高地');
INSERT INTO `travel_strategygroup` VALUES (5, '西班牙巴塞罗那建筑之旅', 'http://localhost:2025/upload/557b6cde-a7cd-417c-b9db-69ae0d8bec18.png', '建筑之旅', 'fas fa-building', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 120, '探索高迪建筑，感受现代主义的魅力。', 5800.00, 'abroad', '巴塞罗那建筑迷', '专注于巴塞罗那的建筑艺术。', '2024年3月10日 - 3月17日', '圣家族大教堂、米拉之家、巴特罗之家');
INSERT INTO `travel_strategygroup` VALUES (6, '印度新德里文化探索', 'http://localhost:2025/upload/557b6cde-a7cd-417c-b9db-69ae0d8bec18.png', '文化之旅', 'fas fa-compass', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 100, '参观泰姬陵，体验印度传统手工艺。', 4200.00, 'abroad', '印度文化探索者', '分享印度的历史与文化。', '2024年4月5日 - 4月12日', '泰姬陵、红堡、印度手工艺市场');
INSERT INTO `travel_strategygroup` VALUES (7, '希腊雅典古典之旅', 'http://localhost:2025/upload/557b6cde-a7cd-417c-b9db-69ae0d8bec18.png', '历史之旅', 'fas fa-landmark', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 180, '参观卫城，感受古希腊文明。', 6500.00, 'abroad', '雅典古典文化', '专注于古希腊文化的探索。', '2024年5月20日 - 5月27日', '卫城、帕特农神庙、雅典娜神庙');
INSERT INTO `travel_strategygroup` VALUES (8, '摩洛哥马拉喀什色彩之旅', 'http://localhost:2025/upload/557b6cde-a7cd-417c-b9db-69ae0d8bec18.png', '色彩之旅', 'fas fa-palette', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 130, '漫步马拉喀什老城，感受摩洛哥的色彩与热情。', 5000.00, 'abroad', '摩洛哥色彩团', '分享摩洛哥的独特色彩与文化。', '2024年6月15日 - 6月22日', '马拉喀什老城、巴哈伊花园、蓝色小镇');
INSERT INTO `travel_strategygroup` VALUES (9, '巴西里约热内卢狂欢之旅', 'http://localhost:2025/upload/557b6cde-a7cd-417c-b9db-69ae0d8bec18.png', '狂欢之旅', 'fas fa-party-horn', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 220, '体验里约狂欢节，感受桑巴舞的魅力。', 7200.00, 'abroad', '里约狂欢团', '分享巴西的热情与活力。', '2024年7月25日 - 8月1日', '里约热内卢狂欢节、基督像、科帕卡巴纳海滩');
INSERT INTO `travel_strategygroup` VALUES (10, '澳大利亚悉尼自然之旅', 'http://localhost:2025/upload/557b6cde-a7cd-417c-b9db-69ae0d8bec18.png', '自然之旅', 'fas fa-tree', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 160, '探索悉尼歌剧院，漫步邦迪海滩，感受自然之美。', 5500.00, 'abroad', '悉尼自然探索者', '分享悉尼的自然与文化。', '2024年9月10日 - 9月17日', '悉尼歌剧院、邦迪海滩、悉尼皇家植物园');
INSERT INTO `travel_strategygroup` VALUES (11, '北京古都文化之旅', 'http://localhost:2025/upload/00445ae1-22ba-437c-a36c-aff87fd605f0.png', '历史之旅', 'fas fa-history', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 140, '参观故宫、长城，感受古都的深厚文化底蕴。', 3200.00, 'domestic', '北京文化探索者', '专注于北京历史文化的深度探索。', '2024年10月1日 - 10月7日', '故宫、八达岭长城、颐和园');
INSERT INTO `travel_strategygroup` VALUES (12, '西安千年古都之旅', 'http://localhost:2025/upload/9cb31e5c-1010-4d1e-a3ef-0f34b911e5a4.png', '文化之旅', 'fas fa-compass', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 130, '漫步古城墙，参观兵马俑，体验千年古都的魅力。', 2800.00, 'domestic', '西安历史迷', '分享西安的历史与文化。', '2024年11月15日 - 11月22日', '兵马俑、古城墙、大雁塔');
INSERT INTO `travel_strategygroup` VALUES (13, '成都熊猫文化之旅', 'http://localhost:2025/upload/3d0ab055-348a-4168-bffb-8d9e34fe2931.png', '生态之旅', 'fas fa-leaf', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 190, '参观熊猫基地，体验川菜美食，感受悠闲的成都生活。', 2500.00, 'domestic', '成都熊猫迷', '分享成都的熊猫文化和美食。', '2024年12月20日 - 12月27日', '熊猫基地、锦里古街、武侯祠');
INSERT INTO `travel_strategygroup` VALUES (14, '杭州西湖文化之旅', 'http://localhost:2025/upload/e2325340-dc2e-42df-a818-be7577de5fe3.png', '自然之旅', 'fas fa-tree', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 170, '漫步西湖，参观灵隐寺，感受江南水乡的韵味。', 2900.00, 'domestic', '杭州西湖文化团', '分享杭州的自然与文化。', '2025年1月5日 - 1月12日', '西湖、灵隐寺、宋城');
INSERT INTO `travel_strategygroup` VALUES (15, '南京历史文化之旅', 'http://localhost:2025/upload/7c32641a-872f-4f19-993f-0c430799308d.png', '历史之旅', 'fas fa-landmark', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 160, '参观中山陵、明孝陵，感受六朝古都的历史。', 3100.00, 'domestic', '南京历史文化团', '专注于南京历史文化的深度探索。', '2025年2月10日 - 2月17日', '中山陵、明孝陵、夫子庙');
INSERT INTO `travel_strategygroup` VALUES (16, '昆明花都文化之旅', 'http://localhost:2025/upload/7220956b-8bae-4e09-83cb-d587dd06d6b0.png', '自然之旅', 'fas fa-flower', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 150, '参观世界自然遗产石林，漫步滇池，感受花都的魅力。', 2700.00, 'domestic', '昆明花都团', '分享昆明的自然与文化。', '2025年3月20日 - 3月27日', '石林、滇池、云南民族村');
INSERT INTO `travel_strategygroup` VALUES (17, '桂林山水文化之旅', 'http://localhost:2025/upload/1a7e7235-63d3-4604-9384-4e1f342f9884.png', '自然之旅', 'fas fa-mountain', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 180, '乘船游览漓江，欣赏阳朔山水，感受桂林的自然美景。', 3000.00, 'domestic', '桂林山水团', '分享桂林的山水与文化。', '2025年4月15日 - 4月22日', '漓江、阳朔、龙脊梯田');
INSERT INTO `travel_strategygroup` VALUES (18, '哈尔滨冰雪文化之旅', 'http://localhost:2025/upload/b1045061-e2a5-4cc7-90f0-964c6436d4fb.png', '冰雪之旅', 'fas fa-snowflake', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 200, '参观冰雪大世界，体验冰雪运动，感受北方的冰雪文化。', 3500.00, 'domestic', '哈尔滨冰雪迷', '分享哈尔滨的冰雪文化。', '2025年1月1日 - 1月8日', '冰雪大世界、中央大街、圣索菲亚教堂');
INSERT INTO `travel_strategygroup` VALUES (19, '厦门海滨文化之旅', 'http://localhost:2025/upload/a1744753-067a-44aa-90f8-79e63e836600.png', '海滨之旅', 'fas fa-beach-umbrella', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 170, '漫步鼓浪屿，感受海滨城市的浪漫风情。', 2600.00, 'domestic', '厦门海滨团', '分享厦门的海滨与文化。', '2025年5月20日 - 5月27日', '鼓浪屿、南普陀寺、环岛路');
INSERT INTO `travel_strategygroup` VALUES (20, '拉萨朝圣之旅', 'http://localhost:2025/upload/5054e86f-c7f8-4e23-bf06-1133e412cb98.png', '宗教之旅', 'fas fa-praying-hands', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 120, '参观布达拉宫，感受藏传佛教的神圣。', 4000.00, 'domestic', '拉萨朝圣团', '分享拉萨的宗教与文化。', '2025年6月10日 - 6月17日', '布达拉宫、大昭寺、纳木错');
INSERT INTO `travel_strategygroup` VALUES (21, '拉萨朝圣之旅', 'http://localhost:2025/upload/0e252fcc-635c-4a11-81a6-f7c78ba8f588.png', '宗教之旅', 'fas fa-praying-hands', 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 120, '参观布达拉宫，感受藏传佛教的神圣。', 4000.00, 'domestic', '拉萨朝圣团', '分享拉萨的宗教与文化。', '2025年6月10日 - 6月17日', '布达拉宫、大昭寺、纳木错');
INSERT INTO `travel_strategygroup` VALUES (24, '野外呼吸', 'http://localhost:2025/upload/92f64a7e-6e00-4121-a610-31b0fd832848.png', '户外之旅', NULL, 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', 1233311, '享受自然的气息，感受微风拂过面庞。', 5677.00, 'domestic', '群山', '去白云下群山脚。', '2025年6月10日 - 6月17日', '呼吸自然的气息');

-- ----------------------------
-- Table structure for travel_worldcharacteristics
-- ----------------------------
DROP TABLE IF EXISTS `travel_worldcharacteristics`;
CREATE TABLE `travel_worldcharacteristics`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `country` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `image` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `features` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `history` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `culture` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `tags` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `category` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_worldcharacteristics
-- ----------------------------
INSERT INTO `travel_worldcharacteristics` VALUES (1, '马丘比丘', '秘鲁', 'http://localhost:2025/upload/70ffc458-24d0-4251-89e5-6c4aa4bb48a1.png', '印加帝国的失落之城，坐落在安第斯山脉之巅', '古代印加建筑杰作，梯田系统，天文观测点', '建于15世纪，西班牙殖民时期被遗弃，1911年被重新发现', '印加文明的精神象征，太阳崇拜的重要遗址', '世界遗产,古代文明,山脉', '城市风情', '#22bf56');
INSERT INTO `travel_worldcharacteristics` VALUES (2, '金字塔', '埃及', 'http://localhost:2025/upload/878269d4-209e-49ab-b14f-2bea6d4d34fc.png', '古埃及法老的陵墓，世界七大奇迹之一', '巨大的石块结构，精准的天文对齐', '建于公元前26世纪，历经千年不倒', '古埃及文明的象征，法老崇拜的体现', '世界遗产,古代文明,沙漠', '城市风情', '#FFD700');
INSERT INTO `travel_worldcharacteristics` VALUES (3, '长城', '中国', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '中国古代的军事防御工程，蜿蜒万里', '砖石结构，烽火台系统', '始建于公元前7世纪，历经多个朝代修建', '中国古代文明的象征，军事防御的典范', '世界遗产,古代文明,山脉', '历史遗迹', '#FF6347');
INSERT INTO `travel_worldcharacteristics` VALUES (4, '泰姬陵', '印度', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '莫卧儿帝国的陵墓，爱情的象征', '白色大理石建筑，精美的雕刻', '建于17世纪，为纪念莫卧儿皇帝的妻子而建', '印度伊斯兰文化的代表', '世界遗产,古代文明,陵墓', '历史遗迹', '#1080E7');
INSERT INTO `travel_worldcharacteristics` VALUES (5, '圣索菲亚大教堂', '土耳其', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '拜占庭建筑的杰作，历史悠久的宗教建筑', '巨大的圆顶，精美的马赛克', '建于6世纪，历经多次改建', '东正教和伊斯兰教文化的融合', '世界遗产,宗教建筑,历史遗迹', '历史遗迹', '#22BCC0');
INSERT INTO `travel_worldcharacteristics` VALUES (6, '吴哥窟', '柬埔寨', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '高棉帝国的宗教建筑，神秘的微笑佛像', '石雕艺术，复杂的建筑群', '建于12世纪，曾被丛林掩埋', '高棉文化的象征，印度教和佛教的融合', '世界遗产,古代文明,宗教建筑', '历史遗迹', '#FFD700');
INSERT INTO `travel_worldcharacteristics` VALUES (7, '佩特拉古城', '约旦', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '纳巴泰人的神秘古城，玫瑰色的建筑', '岩石雕刻建筑，复杂的水渠系统', '建于公元前4世纪，曾被遗忘', '阿拉伯文化的象征，贸易中心', '世界遗产,古代文明,沙漠', '历史遗迹', '#FF6347');
INSERT INTO `travel_worldcharacteristics` VALUES (8, '巨石阵', '英国', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '史前时期的神秘巨石建筑', '巨大的石柱排列，天文观测功能', '建于公元前3000年左右，用途不明', '史前文化的象征，神秘的宗教仪式场所', '世界遗产,古代文明,神秘建筑', '历史遗迹', '#22BCC0');
INSERT INTO `travel_worldcharacteristics` VALUES (9, '雅典卫城', '希腊', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '古希腊文明的象征，帕特农神庙所在地', '大理石建筑，精美的雕塑', '建于公元前5世纪，古希腊的宗教和政治中心', '古希腊文化的象征，民主的发源地', '世界遗产,古代文明,宗教建筑', '历史遗迹', '#FFD700');
INSERT INTO `travel_worldcharacteristics` VALUES (10, '庞贝古城', '意大利', 'http://localhost:2025/upload/ad9d2542-9a74-49af-8ad5-50f196046072.png', '古罗马城市，被火山灰掩埋', '保存完好的古罗马建筑和街道', '建于公元前6世纪，公元79年被维苏威火山爆发掩埋', '古罗马文化的见证，灾难的遗址', '世界遗产,古代文明,历史遗迹', '历史遗迹', '#138b63');
INSERT INTO `travel_worldcharacteristics` VALUES (11, '阿尔罕布拉宫', '西班牙', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '摩尔人的宫殿，伊斯兰艺术的杰作', '精美的瓷砖装饰，水景园林', '建于13世纪，摩尔人在西班牙的统治中心', '西班牙伊斯兰文化的代表', '世界遗产,宗教建筑,宫殿', '历史遗迹', '#FF6347');
INSERT INTO `travel_worldcharacteristics` VALUES (12, '京都御所', '日本', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '日本皇室的宫殿，传统和风建筑', '木结构建筑，精美的庭院', '建于14世纪，日本皇室的居所', '日本传统文化的象征', '世界遗产,宗教建筑,宫殿', '历史遗迹', '#FFD700');
INSERT INTO `travel_worldcharacteristics` VALUES (13, '布达拉宫', '中国', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '藏传佛教的圣地，达赖喇嘛的冬宫', '红白相间的建筑，佛殿和灵塔', '建于17世纪，藏传佛教的重要中心', '藏传佛教文化的象征', '世界遗产,宗教建筑,宫殿', '城市风情', '#FF6347');
INSERT INTO `travel_worldcharacteristics` VALUES (14, '科隆大教堂', '德国', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '哥特式建筑的杰作，高耸的尖塔', '彩色玻璃窗，精美的石雕', '建于13世纪，历经600年完工', '基督教文化的象征，哥特式建筑的典范', '世界遗产,宗教建筑,历史遗迹', '城市风情', '#22BCC0');
INSERT INTO `travel_worldcharacteristics` VALUES (15, '马丘比丘', '秘鲁', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '印加帝国的失落之城，坐落在安第斯山脉之巅', '古代印加建筑杰作，梯田系统，天文观测点', '建于15世纪，西班牙殖民时期被遗弃，1911年被重新发现', '印加文明的精神象征，太阳崇拜的重要遗址', '世界遗产,古代文明,山脉', '历史遗迹', '#b6deec');
INSERT INTO `travel_worldcharacteristics` VALUES (16, '金字塔', '埃及', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '古埃及法老的陵墓，世界七大奇迹之一', '巨大的石块结构，精准的天文对齐', '建于公元前26世纪，历经千年不倒', '古埃及文明的象征，法老崇拜的体现', '世界遗产,古代文明,沙漠', '历史遗迹', '#FFD700');
INSERT INTO `travel_worldcharacteristics` VALUES (17, '长城', '中国', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '中国古代的军事防御工程，蜿蜒万里', '砖石结构，烽火台系统', '始建于公元前7世纪，历经多个朝代修建', '中国古代文明的象征，军事防御的典范', '世界遗产,古代文明,山脉', '历史遗迹', '#FF6347');
INSERT INTO `travel_worldcharacteristics` VALUES (18, '泰姬陵', '印度', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '莫卧儿帝国的陵墓，爱情的象征', '白色大理石建筑，精美的雕刻', '建于17世纪，为纪念莫卧儿皇帝的妻子而建', '印度伊斯兰文化的代表', '世界遗产,古代文明,陵墓', '城市风情', '#FFFFFF');
INSERT INTO `travel_worldcharacteristics` VALUES (19, '圣索菲亚大教堂', '土耳其', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '拜占庭建筑的杰作，历史悠久的宗教建筑', '巨大的圆顶，精美的马赛克', '建于6世纪，历经多次改建', '东正教和伊斯兰教文化的融合', '世界遗产,宗教建筑,历史遗迹', '历史遗迹', '#8B4513');
INSERT INTO `travel_worldcharacteristics` VALUES (20, '吴哥窟', '柬埔寨', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '高棉帝国的宗教建筑，神秘的微笑佛像', '石雕艺术，复杂的建筑群', '建于12世纪，曾被丛林掩埋', '高棉文化的象征，印度教和佛教的融合', '世界遗产,古代文明,宗教建筑', '历史遗迹', '#FFD700');
INSERT INTO `travel_worldcharacteristics` VALUES (21, '佩特拉古城', '约旦', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '纳巴泰人的神秘古城，玫瑰色的建筑', '岩石雕刻建筑，复杂的水渠系统', '建于公元前4世纪，曾被遗忘', '阿拉伯文化的象征，贸易中心', '世界遗产,古代文明,沙漠', '城市风情', '#FF6347');
INSERT INTO `travel_worldcharacteristics` VALUES (22, '巨石阵', '英国', 'https://images.unsplash.com/photo-1526397751294-331021109fbd', '史前时期的神秘巨石建筑', '巨大的石柱排列，天文观测功能', '建于公元前3000年左右，用途不明', '史前文化的象征，神秘的宗教仪式场所', '世界遗产,古代文明,神秘建筑', '历史遗迹', '#a68ce3');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `account`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'http://localhost:2025/upload/e562e668-93a0-4070-b076-9f19fc1e6b32.jpeg', 'admin', '2560177364@qq.com', '123456', '18213200129', '要吃一只小恐龙', NULL, '小说是一种以塑造人物、叙述故事为主要特征的文学体裁。它通过虚构的情节、环境和人物活动来反映社会生活、表达作者的思想感情。小说种类繁多，从主题、风格到篇幅都有着不同的分类，包括历史小说、科幻小说、言情小说等等。不同的小说能给读者带来不同的阅读体验，或引人入胜，或发人深省，或愉悦心情。小说的创作手法多种多样，叙事角度、人物塑造和语言运用都是重要的组成部分。', '我是超级管理员删除你。', '2025-08-23 09:08:09', '2024-12-11 15:37:18', NULL, '1', '357926148523698750', '0');
INSERT INTO `users` VALUES (43, 'http://localhost:2025/upload/5a3e640a-1ae2-4e6f-a3ad-86407a41a954.png', '小懒猪', 'user31@example.com', '123456', '15125994533', '小懒猪爱吃恐龙', NULL, NULL, NULL, '2025-09-03 10:49:36', '2025-03-16 23:04:15', NULL, '1', '987654321098765400', '0');
INSERT INTO `users` VALUES (51, 'http://localhost:2025/upload/4377a97a-5f21-4008-8b12-66798b328006.png', '陶富林', '32082897688@qq.com', '123', '18488206305', '617扛把子', NULL, '家里蹲', '春城第一深情', '2025-09-02 07:28:19', '2025-04-27 14:55:11', NULL, '0', '147852369147852380', '0');
INSERT INTO `users` VALUES (58, 'http://localhost:2025/upload/12bd6fb1-c73e-4e95-9257-2e29246f0b50.png', '华先生', '1514628778@qq.com', '123', '17314599157', '宝贝', NULL, NULL, NULL, '2025-09-02 12:38:34', '2025-04-30 17:06:28', NULL, '0', '258147852369147840', '0');
INSERT INTO `users` VALUES (87, 'http://localhost:2025/upload/6e19e5d3-4c03-4fd9-aad7-c1a0a6838892.png', '小可爱', '2560177354@qq.com', '123456', '16213200129', '', NULL, NULL, NULL, '2025-08-20 22:27:43', '2025-07-21 00:56:08', NULL, '0', '963852741963852741', '1');
INSERT INTO `users` VALUES (88, 'http://localhost:2025/upload/6ca7bfb5-87c0-4aff-894e-a138661e5654.png', 'wdsj', '2904795190@qq.com', '123456', '18213200125', '', NULL, NULL, NULL, '2025-08-21 06:52:06', '2025-07-22 16:56:03', NULL, '0', '741852963741852900', '1');
INSERT INTO `users` VALUES (89, 'http://localhost:2025/upload/3327aefe-72d9-4412-bf19-171506801cc4.png', '张小明', 'admin111@qq.com', '123456', '13218200128', '', NULL, NULL, NULL, '2025-08-20 22:27:47', '2025-08-09 19:48:15', NULL, '0', '369258147369258147', '1');
INSERT INTO `users` VALUES (90, 'http://localhost:2025/upload/cdb45bf4-e404-48d3-9129-ae5865b2d87c.png', '水晶', 'admin@11.com', '123456', '14213200897', '法外狂徒', NULL, NULL, NULL, '2025-09-04 02:35:25', '2025-08-11 12:58:32', NULL, '0', '852741963852742000', '1');
INSERT INTO `users` VALUES (91, 'http://localhost:2025/upload/cb34ab0c-7762-4a31-8cde-5e55334db94a.png', '李四', 'admin@44.com', '123456', '19231200128', '我累了', NULL, '123', '12', '2025-09-04 02:35:47', '2025-08-11 21:38:28', NULL, '0', '232123440735967780', '1');
INSERT INTO `users` VALUES (98, 'http://localhost:2025/upload/45316baf-d3ea-44ad-b823-9658c86c4550.png', '小杨同学', '3147928074@qq.com', '123456', '18312500182', '22', NULL, NULL, NULL, '2025-09-04 02:35:07', '2025-08-20 11:51:34', NULL, '0', '145170000735967780', '0');
INSERT INTO `users` VALUES (100, 'http://localhost:2025/upload/b1af0d49-8d9d-4ad3-91e1-7e8368573ed5.png', '探索', 'admin3333@qq.com', '123456', '17312900129', '123', NULL, NULL, NULL, NULL, '2025-08-21 15:10:21', NULL, '0', '524870000865698820', '1');
INSERT INTO `users` VALUES (101, 'http://localhost:2025/upload/7e954e12-f895-45b6-89d3-edbbc76b9ae0.png', '桂林小张', '277311@qq.com', '123456', '17213299876', '老表', NULL, NULL, NULL, NULL, '2025-08-21 15:12:10', NULL, '0', '678840000041139240', '1');
INSERT INTO `users` VALUES (105, 'http://localhost:2025/upload/014996b0-c80e-47a2-8954-d5f3157d39c1.png', '尼罗河法老', '11387@qq.com', '123456', '18214099127', '', NULL, NULL, NULL, NULL, '2025-08-22 15:04:26', NULL, '0', '659140000871165935', '0');
INSERT INTO `users` VALUES (106, 'http://localhost:2025/upload/3236667f-26fd-4a1f-b7f9-b576e748d1a0.png', '百变小王', '12344@qq.com', '123456', '14812300129', '', NULL, NULL, NULL, NULL, '2025-08-22 07:11:59', NULL, '0', '545570000613164908', '0');
INSERT INTO `users` VALUES (107, 'http://localhost:2025/upload/dfb1821e-626e-446e-9823-50e74957390b.png', '太极张三丰', '123@qq.com', '123456', '13498711092', '', NULL, NULL, NULL, NULL, '2025-08-22 15:21:15', NULL, '0', '746010000031842182', '0');
INSERT INTO `users` VALUES (108, 'http://localhost:2025/upload/ffe669b8-08db-494b-98d0-90804a382d49.png', '123', '1234@qq.com', '123456', '13212911213', '', NULL, NULL, NULL, NULL, '2025-09-02 17:22:10', NULL, '0', '303290000743617086', '0');

SET FOREIGN_KEY_CHECKS = 1;
