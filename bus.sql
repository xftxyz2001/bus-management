/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80200 (8.2.0)
 Source Host           : localhost:3306
 Source Schema         : bus

 Target Server Type    : MySQL
 Target Server Version : 80200 (8.2.0)
 File Encoding         : 65001

 Date: 09/05/2024 16:31:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for board
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of board
-- ----------------------------
INSERT INTO `board` VALUES (1, '通知', '今日因大风天气，部分线路停止运营', '2024-04-19 17:33:06', '2024-05-09 12:37:23');

-- ----------------------------
-- Table structure for busline
-- ----------------------------
DROP TABLE IF EXISTS `busline`;
CREATE TABLE `busline`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `startstation` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '始发站',
  `endstation` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '终点站',
  `routesite` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '途径站点',
  `busid` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公交车号',
  `runtime` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '运行时间',
  `price` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参考票价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busline
-- ----------------------------
INSERT INTO `busline` VALUES (1, '西安', '金滹沱公交调度站', '电视塔', '金滹沱-金滹沱二路中段-金滹沱二路东口-芙蓉西路南三环桥北-芙蓉西路曲江池南路口-芙蓉西路雁南四路口-雁鸣小区-雁塔南路雁南四路口-瓦胡同村-汇新路北口-电视塔北-电视塔南', '1', '6:00-20:30', '2');
INSERT INTO `busline` VALUES (2, '西安', '科技四路公交调度站', '唐延路科技路口', '科技四路公交调度站-鱼化三路科技四路口-鱼化一路科技四路口-鱼化一路科技二路口-丁家桥-高新六路科技二路口-西安软件园-高新医院-科技路团结南路口-唐延路科技路口', '2', '6:00-22:00', '1');
INSERT INTO `busline` VALUES (3, '西安', '西三环石桥路口', '唐延路科技路口', '西三环石桥路口-西三环大寨路口-大寨路华洲城-大寨路铭城-大寨路宏府麒麟山-大寨路昆明池路口-丈八北路大寨路口-枫林华府-唐延路大寨路口-特警支队-唐延路科技路口', '3', '6:00-22:00', '2');
INSERT INTO `busline` VALUES (4, '西安', '安邸立交公交调度站', '长乐公园西门', '安邸立交公交调度站-浐灞商务中心-浐灞大道金茂一路口-金桥六路金茂一路南段-金桥六路金茂七路口-金桥六路金茂十路口-浐灞半岛-广大门村-新房村-广运潭大道广安路口-广运潭大道玄武东路口-广运潭大道矿山路口-东方罗马家园-矿山路北辰东路口-刘南村-东二环矿山路口-东二环米秦北路口-石家街-新兴骏景园-胡家庙-东二环信和路口-东二环金康路口-长乐公园西门', '4', '6:00-20:30', '3');
INSERT INTO `busline` VALUES (5, '西安', '华远路米秦北路口', '东门外', '远路米秦北路口,公田五路春临五路口-公田五路(曲江电竞产业园)-春临三路公田五路口-公田五路春临二路口-曲江池南路新开门南路口-曲江寒窑遗址公园-芙蓉东路寒窑路口-芙蓉东路曲江池北路口-新开门-岳家寨南-岳家寨-北池头南-北池头-西安乐游原-祭台村-友谊路太乙路口-太乙路建东街口-东南城角-东门外', '5', '6:00-20:30', '2');
INSERT INTO `busline` VALUES (6, '西安', '团结西路公交调度站', '西三环锦业一路口', '团结西路公交调度站-汉城路团结西路口-二十四街坊-新华印刷厂-丰镐路团结南路口-昆明路团结南路口-枫林华府-响塘寨-科技路团结南路口-高新医院-西安软件园-团结南路科技四路口-西工大软件园-省交通规划设计研究院-茶张村-丈八西路丈八一路口-锦业路丈八二路口-锦业一路丈八二路口-锦业一路丈八三路口-锦业一路丈八四路口-锦业一路丈八五路口-锦业一路丈八六路口-锦业一路西三环口', '6', '6:00-21:00', '3');
INSERT INTO `busline` VALUES (7, '西安', '白沙路公交调度站', '毕原三路上林苑七路口', '白沙路公交调度站-木塔寺东路西口-文理学院-科技四路东口-枫林绿洲-科技四路高新一路口-南窑头东区-团结南路科技四路口-高新六路科技四路口-南窑头-鱼化一路科技四路口-鱼化三路科技四路口-科技四路公交调度站', '7', '6:30-19:30', '4');
INSERT INTO `busline` VALUES (8, '西安', '纺园五路公交调度站', '灞桥停车场', '纺园五路公交调度站-洪庆新苑-灞柳二路纺园六路口-灞柳一路南段-灞柳一路纺园五路口-灞柳一路纺园四路口-灞柳一路纺园三路口-灞柳一路纺园二路口-灞柳一路祥云路口-田王村-田王-庆华新区-铸管厂-洪庆街向阳北路口-洪庆-赵西村-石桥-豁口-灞桥建材厂-灞桥-灞桥停车场', '8', '6:00-21:30', '2');
INSERT INTO `busline` VALUES (9, '西安', '梨园路和生国际', '火车站西', '梨园路和生国际-梨园路桃园北路口-梨园路御园小区-梨园路大白杨路口-梨园路永全路口-朱宏路龙首北路口-红庙坡-红庙坡路永兴路口-红庙坡路工农路口-文景路红庙坡路口-铁路职业技术学院-北关-北门外-西闸口南口-火车站西', '9', '6:00-20:30', '5');
INSERT INTO `busline` VALUES (10, '西安', '鱼化一路科技五路口', '丈八东路东口', '鱼化一路科技五路口-鱼化一路科技六路口-铺尚村-丈八四路科技八路口-丈八东路科技八路口-唐延路科技八路口-高新路科技八路口-木塔寨-雁南二路西段-电子西街205所-北山门-东仪路雁南二路口-含光路雁南二路口-杨家村-明德门遗址公园-吴家坟-丈八东路东口', '10', '6:00-20:30', '2');

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver`  (
  `DriverID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Age` int NULL DEFAULT NULL,
  `Gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `LicenseNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `WorkLocation` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`DriverID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES (1, '张三', 35, '女', '123456789', '13812345678', '1路公交车');
