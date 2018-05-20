/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50622
 Source Host           : localhost:3306
 Source Schema         : rent

 Target Server Type    : MySQL
 Target Server Version : 50622
 File Encoding         : 65001

 Date: 13/05/2018 23:37:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `username` varchar(32) DEFAULT NULL COMMENT '用户名',
    `pwd` char(32) NOT NULL COMMENT '密码',
    `phone` char(11) NOT NULL COMMENT '手机号码',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(256) DEFAULT NULL COMMENT '备注',
    `deleted` char(1) DEFAULT NULL COMMENT '删除时间戳',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for address
-- ----------------------------
-- DROP TABLE IF EXISTS `address`;
-- CREATE TABLE `address` (
--     `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
--     `province` varchar(32) NOT NULL COMMENT '省',
--     `city` varchar(32) NOT NULL COMMENT '市',
--     `country` varchar(32) DEFAULT NULL COMMENT '县',
--     `town` varchar(32) DEFAULT NULL COMMENT '镇',
--     `street` varchar(32) DEFAULT NULL COMMENT '街道',
--     `village` varchar(32) DEFAULT NULL COMMENT '村',
--     `district` varchar(32) DEFAULT NULL COMMENT '小区',
--     `building_no` varchar(32) DEFAULT NULL COMMENT '楼牌号',
--     `create_time` datetime NOT NULL COMMENT '创建时间',
--     `update_time` datetime DEFAULT NULL COMMENT '更新时间',
--     `remark` varchar(256) DEFAULT NULL COMMENT '备注',
--     `deleted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间戳',
--     PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
-- DROP TABLE IF EXISTS `admin`;
-- CREATE TABLE `admin` (
--     `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
--     `jobNo` char(10) NOT NULL COMMENT '工号',
--     `administrator` char(1) DEFAULT '0' COMMENT '是否超级管理员, 0-否, 1-是',
--     `user_id` int(10) unsigned DEFAULT NULL COMMENT '一个房东对应一个账号, 用户Id',
--     `create_time` datetime NOT NULL COMMENT '创建时间',
--     `update_time` datetime DEFAULT NULL COMMENT '修改时间',
--     `remark` varchar(256) DEFAULT NULL COMMENT '备注',
--     PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
    `attachment_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `store` varchar(64) NOT NULL COMMENT '存放地址',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(256) DEFAULT NULL COMMENT '备注',
    `deleted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间戳',
    PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
    `building_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `water` int(10) unsigned NOT NULL COMMENT '水费[分], eg 1个月1KG水700',
    `power` int(10) unsigned NOT NULL COMMENT '电费[分], eg 1个月1kwh电120',
    `power_three` int(10) DEFAULT '0' COMMENT '三相电费[分], eg 1个月1kwh电150',
    `health` int(10) DEFAULT '0' COMMENT '卫生费[分], eg 1个月1000',
    `internet` int(10) DEFAULT '0' COMMENT '网费[分], eg 1个月1000',
    `management` int(10) DEFAULT '0' COMMENT '物业管理费[分], eg 1个月1000',
    `parking` int(10) DEFAULT '0' COMMENT '停车费[分], eg 1个月1000',
    `rooms` int(10) NOT NULL COMMENT '总的房间数',
    `room_able` int(10) NOT NULL COMMENT '可用房间数,通过总的房间数和可用房间数可以计算出出租房间数',
    `desc` varchar(256) NOT NULL COMMENT '描述',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    `remark` varchar(0) DEFAULT NULL COMMENT '备注',
    `deleted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间戳',
--     `address_id` int(10) unsigned DEFAULT NULL COMMENT '地址Id',
    `address` varchar(128) not null DEFAULT '' comment '地址',
    PRIMARY KEY (`building_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for building_attach
-- ----------------------------
DROP TABLE IF EXISTS `building_attach`;
CREATE TABLE `building_attach` (
    `building_id` int(10) unsigned NOT NULL DEFAULT '0',
    `attach_id` int(10) unsigned NOT NULL DEFAULT '0',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(256) DEFAULT NULL COMMENT '备注',
    `deleted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间戳',
    PRIMARY KEY (`building_id`,`attach_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for building_landlord
-- ----------------------------
DROP TABLE IF EXISTS `building_landlord`;
CREATE TABLE `building_landlord` (
    `landlord_id` int(10) unsigned NOT NULL COMMENT '房东Id',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(256) DEFAULT NULL COMMENT '备注',
    `deleted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
    PRIMARY KEY (`landlord_id`,`building_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for deposit
-- ----------------------------
DROP TABLE IF EXISTS `deposit`;
CREATE TABLE `deposit` (
    `deposit_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_no` varchar(10) NOT NULL COMMENT '房间编号',
    `water` int(10) unsigned DEFAULT NULL COMMENT '水费[分], eg 1个月1KG水700',
    `power` int(10) unsigned DEFAULT NULL COMMENT '电费[分], eg 1个月1kwh电120',
    `power_three` int(10) unsigned DEFAULT NULL COMMENT '三相电费[分], eg 1个月1kwh电150',
    `health` int(10) unsigned DEFAULT NULL COMMENT '卫生费[分], eg 1个月1000',
    `internet` int(10) unsigned DEFAULT NULL COMMENT '网费[分], eg 1个月1000',
    `management` int(10) unsigned DEFAULT NULL COMMENT '物业管理费[分], eg 1个月1000',
    `parking` int(10) unsigned DEFAULT NULL COMMENT '停车费[分], eg 1个月1000',
    `rent` int(10) unsigned DEFAULT NULL COMMENT '租金[分], eg 1个月50000',
    `room_id` int(10) unsigned DEFAULT NULL COMMENT '房间id,一个押金对应一个roomId,一个roomId可能对应多个押金Id',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(256) DEFAULT NULL COMMENT '备注',
    `deleted` char(1) DEFAULT NULL COMMENT '删除时间戳',
    PRIMARY KEY (`deposit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rent
-- ----------------------------
DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent` (
    `rent_id` int(10) NOT NULL,
    `rent_no` varchar(64) NOT NULL COMMENT '房租收据单号',
    `water` int(10) unsigned NOT NULL COMMENT '水费[分], eg 1个月1KG水700',
    `power` int(10) unsigned NOT NULL COMMENT '电费[分], eg 1个月1kwh电120',
    `power_three` int(10) unsigned DEFAULT NULL COMMENT '三相电费[分], eg 1个月1kwh电150',
    `health` int(10) unsigned DEFAULT NULL COMMENT '卫生费[分], eg 1个月1000',
    `internet` int(10) unsigned DEFAULT NULL COMMENT '网费[分], eg 1个月1000',
    `management` int(10) unsigned DEFAULT NULL COMMENT '物业管理费[分], eg 1个月1000',
    `parking` int(10) unsigned DEFAULT NULL COMMENT '停车费[分], eg 1个月1000',
    `rent` int(10) unsigned DEFAULT NULL COMMENT '租金[分], eg 1个月50000',
    `water_last` int(10) unsigned DEFAULT NULL COMMENT '上月水表读数[升]',
    `water_this` int(10) unsigned DEFAULT NULL COMMENT '本月水表读数[升]',
    `power_last` int(10) unsigned DEFAULT NULL COMMENT '上月电表读数[kwh]',
    `power_this` int(10) unsigned DEFAULT NULL COMMENT '本月电表读数[kwh]',
    `power_three_last` int(10) unsigned DEFAULT NULL COMMENT '上月用三电表读数[kwh]',
    `power_three_this` int(10) unsigned DEFAULT NULL COMMENT '本月用三电表读数[kwh]',
    `payed` char(1) DEFAULT '0' COMMENT '是否已支付',
    `channel` char(1) DEFAULT NULL COMMENT '支付渠道',
    `room_id` int(10) unsigned DEFAULT NULL COMMENT '房间id,一个房租对应一个roomId',
    PRIMARY KEY (`rent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for renter
-- ----------------------------
DROP TABLE IF EXISTS `renter`;
CREATE TABLE `renter` (
    `renter_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `id_card` varchar(16) DEFAULT NULL COMMENT 'identification card 身份证号',
    `id_card_pic` varchar(64) DEFAULT NULL COMMENT '身份证正面',
    `id_card_pic_back` varchar(64) DEFAULT NULL COMMENT '身份证反面',
    `room_id` int(10) unsigned NOT NULL COMMENT '房间id,一个租户对应一个房间，一个房间对应多个租户',
    `user_id` int(10) unsigned NOT NULL COMMENT '一个租客对应一个账号',
    `create_time` datetime NOT NULL COMMENT '创建时间 ',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    `remark` varchar(256) DEFAULT NULL COMMENT '备注',
    `deleted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间戳',
    PRIMARY KEY (`renter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
    `room_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_no` varchar(10) NOT NULL COMMENT '房间号',
    `deposit` int(10) NOT NULL COMMENT '押金[分]',
    `rent` int(10) NOT NULL COMMENT '租金[分]',
    `water_last` int(11) NOT NULL DEFAULT '0' COMMENT '上月水表读数',
    `water_this` int(11) NOT NULL DEFAULT '0' COMMENT '本月水表读数',
    `power_last` int(11) NOT NULL DEFAULT '0' COMMENT '上月电表读数',
    `power_this` int(11) NOT NULL DEFAULT '0' COMMENT '本月电表读数',
    `power_three_last` int(11) DEFAULT '0' COMMENT '上月用三电表读数',
    `power_three_this` int(11) DEFAULT '0' COMMENT '本月用三电表读数',
    `rent_day` varchar(2) NOT NULL COMMENT '收租日',
    `desc` varchar(0) DEFAULT NULL COMMENT '描述',
    `rented` char(1) NOT NULL DEFAULT '0' COMMENT '是否出租,0- 未出租,1-已出租',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子id,一个房间对应一个房子id',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    `remark` varchar(256) DEFAULT NULL COMMENT '备注',
    `deleted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '删除时间戳',
    PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room_attach
-- ----------------------------
DROP TABLE IF EXISTS `room_attach`;
CREATE TABLE `room_attach` (
    `room_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '房间Id',
    `attach_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '附件Id',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `remark` varchar(256) DEFAULT NULL COMMENT '备注',
    `deleted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
    PRIMARY KEY (`room_id`,`attach_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `type` char(1) NOT NULL DEFAULT '0' COMMENT '用户类型 , 0-租客, 1-房东, 2-管理员, 3-巡管员, 4-超级管理员',
    `name` varchar(32) NOT NULL COMMENT '姓名',
    `sex` char(1) NOT NULL DEFAULT '0' COMMENT '0-保密, 1-男, 2-女',
    `age` int(3) DEFAULT NULL COMMENT '年龄',
    `avatar` VARCHAR (128) NOT NULL DEFAULT '' COMMENT '头像URL',
    `qq` varchar(32) DEFAULT NULL COMMENT 'qq',
    `wechat` varchar(32) DEFAULT NULL COMMENT '微信',
    `email` varchar(32) DEFAULT NULL COMMENT 'email',
    `profession` varchar(32) DEFAULT NULL COMMENT '职业',
    `hometown` varchar(32) DEFAULT NULL COMMENT '籍贯',
--     `address_id` int(10) unsigned DEFAULT NULL COMMENT '地址Id',
    `address` varchar(128) not null DEFAULT '' comment '地址',
    `account_id` int(10) unsigned NOT NULL COMMENT '账号Id',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    `remark` varchar(256) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
