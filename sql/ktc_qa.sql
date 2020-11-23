/*
 Navicat Premium Data Transfer

 Source Server         : KTC
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 192.168.12.133:3306
 Source Schema         : ktc_qa

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 25/08/2019 13:30:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_pl
-- ----------------------------
DROP TABLE IF EXISTS `tb_pl`;
CREATE TABLE `tb_pl`  (
  `problemid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题ID',
  `labelid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`problemid`, `labelid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_pl
-- ----------------------------
INSERT INTO `tb_pl` VALUES ('1', '1');
INSERT INTO `tb_pl` VALUES ('2', '1');
INSERT INTO `tb_pl` VALUES ('3', '1');
INSERT INTO `tb_pl` VALUES ('4', '1');
INSERT INTO `tb_pl` VALUES ('5', '1');
INSERT INTO `tb_pl` VALUES ('6', '1');
INSERT INTO `tb_pl` VALUES ('7', '1');
INSERT INTO `tb_pl` VALUES ('8', '1');

-- ----------------------------
-- Table structure for tb_problem
-- ----------------------------
DROP TABLE IF EXISTS `tb_problem`;
CREATE TABLE `tb_problem`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '修改日期',
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `visits` bigint(20) NULL DEFAULT NULL COMMENT '浏览量',
  `thumbup` bigint(20) NULL DEFAULT NULL COMMENT '点赞数',
  `reply` bigint(20) NULL DEFAULT NULL COMMENT '回复数',
  `solve` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否解决',
  `replyname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复人昵称',
  `replytime` datetime(0) NULL DEFAULT NULL COMMENT '回复日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_problem
-- ----------------------------
INSERT INTO `tb_problem` VALUES ('1', 'springmvc的工作流程', 'xxx', '2019-02-06 11:50:50', '2019-03-05 11:50:54', '2', NULL, 101, NULL, 20, NULL, NULL, '2019-06-04 11:04:05');
INSERT INTO `tb_problem` VALUES ('1144454401444831232', 'JDK和jre的关系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tb_problem` VALUES ('1144457212186353664', 'JDK和jre的关系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_problem` VALUES ('1158619508106190848', '如何学习Java？', '这样学习Java', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_problem` VALUES ('2', 'JDK怎么安装？', NULL, '2019-06-11 08:35:37', '2019-06-11 11:35:34', NULL, NULL, NULL, NULL, 25, NULL, NULL, '2019-06-05 11:04:14');
INSERT INTO `tb_problem` VALUES ('3', 'Javac怎么用', NULL, '2019-06-12 11:35:27', '2019-06-06 11:35:25', NULL, NULL, NULL, NULL, 15, NULL, NULL, '2019-06-12 11:04:20');
INSERT INTO `tb_problem` VALUES ('4', 'SpringBoot的工作原理？', NULL, '2019-06-27 11:35:18', '2019-06-27 11:35:22', NULL, NULL, NULL, NULL, 5, NULL, NULL, '2019-06-27 18:36:15');
INSERT INTO `tb_problem` VALUES ('5', 'SpringCloud与dobbox的区别？', NULL, '2019-06-11 08:35:37', '2019-06-11 11:35:34', NULL, NULL, NULL, NULL, 2, NULL, NULL, '2019-06-05 11:04:17');
INSERT INTO `tb_problem` VALUES ('6', 'Docker的好处', NULL, '2019-06-11 08:35:37', '2019-06-11 11:35:34', NULL, NULL, NULL, NULL, 19, NULL, NULL, '2019-06-05 11:04:01');
INSERT INTO `tb_problem` VALUES ('7', 'Servlet的声明周期？', NULL, '2019-06-11 08:35:37', '2019-06-11 11:35:34', NULL, NULL, NULL, NULL, 158, NULL, NULL, '2019-06-05 11:04:01');
INSERT INTO `tb_problem` VALUES ('8', '讲述一下redis雪崩', NULL, '2019-02-06 11:50:50', '2019-06-11 11:35:34', NULL, NULL, NULL, NULL, 0, NULL, NULL, '2019-06-18 11:04:20');

-- ----------------------------
-- Table structure for tb_reply
-- ----------------------------
DROP TABLE IF EXISTS `tb_reply`;
CREATE TABLE `tb_reply`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `problemid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题ID',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回答内容',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回答人ID',
  `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回答人昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回答' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_reply
-- ----------------------------
INSERT INTO `tb_reply` VALUES ('1', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_reply` VALUES ('2', '1', '问老师呗', '2018-01-10 14:14:06', NULL, '1', NULL);
INSERT INTO `tb_reply` VALUES ('3', '2', '明天再调', '2018-01-07 14:14:13', NULL, '1', NULL);


