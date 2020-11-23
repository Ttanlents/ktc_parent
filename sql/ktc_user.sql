/*
 Navicat Premium Data Transfer

 Source Server         : KTC
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 192.168.12.133:3306
 Source Schema         : ktc_user

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 25/08/2019 13:31:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `loginname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆名称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('1158601886178336768', 'xiaobiao', '$2a$10$lrKPmPNxDdoxE8FO9Sojau63PJNK.QWEb2jAWdyOkAWFo55deE5KO', '1');
INSERT INTO `tb_admin` VALUES ('1158601964192391168', 'xiaozhun', '$2a$10$99X1/bum.QKo31AUVfhMneiRsKdssW7FKEUupFHsugAnrJXzG/kqa', '1');
INSERT INTO `tb_admin` VALUES ('1158601984551542784', 'dfbz', '$2a$10$llhU2Awfv96oKEM6t9NSFeGftLfKR0qD1vqIicPzDDNvX0W1aP/U6', '1');

-- ----------------------------
-- Table structure for tb_follow
-- ----------------------------
DROP TABLE IF EXISTS `tb_follow`;
CREATE TABLE `tb_follow`  (
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `targetuser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被关注用户ID',
  PRIMARY KEY (`userid`, `targetuser`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_follow
-- ----------------------------
INSERT INTO `tb_follow` VALUES ('1', '1');
INSERT INTO `tb_follow` VALUES ('1', '10');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '出生年月日',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'E-Mail',
  `regdate` datetime(0) NULL DEFAULT NULL COMMENT '注册日期',
  `updatedate` datetime(0) NULL DEFAULT NULL COMMENT '修改日期',
  `lastdate` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆日期',
  `online` bigint(20) NULL DEFAULT NULL COMMENT '在线时长（分钟）',
  `interest` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '兴趣',
  `personality` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性',
  `fanscount` int(20) NULL DEFAULT NULL COMMENT '粉丝数',
  `followcount` int(20) NULL DEFAULT NULL COMMENT '关注数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '110', '$2a$10$o.PkSyPgCqsbnfJP5mCOvOstq/bo7G5S.TYsjbYCqUZR/IryvEGze', 'xiaodong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO `tb_user` VALUES ('2', '112', '$2a$10$eMAysJI.T6UiFdU32DseMeM9GV32aomjxpv9lQzcrSlgcidiEeUie', 'xiaofang', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO `tb_user` VALUES ('3', '119', '$2a$10$QgxfCkfn1Xm.iGQK4Ua.Zud7tIJ4BfhY5bGFvkeoJToSwKUI7zz8.', 'xiaozhun', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 0);

SET FOREIGN_KEY_CHECKS = 1;
