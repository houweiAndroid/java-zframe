/*
 Navicat MySQL Data Transfer

 Source Server         : find
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : zframe

 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 11/15/2017 19:56:07 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(100) NOT NULL COMMENT '菜单路径',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父节点ID',
  `menu_type` int(4) NOT NULL DEFAULT '0' COMMENT '菜单类型',
  `menu_order` int(4) DEFAULT NULL COMMENT '排序',
  `state` int(4) NOT NULL DEFAULT '0' COMMENT '状态：0正常、-1废弃',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '/system', '0', '0', null, '0'), ('2', '菜单管理', '/system/menu', '1', '0', null, '0'), ('3', '用户管理', '/system/user', '1', '0', null, '0'), ('4', '角色管理', '/system/role', '1', '0', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `state` int(4) NOT NULL DEFAULT '0' COMMENT '状态：0正常、1废弃',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', '超级管理', '0'), ('2', '系统管理员', '0'), ('3', '业务人员', '0'), ('4', '运营人员', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('1', '1'), ('1', '2'), ('1', '3'), ('1', '4');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(100) NOT NULL COMMENT '用户名称',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `state` int(4) NOT NULL DEFAULT '0' COMMENT '状态：0正常、-1废弃',
  `last_login` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `ip_addr` varchar(15) DEFAULT NULL COMMENT 'IP地址',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', '111111', null, null, '0', '2017-07-12 12:09:57', null, null, null, null), ('2', '张三', '111111', '张淼', null, '0', '2017-07-12 12:08:15', null, '615810037@qq.com', '18033419527', null), ('3', '李四', '111111', '张淼', null, '0', '2017-07-12 12:08:20', null, '615810037@qq.com', '18033419527', null), ('4', '王五', '111111', '张淼', null, '0', '2017-07-12 12:08:23', null, '615810037@qq.com', '18033419527', null), ('5', '赵六', '111111', '张淼', null, '0', '2017-07-12 12:08:25', null, '615810037@qq.com', '18033419527', null), ('6', '孙七', '111111', '张淼', null, '0', '2017-07-12 12:08:28', null, '615810037@qq.com', '18033419527', null), ('7', '周八', '111111', '张淼', null, '0', '2017-07-12 12:08:30', null, '615810037@qq.com', '18033419527', null), ('8', '吴九', '111111', '张淼', null, '0', '2017-07-12 12:17:23', null, '615810037@qq.com', '18033419527', null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
