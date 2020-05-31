/*
Navicat MySQL Data Transfer

Source Server         : 我的清单
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : our-task

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-05-31 19:13:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int DEFAULT '1',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_run` tinyint unsigned DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('3', '1', '清单3', '清单3的描述', '2020-05-03 22:36:37', null, '0');
INSERT INTO `task` VALUES ('4', '2', '清单4', '清单4的描述', '2020-05-03 22:36:53', null, '0');
INSERT INTO `task` VALUES ('6', '2', '吃吃', '吃吃toutou', '2020-05-04 10:35:19', '2020-05-04 19:19:37', '0');
INSERT INTO `task` VALUES ('32', '1', '学猫叫', '喵喵喵', '2020-05-31 19:11:05', null, '1');
INSERT INTO `task` VALUES ('33', '1', '学狗叫', '汪汪汪', '2020-05-31 19:11:14', null, '1');
INSERT INTO `task` VALUES ('34', '1', '学猪叫', '呃呃呃', '2020-05-31 19:11:24', null, '1');
INSERT INTO `task` VALUES ('35', '2', '吃饭', '吃两碗', '2020-05-31 19:11:32', null, '1');
INSERT INTO `task` VALUES ('36', '2', '早起', '6点钟起床', '2020-05-31 19:11:39', null, '1');
INSERT INTO `task` VALUES ('37', '2', '洗澡', '10分钟', '2020-05-31 19:11:50', null, '1');
INSERT INTO `task` VALUES ('38', '21', '早睡', '', '2020-05-31 19:12:38', null, '1');
INSERT INTO `task` VALUES ('39', '21', '早起', '', '2020-05-31 19:12:42', null, '1');
INSERT INTO `task` VALUES ('40', '21', '打扫卫生', '', '2020-05-31 19:12:46', null, '1');
