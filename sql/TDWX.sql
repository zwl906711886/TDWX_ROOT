/*
 Navicat Premium Data Transfer

 Source Server         : zwl
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : TDWX

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 31/12/2022 21:12:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (1, '123', '<p>123123123</p>', 'zwl', '2022-12-31 21:04:17');

-- ----------------------------
-- Table structure for batch
-- ----------------------------
DROP TABLE IF EXISTS `batch`;
CREATE TABLE `batch`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_time` datetime NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  `cur_state` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `batch_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of batch
-- ----------------------------
INSERT INTO `batch` VALUES (1, '2022-11-30 00:00:00', '2022-12-08 00:00:00', '已开放', '1');
INSERT INTO `batch` VALUES (2, '2022-12-07 00:00:00', '2022-12-06 00:00:00', '未开放', '2');

-- ----------------------------
-- Table structure for dept_major
-- ----------------------------
DROP TABLE IF EXISTS `dept_major`;
CREATE TABLE `dept_major`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `department` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept_major
-- ----------------------------
INSERT INTO `dept_major` VALUES (1, '123', 'zzz1111123');
INSERT INTO `dept_major` VALUES (2, '123', 'xxxx');
INSERT INTO `dept_major` VALUES (3, '123', '3333');

-- ----------------------------
-- Table structure for enroll
-- ----------------------------
DROP TABLE IF EXISTS `enroll`;
CREATE TABLE `enroll`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `school` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `degree` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `batch` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `submit_state` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enroll
-- ----------------------------

-- ----------------------------
-- Table structure for field
-- ----------------------------
DROP TABLE IF EXISTS `field`;
CREATE TABLE `field`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `field_name` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `field_state` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of field
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `parent_id` int NULL DEFAULT NULL,
  `foreign_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '123', 'zwl', '2022-12-31 21:11:07', NULL, 1);
INSERT INTO `message` VALUES (2, '123', 'zwl', '2022-12-31 21:11:11', 1, 1);

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '123', '<p>123123123</p>', 'zwl', NULL);
INSERT INTO `news` VALUES (2, '123', '<p>dsdfsdf<img src=\"http://localhost:9090/files/8fc30311852d4e60aa4506428e5c7960\" contenteditable=\"false\" style=\"color: var(--el-text-color-regular); font-size: var(--el-dialog-content-font-size); max-width: 100%;\"/></p>', 'zwl', NULL);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `click_num` int NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `owner` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, '123', 2, NULL, '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = ascii COLLATE = ascii_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zwl', '123', 'zwl', '1', '11', 1, 'zzz');

SET FOREIGN_KEY_CHECKS = 1;
