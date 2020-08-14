/*
 Navicat Premium Data Transfer

 Source Server         : 我的学习
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : our-task

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 13/08/2020 18:59:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '清单id',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '清单名称',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `run` tinyint(0) NULL DEFAULT 1 COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 1, '今天', '2020-05-03 20:13:39', '2020-07-29 15:01:00', 1);
INSERT INTO `category` VALUES (2, 1, '明天', '2020-05-03 20:13:46', '2020-07-24 15:26:21', 1);
INSERT INTO `category` VALUES (21, 1, '后天', '2020-05-30 18:32:15', '2020-07-29 15:01:01', 1);
INSERT INTO `category` VALUES (41, 1, '大后天', '2020-07-24 15:28:25', '2020-07-29 15:01:08', 1);

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `run` tinyint(1) NULL DEFAULT 1 COMMENT '是否还在使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label
-- ----------------------------

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `category_id` int(0) NULL DEFAULT 1 COMMENT '所属清单id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `run` tinyint(0) UNSIGNED NULL DEFAULT 1 COMMENT '是否完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (4, 1, 1, '清单', '清单4的描述', '2020-05-03 22:36:53', '2020-08-13 18:58:58', 0);
INSERT INTO `task` VALUES (6, 1, 1, '吃吃', '吃吃toutou', '2020-05-04 10:35:19', '2020-08-04 15:43:22', 0);
INSERT INTO `task` VALUES (32, 1, 1, '学猫叫', '喵喵喵', '2020-05-31 19:11:05', '2020-08-04 16:53:46', 0);
INSERT INTO `task` VALUES (33, 1, 1, '学狗叫', '汪汪汪', '2020-05-31 19:11:14', '2020-08-07 20:29:25', 0);
INSERT INTO `task` VALUES (34, 1, 1, '学猪叫', '呃呃呃', '2020-05-31 19:11:24', '2020-08-04 14:04:51', 1);
INSERT INTO `task` VALUES (35, 1, 2, '吃饭', '吃两碗', '2020-05-31 19:11:32', '2020-08-04 14:04:51', 1);
INSERT INTO `task` VALUES (36, 1, 2, '早起', '6点钟起床', '2020-05-31 19:11:39', '2020-08-04 14:04:51', 1);
INSERT INTO `task` VALUES (37, 1, 2, '洗澡', '10分钟', '2020-05-31 19:11:50', '2020-08-04 14:04:51', 1);

-- ----------------------------
-- Table structure for task_label
-- ----------------------------
DROP TABLE IF EXISTS `task_label`;
CREATE TABLE `task_label`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_id` int(0) NOT NULL COMMENT '清单id',
  `label_id` int(0) NULL DEFAULT NULL COMMENT '标签id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_label
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `head_portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `roles` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '权限',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '是否被冻结',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user', '15484596587', '$2a$10$sWcogzyDN43YUx38iUdADe9U4skcAtLcNQb93FmI3wg5xiom6V.P6', '2020-06-06 15:36:54', '2020-07-22 09:41:21', '541269@qq.ocm', NULL, 'ROLE_USER', 1);
INSERT INTO `user` VALUES (2, 'admin', '12569854865', '$2a$10$sWcogzyDN43YUx38iUdADe9U4skcAtLcNQb93FmI3wg5xiom6V.P6', '2020-06-06 15:37:09', '2020-07-22 09:41:25', '98714649544@qq.com', NULL, 'ROLE_USER', 1);

SET FOREIGN_KEY_CHECKS = 1;