INSERT INTO `driver` VALUES (2, '李四', 28, '男', '987654321', '13987654321', '2路公交车');
INSERT INTO `driver` VALUES (3, '王五', 40, '男', '654321789', '13665432178', '3路公交车');
INSERT INTO `driver` VALUES (4, '赵六', 33, '女', '456789123', '13745678912', '4路公交车');
INSERT INTO `driver` VALUES (5, '钱七', 29, '女', '789123456', '13878912345', '5路公交车');
INSERT INTO `driver` VALUES (6, '孙八', 38, '男', '321654987', '13732165498', '6路公交车');
INSERT INTO `driver` VALUES (7, '周九', 42, '男', '987654321', '13698765432', '7路公交车');
INSERT INTO `driver` VALUES (8, '吴十', 31, '女', '654987321', '13965498732', '8路公交车');
INSERT INTO `driver` VALUES (9, '郑十一', 36, '女', '123789456', '13812378945', '9路公交车');

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint NOT NULL COMMENT '留言人id',
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '留言内容',
  `reply` bigint NULL DEFAULT NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messages
-- ----------------------------
INSERT INTO `messages` VALUES (2, '2024-04-22 12:46:09', 9, '刘亚峰', '刘亚峰最帅！！！！！', NULL);
INSERT INTO `messages` VALUES (3, '2024-04-22 14:55:33', 9, '刘亚峰', '刘亚峰最帅！！！！！', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '性别, 1:男,2::女',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `identify` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 23, 0, '12345678910', '21232f297a57a5a743894a0e4a801fc3', NULL, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ4LXVzZXItaWQiOjEsImV4cCI6MTcxNTI0MzU0OH0.9md1tfTuDUcO0BzJcKITfSiVuZ1-KhWh2sRR2bihj6s', 0000000001);
INSERT INTO `user` VALUES (22, '段朝辉', 3, 1, '18292598690', '4fc4cf39cd9443bb6ad69496d71a059a', '1650604245@qq.com', '0', 0000000000);

SET FOREIGN_KEY_CHECKS = 1;
