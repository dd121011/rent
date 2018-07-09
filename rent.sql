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
    `dic_code` char (4) NOT NULL COMMENT '字典类型编号',
    `name` varchar(32) NOT NULL COMMENT '字典类型名称',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`dic_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dictionary_iterm
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_iterm`;
CREATE TABLE `dictionary_iterm` (
    `dic_iterm_code` char (4) NOT NULL COMMENT '字典类型项编号',
    `dic_code` char (4) NOT NULL COMMENT '字典类型编号',
    `value` varchar(32) NOT NULL COMMENT '字典类型项值',
    `unit` varchar(16) DEFAULT '' COMMENT '字典类型项值',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`dic_iterm_code`)
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
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
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
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
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
    `openid` varchar(64) DEFAULT '' COMMENT '微信openid',
    `unionid` varchar(64) DEFAULT '' COMMENT '微信unionid',

    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for renter
-- ----------------------------
DROP TABLE IF EXISTS `renter`;
CREATE TABLE `renter` (
    `renter_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `id_card` varchar(18) NOT NULL COMMENT 'identification card 身份证号',
    `id_card_pic` varchar(64) NOT NULL DEFAULT '' COMMENT '身份证正面',
    `id_card_pic_back` varchar(64) NOT NULL DEFAULT '' COMMENT '身份证反面',
    `user_id` int(10) unsigned NOT NULL COMMENT '一个租客对应一个账号',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`renter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_sns
-- ----------------------------
DROP TABLE IF EXISTS `wx_sns`;
CREATE TABLE `wx_sns` (
    `openid` varchar(64) NOT NULL COMMENT 'wx openid',
    `unionid` varchar(64) DEFAULT '' COMMENT 'wx unionid',
    `user_id` int(10) unsigned COMMENT 'userId',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
    `building_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(256) NOT NULL COMMENT '房子名称',
    `rooms` int(10) unsigned DEFAULT '0' COMMENT '总的房间数',
    `room_able` int(10) unsigned DEFAULT '0' COMMENT '可用房间数,通过总的房间数和可用房间数可以计算出出租房间数',
    `description` varchar(256) DEFAULT '' COMMENT '描述',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
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
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`building_attach_id`),
    UNIQUE KEY (`building_id`,`attach_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for building_landlord
-- ----------------------------
DROP TABLE IF EXISTS `building_landlord`;
CREATE TABLE `building_landlord` (
    `building_landlord_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `landlord_id` int(10) unsigned NOT NULL COMMENT '房东Id',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    UNIQUE KEY (`landlord_id`,`building_id`),
    PRIMARY KEY (building_landlord_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
    `room_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_no` varchar(10) NOT NULL COMMENT '房间号',
    `living` int(2) unsigned NOT NULL COMMENT '客厅数量',
    `bedroom` int(2) unsigned NOT NULL COMMENT '房间数量',
    `toilet` int(2) unsigned NOT NULL COMMENT '卫生间数量',
    `orientation` char(4) NOT NULL COMMENT '房间朝向',
    `decoration` char(4) NOT NULL COMMENT '装修情况',
    `guaranty` int(2) unsigned NOT NULL COMMENT '押金月份',
    `pay` int(2) unsigned NOT NULL COMMENT '租金月份',
    `rent_fee` int(10) unsigned NOT NULL COMMENT '租金[分/月]',
    `area` int(10) unsigned NOT NULL COMMENT '使用面积[平方分米]',
    `description` varchar(256) DEFAULT '' COMMENT '描述',
    `facilities` varchar(256) DEFAULT '' COMMENT '配套设施id字符串[,隔开]',
    `extra_fee` varchar(256) DEFAULT '' COMMENT '额外收费项id字符串[,隔开]',
    `deposits` varchar(256) DEFAULT '' COMMENT '押金项id字符串[,隔开]',
    `rent_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '出租时间',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子id,一个房间对应一个房子id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    UNIQUE KEY (`room_no`,`building_id`),
    PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room_attach
-- ----------------------------
DROP TABLE IF EXISTS `room_attach`;
CREATE TABLE `room_attach` (
    room_attach_id int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_id` int(10) unsigned NOT NULL COMMENT '房间Id',
    `attach_id` int(10) unsigned NOT NULL COMMENT '附件Id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    UNIQUE KEY (`room_id`,`attach_id`),
    PRIMARY KEY (room_attach_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room_renter
-- ----------------------------
DROP TABLE IF EXISTS `room_renter`;
CREATE TABLE `room_renter` (
    room_renter_id int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_id` int(10) unsigned NOT NULL COMMENT '房间Id',
    `user_id` int(10) unsigned NOT NULL COMMENT '租客的user_id',
    `renter_id` int(10) unsigned NOT NULL COMMENT '租客的renter_id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间/入住时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间/离开时间, 13位时间戳',
    PRIMARY KEY (room_renter_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bargin
-- ----------------------------
DROP TABLE IF EXISTS `bargin`;
CREATE TABLE `bargin` (
    `bargin_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `bargin_no` char(32) NOT NULL COMMENT '合同编号',
    `name` varchar(64) NOT NULL COMMENT '姓名',
    `sex` char(1) NOT NULL DEFAULT '0' COMMENT '性別, 0-保密, 1-男, 2-女',
    `phone` char(11) NOT NULL COMMENT '手机号码',
    `id_card` varchar(18) NOT NULL COMMENT 'identification card 身份证号',
    `id_card_pic` varchar(64) DEFAULT '' COMMENT '身份证正面',
    `id_card_pic_back` varchar(64) DEFAULT '' COMMENT '身份证反面',
    `guaranty` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '押金月份',
    `pay` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '租金月份',
    `rent_Day` int(2) unsigned NOT NULL COMMENT '交租日',
    `rent_fee` int(10) unsigned NOT NULL COMMENT '租金[分/月]',
    `guaranty_fee` int(10) unsigned NOT NULL COMMENT '押金[分]',
    `total` int(10) unsigned NOT NULL COMMENT '首次缴费[分]',
    `facilities` varchar(256) DEFAULT '' COMMENT '配套设施id字符串[,隔开]',
    `room_id` int(10) unsigned NOT NULL COMMENT '房间id,一个合同对应一个房间，一个房间对应多个合同',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `user_id` int(10) unsigned NOT NULL COMMENT '一个合同对应一个租客,租客的user_id',
    `renter_id` int(10) unsigned NOT NULL COMMENT '一个合同对应一个租客,租客的renter_id',
    `landlord_id` int(10) unsigned NOT NULL COMMENT '一个合同对应一个房东,房东的user_id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `live_ts` bigint unsigned NOT NULL COMMENT '入住时间，13位时间戳',
    `leave_ts` bigint unsigned NOT NULL COMMENT '退租时间，13位时间戳',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    UNIQUE KEY (`bargin_no`),
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
    `dic_iterm_code` char(4) NOT NULL COMMENT '额外收费项code',
    `value` varchar(32) NOT NULL COMMENT '名称',
    `unit` varchar(16) DEFAULT '' COMMENT '单位',
    `price` int(10) unsigned NOT NULL COMMENT '价格',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (bargin_extra_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for deposit
-- ----------------------------
DROP TABLE IF EXISTS `deposit`;
CREATE TABLE `deposit` (
    `deposit_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `deposit_no` char(32) NOT NULL COMMENT '押金编号',
    `fee` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '总费用',
    `pay_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '支付时间戳，13位',
    `pay_no` varchar(256) DEFAULT '' COMMENT '支付订单号',
    `channel` char(2) DEFAULT '99' COMMENT '支付渠道，99-未支付; 0-线下支付; 1-微信支付; 2-支付宝支付',
    `room_id` int(10) unsigned NOT NULL COMMENT '房间id,一个押金对应一个roomId,一个roomId可能对应多个押金Id',
    `user_id` int(10) unsigned NOT NULL COMMENT '一个押金对应一个租客,租客的user_id',
    `renter_id` int(10) unsigned NOT NULL COMMENT '一个押金对应一个租客,租客的renter_id',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子Id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    UNIQUE KEY (`deposit_no`),
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
    `value` varchar(32) NOT NULL COMMENT '押金项目名称',
    `price` int(10) NOT NULL COMMENT '押金项目单价[分]',
    `unit` varchar(16) DEFAULT '' COMMENT '押金项目单位',
    `number` int(10) unsigned NOT NULL COMMENT '押金项目数量',
    `money` int(10) unsigned NOT NULL COMMENT '押金项目金额',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`deposit_iterm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rent
-- ----------------------------
DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent` (
    `rent_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `rent_no` char(32) NOT NULL COMMENT '房租收据单号',
    `rent_month` char(6) NOT NULL COMMENT '房租月份, eg 201806',
    `fee` int(10) unsigned NOT NULL COMMENT '总费用',
    `count` int(10) unsigned NOT NULL default '0' COMMENT '折扣费用',
    `real_fee` int(10) unsigned NOT NULL COMMENT '实际费用',
    `pay_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '支付时间戳，13位',
    `pay_no` varchar(256) DEFAULT '' COMMENT '支付订单号',
    `channel` char(2) DEFAULT '99' COMMENT '支付渠道，0-线下支付；1-微信支付；2-支付宝支付',
    `room_id` int(10) unsigned NOT NULL COMMENT '房间id,一个房租对应一个room_id',
    `building_id` int(10) unsigned NOT NULL COMMENT '房子id,一个房租对应一个building_id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`rent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dictionary_iterm
-- ----------------------------
DROP TABLE IF EXISTS `rent_iterm`;
CREATE TABLE `rent_iterm` (
    `rent_iterm_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `rent_id` int(10) unsigned NOT NULL COMMENT '房租Id',
    `bargin_extra_id` int(10) unsigned NOT NULL COMMENT '房租额外收费项Id',
    `value` varchar(32) NOT NULL COMMENT '房租项目名称',
    `price` int(10) unsigned NOT NULL COMMENT '房租项目单价[分]',
    `unit` varchar(32) DEFAULT '' COMMENT '房租项目单位,从合同获得',
    `number` int(10) unsigned NOT NULL COMMENT '房租项目数量',
    `money` int(10) unsigned NOT NULL COMMENT '房租项目金额',
    `description` varchar (256) DEFAULT '' COMMENT '房租项目描述',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`rent_iterm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for water_history
-- ----------------------------
DROP TABLE IF EXISTS `extra_history`;
CREATE TABLE `extra_history` (
    `extra_history_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `room_id` int(10) NOT NULL COMMENT '房间Id',
    `count` int(10) unsigned NOT NULL COMMENT '水表读数, 单位KG',
    `month` char(6) NOT NULL COMMENT '统计月, eg 201805',
    `dic_iterm_code` char(4) NOT NULL COMMENT '额外收费项code',
    `bargin_extra_id` int(10) NOT NULL COMMENT '额外收费项Id',
    `remark` varchar(256) DEFAULT '' COMMENT '备注',
    `create_ts` bigint unsigned NOT NULL COMMENT '创建时间，13位时间戳',
    `update_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '更新时间, 13位时间戳',
    `delete_ts` bigint unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
    PRIMARY KEY (`extra_history_id`)
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
--     `delete_ts` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '删除时间, 13位时间戳',
--     PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
