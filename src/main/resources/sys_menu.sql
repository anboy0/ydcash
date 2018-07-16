/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : store2

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-01-09 10:13:05
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id`     INT(11) NOT NULL AUTO_INCREMENT,
  `name`   VARCHAR(32)      DEFAULT NULL
  COMMENT '栏目名称',
  `sort`   INT(11)          DEFAULT NULL
  COMMENT '排序',
  `pid`    INT(11)          DEFAULT NULL
  COMMENT '父级id',
  `icon`   VARCHAR(64)      DEFAULT NULL
  COMMENT '图标',
  `action` VARCHAR(64)      DEFAULT NULL
  COMMENT '菜单相应地址',
  `status` INT(11)          DEFAULT '1'
  COMMENT '菜单状态',
  `flag`   INT(11)          DEFAULT '1'
  COMMENT '菜单功能标志(1为菜单,0为功能)',
  `url`    VARCHAR(128)     DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 47
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT,
  `cname`       VARCHAR(64)      DEFAULT NULL
  COMMENT '中文名称',
  `name`        VARCHAR(64)      DEFAULT NULL
  COMMENT '组名称',
  `status`      INT(11)          DEFAULT NULL
  COMMENT '组状态',
  `create_time` DATETIME         DEFAULT NULL,
  `desct`       VARCHAR(255)     DEFAULT NULL
  COMMENT '描述',
  `info`        VARCHAR(24)      DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id`          INT(24)     NOT NULL AUTO_INCREMENT
  COMMENT '用户ID',
  `name`        VARCHAR(32) NOT NULL
  COMMENT '用户名',
  `email`       VARCHAR(32) NOT NULL
  COMMENT '邮箱',
  `tel`         VARCHAR(24) NOT NULL
  COMMENT '电话',
  `pwd`         VARCHAR(32) NOT NULL
  COMMENT '密码',
  `create_time` DATETIME    NOT NULL
  COMMENT '创建时间',
  `status`      TINYINT(4)  NOT NULL DEFAULT '1'
  COMMENT '用户状态',
  `login_time`  DATETIME             DEFAULT NULL
  COMMENT '登录时间',
  `username`    VARCHAR(32)          DEFAULT NULL
  COMMENT '用户名',
  `py`          VARCHAR(32)          DEFAULT NULL
  COMMENT '拼音首字母',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log` (
  `id`          INT(65) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `logtype`     VARCHAR(255)     DEFAULT NULL
  COMMENT '日志类型',
  `logname`     VARCHAR(255)     DEFAULT NULL
  COMMENT '日志名称',
  `userid`      INT(65)          DEFAULT NULL
  COMMENT '用户id',
  `create_time` DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `succeed`     VARCHAR(255)     DEFAULT NULL
  COMMENT '是否成功',
  `message`     TEXT COMMENT '备注',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 558
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT
  COMMENT ='操作日志表';

-- ----------------------------
-- Table structure for base_dict
-- ----------------------------
DROP TABLE IF EXISTS `base_dict`;
CREATE TABLE `base_dict` (
  `id`          INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '字典ID',
  `name`        VARCHAR(64)      DEFAULT NULL
  COMMENT '字典名称',
  `type_name`   VARCHAR(64)      DEFAULT NULL
  COMMENT '字典类型',
  `pid`         INT(11)          DEFAULT NULL
  COMMENT '父ID',
  `create_time` DATETIME         DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `create_by`   VARCHAR(11)      DEFAULT NULL
  COMMENT '创建人',
  `modify_time` DATETIME         DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  `modify_by`   INT(11)          DEFAULT NULL
  COMMENT '修改人',
  `status`      SMALLINT(6)      DEFAULT NULL
  COMMENT '状态',
  `remark`      VARCHAR(64)      DEFAULT NULL,
  `value`       VARCHAR(64)      DEFAULT NULL
  COMMENT '值',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='系统字典表';

DROP TABLE IF EXISTS `bus_receipt`;
CREATE TABLE `bus_receipt` (
  `id`                 INT(11)        NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `Serial_no`          VARCHAR(10)    NOT NULL
  COMMENT '流水号',
  `receive_company_id` INT(11)        NOT NULL
  COMMENT '收款公司ID',
  `pay_company_type`   CHAR(1)        NOT NULL
  COMMENT '实缴类型，网点，公司，个人',
  `pay_company_id`     INT(11)        NOT NULL
  COMMENT '实缴公司ID',
  `pay_name`           VARCHAR(20)             DEFAULT NULL
  COMMENT '实缴个人名称',
  `pay_type`           CHAR(1)        NOT NULL
  COMMENT '收费方式，现金、转账、支票',
  `totalMoney`              DECIMAL(11, 2) NOT NULL DEFAULT '0.00'
  COMMENT '汇总金额',
  `status`             CHAR(1)        NOT NULL
  COMMENT '票据状态，生效，已冲销',
  `receive_time`       DATETIME       NOT NULL
  COMMENT '收款日期',
  `create_by`          INT(11)        NOT NULL
  COMMENT '收款操作人',
  `create_time`        DATETIME       NOT NULL
  COMMENT '开票日期',
  `print_time`         DATETIME                DEFAULT NULL
  COMMENT '打印日期',
  `print_count`        INT(11)                 DEFAULT '0'
  COMMENT '打印次数',
  `write_off_time`     DATETIME                DEFAULT NULL
  COMMENT '冲销日期',
  `write_off_by`       INT(11)                 DEFAULT NULL
  COMMENT '冲销操作人',
  `modify_time`        DATETIME                DEFAULT NULL
  COMMENT '修改日期',
  `modify_by`          INT(11)                 DEFAULT NULL
  COMMENT '修改人',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT
  COMMENT ='收据主表';


DROP TABLE IF EXISTS `bus_receipt_detail`;
CREATE TABLE `bus_receipt_detail` (
  `id`             INT(11)        NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `receipt_id`     INT(11)        NOT NULL
  COMMENT '收据ID',
  `charge_item_id` INT(11)        NOT NULL
  COMMENT '费用项目ID',
  `money`          DECIMAL(11, 2) NOT NULL DEFAULT '0.00'
  COMMENT '金额',
  `ewb_no`         VARCHAR(30)             DEFAULT NULL
  COMMENT '运单号',
  `remark`         VARCHAR(100)            DEFAULT NULL
  COMMENT '备注',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT
  COMMENT ='收据明细表';

DROP TABLE IF EXISTS `base_sequence`;
CREATE TABLE `base_sequence` (
  `name` varchar(50) NOT NULL,
  `current_value` bigint(20) unsigned NOT NULL DEFAULT '0',
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop procedure if exists nextval; 
CREATE FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS bigint(20)
BEGIN
     DECLARE value BIGINT;
     UPDATE base_sequence
     SET current_value = current_value + increment 
     WHERE upper(name) = upper(seq_name);
     RETURN currval(seq_name); 
END;

drop procedure if exists currval; 
CREATE  FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS bigint(20)
BEGIN
     DECLARE value BIGINT;
     SELECT current_value INTO value
     FROM base_sequence
     WHERE upper(name) = upper(seq_name); -- 大小写不区分.
     RETURN value;
END;

drop procedure if exists getChargeItemParentList; 
CREATE FUNCTION `getChargeItemParentList`(rootId varchar(100)) RETURNS varchar(1000) CHARSET utf8
BEGIN   
DECLARE fid varchar(100) default '';   
DECLARE str varchar(1000) default rootId;   
  
WHILE rootId is not null  do   
    SET fid =(SELECT parent_id FROM bus_charge_item WHERE id = rootId);   
    IF fid is not null THEN   
        SET str = concat(str, ',', fid);   
        SET rootId = fid;   
    ELSE   
        SET rootId = fid;   
    END IF;   
END WHILE;   
return str;  
END;

drop procedure if exists getChargeItemChildList; 
CREATE FUNCTION `getChargeItemChildList`(rootId varchar(100)) RETURNS varchar(2000) CHARSET utf8
BEGIN   
DECLARE str varchar(2000);  
DECLARE cid varchar(100);   
SET str = '';   
SET cid = rootId;
SELECT group_concat(id) INTO cid FROM bus_charge_item where FIND_IN_SET(parent_id, cid) > 0;  
WHILE cid is not null DO  
	SET str = concat(str, ',', cid); 
	SELECT group_concat(id) INTO cid FROM bus_charge_item where FIND_IN_SET(parent_id, cid) > 0;
END WHILE;   
RETURN str;   
END;
