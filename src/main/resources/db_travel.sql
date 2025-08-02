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

 Date: 14/05/2025 09:20:45
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of destination
-- ----------------------------
INSERT INTO `destination` VALUES (35, '法国', 55.00, '欧洲', 'http://localhost:2025/image/destinatin/fg.jpg', '2025-04-10 21:12:17', '巴黎');
INSERT INTO `destination` VALUES (36, '日本', 123.00, '亚洲', 'http://localhost:2025/image/destinatin/rb.jpg', '2025-04-30 21:12:22', '东京');
INSERT INTO `destination` VALUES (37, '美国', 330.00, '北美洲', 'http://localhost:2025/image/destinatin/mgnyzygy.jpg', '2025-04-23 21:12:26', '纽约');
INSERT INTO `destination` VALUES (38, '澳大利亚', 120.00, '大洋洲', 'http://localhost:2025/image/destinatin/xn.jpg', NULL, '悉尼');
INSERT INTO `destination` VALUES (39, '英国', 540.00, '欧洲', 'http://localhost:2025/image/destinatin/yg.jpg', NULL, '伦敦');
INSERT INTO `destination` VALUES (40, '阿拉伯联合酋长国', 440.00, '亚洲', 'http://localhost:2025/image/destinatin/db.jpg', NULL, '迪拜');
INSERT INTO `destination` VALUES (41, '中国', 650.00, '亚洲', 'http://localhost:2025/image/destinatin/bj.jpg', NULL, '北京');
INSERT INTO `destination` VALUES (42, '巴西', 440.00, '南美洲', 'http://localhost:2025/image/destinatin/lyrnl.jpg', NULL, '里约热内卢');
INSERT INTO `destination` VALUES (43, '南非', 12.00, '非洲', 'http://localhost:2025/image/destinatin/nf.jpg', NULL, '开普敦');
INSERT INTO `destination` VALUES (45, '应该', 0.00, '欧洲', 'http://localhost:2025/image/destinatin/fgbl.jpg', NULL, '巴黎');
INSERT INTO `destination` VALUES (46, '美国', 0.00, '北美洲', 'statue_of_liberty.jpg', NULL, '纽约自由女神像');
INSERT INTO `destination` VALUES (47, '日本', 0.00, '亚洲', 'mount_fuji.jpg', NULL, '东京富士山');
INSERT INTO `destination` VALUES (48, '大峡谷', 0.00, '北美洲', 'grand_canyon.jpg', NULL, '拉斯维加斯');
INSERT INTO `destination` VALUES (49, '长城', 0.00, '亚洲', 'great_wall.jpg', NULL, '北京');
INSERT INTO `destination` VALUES (50, '金字塔', 0.00, '非洲', 'pyramids.jpg', NULL, '开罗');
INSERT INTO `destination` VALUES (51, '悉尼港', 0.00, '大洋洲', 'sydney_harbor.jpg', NULL, '悉尼');
INSERT INTO `destination` VALUES (52, '亚马逊雨林', 0.00, '南美洲', 'amazon_rainforest.jpg', NULL, '马瑙斯');
INSERT INTO `destination` VALUES (53, '尼亚加拉大瀑布', 0.00, '北美洲', 'niagara_falls.jpg', NULL, '多伦多');
INSERT INTO `destination` VALUES (54, '威尼斯', 0.00, '欧洲', 'venice.jpg', NULL, '威尼斯');
INSERT INTO `destination` VALUES (55, '布拉格', 0.00, '欧洲', 'prague.jpg', NULL, '布拉格');
INSERT INTO `destination` VALUES (56, '巴塞罗那', 0.00, '欧洲', 'barcelona.jpg', NULL, '巴塞罗那');
INSERT INTO `destination` VALUES (57, '阿姆斯特丹', 0.00, '欧洲', 'amsterdam.jpg', NULL, '阿姆斯特丹');
INSERT INTO `destination` VALUES (58, '京都', 0.00, '亚洲', 'kyoto.jpg', NULL, '京都');
INSERT INTO `destination` VALUES (59, '悉尼', 0.00, '大洋洲', 'sydney.jpg', NULL, '悉尼');
INSERT INTO `destination` VALUES (60, '巴黎', 0.00, '欧洲', 'paris.jpg', NULL, '巴黎');
INSERT INTO `destination` VALUES (61, '东京', 0.00, '亚洲', 'tokyo.jpg', NULL, '东京');
INSERT INTO `destination` VALUES (62, '纽约', 0.00, '北美洲', 'newyork.jpg', NULL, '纽约');
INSERT INTO `destination` VALUES (63, '悉尼', 0.00, '大洋洲', 'sydney.jpg', NULL, '悉尼');
INSERT INTO `destination` VALUES (64, '伦敦', 0.00, '欧洲', 'london.jpg', NULL, '伦敦');
INSERT INTO `destination` VALUES (65, '迪拜', 0.00, '亚洲', 'dubai.jpg', NULL, '迪拜');
INSERT INTO `destination` VALUES (66, '北京', 0.00, '亚洲', 'beijing.jpg', NULL, '北京');
INSERT INTO `destination` VALUES (67, '里约热内卢', 0.00, '南美洲', 'rio.jpg', NULL, '里约热内卢');
INSERT INTO `destination` VALUES (68, '开普敦', 0.00, '非洲', 'cape_town.jpg', NULL, '开普敦');
INSERT INTO `destination` VALUES (69, '圣托里尼', 0.00, '欧洲', 'santorini.jpg', NULL, '圣托里尼');
INSERT INTO `destination` VALUES (70, '摩洛哥', 0.00, '非洲', 'morocco.jpg', NULL, '马拉喀什');
INSERT INTO `destination` VALUES (71, '布达佩斯', 0.00, '欧洲', 'budapest.jpg', NULL, '布达佩斯');

-- ----------------------------
-- Table structure for foods
-- ----------------------------
DROP TABLE IF EXISTS `foods`;
CREATE TABLE `foods`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '美食名称',
  `province_id` int NULL DEFAULT NULL COMMENT '所属省份ID',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `sales` int NULL DEFAULT 0 COMMENT '销量',
  `rating` decimal(3, 1) NULL DEFAULT 0.0 COMMENT '评分',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '美食描述',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '美食图片URL',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `province_id`(`province_id` ASC) USING BTREE,
  CONSTRAINT `foods_ibfk_1` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of foods
