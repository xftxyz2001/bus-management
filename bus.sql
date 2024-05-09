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

 Date: 09/05/2024 12:40:02
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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busline
-- ----------------------------
INSERT INTO `busline` VALUES (1, '西安', '金滹沱公交调度站', '电视塔', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '1', '6:00-20:30', '1');
INSERT INTO `busline` VALUES (2, '西安', '科技四路公交调度站', '唐延路科技路口', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '2', '6:00-22:00', '1');
INSERT INTO `busline` VALUES (3, '西安', '西三环石桥路口', '唐延路科技路口', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '3', '6:00-22:00', '2');
INSERT INTO `busline` VALUES (4, '西安', '安邸立交公交调度站', '长乐公园西门', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '4', '6:00-20:30', '3');
INSERT INTO `busline` VALUES (5, '西安', '华远路米秦北路口', '东门外', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '5', '6:00-20:30', '2');
INSERT INTO `busline` VALUES (6, '西安', '团结西路公交调度站', '西三环锦业一路口', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '6', '6:00-21:00', '3');
INSERT INTO `busline` VALUES (7, '西安', '白沙路公交调度站', '毕原三路上林苑七路口', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '7', '6:30-19:30', '4');
INSERT INTO `busline` VALUES (8, '西安', '纺园五路公交调度站', '灞桥停车场', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '8', '6:00-21:30', '2');
INSERT INTO `busline` VALUES (9, '西安', '梨园路和生国际', '火车站西', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '9', '6:00-20:30', '5');
INSERT INTO `busline` VALUES (10, '西安', '鱼化一路科技五路口', '丈八东路东口', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '10', '6:00-20:30', '2');
INSERT INTO `busline` VALUES (11, '西安', '安邸立交公交调度站', '浐河', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '11', '6:00-20:30', '5');
INSERT INTO `busline` VALUES (12, '西安', '马腾空', '经九路西影路口', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '12', '6:00-20:30', '1');
INSERT INTO `busline` VALUES (13, '西安', '昆明池东', '王寺地铁站', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '13', '6:30-20:00', '2');
INSERT INTO `busline` VALUES (14, '西安', '西北大学长安校区', '南门外', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '14', '6:00-20:30', '4');
INSERT INTO `busline` VALUES (15, '西安', '西北大学长安校区', '南门外', '石家街-东二环米秦北路口-东二环矿山路口-太元路东段-太元路御井路口-太元路太和路口-太元路西段-太元路马旗寨路口-太华路太元路口-含元殿地铁站-大明宫地铁站-大华1935-太华路自强路口-火车站北-建强路自强路口-北关-铁路职业技术学院-自强小区-纸坊村-陕西交通职业技术学院-丰禾路东口-星火立交南-玉祥门-潘家村-大庆路劳动路口-大庆路桃园路口-桃园路团结东路口-丰镐路桃园路口-桃园路锦园小区-南二环桃园路口-西桃园-新桃园-西安锦都-特警支队', '616', '6:00-20:30', '2');

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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 23, 0, '12345678910', '21232f297a57a5a743894a0e4a801fc3', NULL, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ4LXVzZXItaWQiOjEsImV4cCI6MTcxNTIzMTIyOX0.FIHVA9Pn3-90ZGP6MJbhAqKTpXkEpl4k7NwF9BCGnR0', 0000000001);
INSERT INTO `user` VALUES (22, '段朝辉', 3, 1, '18292598690', '847584979b54acc1f221c225a5dbc7d3', '1650604245@qq.com', '0', 0000000000);

SET FOREIGN_KEY_CHECKS = 1;
