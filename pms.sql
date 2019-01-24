/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : pms

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 24/01/2019 09:28:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_admin
-- ----------------------------
DROP TABLE IF EXISTS `pms_admin`;
CREATE TABLE `pms_admin` (
  `adminName` varchar(20) DEFAULT NULL,
  `adminSex` varchar(20) DEFAULT NULL,
  `adminId` bigint(20) NOT NULL AUTO_INCREMENT,
  `adminTime` date DEFAULT NULL,
  `adminPassword` varchar(20) DEFAULT NULL,
  `type` int(5) DEFAULT NULL,
  `adminPhone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pms_admin
-- ----------------------------
BEGIN;
INSERT INTO `pms_admin` VALUES ('admin', '男', 1, '2019-01-08', '123', 1, '15642652552');
INSERT INTO `pms_admin` VALUES ('liming', '男', 2, '2019-01-09', '123', 1, '13354236545');
INSERT INTO `pms_admin` VALUES ('小红', '女', 3, '2019-01-01', '123', 1, '13697552152');
INSERT INTO `pms_admin` VALUES ('小芳', '女', 4, '2019-01-03', '123', 1, '13745628466');
INSERT INTO `pms_admin` VALUES ('胖虎', '男', 5, '2019-02-04', '123', 1, '13456252652');
INSERT INTO `pms_admin` VALUES ('胖虎', '男', 33, '2019-01-23', '123', 1, '17788887777');
COMMIT;

-- ----------------------------
-- Table structure for pms_complaint
-- ----------------------------
DROP TABLE IF EXISTS `pms_complaint`;
CREATE TABLE `pms_complaint` (
  `userId` bigint(20) DEFAULT NULL,
  `adminId` bigint(20) DEFAULT NULL,
  `subDate` date DEFAULT NULL,
  `solDate` date DEFAULT NULL,
  `reason` varchar(100) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `3` (`userId`),
  KEY `4` (`adminId`),
  CONSTRAINT `3` FOREIGN KEY (`userId`) REFERENCES `pms_user` (`userid`),
  CONSTRAINT `4` FOREIGN KEY (`adminId`) REFERENCES `pms_admin` (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pms_complaint
-- ----------------------------
BEGIN;
INSERT INTO `pms_complaint` VALUES (1, 1, '2019-01-21', '2019-01-22', '啊是大', 1);
INSERT INTO `pms_complaint` VALUES (1, 1, '2019-01-22', '2019-01-23', '修门态度极其恶劣', 2);
COMMIT;

-- ----------------------------
-- Table structure for pms_houseproperty
-- ----------------------------
DROP TABLE IF EXISTS `pms_houseproperty`;
CREATE TABLE `pms_houseproperty` (
  `resName` varchar(20) DEFAULT NULL,
  `resId` bigint(20) NOT NULL,
  PRIMARY KEY (`resId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pms_houseproperty
-- ----------------------------
BEGIN;
INSERT INTO `pms_houseproperty` VALUES ('卧室窗', 1);
INSERT INTO `pms_houseproperty` VALUES ('卧室门', 2);
INSERT INTO `pms_houseproperty` VALUES ('客厅门', 3);
INSERT INTO `pms_houseproperty` VALUES ('客厅窗', 4);
COMMIT;

-- ----------------------------
-- Table structure for pms_repair
-- ----------------------------
DROP TABLE IF EXISTS `pms_repair`;
CREATE TABLE `pms_repair` (
  `userId` bigint(20) DEFAULT NULL,
  `resId` bigint(20) DEFAULT NULL,
  `subDate` date DEFAULT NULL,
  `solDate` date DEFAULT NULL,
  `reason` varchar(100) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `1` (`userId`),
  KEY `2` (`resId`),
  CONSTRAINT `1` FOREIGN KEY (`userId`) REFERENCES `pms_user` (`userid`),
  CONSTRAINT `2` FOREIGN KEY (`resId`) REFERENCES `pms_houseproperty` (`resId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pms_repair
-- ----------------------------
BEGIN;
INSERT INTO `pms_repair` VALUES (1, 2, '2019-01-23', '2019-01-23', '门烂了', 1);
INSERT INTO `pms_repair` VALUES (1, 2, '2019-01-23', '2019-01-23', '门坏了，速度来修', 2);
INSERT INTO `pms_repair` VALUES (2, 4, '2019-01-24', NULL, '客厅窗子漏风', 3);
COMMIT;

-- ----------------------------
-- Table structure for pms_user
-- ----------------------------
DROP TABLE IF EXISTS `pms_user`;
CREATE TABLE `pms_user` (
  `userName` varchar(20) DEFAULT NULL,
  `userSex` varchar(20) DEFAULT NULL,
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userTime` date DEFAULT NULL,
  `userHouseArea` varchar(10) DEFAULT NULL,
  `userPassword` varchar(20) DEFAULT NULL,
  `type` int(5) DEFAULT NULL,
  `userPhone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=10088 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pms_user
-- ----------------------------
BEGIN;
INSERT INTO `pms_user` VALUES ('张三', '男', 1, '2019-01-20', '100', '123', 2, '13958653564');
INSERT INTO `pms_user` VALUES ('李四', '女', 2, '2019-01-12', '200', '456', 2, '15956258625');
INSERT INTO `pms_user` VALUES ('王五', '男', 3, '2019-01-01', '120', '789', 2, '13355696664');
INSERT INTO `pms_user` VALUES ('小夫', '男', 10087, '2019-01-02', '123', '123', 2, '12312312313');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