-- ----------------------------
INSERT INTO `foods` VALUES (1, '北京烤鸭', 1, 250.00, 3200, 4.9, '北京特色烤鸭，皮脆肉嫩，风味独特', 'http://localhost:2025/image/foods/bjky.jpg', '2025-03-17 10:28:45', '2025-04-27 16:05:59');
INSERT INTO `foods` VALUES (2, '上海小笼包', 2, 28.00, 5600, 4.8, '上海特色小吃，小笼包皮薄馅嫩，汤汁丰富', 'http://localhost:2025/image/foods/shxlb.jpg', '2025-03-17 10:28:45', '2025-04-08 14:57:25');
INSERT INTO `foods` VALUES (4, '重庆火锅', 4, 98.00, 2543, 4.8, '重庆特色火锅，麻辣鲜香，回味无穷', 'http://localhost:2025/image/foods/cqhg.jpg', '2025-03-17 10:28:45', '2025-03-25 13:34:16');
INSERT INTO `foods` VALUES (5, '河北板面', 5, 2220.00, 4300, 4.7, '河北特色面食，面条劲道，汤汁浓郁', 'http://localhost:2025/image/foods/hbbm.jpg', '2025-03-17 10:28:45', '2025-04-27 16:06:42');
INSERT INTO `foods` VALUES (6, '山西刀削面', 6, 28.00, 5600, 4.8, '山西特色面食，面条劲道，配料丰富', 'http://localhost:2025/image/foods/sxtxm.jpg', '2025-03-17 10:28:45', '2025-03-25 16:14:42');
INSERT INTO `foods` VALUES (7, '辽宁锅包肉', 7, 48.00, 5600, 4.8, '辽宁传统名菜，锅包肉外酥里嫩，酸甜可口', 'http://localhost:2025/image/foods/gbr.jpg', '2025-03-17 10:28:45', '2025-03-25 16:14:49');
INSERT INTO `foods` VALUES (8, '吉林朝鲜冷面', 8, 22.00, 3400, 4.7, '吉林特色小吃，冷面爽滑，汤汁酸甜', 'http://localhost:2025/image/foods/cxlm.jpg', '2025-03-17 10:28:45', '2025-03-25 16:15:18');
INSERT INTO `foods` VALUES (9, '黑龙江红肠', 9, 38.00, 4500, 4.6, '黑龙江特色美食，红肠口感醇厚，风味独特', 'http://localhost:2025/image/foods/hlj.jpg', '2025-03-17 10:28:45', '2025-03-25 16:15:26');
INSERT INTO `foods` VALUES (11, '浙江东坡肉', 11, 88.00, 1123, 4.7, '浙江名菜，肥而不腻，入口即化', 'http://localhost:2025/image/foods/dpr.jpg', '2025-03-17 10:28:45', '2025-03-25 16:16:23');
INSERT INTO `foods` VALUES (12, '安徽臭豆腐', 12, 18.00, 7800, 4.7, '安徽特色小吃，外焦里嫩，香气扑鼻', 'http://localhost:2025/image/foods/cdf.jpg', '2025-03-17 10:28:45', '2025-03-25 16:16:11');
INSERT INTO `foods` VALUES (13, '福建佛跳墙', 13, 298.00, 1200, 4.9, '福建名菜，食材丰富，汤汁浓郁', 'http://localhost:2025/image/foods/ftq.jpg', '2025-03-17 10:28:45', '2025-03-25 16:18:01');
INSERT INTO `foods` VALUES (14, '江西瓦罐汤', 14, 38.00, 4500, 4.6, '江西特色汤品，鲜美可口，营养丰富', 'http://localhost:2025/image/foods/wgt.jpg', '2025-03-17 10:28:45', '2025-03-25 16:18:14');
INSERT INTO `foods` VALUES (15, '山东煎饼', 15, 12.00, 6700, 4.5, '山东传统小吃，薄脆可口，搭配丰富', 'https://source.unsplash.com/random/?jianbing', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (16, '河南烩面', 16, 28.00, 5600, 4.8, '河南特色面食，面条劲道，汤汁浓郁', 'https://source.unsplash.com/random/?huimian', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (17, '湖北鸭脖', 17, 22.00, 4300, 4.7, '湖北特色小吃，鸭脖香辣可口', 'https://source.unsplash.com/random/?duck', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (18, '湖南米粉', 18, 18.00, 7800, 4.7, '湖南经典小吃，米粉爽滑，汤汁鲜美', 'https://source.unsplash.com/random/?rice', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (19, '广东早茶', 19, 58.00, 1890, 4.7, '经典广东早茶，丰富多样的点心', 'https://source.unsplash.com/random/?dimsum', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (20, '海南清补凉', 20, 18.00, 3400, 4.6, '海南特色甜品，食材丰富，冰爽可口', 'https://source.unsplash.com/random/?qingbuliang', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (21, '四川麻辣火锅', 21, 98.00, 2543, 4.8, '正宗四川麻辣火锅，体验舌尖上的麻辣盛宴', 'https://source.unsplash.com/random/?hotpot', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (22, '贵州酸汤鱼', 22, 68.00, 2100, 4.5, '贵州特色美食，酸汤开胃，鱼肉鲜嫩', 'https://source.unsplash.com/random/?suanthangyu', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (23, '云南过桥米线', 24, 48.00, 3400, 4.8, '云南特色小吃，汤热味鲜，米线爽滑', 'https://source.unsplash.com/random/?guoqiaomixian', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (24, '陕西肉夹馍', 25, 18.00, 5600, 4.7, '陕西传统小吃，馍香肉嫩，肥而不腻', 'https://source.unsplash.com/random/?roujiamo', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (25, '甘肃拉面', 26, 22.00, 4300, 4.6, '甘肃特色面食，面条劲道，汤汁浓郁', 'https://source.unsplash.com/random/?lanzhou', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (26, '青海酸奶', 27, 12.00, 1500, 4.5, '青海特色饮品，口感醇厚，营养丰富', 'https://source.unsplash.com/random/?yogurt', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (27, '台湾珍珠奶茶', 28, 18.00, 7800, 4.9, '台湾特色饮品，奶茶醇厚，珍珠Q弹', 'https://source.unsplash.com/random/?pearlmilktea', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (28, '内蒙古烤全羊', 29, 398.00, 800, 4.9, '内蒙古特色美食，烤羊外焦里嫩，香气四溢', 'https://source.unsplash.com/random/?kaoquanyang', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (29, '广西螺蛳粉', 30, 18.00, 7800, 4.7, '广西特色小吃，酸辣鲜香，风味独特', 'https://source.unsplash.com/random/?luosifen', '2025-03-17 10:28:45', '2025-03-17 10:28:45');
INSERT INTO `foods` VALUES (30, '西藏酥油茶', 31, 122.00, 150, 4.6, '西藏特色饮品，口感醇厚，营养丰富', NULL, '2025-03-17 10:28:45', '2025-04-09 10:37:32');

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `hotelname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `sales` decimal(10, 1) NULL DEFAULT NULL,
  `evaluation` varbinary(10) NULL DEFAULT NULL,
  `hoteldescription` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `hotelimage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `hotelprice` decimal(10, 2) NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (1, '长城酒店', 4.5, 0x3132352E30, '位于长城脚下，风景优美，交通便利', 'http://localhost:2025/image/hotels/ccjd.jpg', 80.00, '2025-03-12 16:01:45', '2025-04-08 13:58:01');
INSERT INTO `hotel` VALUES (2, '故宫酒店', NULL, NULL, '临近故宫，方便游览，周边美食众多', 'http://localhost:2025/image/hotels/ggjd.jpg', 1200.00, '2025-03-12 16:01:45', '2025-03-25 22:03:11');
INSERT INTO `hotel` VALUES (4, '黄山酒店', NULL, NULL, '位于黄山脚下，登山游客的首选', 'http://localhost:2025/image/hotels/hhjd.jpg', 750.00, '2025-03-12 16:01:45', '2025-03-25 22:05:12');
INSERT INTO `hotel` VALUES (5, '九寨沟酒店', NULL, NULL, '靠近九寨沟景区，自然风光迷人', 'http://localhost:2025/image/hotels/jzgjd.jpg', 600.00, '2025-03-12 16:01:45', '2025-03-25 22:03:37');
INSERT INTO `hotel` VALUES (6, '桂林山水酒店', NULL, NULL, '依山傍水，喀斯特地貌景观独特', 'http://localhost:2025/image/hotels/gljd.jpg', 550.00, '2025-03-12 16:01:45', '2025-03-25 22:03:53');
INSERT INTO `hotel` VALUES (7, '张家界酒店', NULL, NULL, '临近张家界国家森林公园，适合探险', 'http://localhost:2025/image/hotels/jzgjd.jpg', 700.00, '2025-03-12 16:01:45', '2025-03-25 22:07:46');
INSERT INTO `hotel` VALUES (8, '布达拉宫酒店', NULL, NULL, '位于拉萨市中心，靠近布达拉宫', 'http://localhost:2025/image/hotels/bdlgjd.jpg', 1000.00, '2025-03-12 16:01:45', '2025-03-25 22:05:58');
INSERT INTO `hotel` VALUES (9, '兵马俑酒店', NULL, NULL, '距离西安兵马俑景区仅几分钟车程', 'http://localhost:2025/image/hotels/bmyjd.jpg', 850.00, '2025-03-12 16:01:45', '2025-03-25 22:06:09');
INSERT INTO `hotel` VALUES (12, '青岛海景酒店', NULL, NULL, '临海而建，海景房视野开阔', 'http://localhost:2025/image/hotels/qdjd.jpg', 1300.00, '2025-03-12 16:01:45', '2025-03-25 22:06:20');
INSERT INTO `hotel` VALUES (13, '成都熊猫酒店', NULL, NULL, '靠近熊猫基地，适合亲子游', 'http://localhost:2025/image/hotels/cdxmjd.jpg', 700.00, '2025-03-12 16:01:45', '2025-03-25 22:06:29');
INSERT INTO `hotel` VALUES (14, '厦门鼓浪屿酒店', NULL, NULL, '位于鼓浪屿岛上，充满文艺气息', 'http://localhost:2025/image/hotels/xmglyjd.jpg', 900.00, '2025-03-12 16:01:45', '2025-03-25 22:06:49');
INSERT INTO `hotel` VALUES (15, '哈尔滨冰雪酒店', NULL, NULL, '冬季热门，冰雪大世界旁', 'http://localhost:2025/image/hotels/hebjd.jpg', 1200.00, '2025-03-12 16:01:45', '2025-03-25 22:07:10');
INSERT INTO `hotel` VALUES (16, '西双版纳度假酒店', NULL, NULL, '热带雨林风情，适合避寒', 'http://localhost:2025/image/hotels/xsbn.jpg', 800.00, '2025-03-12 16:01:45', '2025-03-25 22:08:15');
INSERT INTO `hotel` VALUES (17, '上海外滩酒店', NULL, NULL, '位于外滩，城市景观绝佳', 'http://localhost:2025/image/hotels/shwtjd.jpg', 1800.00, '2025-03-12 16:01:45', '2025-03-25 22:09:03');
INSERT INTO `hotel` VALUES (18, '北京王府井酒店', NULL, NULL, '地处繁华商圈，购物便利', 'https://example.com/images/beijing_wangfujing_hotel.jpg', 1400.00, '2025-03-12 16:01:45', '2025-03-12 16:01:45');
INSERT INTO `hotel` VALUES (19, '广州塔酒店', NULL, NULL, '靠近广州塔，城市地标旁', 'https://example.com/images/guangzhou_tower_hotel.jpg', 1350.00, '2025-03-12 16:01:45', '2025-03-12 16:01:45');
INSERT INTO `hotel` VALUES (20, '南京夫子庙酒店', NULL, NULL, '临近夫子庙，古色古香', 'https://example.com/images/nanjing_fuzimiao_hotel.jpg', 950.00, '2025-03-12 16:01:45', '2025-03-12 16:01:45');
INSERT INTO `hotel` VALUES (21, '杭州千岛湖酒店', 4.8, 0x383838382E30, '湖光山色，适合休闲度假', 'https://example.com/images/hangzhou_qiandao_lake_hotel.jpg', 850.00, '2025-03-12 16:01:45', '2025-04-08 13:57:30');
INSERT INTO `hotel` VALUES (22, '泰山酒店', NULL, NULL, '位于泰山脚下，登山游客的理想选择', 'https://example.com/im', 780.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (23, '华山酒店', NULL, NULL, '华山的著名酒店，提供壮丽的山景', 'https://example.com/im', 790.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (24, '嵩山酒店', NULL, NULL, '嵩山地区的舒适住宿，靠近少林寺', 'https://example.com/im', 800.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (25, '衡山酒店', NULL, NULL, '衡山的豪华酒店，享受宁静的环境', 'https://example.com/im', 810.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (26, '恒山酒店', NULL, NULL, '恒山的精品酒店，提供温馨的服务', 'https://example.com/im', 820.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (27, '庐山酒店', NULL, NULL, '庐山的顶级酒店，拥有湖景套房', 'https://example.com/im', 830.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (28, '峨眉山酒店', NULL, NULL, '峨眉山的生态酒店，亲近自然', 'https://example.com/im', 840.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (29, '武夷山酒店', NULL, NULL, '武夷山的度假村，享受茶文化', 'https://example.com/im', 850.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (30, '五台山酒店', NULL, NULL, '五台山的佛教主题酒店，体验禅意', 'https://example.com/im', 860.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (31, '普陀山酒店', NULL, NULL, '普陀山的海景酒店，享受海风', 'https://example.com/im', 870.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (32, '九华山酒店', NULL, NULL, '九华山的静心酒店，适合冥想', 'https://example.com/im', 880.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (33, '黄山酒店', NULL, NULL, '黄山的豪华酒店，提供顶级服务', 'https://example.com/im', 890.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (34, '泰山酒店', NULL, NULL, '泰山的舒适酒店，适合家庭旅游', 'https://example.com/im', 900.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (35, '华山酒店', NULL, NULL, '华山的经济型酒店，性价比高', 'https://example.com/im', 910.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (36, '嵩山酒店', NULL, NULL, '嵩山的精品酒店，设计独特', 'https://example.com/im', 920.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (37, '衡山酒店', NULL, NULL, '衡山的生态酒店，环保理念', 'https://example.com/im', 930.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (38, '恒山酒店', NULL, NULL, '恒山的度假村，适合度假', 'https://example.com/im', 940.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (39, '庐山酒店', NULL, NULL, '庐山的温泉酒店，享受温泉浴', 'https://example.com/im', 950.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (40, '峨眉山酒店', NULL, NULL, '峨眉山的豪华酒店，提供SPA服务', 'https://example.com/im', 960.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (41, '武夷山酒店', NULL, NULL, '武夷山的精品酒店，设计现代', 'https://example.com/im', 970.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (42, '五台山酒店', NULL, NULL, '五台山的文化酒店，体验传统文化', 'https://example.com/im', 980.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (43, '普陀山酒店', NULL, NULL, '普陀山的度假村，适合家庭度假', 'https://example.com/im', 990.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (44, '九华山酒店', NULL, NULL, '九华山的生态酒店，亲近自然', 'https://example.com/im', 1000.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (45, '黄山酒店', NULL, NULL, '黄山的度假村，适合长期居住', 'https://example.com/im', 1010.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (46, '泰山酒店', NULL, NULL, '泰山的豪华酒店，提供顶级服务', 'https://example.com/im', 1020.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (47, '华山酒店', NULL, NULL, '华山的精品酒店，设计独特', 'https://example.com/im', 1030.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (48, '嵩山酒店', NULL, NULL, '嵩山的经济型酒店，性价比高', 'https://example.com/im', 1040.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (49, '衡山酒店', NULL, NULL, '衡山的舒适酒店，适合家庭旅游', 'https://example.com/im', 1050.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `hotel` VALUES (53, '12', NULL, NULL, '121', '', 11.00, '2025-03-26 09:33:03', '2025-03-26 09:33:03');
INSERT INTO `hotel` VALUES (54, '12', NULL, NULL, '131', '', 123.00, '2025-04-08 14:16:13', '2025-04-08 14:16:13');
INSERT INTO `hotel` VALUES (55, '111', NULL, NULL, '111', '', 111111.00, '2025-04-08 14:16:25', '2025-04-08 14:16:25');

-- ----------------------------
-- Table structure for my_post
-- ----------------------------
DROP TABLE IF EXISTS `my_post`;
CREATE TABLE `my_post`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `images` json NULL,
  `tags` json NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of my_post
-- ----------------------------
INSERT INTO `my_post` VALUES (1, '神秘的南极之旅', '南极洲', '2025-04-15', '在南极洲的冰原上，我看到了壮观的冰山和可爱的企鹅。', '[\"https://example.com/image1.jpg\", \"https://example.com/image2.jpg\"]', '[\"旅行\", \"探险\", \"南极\"]');
INSERT INTO `my_post` VALUES (2, '亚马逊雨林探险', '巴西', '2025-03-20', '深入亚马逊雨林，体验了原始森林的神秘与危险。', '[\"https://example.com/image3.jpg\", \"https://example.com/image4.jpg\"]', '[\"旅行\", \"探险\", \"雨林\"]');
INSERT INTO `my_post` VALUES (3, '尼泊尔徒步之旅', '尼泊尔', '2025-02-10', '在尼泊尔的喜马拉雅山脉徒步，感受了高山的壮丽。', '[\"https://example.com/image5.jpg\", \"https://example.com/image6.jpg\"]', '[\"旅行\", \"徒步\", \"喜马拉雅\"]');
INSERT INTO `my_post` VALUES (4, '非洲大草原之旅', '肯尼亚', '2025-01-18', '在肯尼亚的大草原上，我看到了狮子、大象和斑马。', '[\"https://example.com/image7.jpg\", \"https://example.com/image8.jpg\"]', '[\"旅行\", \"野生动物\", \"非洲\"]');
INSERT INTO `my_post` VALUES (5, '北极光下的冒险', '挪威', '2025-05-05', '在挪威的极夜中，我看到了绚丽的北极光。', '[\"https://example.com/image9.jpg\", \"https://example.com/image10.jpg\"]', '[\"旅行\", \"探险\", \"北极光\"]');
INSERT INTO `my_post` VALUES (6, '穿越撒哈拉沙漠', '摩洛哥', '2025-06-20', '在撒哈拉沙漠中，我体验了沙漠的广袤与寂静。', '[\"https://example.com/image11.jpg\", \"https://example.com/image12.jpg\"]', '[\"旅行\", \"探险\", \"沙漠\"]');
INSERT INTO `my_post` VALUES (7, '日本富士山之旅', '日本', '2025-07-10', '攀登富士山，感受了日本的自然之美。', '[\"https://example.com/image13.jpg\", \"https://example.com/image14.jpg\"]', '[\"旅行\", \"登山\", \"富士山\"]');
INSERT INTO `my_post` VALUES (8, '澳大利亚大堡礁潜水', '澳大利亚', '2025-08-15', '在大堡礁潜水，看到了五彩斑斓的珊瑚礁和海洋生物。', '[\"https://example.com/image15.jpg\", \"https://example.com/image16.jpg\"]', '[\"旅行\", \"潜水\", \"大堡礁\"]');
INSERT INTO `my_post` VALUES (9, '秘鲁马丘比丘之旅', '秘鲁', '2025-09-20', '在马丘比丘，我感受到了印加文明的伟大。', '[\"https://example.com/image17.jpg\", \"https://example.com/image18.jpg\"]', '[\"旅行\", \"历史\", \"马丘比丘\"]');
INSERT INTO `my_post` VALUES (10, '冰岛火山探险', '冰岛', '2025-10-30', '在冰岛的火山地区，我看到了火山喷发的壮观景象。', '[\"https://example.com/image19.jpg\", \"https://example.com/image20.jpg\"]', '[\"旅行\", \"探险\", \"火山\"]');

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
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (37, 'http://localhost:2025/image/order/szcx.jpg', 120, 120.00, 'COMPLETED', '2025-01-15 10:23:45', '2025-01-15 10:25:30', 'WECHAT', 'SKU001', '苏州刺绣', '2025-03-26 20:57:40', '中国传统手工艺品，以精细的针法和丰富的色彩闻名');
INSERT INTO `orders` VALUES (38, 'http://localhost:2025/image/order/jdzcc.jpg', 85, 85.00, 'PAID', '2025-01-18 14:12:33', '2025-01-18 14:13:45', 'PAYPAL', 'SKU002', '景德镇瓷器', '2025-03-26 20:57:40', '中国瓷器之都出品，白如玉、明如镜、薄如纸、声如磬');
INSERT INTO `orders` VALUES (39, 'http://localhost:2025/image/order/ynpec.jpg', 45, 45.00, 'SHIPPED', '2025-02-05 09:45:21', '2025-02-05 09:46:10', 'BANK', 'SKU003', '云南普洱茶', '2025-03-26 20:57:40', '陈年发酵茶，具有独特的陈香和保健功效');
INSERT INTO `orders` VALUES (40, 'http://localhost:2025/image/order/xztk.jpg', 150657, 239.00, 'PENDING', '2025-02-12 16:30:15', NULL, 'WECHAT', 'SKU004', '西藏唐卡', '2025-03-26 20:57:40', '藏传佛教绘画艺术，色彩鲜艳，具有宗教和文化价值');
INSERT INTO `orders` VALUES (41, 'http://localhost:2025/image/order/hzsc.jpg', 65, 65.00, 'COMPLETED', '2025-02-20 11:22:10', '2025-02-20 11:23:45', 'PAYPAL', 'SKU005', '杭州丝绸', '2025-03-26 20:57:40', '质地柔软光滑，中国四大名绣之一');
INSERT INTO `orders` VALUES (42, 'http://localhost:2025/image/order/bmy.jpg', 180, 180.00, 'PAID', '2025-03-01 13:45:33', '2025-03-01 13:46:20', 'BANK', 'SKU006', '西安兵马俑复制品', '2025-03-26 20:57:40', '按比例缩小的秦始皇兵马俑复制品，具有历史纪念意义');
INSERT INTO `orders` VALUES (43, 'http://localhost:2025/image/order/hnyt.jpg', 95, 95.00, 'COMPLETED', '2025-03-08 10:15:22', '2025-03-08 10:16:30', 'WECHAT', 'SKU007', '海南椰雕', '2025-03-26 20:57:40', '用海南椰子壳雕刻而成的工艺品，具有热带风情');
INSERT INTO `orders` VALUES (44, 'http://localhost:2025/image/order/bjjtl.jpg', 55, 55.00, 'SHIPPED', '2025-03-12 15:33:44', '2025-03-12 15:34:50', 'PAYPAL', 'SKU008', '北京景泰蓝', '2025-03-26 20:57:40', '传统珐琅工艺品，色彩绚丽，工艺精湛');
INSERT INTO `orders` VALUES (45, 'http://localhost:2025/image/order/scsx.jpg', 120, 120.00, 'PENDING', '2025-03-15 09:12:11', NULL, 'BANK', 'SKU009', '四川蜀绣', '2025-03-26 20:57:40', '中国四大名绣之一，以细腻的针法和丰富的色彩著称');
INSERT INTO `orders` VALUES (46, 'http://localhost:2025/image/order/xjhty.jpg', 75, 75.00, 'COMPLETED', '2025-03-18 14:45:33', '2025-03-18 14:46:20', 'WECHAT', 'SKU010', '新疆和田玉', '2025-03-26 20:57:40', '质地温润，中国四大名玉之一');
INSERT INTO `orders` VALUES (47, 'http://localhost:2025/image/order/gdcsm.jpg', 200, 200.00, 'PAID', '2025-03-20 11:22:44', '2025-03-20 11:23:30', 'PAYPAL', 'SKU011', '广东潮汕木雕', '2025-03-26 20:57:40', '精细的木雕工艺品，具有浓郁的岭南特色');
INSERT INTO `orders` VALUES (48, 'http://localhost:2025/image/order/hnxx.jpg', 90, 90.00, 'COMPLETED', '2025-03-22 16:15:22', '2025-03-22 16:16:10', 'BANK', 'SKU012', '湖南湘绣', '2025-03-26 20:57:40', '中国四大名绣之一，以写实风格和丰富色彩见长');
INSERT INTO `orders` VALUES (49, 'item13.jpg', 110, 110.00, 'SHIPPED', '2025-03-23 10:33:11', '2025-03-23 10:34:05', 'WECHAT', 'SKU013', '福建武夷岩茶', '2025-03-26 20:57:40', '乌龙茶中的极品，具有独特的岩韵');
INSERT INTO `orders` VALUES (50, 'item14.jpg', 60, 60.00, 'PENDING', '2025-03-24 13:45:22', NULL, 'PAYPAL', 'SKU014', '内蒙古马头琴', '2025-03-26 20:57:40', '蒙古族传统乐器，琴头雕刻成马头形状');
INSERT INTO `orders` VALUES (51, 'item15.jpg', 130, 130.00, 'COMPLETED', '2025-03-25 09:12:33', '2025-03-25 09:13:20', 'BANK', 'SKU015', '山西老陈醋', '2025-03-26 20:57:40', '传统发酵工艺酿造，酸香浓郁，回味悠长');
INSERT INTO `orders` VALUES (52, 'item16.jpg', 70, 70.00, 'PAID', '2025-03-26 14:22:44', '2025-03-26 14:23:30', 'WECHAT', 'SKU016', '南京云锦', '2025-03-26 20:57:40', '中国传统提花丝织工艺品，有\"寸锦寸金\"之称');
INSERT INTO `orders` VALUES (53, 'item17.jpg', 95, 95.00, 'COMPLETED', '2025-03-27 11:15:55', '2025-03-27 11:16:40', 'PAYPAL', 'SKU017', '安徽宣纸', '2025-03-26 20:57:40', '中国传统书画用纸，质地绵韧，墨韵清晰');
INSERT INTO `orders` VALUES (54, 'item18.jpg', 180, 180.00, 'SHIPPED', '2025-03-28 15:33:22', '2025-03-28 15:34:10', 'BANK', 'SKU018', '贵州茅台酒', '2025-03-26 20:57:40', '中国酱香型白酒的代表，具有独特的酿造工艺');
INSERT INTO `orders` VALUES (55, 'item19.jpg', 50, 50.00, 'PENDING', '2025-03-29 10:45:11', NULL, 'WECHAT', 'SKU019', '台湾高山茶', '2025-03-26 20:57:40', '生长在海拔1000米以上的高山茶园，香气清雅');
INSERT INTO `orders` VALUES (56, 'item20.jpg', 140, 140.00, 'COMPLETED', '2025-03-30 13:22:33', '2025-03-30 13:23:20', 'PAYPAL', 'SKU020', '甘肃敦煌壁画复制品', '2025-03-26 20:57:40', '莫高窟艺术精华的复制品，具有极高的艺术价值');
INSERT INTO `orders` VALUES (57, '', 12, 11.00, 'PAID', '2025-04-08 14:15:47', NULL, 'PAYPAL', '1212', NULL, '2025-04-08 14:15:46', NULL);
INSERT INTO `orders` VALUES (58, '', 12111, 12.00, 'PAID', '2025-04-08 14:16:03', NULL, 'BANK', '12', NULL, '2025-04-08 14:16:02', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 143 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payments
-- ----------------------------
INSERT INTO `payments` VALUES (120, 'hhj', 'PAY17467587392354052', 0, '重庆火锅', 98.00, 'ALIPAY', 'SUCCESS', '2025-05-09 10:45:39', '2025-05-11 12:23:04', 0, '1');
INSERT INTO `payments` VALUES (121, 'hhj', 'PAY17467590468854939', 0, '景德镇瓷器', 85.00, 'BANK', 'SUCCESS', '2025-05-09 10:50:47', '2025-05-11 12:23:04', 0, '1');
INSERT INTO `payments` VALUES (122, 'hhj', 'PAY17467612120942358', 2, '上海小笼包', 28.00, 'BANK', 'SUCCESS', '2025-05-09 11:26:52', '2025-05-11 12:23:05', 0, '1');
INSERT INTO `payments` VALUES (123, 'hhj', 'PAY17467612430562292', 5, '九寨沟酒店', 600.00, NULL, 'PENDING', '2025-05-09 11:27:23', '2025-05-11 12:23:05', 1, '1');
INSERT INTO `payments` VALUES (124, 'gzy', 'PAY17467670312226814', 0, '日本', 1.00, 'ALIPAY', 'SUCCESS', '2025-05-09 13:03:51', '2025-05-11 12:23:06', 0, '1');
INSERT INTO `payments` VALUES (125, 'gzy', 'PAY17467670393912636', 0, '黄山酒店', 750.00, 'WECHAT', 'SUCCESS', '2025-05-09 13:03:59', '2025-05-11 12:23:06', 0, '1');
INSERT INTO `payments` VALUES (126, 'gzy', 'PAY17467670491309638', 38, '景德镇瓷器', 85.00, NULL, 'PENDING', '2025-05-09 13:04:09', '2025-05-11 12:23:07', 1, '1');
INSERT INTO `payments` VALUES (127, 'admin', 'PAY17468542165622605', 4, '重庆火锅', 98.00, NULL, 'PENDING', '2025-05-10 13:16:56', '2025-05-11 12:23:07', 1, '1');
INSERT INTO `payments` VALUES (128, 'admin', 'PAY17468613397908341', 0, '黄山酒店', 750.00, 'ALIPAY', 'SUCCESS', '2025-05-10 15:15:40', '2025-05-11 12:23:07', 0, '1');
INSERT INTO `payments` VALUES (129, 'admin', 'PAY17468873964417505', 0, '苏州刺绣', 120.00, 'ALIPAY', 'SUCCESS', '2025-05-10 22:29:56', '2025-05-11 12:23:08', 0, '1');
INSERT INTO `payments` VALUES (132, 'admin', 'PAY17468914213177233', 3, '千岛湖自驾', 12312.00, NULL, 'PENDING', '2025-05-10 23:37:01', '2025-05-11 12:23:08', 1, '1');
INSERT INTO `payments` VALUES (133, 'admin', 'PAY17468914327764327', 14, '嵩山', 440.00, NULL, 'PENDING', '2025-05-10 23:37:12', '2025-05-11 12:23:10', 1, '1');
INSERT INTO `payments` VALUES (135, 'admin', 'PAY17469369061661729', 0, '横店影视城', 213.00, 'WECHAT', 'SUCCESS', '2025-05-11 12:15:06', '2025-05-11 12:15:06', 0, '1');
INSERT INTO `payments` VALUES (136, 'admin', 'PAY17469377265705713', 0, '乌镇夜色漫游', 702.00, 'BANK', 'SUCCESS', '2025-05-11 12:28:47', '2025-05-11 12:28:46', 0, '3');
INSERT INTO `payments` VALUES (137, 'admin', 'PAY17469413318534518', 2, '故宫', 360.00, NULL, 'PENDING', '2025-05-11 13:28:51', '2025-05-11 13:28:51', 1, '4');
INSERT INTO `payments` VALUES (138, '345', 'PAY17470605963544332', 2, '绍兴古镇探秘', 369.00, 'ALIPAY', 'SUCCESS', '2025-05-12 22:36:36', '2025-05-12 22:38:42', 0, '3');
INSERT INTO `payments` VALUES (140, '567', 'PAY17471835022299237', 2, '绍兴古镇探秘', 369.00, NULL, 'PENDING', '2025-05-14 08:45:02', '2025-05-14 08:45:02', 1, '3');
INSERT INTO `payments` VALUES (141, '567', 'PAY17471835207665919', 0, '故宫', 240.00, 'WECHAT', 'FAILED', '2025-05-14 08:45:21', '2025-05-14 08:45:20', 0, '2');
INSERT INTO `payments` VALUES (142, '567', 'PAY17471835225468349', 0, '故宫', 240.00, 'WECHAT', 'SUCCESS', '2025-05-14 08:45:23', '2025-05-14 08:45:22', 0, '2');

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
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '安全知识提示表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of safety_tips
-- ----------------------------
INSERT INTO `safety_tips` VALUES (1, '火灾逃生指南', '1.保持冷静，迅速判断安全出口位置\n2.用湿毛巾捂住口鼻', 'http://localhost:2025/image/safetytips/hz.jpg', 1, 0, 1, 1, NULL, '2025-04-07 09:29:56', '2025-04-10 22:19:51');
INSERT INTO `safety_tips` VALUES (2, '电器使用安全', '1.不要用湿手接触电器\n2.定期检查电线是否老化', 'http://localhost:2025/image/safetytips/ydaq.jpg', 2, 0, 1, 1, NULL, '2025-04-07 09:29:56', '2025-04-09 08:26:27');
INSERT INTO `safety_tips` VALUES (3, '安全过马路', '1.遵守交通信号灯\n2.走人行横道\n', 'http://localhost:2025/image/safetytips/jtaq.jpg', 3, 0, 1, 1, NULL, '2025-04-07 09:29:56', '2025-04-09 08:26:20');

-- ----------------------------
-- Table structure for scenic
-- ----------------------------
DROP TABLE IF EXISTS `scenic`;
CREATE TABLE `scenic`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `evaluation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `sales` decimal(10, 1) NULL DEFAULT NULL,
  `subtitle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT 0.00,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic
-- ----------------------------
INSERT INTO `scenic` VALUES (1, '长城', '1231', 4.8, '中国古代伟大的防御工程', 'http://localhost:2025/image/scenic/zgcc.jpg', 80.00, '2025-03-12 16:28:19', '2025-05-06 14:32:21');
INSERT INTO `scenic` VALUES (2, '故宫', NULL, NULL, '明清两代的皇家宫殿', 'http://localhost:2025/image/scenic/zggg.jpg', 120.00, '2025-03-12 16:28:19', '2025-03-25 13:35:00');
INSERT INTO `scenic` VALUES (3, '西湖', NULL, NULL, '杭州著名的风景名胜区', 'http://localhost:2025/image/scenic/hzxh.jpg', 60.00, '2025-03-12 16:28:19', '2025-03-25 13:35:03');
INSERT INTO `scenic` VALUES (5, '九寨沟', NULL, NULL, '四川省的自然保护区', 'http://localhost:2025/image/scenic/zgjzg.jpg', 150.00, '2025-03-12 16:28:19', '2025-03-25 13:38:59');
INSERT INTO `scenic` VALUES (6, '桂林山水', NULL, NULL, '广西壮族自治区的喀斯特地貌', 'http://localhost:2025/image/scenic/glss.jpg', 99.00, '2025-03-12 16:28:19', '2025-04-27 15:54:00');
INSERT INTO `scenic` VALUES (7, '张家界', NULL, NULL, '湖南省的国家森林公园', 'http://localhost:2025/image/scenic/zjj.jpg', 110.00, '2025-03-12 16:28:19', '2025-03-25 16:01:54');
INSERT INTO `scenic` VALUES (8, '布达拉宫', NULL, NULL, '西藏的著名宗教建筑', 'http://localhost:2025/image/scenic/bdlg.jpg', 130.00, '2025-03-12 16:28:19', '2025-03-25 16:02:01');
INSERT INTO `scenic` VALUES (9, '兵马俑', NULL, NULL, '西安的秦始皇陵兵马俑', 'http://localhost:2025/image/scenic/bmy.jpg', 70.00, '2025-03-12 16:28:19', '2025-03-25 16:02:12');
INSERT INTO `scenic` VALUES (13, '华山', NULL, NULL, '陕西省的著名山岳风景区', 'http://localhost:2025/image/scenic/hs.jpg', 180.00, '2025-03-12 16:28:19', '2025-03-25 16:02:19');
INSERT INTO `scenic` VALUES (14, '嵩山', NULL, NULL, '河南省的著名山岳风景区', 'http://localhost:2025/image/scenic/ss.jpg', 220.00, '2025-03-12 16:28:19', '2025-03-25 16:02:32');
INSERT INTO `scenic` VALUES (15, '衡山', NULL, NULL, '湖南省的著名山岳风景区', 'http://localhost:2025/image/scenic/hs.jpg', 210.00, '2025-03-12 16:28:19', '2025-03-25 16:02:55');
INSERT INTO `scenic` VALUES (16, '恒山', NULL, NULL, '山西省的著名山岳风景区', 'http://localhost:2025/image/scenic/hengss.jpg', 190.00, '2025-03-12 16:28:19', '2025-03-25 16:04:27');
INSERT INTO `scenic` VALUES (17, '庐山', NULL, NULL, '江西省的著名山岳风景区', 'http://localhost:2025/image/scenic/lsjpg.jpg', 230.00, '2025-03-12 16:28:19', '2025-03-25 16:04:42');
INSERT INTO `scenic` VALUES (18, '峨眉山', NULL, NULL, '四川省的著名山岳风景区', 'http://localhost:2025/image/scenic/ems.jpg', 240.00, '2025-03-12 16:28:19', '2025-03-25 16:03:09');
INSERT INTO `scenic` VALUES (19, '武夷山', NULL, NULL, '福建省的著名山岳风景区', 'http://localhost:2025/image/scenic/wys.jpg', 250.00, '2025-03-12 16:28:19', '2025-03-25 16:03:13');
INSERT INTO `scenic` VALUES (20, '五台山', NULL, NULL, '山西省的著名佛教圣地', 'http://localhost:2025/image/scenic/wts.jpg', 260.00, '2025-03-12 16:28:19', '2025-03-25 16:03:17');
INSERT INTO `scenic` VALUES (21, '普陀山', NULL, NULL, '浙江省的著名佛教圣地', 'http://localhost:2025/image/scenic/pts.jpg', 270.00, '2025-03-12 16:28:19', '2025-03-25 16:03:22');
INSERT INTO `scenic` VALUES (22, '九华山', NULL, NULL, '安徽省的著名佛教圣地', 'http://localhost:2025/image/scenic/jhs.jpg', 280.00, '2025-03-12 16:28:19', '2025-03-25 16:03:30');
INSERT INTO `scenic` VALUES (23, '峨眉山', NULL, NULL, '四川省的著名佛教圣地', 'https://example.com/im', 290.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (24, '黄山', NULL, NULL, '安徽省的著名山岳风景区', 'https://example.com/im', 300.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (25, '泰山', NULL, NULL, '山东省的著名山岳风景区', 'https://example.com/im', 310.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (26, '华山', NULL, NULL, '陕西省的著名山岳风景区', 'https://example.com/im', 320.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (27, '嵩山', NULL, NULL, '河南省的著名山岳风景区', 'https://example.com/im', 330.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (28, '衡山', NULL, NULL, '湖南省的著名山岳风景区', 'https://example.com/im', 340.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (29, '恒山', NULL, NULL, '山西省的著名山岳风景区', 'https://example.com/im', 350.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (30, '庐山', NULL, NULL, '江西省的著名山岳风景区', 'https://example.com/im', 360.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (31, '峨眉山', NULL, NULL, '四川省的著名山岳风景区', 'https://example.com/im', 370.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (32, '武夷山', NULL, NULL, '福建省的著名山岳风景区', 'https://example.com/im', 380.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (33, '五台山', NULL, NULL, '山西省的著名佛教圣地', 'https://example.com/im', 390.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (34, '普陀山', NULL, NULL, '浙江省的著名佛教圣地', 'https://example.com/im', 400.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (35, '九华山', NULL, NULL, '安徽省的著名佛教圣地', 'https://example.com/im', 410.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (36, '峨眉山', NULL, NULL, '四川省的著名佛教圣地', 'https://example.com/im', 420.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (37, '黄山', NULL, NULL, '安徽省的著名山岳风景区', 'https://example.com/im', 430.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (38, '泰山', NULL, NULL, '山东省的著名山岳风景区', 'https://example.com/im', 440.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (39, '华山', NULL, NULL, '陕西省的著名山岳风景区', 'https://example.com/im', 450.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (40, '嵩山', NULL, NULL, '河南省的著名山岳风景区', 'https://example.com/im', 460.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (41, '衡山', NULL, NULL, '湖南省的著名山岳风景区', 'https://example.com/im', 470.00, '2025-03-12 16:28:19', '2025-03-12 16:28:19');
INSERT INTO `scenic` VALUES (44, '12', NULL, NULL, '12', '', 12.00, '2025-04-08 14:17:39', '2025-04-08 14:17:39');
INSERT INTO `scenic` VALUES (45, '123', NULL, NULL, '12', '', 11.00, '2025-05-04 18:09:52', '2025-05-04 18:09:51');

-- ----------------------------
-- Table structure for travel_blog
-- ----------------------------
DROP TABLE IF EXISTS `travel_blog`;
CREATE TABLE `travel_blog`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `likes` int NULL DEFAULT 0,
  `price` decimal(10, 2) NOT NULL,
  `favorites` int NULL DEFAULT 0,
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_blog
-- ----------------------------
INSERT INTO `travel_blog` VALUES (2, '绍兴古镇探秘', '小桥流水人家的江南韵味', '绍兴', 'http://localhost:2025/image/traveblog/sxgz.jpg', 259, 123.00, 229, NULL);
INSERT INTO `travel_blog` VALUES (3, '千岛湖自驾', '湖中青岛，岛中有湖的奇妙景观', '杭州', 'http://localhost:2025/image/traveblog/qdh.jpg', 398, 12312.00, 105, '2025-03-19 19:19:00');
INSERT INTO `travel_blog` VALUES (4, '乌镇夜色漫游', '桨声灯影里的江南水乡', '嘉兴', 'http://localhost:2025/image/traveblog/wz.jpg', 143, 234.00, 205, '2024-08-16 18:13:47');
INSERT INTO `travel_blog` VALUES (5, '普陀山朝圣之旅', '海天佛国的宗教文化体验', '舟山', 'http://localhost:2025/image/traveblog/pts.jpg', 398, 2314.00, 82, '2024-03-21 18:13:47');
INSERT INTO `travel_blog` VALUES (6, '横店影视城', '穿越古今的影视主题乐园', '金华', 'http://localhost:2025/image/traveblog/hd.jpg', 49, 213.00, 106, '2024-08-01 18:13:47');
INSERT INTO `travel_blog` VALUES (7, '雁荡山地质奇观', '火山岩地貌的自然博物馆', '温州', 'http://localhost:2025/image/traveblog/yd.jpg', 498, 213.00, 43, '2024-06-16 18:13:47');
INSERT INTO `travel_blog` VALUES (8, '莫干山民宿体验', '竹林深处的避世天堂', '湖州', 'http://localhost:2025/image/traveblog/mgs.jpg', 128, 34435.00, 20, '2024-08-14 18:13:47');
INSERT INTO `travel_blog` VALUES (9, '南浔古镇美食记', '江南传统小吃的舌尖之旅', '湖州', 'http://localhost:2025/image/traveblog/nxgz.jpg', 336, 2323.00, 191, '2025-01-08 18:13:47');
INSERT INTO `travel_blog` VALUES (11, '天台山国清寺', '佛教天台宗的发源地', '台州', 'http://localhost:2025/image/traveblog/ttsgqs.jpg', 416, 76.00, 73, '2024-06-19 18:13:47');
INSERT INTO `travel_blog` VALUES (12, '宁波老外滩夜游', '百年商埠的现代风情', '宁波', 'http://localhost:2025/image/traveblog/nblwt.jpg', 459, 213.00, 120, '2024-12-12 18:13:47');
INSERT INTO `travel_blog` VALUES (13, '嵊泗列岛自由行', '东海上的璀璨明珠', '舟山', 'http://localhost:2025/image/traveblog/yd.jpg', 21, 234.00, 139, '2025-03-19 19:20:00');
INSERT INTO `travel_blog` VALUES (14, '丽水梯田摄影', '华东最大的梯田群', '丽水', '/images/lishui.jpg', 298, 870.00, 117, '2025-01-12 18:13:47');
INSERT INTO `travel_blog` VALUES (15, '安吉竹海漫步', '《卧虎藏龙》拍摄地探秘', '湖州', '/images/anji.jpg', 318, 770.00, 210, '2024-08-08 18:13:47');
INSERT INTO `travel_blog` VALUES (16, '溪口蒋氏故居', '民国历史文化的窗口', '宁波', '/images/xikou.jpg', 462, 88.00, 161, '2025-02-26 18:13:47');
INSERT INTO `travel_blog` VALUES (17, '象山影视城攻略', '武侠主题的实景体验', '宁波', '/images/xiangshan.jpg', 310, 880.00, 297, '2025-02-08 18:13:47');
INSERT INTO `travel_blog` VALUES (18, '嘉兴南湖红船', '红色旅游的起点', '嘉兴', '/images/nanhu.jpg', 239, 89.00, 36, '2025-01-08 18:13:47');
INSERT INTO `travel_blog` VALUES (19, '临安大明山滑雪', '江南罕见的滑雪胜地', '杭州', '/images/daming.jpg', 253, 236.00, 3, '2024-08-29 18:13:47');
INSERT INTO `travel_blog` VALUES (20, '衢州孔庙朝圣', '南孔圣地的人文之旅', '衢州', '', 321, 324.00, 183, '2025-01-24 18:13:47');
INSERT INTO `travel_blog` VALUES (24, '昆明', '好玩', '云南', '', 9, 222.00, 0, '2025-04-14 10:23:27');

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
INSERT INTO `travel_cards` VALUES (23, '挪威峡湾游船之旅', '乘坐游船游览挪威的峡湾，欣赏壮观的峡湾景色...', '@/assets/北京天坛.jpg', 886, 1, 'cruise', 'hot', '热门', NULL);
INSERT INTO `travel_cards` VALUES (24, '柬埔寨吴哥窟之旅', '参观柬埔寨的吴哥窟，感受古高棉文化的辉煌...', '@/assets/北京天坛.jpg', 1505, 1, 'culture', NULL, NULL, NULL);
INSERT INTO `travel_cards` VALUES (25, '葡萄牙里斯本之旅', '漫步里斯本的街头，感受葡萄牙的海洋文化和历史韵味...', '@/assets/北京天坛.jpg', 624, 1, 'history', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (26, '南非开普敦之旅', '在开普敦欣赏桌山的美景，体验南非的多元文化...', '@/assets/北京天坛.jpg', 307, 1, 'culture', 'hot', '热门', NULL);
INSERT INTO `travel_cards` VALUES (27, '日本北海道雪景之旅', '在日本北海道欣赏美丽的雪景，体验冬季的浪漫与宁静...', '@/assets/北京天坛.jpg', 235, 1, 'winter', NULL, NULL, NULL);
INSERT INTO `travel_cards` VALUES (28, '澳大利亚大堡礁潜水之旅', '在大堡礁潜水，探索海底世界的奇妙与美丽...', '@/assets/北京天坛.jpg', 1695, 0, 'diving', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (29, '意大利威尼斯水城之旅', '乘坐贡多拉游览威尼斯的水道，感受水城的独特魅力...', '@/assets/北京天坛.jpg', 91, 0, 'watercity', 'hot', '热门', NULL);
INSERT INTO `travel_cards` VALUES (30, '美国纽约都市之旅', '感受纽约的繁华与活力，参观自由女神像、时代广场等标志性建筑...', '@/assets/北京天坛.jpg', 473, 1, 'city', NULL, NULL, NULL);
INSERT INTO `travel_cards` VALUES (31, '希腊雅典古迹之旅', '参观雅典卫城等古迹，感受古希腊文明的辉煌...', '@/assets/北京天坛.jpg', 177, 0, 'history', 'new', '新品', NULL);
INSERT INTO `travel_cards` VALUES (32, '马来西亚兰卡威岛之旅', '在兰卡威岛享受阳光沙滩，体验水上活动的乐趣...', '@/assets/北京天坛.jpg', 1108, 0, 'beach', 'hot', '热门', NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `travelmage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `experience` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `signature` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `account`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'http://localhost:2025/image/users/preview.jpg', 'admin', '2560177364@example.com', '123456', '18213200129', '超级管理员', '/images/lishui.jpg', '哪也没有去过,问就是不知道。', '我是超级管理员删除你。', '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (2, 'http://localhost:2025/image/users/tx.jpg', 'user2', '25601773164@example.com', 'password2', '1390013911', 'nickname2213', '/images/lishui.jpg', NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (3, 'http://localhost:2025/image/users/tx.jpg', 'user3', 'user3@example.com', 'password3', '13900139', 'nickname3', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (4, 'http://localhost:2025/image/users/tx.jpg', 'user4', 'user4@example.com', 'password4', '13900139', 'nickname4', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (5, 'http://localhost:2025/image/users/tx.jpg', 'user5', 'user5@example.com', 'password5', '13900139', 'nickname5', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (6, 'http://localhost:2025/image/users/tx.jpg', 'user6', 'user6@example.com', 'password6', '13900139', 'nickname6', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (7, 'http://localhost:2025/image/users/tx.jpg', 'user7', 'user7@example.com', 'password7', '13900139', 'nickname7', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (8, 'http://localhost:2025/image/users/tx.jpg', 'user8', 'user8@example.com', 'password8', '13900139', 'nickname8', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (9, 'http://localhost:2025/image/users/tx.jpg', 'user9', 'user9@example.com', 'password9', '13900139', 'nickname9', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (15, 'http://localhost:2025/image/users/tx.jpg', 'user15', 'user15@example.com', 'password15', '13900139', 'nickname15', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (16, 'http://localhost:2025/image/users/tx.jpg', 'user16', 'user16@example.com', 'password16', '13900139', 'nickname16', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (17, 'http://localhost:2025/image/users/tx.jpg', 'user17', 'user17@example.com', 'password17', '13900139', 'nickname17', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (18, 'http://localhost:2025/image/users/tx.jpg', 'user18', 'user18@example.com', 'password18', '13900139', 'nickname18', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (19, 'http://localhost:2025/image/users/tx.jpg', 'user19', 'user19@example.com', 'password19', '13900139', 'nickname19', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (20, 'http://localhost:2025/image/users/tx.jpg', 'user20', 'user20@example.com', 'password20', '13900139', 'nickname20', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (21, 'http://localhost:2025/image/users/tx.jpg', 'user21', 'user21@example.com', 'password21', '13900139', 'nickname21', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (22, 'http://localhost:2025/image/users/tx.jpg', 'user22', 'user22@example.com', 'password22', '13900139', 'nickname22', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (23, 'http://localhost:2025/image/users/tx.jpg', 'user23', 'user23@example.com', 'password23', '13900139', 'nickname23', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (24, 'http://localhost:2025/image/users/tx.jpg', 'user24', 'user24@example.com', 'password24', '13900139', 'nickname24', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (25, 'http://localhost:2025/image/users/tx.jpg', 'user25', 'user25@example.com', 'password25', '13900139', 'nickname25', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (26, 'http://localhost:2025/image/users/tx.jpg', 'user26', 'user26@example.com', 'password26', '13900139', 'nickname26', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (27, 'http://localhost:2025/image/users/tx.jpg', 'user27', 'user27@example.com', 'password27', '13900139', 'nickname27', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (28, 'http://localhost:2025/image/users/tx.jpg', 'user28', 'user28@example.com', 'password28', '13900139', 'nickname28', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (29, 'http://localhost:2025/image/users/tx.jpg', 'user29', 'user29@example.com', 'password29', '13900139', 'nickname29', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (30, 'http://localhost:2025/image/users/tx.jpg', 'user30', 'user30@example.com', 'password30', '13900139', 'nickname30', NULL, NULL, NULL, '2025-03-11 15:37:18');
INSERT INTO `users` VALUES (43, 'http://localhost:2025/image/users/43.jpg', 'hhj', '2560177364@qq.com', '123456', '15125994533', '小懒猪爱吃恐龙', NULL, '在去年夏天，我和家人去了云南旅行。我们首先到达了昆明，参观了美丽的滇池，湖水碧蓝，周围风景如画。接着，我们去了大理，漫步在古城的小巷中，品尝了当地的过桥米线。最后，我们前往丽江，看到了壮丽的玉龙雪山。晚上，我们在古城的酒吧里享受了悠扬的民谣，感受到了浓厚的文化氛围。这次旅行让我深刻体会到了云南的自然之美和丰富的历史文化，令人难忘。', '人生是狂野不，应该被早八束缚。', '2025-03-16 23:04:15');
INSERT INTO `users` VALUES (51, 'http://localhost:2025/image/users/tx.jpg', '陶富林', '3208289768@qq.com', '123', '18488206305', '617扛把子', NULL, '家里蹲', '春城第一深情', '2025-04-27 14:55:11');
INSERT INTO `users` VALUES (58, 'http://localhost:2025/image/users/tx.jpg', '华先生', '1213@qq.com', '123', '17314599157', '宝贝', NULL, NULL, NULL, '2025-04-30 17:06:28');
INSERT INTO `users` VALUES (60, NULL, '123', '123@qq.com', '123', '18213200989', '', NULL, NULL, NULL, '2025-05-06 14:37:05');
INSERT INTO `users` VALUES (61, NULL, 'gzy', '743713465@qq.com', '123', '15894362018', '宝贝', NULL, '曾经在曹营七进七出。', '丽江第一深情哥。', '2025-05-09 12:53:14');
INSERT INTO `users` VALUES (63, NULL, '王武', '222@ww.com', '123', '12858655129', '', NULL, NULL, NULL, '2025-05-13 14:08:51');
INSERT INTO `users` VALUES (64, NULL, '4444', '12@11.com', '122', '12312322123', '', NULL, NULL, NULL, '2025-05-13 14:16:25');
INSERT INTO `users` VALUES (65, NULL, '567', '12@qq.com', '234', '17213277129', '小明', NULL, NULL, NULL, '2025-05-14 08:42:41');

SET FOREIGN_KEY_CHECKS = 1;
