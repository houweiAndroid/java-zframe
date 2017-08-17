/*
 Navicat MySQL Data Transfer

 Source Server         : mac
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : zframe

 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 07/13/2017 20:43:31 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

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
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tab_published`
-- ----------------------------
DROP TABLE IF EXISTS `tab_published`;
CREATE TABLE `tab_published` (
  `published_id` int(11) NOT NULL COMMENT '发布ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `published_title` varchar(255) NOT NULL COMMENT '发布主题',
  `published_location` varchar(150) DEFAULT NULL COMMENT '发布位置',
  `published_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `published_type` int(4) NOT NULL COMMENT '发布类型: 1认领  2寻物',
  `remark` varchar(120) DEFAULT NULL COMMENT '备注',
  `picture_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `state` int(4) NOT NULL DEFAULT '0' COMMENT '状态 0正常 -1废弃',
  PRIMARY KEY (`published_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发布表';

SET FOREIGN_KEY_CHECKS = 1;
