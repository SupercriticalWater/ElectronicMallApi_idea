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

 Date: 04/01/2024 18:10:25
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

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cid` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '加入时间',
  `gid` bigint NULL DEFAULT NULL COMMENT '商品id',
  `standard` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `uid` bigint NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `cart_uid`(`uid`) USING BTREE,
  INDEX `cart_gid`(`gid`) USING BTREE,
  CONSTRAINT `cart_gid` FOREIGN KEY (`gid`) REFERENCES `good` (`gid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cart_uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '购物车表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------

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
  `recommended` tinyint NULL DEFAULT NULL COMMENT '是否推荐',
  `is_deleted` tinyint NULL DEFAULT NULL COMMENT '是否删除',
  `description` varchar(1660) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
  `sales` bigint NOT NULL DEFAULT 0 COMMENT '销量',
  `sale_money` double(10, 2) NULL DEFAULT NULL COMMENT '销售额',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`gid`) USING BTREE,
  INDEX `seller`(`uid`) USING BTREE,
  INDEX `category`(`category_id`) USING BTREE,
  CONSTRAINT `category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `seller` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good
-- ----------------------------

-- ----------------------------
-- Table structure for good_standard
-- ----------------------------
DROP TABLE IF EXISTS `good_standard`;
CREATE TABLE `good_standard`  (
  `gid` bigint NOT NULL COMMENT '商品id',
  `value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '规格',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `store` bigint NULL DEFAULT NULL COMMENT '库存',
  `processTime` bigint NULL DEFAULT NULL COMMENT '工期',
  PRIMARY KEY (`gid`, `value`) USING BTREE,
  CONSTRAINT `uid` FOREIGN KEY (`gid`) REFERENCES `good` (`gid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '商品规格表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of good_standard
-- ----------------------------

-- ----------------------------
-- Table structure for order_goods
-- ----------------------------
DROP TABLE IF EXISTS `order_goods`;
CREATE TABLE `order_goods`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` bigint NULL DEFAULT NULL COMMENT '订单id',
  `gid` bigint NULL DEFAULT NULL COMMENT '商品id',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `standard` varchar(1600) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '规格',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ono`(`gid`) USING BTREE,
  INDEX `order_no`(`order_no`) USING BTREE,
  CONSTRAINT `ono` FOREIGN KEY (`gid`) REFERENCES `good` (`gid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_goods
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '订单号',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价',
  `uid` bigint NULL DEFAULT NULL COMMENT '用户id',
  `link_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '联系电话',
  `link_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '地址',
  `state` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '订单状态',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_order`(`uid`) USING BTREE,
  CONSTRAINT `u_order` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `uid` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (2, 'user', '/resources/avatar/09cd5add81ff4abfbd1ccf91b2e9c820.jpg', '123456', 'user');
INSERT INTO `users` VALUES (3, 'water', '/resources/avatar/09cd5add81ff4abfbd1ccf91b2e9c820.jpg', '123456', 'admin');
INSERT INTO `users` VALUES (4, 'Alina', '/test', '123456', 'user');
INSERT INTO `users` VALUES (5, 'zhaoaa', NULL, '123456', 'user');

SET FOREIGN_KEY_CHECKS = 1;
