/*
 Navicat Premium Data Transfer

 Source Server         : mySQL
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : localhost:3306
 Source Schema         : diymall

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 16/01/2024 12:01:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `aid` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `link_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '联系人',
  `link_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '地址',
  `link_phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '电话',
  `uid` bigint NULL DEFAULT NULL COMMENT '所属用户id',
  PRIMARY KEY (`aid`) USING BTREE,
  INDEX `user`(`uid`) USING BTREE,
  CONSTRAINT `user` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '地址表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, '华子', '中山小区', '13867546787', 3);
INSERT INTO `address` VALUES (2, '小安', '四季新城', '27653456789', 4);
INSERT INTO `address` VALUES (3, '华子', '上海大学', '13867546787', 3);
INSERT INTO `address` VALUES (4, '小李', '东华大学', '18675462567', 5);

-- ----------------------------
-- Table structure for avatar
-- ----------------------------
DROP TABLE IF EXISTS `avatar`;
CREATE TABLE `avatar`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `size` bigint NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of avatar
-- ----------------------------
INSERT INTO `avatar` VALUES (1, 'jpg', 492, '/avatar/978418fbe75243b4ba38da389a468b78.jpg', '1e5802c8b96198fd524cc91ad3f9d476');
INSERT INTO `avatar` VALUES (2, 'jpg', 146, '/avatar/e8663626d17b41bd89707299fcd5ac81.jpg', '5c072037e4e9662831fe448e28795770');
INSERT INTO `avatar` VALUES (3, 'jpg', 175, '/avatar/09cd5add81ff4abfbd1ccf91b2e9c820.jpg', '507704f05fbca53793bce9970b40e6c8');

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel`  (
  `carousel_id` bigint NOT NULL AUTO_INCREMENT COMMENT '轮播id',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '轮播图url',
  `show_order` bigint NOT NULL COMMENT '轮播顺序',
  PRIMARY KEY (`carousel_id`) USING BTREE,
  INDEX `file_url`(`file_url`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES (1, '/file/IMG_2730.JPG', 1);
INSERT INTO `carousel` VALUES (2, '/file/IMG_2731.JPG', 2);

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cid` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint NULL DEFAULT NULL COMMENT '用户id',
  `seller` bigint NULL DEFAULT NULL COMMENT '卖家用户id',
  `gid` bigint NULL DEFAULT NULL COMMENT '商品id',
  `standard` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '商品规格',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `cart_uid`(`uid`) USING BTREE,
  INDEX `cart_gid`(`gid`) USING BTREE,
  CONSTRAINT `cart_gid` FOREIGN KEY (`gid`) REFERENCES `good` (`gid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cart_uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '购物车表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (2, 5, 4, 1, '青色', 1, '2024-01-02 16:31:30');
INSERT INTO `cart` VALUES (7, 3, 4, 1, '红色', 1, '2024-01-10 17:26:23');
INSERT INTO `cart` VALUES (8, 5, 4, 4, '菩提吊坠版', 1, '2024-01-11 00:16:47');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '服装');
INSERT INTO `category` VALUES (2, '饰品');
INSERT INTO `category` VALUES (3, '摆件');

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `fid` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint NULL DEFAULT NULL COMMENT '用户id',
  `gid` bigint NULL DEFAULT NULL COMMENT '商品id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `imgs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`fid`) USING BTREE,
  INDEX `gid`(`gid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`gid`) REFERENCES `good` (`gid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES (1, 2, 2, '樱花流苏发簪', '/files/b.jpg');
INSERT INTO `favorite` VALUES (2, 2, 7, '梅花刺绣斗篷', '/files/e.png');

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good`  (
  `gid` bigint NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `uid` bigint NOT NULL COMMENT '卖家编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
  `category_id` bigint NOT NULL COMMENT '分类编号',
  `imgs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品图片',
  `description` varchar(1660) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `sales` bigint NOT NULL DEFAULT 0 COMMENT '销量',
  `sale_money` double(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '销售额',
  `recommend` tinyint NULL DEFAULT 0 COMMENT '是否推荐',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`gid`) USING BTREE,
  INDEX `seller`(`uid`) USING BTREE,
  INDEX `category`(`category_id`) USING BTREE,
  CONSTRAINT `category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `seller` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES (1, 4, '马面裙', 1, '/files/a.jpg', '一条裙子', '2024-01-03 22:07:44', 13, 1660.00, 1, 0);
INSERT INTO `good` VALUES (2, 4, '樱花流苏发簪', 2, '/files/b.jpg', '自制发簪', '2024-01-01 22:09:03', 5, 400.00, 1, 0);
INSERT INTO `good` VALUES (4, 4, '白水晶手链', 2, '/files/c.jpg', '一条手链', '2024-01-08 14:14:30', 2, 400.00, 0, NULL);
INSERT INTO `good` VALUES (7, 4, '梅花刺绣斗篷', 1, '/files/e.png', '一件斗篷', '2024-01-11 18:57:07', 0, 0.00, 0, 0);

-- ----------------------------
-- Table structure for good_standard
-- ----------------------------
DROP TABLE IF EXISTS `good_standard`;
CREATE TABLE `good_standard`  (
  `gid` bigint NOT NULL COMMENT '商品id',
  `value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '规格',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '价格',
  `store` bigint NULL DEFAULT 0 COMMENT '库存',
  `process_time` bigint NULL DEFAULT NULL COMMENT '工期',
  PRIMARY KEY (`gid`, `value`) USING BTREE,
  CONSTRAINT `uid` FOREIGN KEY (`gid`) REFERENCES `good` (`gid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '商品规格表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of good_standard
-- ----------------------------
INSERT INTO `good_standard` VALUES (1, '红色', 130.00, 10, 14);
INSERT INTO `good_standard` VALUES (1, '青色', 120.00, 7, 14);
INSERT INTO `good_standard` VALUES (2, '无吊坠版', 60.00, 5, 10);
INSERT INTO `good_standard` VALUES (2, '有吊坠版', 80.00, 5, 10);
INSERT INTO `good_standard` VALUES (4, '菩提吊坠版', 200.00, 20, 20);
INSERT INTO `good_standard` VALUES (4, '银杏吊坠版', 80.00, 15, 25);
INSERT INTO `good_standard` VALUES (7, '厚款', 160.00, 10, 13);
INSERT INTO `good_standard` VALUES (7, '薄款', 200.00, 10, 15);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `mid` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sender` bigint NOT NULL COMMENT '发送方用户id',
  `receiver` bigint NOT NULL COMMENT '接收方用户id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '消息内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`mid`) USING BTREE,
  INDEX `sender_uid`(`sender`) USING BTREE,
  INDEX `receiver_uid`(`receiver`) USING BTREE,
  CONSTRAINT `receiver_uid` FOREIGN KEY (`receiver`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sender_uid` FOREIGN KEY (`sender`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 2, 3, 'TestTitle', 'TestContent', '2024-01-12 13:49:52');
INSERT INTO `message` VALUES (2, 3, 2, 'hello', 'world', '2024-01-13 13:50:29');
INSERT INTO `message` VALUES (4, 5, 3, 'test add message', 'hello world!', '2024-01-15 15:53:34');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `nid` bigint NOT NULL AUTO_INCREMENT,
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `show_order` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`nid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '/file/IMG_2732.JPG', 2);
INSERT INTO `news` VALUES (2, '/file/IMG_2733.JPG', 1);
INSERT INTO `news` VALUES (3, '/file/IMG_2734.JPG', 6);
INSERT INTO `news` VALUES (4, '/file/IMG_2735.JPG', 3);
INSERT INTO `news` VALUES (5, '/file/IMG_2736.JPG', 4);
INSERT INTO `news` VALUES (6, '/file/IMG_2737.JPG', 5);
INSERT INTO `news` VALUES (7, '/file/IMG_2738.JPG', 7);
INSERT INTO `news` VALUES (8, 'IMG_2732.JPG', 8);

-- ----------------------------
-- Table structure for order_goods
-- ----------------------------
DROP TABLE IF EXISTS `order_goods`;
CREATE TABLE `order_goods`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint NULL DEFAULT NULL COMMENT '订单id',
  `gid` bigint NULL DEFAULT NULL COMMENT '商品id',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `standard` varchar(1600) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '规格',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ono`(`gid`) USING BTREE,
  INDEX `order_no`(`order_id`) USING BTREE,
  CONSTRAINT `ono` FOREIGN KEY (`gid`) REFERENCES `good` (`gid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_goods
-- ----------------------------
INSERT INTO `order_goods` VALUES (29, 34, 1, 3, '青色');
INSERT INTO `order_goods` VALUES (30, 36, 4, 1, '菩提吊坠版');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint NULL DEFAULT NULL COMMENT '大小',
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '文件路径',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  `enable` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用',
  `md5` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT 'md5值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '系统文件表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (7, '07.jpg', 'jpg', 1814, '/file/7dfd10628edc4b4e97de19c1cb86585e.jpg', 0, 0, '04271616ebc6914643c3af592dd58bef');
INSERT INTO `sys_file` VALUES (8, '9a49edb823cc4cb799cb3438a7419a83.jpg', 'jpg', 132, '/file/2b6249b9ba24491a9048c1e8c0b5256e.jpg', 0, 0, 'f11ed5acc29b90770a453eceb7524712');
INSERT INTO `sys_file` VALUES (9, '9a49edb823cc4cb799cb3438a7419a83.jpg', 'jpg', 132, '/file/2b6249b9ba24491a9048c1e8c0b5256e.jpg', 0, 0, 'f11ed5acc29b90770a453eceb7524712');
INSERT INTO `sys_file` VALUES (10, '9a49edb823cc4cb799cb3438a7419a83.jpg', 'jpg', 132, '/file/2b6249b9ba24491a9048c1e8c0b5256e.jpg', 0, 0, 'f11ed5acc29b90770a453eceb7524712');
INSERT INTO `sys_file` VALUES (11, '9a49edb823cc4cb799cb3438a7419a83.jpg', 'jpg', 132, '/file/2b6249b9ba24491a9048c1e8c0b5256e.jpg', 0, 0, 'f11ed5acc29b90770a453eceb7524712');
INSERT INTO `sys_file` VALUES (12, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 0, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (13, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 0, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (14, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 0, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (15, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 0, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (16, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 0, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (17, '5a776cc21c1b407bbd2595a7af726a61.jpg', 'jpg', 846, '/file/0e8132c00dc6484faa18b2d1487b34ec.jpg', 0, 0, '8f0a34a66bbc1a794b7c138897a66dad');
INSERT INTO `sys_file` VALUES (18, '02.jpg', 'jpg', 33, '/file/84ad8a9829424254811ce2220edc2d3b.jpg', 0, 0, 'fcf09e93c497c75cf2b3656f80f997cc');
INSERT INTO `sys_file` VALUES (19, '01.jpg', 'jpg', 26, '/file/cef757d124ec4b169cffd65de5e3c47c.jpg', 0, 0, 'd5b6bb3b068c1980d77c59079248a4a4');
INSERT INTO `sys_file` VALUES (20, '03.jpg', 'jpg', 22, '/file/7791be8ea1ee4aa0a149ae8e75c857d6.jpg', 0, 0, '75e8b3e8790e514fb799857f636a1623');
INSERT INTO `sys_file` VALUES (21, '04.jpg', 'jpg', 25, '/file/867aaf026b684b1e8b1a10c87e31df7e.jpg', 0, 0, '1d7397d5a4ce0995f711a1484d593f44');
INSERT INTO `sys_file` VALUES (22, '05.jpg', 'jpg', 19, '/file/f9f26a01e13d4ba68d7f7bd12df282e0.jpg', 0, 0, 'b2d243af2652abf08a491074c8f099ea');
INSERT INTO `sys_file` VALUES (23, '06.jpg', 'jpg', 11, '/file/286aa7816325455b8cdcd522aca833fe.jpg', 0, 0, 'c160a645c3dacb58ffb123a4239dcb50');
INSERT INTO `sys_file` VALUES (24, '037c5b1f3e40406893b423563c557a91.jpg', 'jpg', 1641, '/file/09bb6edab07a4c68a44cce41a3300d97.jpg', 0, 0, '067143803d2f87dcb939de5d4ace2bbb');
INSERT INTO `sys_file` VALUES (25, '01.jpg', 'jpg', 329, '/file/15cb9fc604984dfa97e0e968eb1d196d.jpg', 0, 0, '8c78b307ff66fbc7db624da25138f480');
INSERT INTO `sys_file` VALUES (26, '02.jpg', 'jpg', 738, '/file/e2cf8486c2384b8296972a550bf7e934.jpg', 0, 0, '7db1f7335529ad2a68367d29d0441695');
INSERT INTO `sys_file` VALUES (27, '04.jpg', 'jpg', 158, '/file/0afa4eb1c51943808f6e83cd9ced25e8.jpg', 0, 0, '0bfaaafc7ca1a9a5478baa8c9cae492c');
INSERT INTO `sys_file` VALUES (28, '05 (1).jpg', 'jpg', 773, '/file/8dc5354c7332454796c614bb4a0572fb.jpg', 0, 0, '925882b34e70434888ee7ca373bae52c');
INSERT INTO `sys_file` VALUES (29, '03 (2).jpg', 'jpg', 208, '/file/b4ac53ed62c74c298366619399c39f99.jpg', 0, 0, '1468738643a2f6dbd5fad1f7c80cdb00');
INSERT INTO `sys_file` VALUES (30, '06.jpg', 'jpg', 3494, '/file/be9d2d6a17c5436fb0b8c2f7927484b2.jpg', 0, 0, 'd9950e2a7400a3d26ebde81c47e92e04');
INSERT INTO `sys_file` VALUES (31, '02.png', 'png', 5898, '/file/74488020672944968462e9e4a9c89096.png', 0, 0, 'ad801047fc9918bd626656d08d696898');
INSERT INTO `sys_file` VALUES (32, '02.png', 'png', 71, '/file/2e2a1df657324a3293642344327310cb.png', 0, 0, '2c3a088b474cb2144645411f2e3da9c6');
INSERT INTO `sys_file` VALUES (33, '03.jpg', 'jpg', 31, '/file/449ab0163ba648c08cb4a76b40a9dcec.jpg', 0, 0, 'fa95b8365bc4ec2096f4dc3f31dc8e27');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '订单号',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `seller` bigint NULL DEFAULT NULL COMMENT '卖家id',
  `uid` bigint NULL DEFAULT NULL COMMENT '用户id',
  `link_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '联系电话',
  `link_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '地址',
  `state` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '订单状态',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `deadline` datetime NULL DEFAULT NULL COMMENT '按工期计算的截止时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_order`(`uid`) USING BTREE,
  INDEX `order_no`(`order_no`) USING BTREE,
  CONSTRAINT `u_order` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (34, '20240113221326347086', 360.00, 4, 3, '华子', '13867546787', '上海大学', '已完成', '2024-01-13 22:13:26', '2024-01-27 22:13:27');
INSERT INTO `t_order` VALUES (36, '20240114002252674673', 200.00, 4, 5, '小李', '18675462567', '东华大学', '已完成', '2024-01-14 00:22:52', '2024-01-24 00:22:52');
INSERT INTO `t_order` VALUES (37, 'testorder', 100.00, 4, 3, '华子', '13867546787', '上海大学', '已超时', '2023-12-31 16:56:03', '2024-01-14 18:26:00');
INSERT INTO `t_order` VALUES (38, 'testtime2', 100.00, 4, 3, '华子', '13867546787', '上海大学', '已超时', '2024-01-14 17:50:07', '2024-01-14 18:27:11');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `uid` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '/avatar/a.png' COMMENT '头像',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (2, 'user', '/avatar/h09cd5add81ff4abfbd1ccf91b2e9c820.jpg', '123456', 'user');
INSERT INTO `users` VALUES (3, 'water', '/avatar/09cd5add81ff4abfbd1ccf91b2e9c820.jpg', '123456', 'admin');
INSERT INTO `users` VALUES (4, 'Alina', '/avatar/09cd5add81ff4abfbd1ccf91b2e9c820.jpg', '123456', 'user');
INSERT INTO `users` VALUES (5, 'zhaoa1111', '/avatar/09cd5add81ff4abfbd1ccf91b2e9c820.jpg', '123456', 'user');

SET FOREIGN_KEY_CHECKS = 1;
