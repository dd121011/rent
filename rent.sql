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
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
    `dic_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(32) NOT NULL COMMENT '字典类型名称',
    `code` char (3) NOT NULL COMMENT '字典类型编号',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`dic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dictionary_iterm
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_iterm`;
CREATE TABLE `dictionary_iterm` (
    `dic_iterm_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `dic_id` int(10) unsigned NOT NULL COMMENT '字典类型Id',
    `value` varchar(32) NOT NULL COMMENT '字典项目值',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`dic_iterm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
    `attachment_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `store` varchar(64) NOT NULL COMMENT '存放地址',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
    `account_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `username` varchar(32) NOT NULL COMMENT '用户名',
    `pwd` char(32) NOT NULL COMMENT '密码',
    `phone` char(11) NOT NULL COMMENT '手机号码',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `type` char(1) NOT NULL COMMENT '用户类型 , 0-租客, 1-房东, 2-管理员, 3-巡管员, 4-超级管理员',
    `name` varchar(32) NOT NULL COMMENT '姓名',
    `sex` char(1) NOT NULL DEFAULT '0' COMMENT '0-保密, 1-男, 2-女',
    `age` int(3) NOT NULL DEFAULT '0' COMMENT '年龄',
    `avatar` varchar (128) DEFAULT '' COMMENT '头像URL',
    `qq` varchar(32) DEFAULT '' COMMENT 'qq',
    `wechat` varchar(32) DEFAULT '' COMMENT '微信',
    `email` varchar(64) DEFAULT '' COMMENT 'email',
    `profession` varchar(32) DEFAULT '' COMMENT '职业',
    `hometown` varchar(32) DEFAULT '' COMMENT '籍贯',
    --     `address_id` int(10) unsigned DEFAULT NULL COMMENT '地址Id',
    `address` varchar(128) DEFAULT '' COMMENT '地址',
    `account_id` int(10) unsigned NOT NULL COMMENT '账号Id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for renter
-- ----------------------------
DROP TABLE IF EXISTS `renter`;
CREATE TABLE `renter` (
    `renter_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `id_card` varchar(16) NOT NULL COMMENT 'identification card 身份证号',
    `id_card_pic` varchar(64) NOT NULL DEFAULT '' COMMENT '身份证正面',
    `id_card_pic_back` varchar(64) NOT NULL DEFAULT '' COMMENT '身份证反面',
    `room_id` int(10) unsigned NOT NULL COMMENT '房间id,一个租户对应一个房间，一个房间对应多个租户',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `user_id` int(10) unsigned NOT NULL COMMENT '一个租客对应一个账号',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`renter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
    `building_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `facilities` varchar(256) DEFAULT '' COMMENT '配套设施id字符串[,隔开]',
    `extra_fee` varchar(256) DEFAULT '' COMMENT '额外收费项id字符串[,隔开]',
    `rooms` int(10) NOT NULL COMMENT '总的房间数',
    `room_able` int(10) DEFAULT '0' COMMENT '可用房间数,通过总的房间数和可用房间数可以计算出出租房间数',
    `describe` varchar(256) DEFAULT '' COMMENT '描述',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    --     `address_id` int(10) unsigned DEFAULT NULL COMMENT '地址Id',
    `address` varchar(128) DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`building_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for building_attach
-- ----------------------------
DROP TABLE IF EXISTS `building_attach`;
CREATE TABLE `building_attach` (
    `building_attach_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `attach_id` int(10) unsigned NOT NULL COMMENT '附件Id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`building_attach_id`),
    UNIQUE KEY (`building_id`,`attach_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for building_landlord
-- ----------------------------
DROP TABLE IF EXISTS `building_landlord`;
CREATE TABLE `building_landlord` (
    `building_landlord_id` int unsigned NOT NULL AUTO_INCREMENT,
    `landlord_id` int(10) unsigned NOT NULL COMMENT '房东Id',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    UNIQUE KEY (`landlord_id`,`building_id`),
    PRIMARY KEY (building_landlord_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for extra
-- ----------------------------
DROP TABLE IF EXISTS `extra`;
CREATE TABLE `extra` (
    `extra_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(10) NOT NULL COMMENT '名称',
    `unit` varchar(10) NOT NULL COMMENT '单位',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`extra_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
    `room_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_no` varchar(10) NOT NULL COMMENT '房间号',
    `style` varchar(16) DEFAULT '0室0厅0卫' COMMENT '房型',
    `orientation` int(10) NOT NULL COMMENT '房间朝向',
    `decoration` int(10) NOT NULL COMMENT '装修情况',
    `guaranty` int(2) DEFAULT 1 COMMENT '押金月份',
    `pay` int(2) DEFAULT 1 COMMENT '租金月份',
    `rent_fee` int(10) unsigned NOT NULL COMMENT '租金[分/月]',
    `area` int(10) NOT NULL COMMENT '使用面积[平方分米]',
    `describe` varchar(0) DEFAULT '' COMMENT '描述',
    `facilities` varchar(256) DEFAULT '' COMMENT '配套设施id字符串[,隔开]',
    `extra_fee` varchar(256) DEFAULT '' COMMENT '额外收费项id字符串[,隔开]',
    `rent_ts` bigint unsigned DEFAULT '0' COMMENT '出租时间',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子id,一个房间对应一个房子id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room_attach
-- ----------------------------
DROP TABLE IF EXISTS `room_attach`;
CREATE TABLE `room_attach` (
    room_attach_id int unsigned NOT NULL AUTO_INCREMENT,
    `room_id` int(10) unsigned NOT NULL COMMENT '房间Id',
    `attach_id` int(10) unsigned NOT NULL COMMENT '附件Id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    UNIQUE KEY (`room_id`,`attach_id`),
    PRIMARY KEY (room_attach_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bargin
-- ----------------------------
DROP TABLE IF EXISTS `bargin`;
CREATE TABLE `bargin` (
    `bargin_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(64) NOT NULL COMMENT '姓名',
    `sex` char(1) NOT NULL DEFAULT '0' COMMENT '0-保密, 1-男, 2-女',
    `phone` char(11) NOT NULL COMMENT '手机号码',
    `id_card` varchar(16) NOT NULL COMMENT 'identification card 身份证号',
    `id_card_pic` varchar(64) NOT NULL DEFAULT '' COMMENT '身份证正面',
    `id_card_pic_back` varchar(64) NOT NULL DEFAULT '' COMMENT '身份证反面',
    `guaranty` int(2) DEFAULT 1 COMMENT '押金月份',
    `pay` int(2) DEFAULT 1 COMMENT '租金月份',
    `rent_fee` int(10) unsigned NOT NULL COMMENT '租金[分/月]',
    `guaranty_fee` int(10) unsigned NOT NULL COMMENT '押金[分]',
    `total` int(10) unsigned NOT NULL COMMENT '首次缴费[分]',
    `water` int(10) unsigned DEFAULT '0' COMMENT '水表初始读数, 单位KG',
    `electric` int(10) unsigned DEFAULT '0' COMMENT '电表初始读数, 单位Kwh',
    `electric_three` int(10) unsigned DEFAULT '0' COMMENT '三相电表初始读数, 单位Kwh',
    `gas_three` int(10) unsigned DEFAULT '0' COMMENT '天然气初始读数, 单位m2',
    `facilities` varchar(256) DEFAULT '' COMMENT '配套设施id字符串[,隔开]',
    `room_id` int(10) unsigned NOT NULL COMMENT '房间id,一个合同对应一个房间，一个房间对应多个合同',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `user_id` int(10) unsigned NOT NULL COMMENT '一个租客对应一个账号',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `live_ts` bigint unsigned NOT NULL COMMENT '入住时间，13位时间戳',
    `lease_ts` bigint unsigned NOT NULL COMMENT '退租时间，13位时间戳',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`bargin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bargin_extra
-- ----------------------------
DROP TABLE IF EXISTS `bargin_extra`;
CREATE TABLE `bargin_extra` (
    `bargin_extra_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `bargin_id` int(10) unsigned NOT NULL COMMENT '合同Id',
    `room_id` int(10) unsigned NOT NULL COMMENT '房间Id',
    `extra_id` int(10) unsigned NOT NULL COMMENT '额外收费项Id',
    `name` varchar(10) NOT NULL COMMENT '名称',
    `unit` varchar(10) NOT NULL COMMENT '单位',
    `price` int(10) unsigned NOT NULL COMMENT '价格',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (bargin_extra_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for deposit
-- ----------------------------
DROP TABLE IF EXISTS `deposit`;
CREATE TABLE `deposit` (
    `deposit_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_no` varchar(10) NOT NULL COMMENT '房间编号',
    `fee` int unsigned NOT NULL DEFAULT '0' COMMENT '总费用',
    `pay_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '支付时间戳，13位',
    `pay_no` varchar(256) DEFAULT '' COMMENT '支付订单号',
    `channel` char(1) DEFAULT NULL COMMENT '支付渠道，0-线下支付；1-微信支付；2-支付宝支付',
    `room_id` int(10) unsigned NOT NULL COMMENT '房间id,一个押金对应一个roomId,一个roomId可能对应多个押金Id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`deposit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dictionary_iterm
-- ----------------------------
DROP TABLE IF EXISTS `deposit_iterm`;
CREATE TABLE `deposit_iterm` (
    `deposit_iterm_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `deposit_id` int(10) unsigned NOT NULL COMMENT '押金Id',
    `room_id` int(10) unsigned NOT NULL COMMENT '房间Id',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `name` varchar(32) NOT NULL COMMENT '押金项目名称',
    `price` varchar(32) NOT NULL COMMENT '押金项目单价[分]',
    `unit` varchar(32) NOT NULL COMMENT '押金项目单位',
    `total` varchar(32) NOT NULL COMMENT '押金项目金额',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`deposit_iterm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rent
-- ----------------------------
DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent` (
    `rent_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `rent_no` varchar(64) NOT NULL COMMENT '房租收据单号',
    `rent_month` char(6) NOT NULL COMMENT '房租月份, eg 201806',
    `fee` int unsigned NOT NULL DEFAULT '0' COMMENT '总费用',
    `count` int unsigned NOT NULL DEFAULT '0' COMMENT '折扣费用',
    `real_fee` int unsigned NOT NULL DEFAULT '0' COMMENT '实际费用',
    `pay_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '支付时间戳，13位',
    `pay_no` varchar(256) DEFAULT '' COMMENT '支付订单号',
    `channel` char(1) DEFAULT NULL COMMENT '支付渠道，0-线下支付；1-微信支付；2-支付宝支付',
    `room_id` int(10) unsigned NOT NULL COMMENT '房间id,一个房租对应一个roomId',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`rent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dictionary_iterm
-- ----------------------------
DROP TABLE IF EXISTS `rent_iterm`;
CREATE TABLE `rent_iterm` (
    `rent_iterm_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `rent_id` int(10) unsigned NOT NULL COMMENT '房租Id',
    `room_id` int(10) unsigned NOT NULL COMMENT '房间Id',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `name` varchar(32) NOT NULL COMMENT '房租项目名称',
    `price` int(10) unsigned NOT NULL COMMENT '房租项目单价[分]',
    `unit` varchar(32) NOT NULL COMMENT '房租项目单位,从字典表获得',
    `number` int(10) unsigned NOT NULL COMMENT '房租项目数量',
    `money` int(10) unsigned NOT NULL COMMENT '房租项目金额',
    `describe` varchar (128) DEFAULT '' COMMENT '房租项目描述',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`rent_iterm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for water_history
-- ----------------------------
DROP TABLE IF EXISTS `water_history`;
CREATE TABLE `water_history` (
    `water_history_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_id` varchar(10) NOT NULL COMMENT '房间Id',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `count` int(10) unsigned DEFAULT '0' COMMENT '水表读数, 单位KG',
    `month` char(6) NOT NULL COMMENT '统计月, eg 201805',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`water_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for electric_history
-- ----------------------------
DROP TABLE IF EXISTS `electric_history`;
CREATE TABLE `electric_history` (
    `electric_history_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_id` varchar(10) NOT NULL COMMENT '房间Id',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `count` int(10) unsigned DEFAULT '0' COMMENT '电表读数, 单位Kwh',
    `month` char(6) NOT NULL COMMENT '统计月, eg 201805',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`electric_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for electric_three_history
-- ----------------------------
DROP TABLE IF EXISTS `electric_three_history`;
CREATE TABLE `electric_three_history` (
    `electric_three_history_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_id` varchar(10) NOT NULL COMMENT '房间Id',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `count` int(10) unsigned DEFAULT '0' COMMENT '三相电表读数, 单位Kwh',
    `month` char(6) NOT NULL COMMENT '统计月, eg 201805',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`electric_three_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for gas
-- ----------------------------
DROP TABLE IF EXISTS `gas_history`;
CREATE TABLE `gas_history` (
    `gas_history_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_id` varchar(10) NOT NULL COMMENT '房间Id',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `count` int(10) unsigned DEFAULT '0' COMMENT '天然气读数, 单位m2',
    `month` char(6) NOT NULL COMMENT '统计月, eg 201805',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`gas_history_id`)
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
--     `create_time` datetime NOT NULL COMMENT '创建时间，13位时间戳',
--     `update_time` datetime DEFAULT NULL COMMENT '更新时间, 13位时间戳',
--     `remark` varchar(256) DEFAULT NULL COMMENT '备注',
--     `delete_ts` int unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
--     PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
