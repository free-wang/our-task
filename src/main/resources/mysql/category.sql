/*
Navicat MySQL Data Transfer

Source Server         : 我的清单
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : our-task

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-05-31 19:13:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '今天', '2020-05-03 20:13:39', null);
INSERT INTO `category` VALUES ('2', '明天', '2020-05-03 20:13:46', null);
INSERT INTO `category` VALUES ('21', '大后天', '2020-05-30 18:32:15', null);
