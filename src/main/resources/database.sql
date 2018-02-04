# docker run --name mysql -p 6033:3306 -e MYSQL_ROOT_PASSWORD=xiangninvshen -d mysql:latest

create database xnsmsnotifiter;
use xnsmsnotifiter;

/*
 Navicat Premium Data Transfer

 Source Server         : DockerDB
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : xnnotifiter

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 12/07/2017 17:34:14 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `alert`
-- ----------------------------
DROP TABLE IF EXISTS `alert`;
CREATE TABLE `alert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fingerprint` varchar(16) NOT NULL COMMENT 'Alert唯一标识',
  `alertname` varchar(16) NOT NULL COMMENT 'Alert名称',
  `state` varchar(8) NOT NULL COMMENT '状态：unprocessed or active',
  `json` text NOT NULL COMMENT 'json串',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`,`fingerprint`),
  UNIQUE KEY `fp_unique_idx` (`fingerprint`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `sms`
-- ----------------------------
DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fingerprint` varchar(16) NOT NULL COMMENT 'Alert唯一标识',
  `content` text NOT NULL COMMENT '短息内容',
  `sent` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已发送',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`,`fingerprint`),
  UNIQUE KEY `fp_unique_idx` (`fingerprint`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
